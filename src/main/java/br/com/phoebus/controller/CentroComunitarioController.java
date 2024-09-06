package br.com.phoebus.controller;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.service.CentroComunitarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PatchMapping("/{id}/ocupacao")
    public ResponseEntity atualizarOcupacao(@PathVariable String id, @RequestBody int novaOcupacao) {
        try {
            CentroComunitarioDTO dto = centroComunitarioService.atualizarOcupacao(id, novaOcupacao);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Erro ao atualizar a ocupação.\"}");
        }
    }

}
