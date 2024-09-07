package br.com.phoebus.service;

import br.com.phoebus.DTO.IntercambioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.entity.Intercambio;
import br.com.phoebus.entity.Recurso;
import br.com.phoebus.repository.IntercambioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntercambioService extends BaseService<Intercambio, IntercambioDTO> {

    public IntercambioService(IntercambioRepository IntercambioRepository) {
        super(IntercambioRepository, IntercambioDTO.class, Intercambio.class);
    }

    public void realizarIntercambio(CentroComunitario origem, CentroComunitario destino, List<Recurso> recursosOrigem, List<Recurso> recursosDestino) {
        int pontosOrigem = calcularPontos(recursosOrigem);
        int pontosDestino = calcularPontos(recursosDestino);

        boolean intercambioValido = validarIntercambio(origem, destino, pontosOrigem, pontosDestino);
        if (!intercambioValido) {
            throw new IllegalArgumentException("Intercâmbio inválido");
        }

        // Atualizar os recursos dos centros
        origem.removerRecursos(recursosOrigem);
        origem.adicionarRecursos(recursosDestino);

        destino.removerRecursos(recursosDestino);
        destino.adicionarRecursos(recursosOrigem);

        centroRepository.save(origem);
        centroRepository.save(destino);

        registrarHistorico(origem, destino, recursosOrigem, recursosDestino);
    }

    private int calcularPontos(List<Recurso> recursos) {
        return recursos.stream().mapToInt(Recurso::getPontos).sum();
    }

    private boolean validarIntercambio(CentroComunitario origem, CentroComunitario destino, int pontosOrigem, int pontosDestino) {
        if (origem.getOcupacaoAtual() > 0.9 * origem.getCapacidadeMaxima()) {
            return pontosOrigem <= pontosDestino;
        }
        return pontosOrigem == pontosDestino;
    }

    private void registrarHistorico(CentroComunitario origem, CentroComunitario destino, List<Recurso> recursosOrigem, List<Recurso> recursosDestino) {
        // Lógica para registrar a negociação no histórico
    }

}