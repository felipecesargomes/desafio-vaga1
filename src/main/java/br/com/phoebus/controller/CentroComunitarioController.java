package br.com.phoebus.controller;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.service.CentroComunitarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centrocomunitario")
public class CentroComunitarioController extends BaseController<CentroComunitarioDTO> {

    private CentroComunitarioService centroComunitarioService;

    public CentroComunitarioController(CentroComunitarioService centroComunitarioService) {
        super(centroComunitarioService);
        this.centroComunitarioService = centroComunitarioService;
    }

}
