package br.com.phoebus.repository;

import br.com.phoebus.entity.Intercambio;
import br.com.phoebus.entity.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntercambioRepository extends MongoRepository<Intercambio, String> {

}
