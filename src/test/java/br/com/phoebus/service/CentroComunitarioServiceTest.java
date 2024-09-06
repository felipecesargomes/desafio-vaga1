package br.com.phoebus.service;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.repository.CentroComunitarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Collectors;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CentroComunitarioServiceTest {

    @Mock
    private CentroComunitarioRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CentroComunitarioService centroComunitarioService;

    private CentroComunitario entity = new CentroComunitario();
    private CentroComunitarioDTO dto = new CentroComunitarioDTO();

    @BeforeEach
    void setUp() {
        // Inicializando o ModelMapper
        modelMapper = new ModelMapper();

        centroComunitarioService = new CentroComunitarioService(repository);

        // Configurar o DTO que deve ser retornado pelo mapeamento

        // Configurar a entidade com valores
        entity = new CentroComunitario();
        entity.setId("123");
        entity.setNome("Centro Comunitário 1");
    }

    @Test
    void findAll() throws Exception {

        List<CentroComunitario> entities = List.of(entity);
        when(repository.findAll()).thenReturn(entities);

        // Fazer o mapeamento da entidade para o DTO dentro do teste
        List<CentroComunitarioDTO> dtos = entities.stream()
                .map(entity -> modelMapper.map(entity, CentroComunitarioDTO.class))
                .collect(Collectors.toList());

        List<CentroComunitarioDTO> resultDtos = centroComunitarioService.findAll();

        // Verificar o tamanho da lista e os valores mapeados
        assertEquals(1, resultDtos.size());
        assertEquals(entity.getId(), resultDtos.get(0).getId());
        assertEquals(entity.getNome(), resultDtos.get(0).getNome());
    }

    @Test
    void findById() throws Exception {

        CentroComunitarioDTO dtos = new CentroComunitarioDTO();
        when(centroComunitarioService.findById(entity.getId())).thenReturn(dtos);

        dto = centroComunitarioService.findById(entity.getId());

        assertEquals(dto.getId(),"123");

    }


}