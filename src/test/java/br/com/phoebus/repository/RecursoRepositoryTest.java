package br.com.phoebus.repository;

import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.entity.Recurso;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//@Import(MongoConfig.class)
//@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RecursoRepositoryTest {

    @Mock
    private RecursoRepository repository;

    @Test
    void testFindAll() {
        // ARRANGE
        Recurso resource1 = new Recurso();
        resource1.setTipo("Medico 1");
        Recurso resource2 = new Recurso();
        resource2.setTipo("Medico 2");
        List<Recurso> recursos = Arrays.asList(resource1, resource2);
        // Configurando o mock para retornar os objetos da lista quando findAll for chamado
        when(repository.findAll()).thenReturn(recursos);

        // ACT
        List<Recurso> allRecursos = repository.findAll();

        // ASSERT
        // Verifica se a lista retornada não é nula
        assertNotNull(allRecursos);
        // Verifica se o tamanho da lista é igual a 2
        assertEquals(2, allRecursos.size());
        assertEquals("Medico 1", allRecursos.get(0).getTipo());
        assertEquals("Medico 2", allRecursos.get(1).getTipo());
    }

    @Test
    void testFindById() {
        // ARRANGE
        Recurso resource1 = new Recurso();
        resource1.setTipo("Medico 1");
        resource1.setId("32");
        when(repository.findById("32")).thenReturn(java.util.Optional.of(resource1));

        // ACT
        Recurso foundRecurso = repository.findById("32").orElse(null);

        // ASSERT
        assertNotNull(foundRecurso);
        assertEquals("Medico 1", foundRecurso.getTipo());
        assertEquals("32", foundRecurso.getId());
    }

    @Test
    void testSaveRecurso() {
        // ARRANGE
        Recurso recurso = new Recurso();
        recurso.setTipo("Medico 1");
        when(repository.save(recurso)).thenReturn(recurso);

        // ACT
        // Invoca o metodo save
        Recurso saved = repository.save(recurso);
        // Verifica se o metodo save no repositorio foi chamado uma vez
        verify(repository, times(1)).save(recurso);

        // ASSERT
        // Verifica se o retorno está correto
        assertEquals(recurso, saved);
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
    void testUpdateRecurso() {
        // ARRANGE
        Recurso recurso = new Recurso();
        recurso.setTipo("Medico 2");
        when(repository.save(recurso)).thenReturn(recurso);

        // ACT
        Recurso updatedRecurso = repository.save(recurso);

        // ASSERT
        assertNotNull(updatedRecurso);
        assertEquals("Medico 2", updatedRecurso.getTipo());
    }


}