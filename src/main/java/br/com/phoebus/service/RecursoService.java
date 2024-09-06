package br.com.phoebus.service;

import br.com.phoebus.DTO.RecursoDTO;
import br.com.phoebus.entity.Recurso;
import br.com.phoebus.repository.RecursoRepository;
import org.springframework.stereotype.Service;

@Service
public class RecursoService extends BaseService<Recurso, RecursoDTO> {

    public RecursoService(RecursoRepository recursoRepository) {
        super(recursoRepository, RecursoDTO.class, Recurso.class);
    }
}