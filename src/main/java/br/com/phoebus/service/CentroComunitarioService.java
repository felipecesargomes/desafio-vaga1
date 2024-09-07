package br.com.phoebus.service;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.repository.CentroComunitarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CentroComunitarioService extends BaseService<CentroComunitario, CentroComunitarioDTO> {

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

    private void notificarCapacidadeMaxima(CentroComunitario centro) {
        // Implementar lógica de notificação para outro micro serviço
    }
    
}