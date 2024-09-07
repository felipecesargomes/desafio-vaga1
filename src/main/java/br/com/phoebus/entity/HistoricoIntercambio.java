package br.com.phoebus.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "historico_intercambio")
public class HistoricoIntercambio extends BaseEntity {

    private String centroOrigemId;
    private String centroDestinoId;
    private List<Recurso> recursosOrigem;
    private List<Recurso> recursosDestino;
    private LocalDateTime dataIntercambio;

    public HistoricoIntercambio(String centroOrigemId, String centroDestinoId, List<Recurso> recursosOrigem, List<Recurso> recursosDestino) {
        this.centroOrigemId = centroOrigemId;
        this.centroDestinoId = centroDestinoId;
        this.recursosOrigem = recursosOrigem;
        this.recursosDestino = recursosDestino;
        this.dataIntercambio = LocalDateTime.now();
    }

    public String getCentroOrigemId() {
        return centroOrigemId;
    }

    public void setCentroOrigemId(String centroOrigemId) {
        this.centroOrigemId = centroOrigemId;
    }

    public String getCentroDestinoId() {
        return centroDestinoId;
    }

    public void setCentroDestinoId(String centroDestinoId) {
        this.centroDestinoId = centroDestinoId;
    }

    public List<Recurso> getRecursosOrigem() {
        return recursosOrigem;
    }

    public void setRecursosOrigem(List<Recurso> recursosOrigem) {
        this.recursosOrigem = recursosOrigem;
    }

    public List<Recurso> getRecursosDestino() {
        return recursosDestino;
    }

    public void setRecursosDestino(List<Recurso> recursosDestino) {
        this.recursosDestino = recursosDestino;
    }

    public LocalDateTime getDataIntercambio() {
        return dataIntercambio;
    }

    public void setDataIntercambio(LocalDateTime dataIntercambio) {
        this.dataIntercambio = dataIntercambio;
    }
}
