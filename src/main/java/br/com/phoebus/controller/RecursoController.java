package br.com.phoebus.controller;

import br.com.phoebus.DTO.RecursoDTO;
import br.com.phoebus.service.RecursoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recurso")
public class RecursoController extends BaseController<RecursoDTO> {

    private RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        super(recursoService);
        this.recursoService = recursoService;
    }

}