package br.com.phoebus.repository;

import br.com.phoebus.entity.HistoricoIntercambio;
import br.com.phoebus.entity.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoIntercambioRepository extends MongoRepository<HistoricoIntercambio, String> {

}
