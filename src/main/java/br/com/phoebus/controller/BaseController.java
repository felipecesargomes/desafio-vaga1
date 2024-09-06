package br.com.phoebus.controller;

import br.com.phoebus.service.IBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<DTO> {

    private IBaseService<DTO> service;

    public BaseController(IBaseService<DTO> service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Error. Tente novamente a listagem.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Error. Verifique o ID.\"}");
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Error. Por favor, cheque o ID e tente novamente.\"}");
        }
    }

}