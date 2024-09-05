package br.com.phoebus.service;

import br.com.phoebus.DTO.CentroComunitarioDTO;
import br.com.phoebus.entity.CentroComunitario;
import br.com.phoebus.repository.CentroComunitarioRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class CentroComunitarioService extends BaseService<CentroComunitario, CentroComunitarioDTO> {

    private CentroComunitarioRepository centroComunitarioRepository;

    public CentroComunitarioService(CentroComunitarioRepository centroComunitarioRepository) {
        super(centroComunitarioRepository, CentroComunitarioDTO.class, CentroComunitario.class);
    }
}
