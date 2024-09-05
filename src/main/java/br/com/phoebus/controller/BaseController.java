package br.com.phoebus.controller;

import br.com.phoebus.service.IBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BaseController<DTO> {

    private IBaseService<DTO> service;

    public BaseController(IBaseService<DTO> service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity post(@RequestBody DTO dto) {
        try {
            DTO result = (DTO) service.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Erro ao tentar inserir os dados.\"}");

        }

    }

}