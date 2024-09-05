package br.com.phoebus.repository;

import br.com.phoebus.entity.CentroComunitario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroComunitarioRepository extends MongoRepository<CentroComunitario, String> {

}
