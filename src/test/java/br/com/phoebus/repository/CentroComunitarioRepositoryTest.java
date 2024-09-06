package br.com.phoebus.repository;

import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.util.MongoConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@Import(MongoConfig.class)
//@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CentroComunitarioRepositoryTest {

    @Mock
    private CentroComunitarioRepository repository;

    @Test
    void testFindAll() {
        // ARRANGE
        CentroComunitario centro1 = new CentroComunitario();
        centro1.setNome("Centro A");
        CentroComunitario centro2 = new CentroComunitario();
        centro2.setNome("Centro B");
        List<CentroComunitario> centros = Arrays.asList(centro1, centro2);
        // Configurando o mock para retornar os objetos da lista quando findAll for chamado
        when(repository.findAll()).thenReturn(centros);

        // ACT
        List<CentroComunitario> allCentros = repository.findAll();

        // ASSERT
        // Verifica se a lista retornada não é nula
        assertNotNull(allCentros);
        // Verifica se o tamanho da lista é igual a 2
        assertEquals(2, allCentros.size());
        assertEquals("Centro A", allCentros.get(0).getNome());
        assertEquals("Centro B", allCentros.get(1).getNome());
    }

    @Test
    void testFindById() {
        // ARRANGE
        CentroComunitario centro = new CentroComunitario();
        centro.setNome("Centro Comunitário de João Pessoa");
        when(repository.findById("123")).thenReturn(java.util.Optional.of(centro));

        // ACT
        CentroComunitario foundCentro = repository.findById("123").orElse(null);

        // ASSERT
        assertNotNull(foundCentro);
        assertEquals("Centro Comunitário de João Pessoa", foundCentro.getNome());
    }

    @Test
    void testSaveCentroComunitario() {
        // ARRANGE
        CentroComunitario centro = new CentroComunitario();
        centro.setNome("Centro Comunitário de João Pessoa");
        when(repository.save(centro)).thenReturn(centro);

        // ACT
        // Invoca o metodo save
        CentroComunitario savedCentro = repository.save(centro);
        // Verifica se o metodo save no repositorio foi chamado uma vez
        verify(repository, times(1)).save(centro);

        // ASSERT
        // Verifica se o retorno está correto
        assertEquals(centro, savedCentro);
    }

    @Test
    void testDeleteById() {
        // ARRANGE
        String idToDelete = "123";

        // ACT
        repository.deleteById(idToDelete);

        // ASSERT
        verify(repository, times(1)).deleteById(idToDelete);
    }

    @Test
    void testUpdateCentroComunitario() {
        // ARRANGE
        CentroComunitario centro = new CentroComunitario();
        centro.setNome("Centro Atualizado");
        when(repository.save(centro)).thenReturn(centro);

        // ACT
        CentroComunitario updatedCentro = repository.save(centro);

        // ASSERT
        assertNotNull(updatedCentro);
        assertEquals("Centro Atualizado", updatedCentro.getNome());
    }
}