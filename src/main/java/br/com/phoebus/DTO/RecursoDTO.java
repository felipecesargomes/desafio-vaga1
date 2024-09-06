package br.com.phoebus.DTO;

import br.com.phoebus.entity.Recurso;

import java.util.List;

public class RecursoDTO extends BaseDTO {

    private String tipo;
    private int quantidade;
    private int pontos;
    private String centroComunitarioId;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getCentroComunitarioId() {
        return centroComunitarioId;
    }

    public void setCentroComunitarioId(String centroComunitarioId) {
        this.centroComunitarioId = centroComunitarioId;
    }
}