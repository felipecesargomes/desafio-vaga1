package br.com.phoebus.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "intercambio")
public class Intercambio extends BaseEntity {
    private String centroOrigemId;
    private String centroDestinoId;
    private List<Recurso> recursosTrocados;
    private LocalDateTime dataHora;

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

    public List<Recurso> getRecursosTrocados() {
        return recursosTrocados;
    }

    public void setRecursosTrocados(List<Recurso> recursosTrocados) {
        this.recursosTrocados = recursosTrocados;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
