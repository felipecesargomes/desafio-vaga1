package br.com.phoebus;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.repository.CentroComunitarioRepository;
import br.com.phoebus.service.CentroComunitarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import static org.mockito.ArgumentMatchers.any;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CentroComunitarioServiceTest {
    @InjectMocks
    private CentroComunitarioService centroComunitarioService;

    @Mock
    private CentroComunitarioRepository centroComunitarioRepository;

    @Mock
    private ModelMapper modelMapper;

    private CentroComunitarioDTO centroComunitarioDTO;
    private CentroComunitario centroComunitario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando um objeto de exemplo
        centroComunitarioDTO = new CentroComunitarioDTO();
        centroComunitarioDTO.setNome("Centro Comunitário T");
        centroComunitarioDTO.setEndereco("Rua Exemplo, 123");
        centroComunitarioDTO.setLocalizacao("São Paulo");
        centroComunitarioDTO.setCapacidadeMaxima(100);
        centroComunitarioDTO.setOcupacaoAtual(50);

        // Inicializando a entidade
        centroComunitario = new CentroComunitario();
        centroComunitario.setId("123");
        centroComunitario.setNome("Centro Comunitário B");
        centroComunitario.setEndereco("Rua Exemplo, 123");
        centroComunitario.setLocalizacao("São Paulo");
        centroComunitario.setCapacidadeMaxima(100);
        centroComunitario.setOcupacaoAtual(50);
    }

    @Test
    public void testSaveCentroComunitario() throws Exception {
        // Mockando a conversão de DTO para entidade
        when(modelMapper.map(any(CentroComunitarioDTO.class), any(Class.class))).thenReturn(centroComunitario);

        // Mockando a chamada do repository
        when(centroComunitarioRepository.save(any(CentroComunitario.class))).thenReturn(centroComunitario);

        // Mockando a conversão de entidade para DTO
        when(modelMapper.map(any(CentroComunitario.class), any(Class.class))).thenReturn(centroComunitarioDTO);

        // Chamando o método do serviço
        CentroComunitarioDTO result = centroComunitarioService.save(centroComunitarioDTO);

        // Verificando o resultado
        assertEquals(centroComunitarioDTO.getNome(), result.getNome());
        assertEquals(centroComunitarioDTO.getEndereco(), result.getEndereco());
    }
}
