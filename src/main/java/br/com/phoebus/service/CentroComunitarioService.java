package br.com.phoebus.service;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.entity.HistoricoIntercambio;
import br.com.phoebus.entity.Recurso;
import br.com.phoebus.repository.CentroComunitarioRepository;
import br.com.phoebus.repository.HistoricoIntercambioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroComunitarioService extends BaseService<CentroComunitario, CentroComunitarioDTO> {

    @Autowired
    private HistoricoIntercambioRepository historicoRepository;

    private static final int LIMITE = 90;
    ModelMapper modelMapper = new ModelMapper();

    public CentroComunitarioService(CentroComunitarioRepository centroComunitarioRepository) {
        super(centroComunitarioRepository, CentroComunitarioDTO.class, CentroComunitario.class);
    }

    public CentroComunitarioDTO atualizarOcupacao(String id, int novaOcupacao) throws Exception {
        CentroComunitario centro = repository.findById(id)
                .orElseThrow(() -> new Exception("Centro comunitário não encontrado"));

        centro.setOcupacaoAtual(novaOcupacao);

        if (centro.getOcupacaoAtual() > centro.getCapacidadeMaxima()) {
            throw new Exception("A ocupação não pode ser maior que a capacidade máxima");
        }

        CentroComunitario atualizado = repository.save(centro);

        if (centro.getOcupacaoAtual() > LIMITE) {
            // Gerar notificação aqui. Em um sistema real, você enviaria uma mensagem para outro micro serviço.
            System.out.println("Notificação: Centro comunitário com alta ocupação!");
        }

        return modelMapper.map(atualizado, CentroComunitarioDTO.class);
    }

    public void realizarIntercambio(String centroOrigemId, String centroDestinoId, List<Recurso> recursosOrigem, List<Recurso> recursosDestino) throws Exception {
        CentroComunitario origem = repository.findById(centroOrigemId).orElseThrow(() -> new Exception("Centro origem não encontrado"));
        CentroComunitario destino = repository.findById(centroDestinoId).orElseThrow(() -> new Exception("Centro destino não encontrado"));

        if (validarIntercambio(origem, destino, recursosOrigem, recursosDestino)) {
            origem.removerRecursos(recursosOrigem);
            destino.adicionarRecursos(recursosOrigem);
            destino.removerRecursos(recursosDestino);
            origem.adicionarRecursos(recursosDestino);
            registrarHistorico(origem, destino, recursosOrigem, recursosDestino);

            repository.save(origem);
            repository.save(destino);
        } else {
            throw new Exception("Intercâmbio inválido");
        }
    }

    private boolean validarIntercambio(CentroComunitario origem, CentroComunitario destino, List<Recurso> recursosOrigem, List<Recurso> recursosDestino) {
        // Calcula a soma dos pontos dos recursos oferecidos pela origem
        int pontosOrigem = recursosOrigem.stream()
                .mapToInt(Recurso::getPontos)  // Supondo que Recurso tenha um método getPontos()
                .sum();

        // Calcula a soma dos pontos dos recursos oferecidos pelo destino
        int pontosDestino = recursosDestino.stream()
                .mapToInt(Recurso::getPontos)
                .sum();

        // Verifica se algum centro tem ocupação maior que 90%
        boolean origemComAltaOcupacao = origem.getOcupacaoAtual() > 0.9 * origem.getCapacidadeMaxima();
        boolean destinoComAltaOcupacao = destino.getOcupacaoAtual() > 0.9 * destino.getCapacidadeMaxima();

        // Se ambos os centros estão abaixo de 90% de ocupação, os pontos devem ser equivalentes
        if (!origemComAltaOcupacao && !destinoComAltaOcupacao) {
            return pontosOrigem == pontosDestino;
        }

        // Se pelo menos um dos centros está com ocupação acima de 90%, permitimos intercâmbio desigual
        return true;
    }

    private void registrarHistorico(CentroComunitario origem, CentroComunitario destino, List<Recurso> recursosOrigem, List<Recurso> recursosDestino) {
        HistoricoIntercambio historico = new HistoricoIntercambio(
                origem.getId(),
                destino.getId(),
                recursosOrigem,
                recursosDestino
        );

        // Salva o histórico da negociação
        historicoRepository.save(historico);
    }
    
}