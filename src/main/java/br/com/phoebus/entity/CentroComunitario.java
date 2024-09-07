package br.com.phoebus.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "centro_comunitario")
public class CentroComunitario extends BaseEntity {

    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int ocupacaoAtual;
    private List<Recurso> recursos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getOcupacaoAtual() {
        return ocupacaoAtual;
    }

    public void setOcupacaoAtual(int ocupacaoAtual) {
        this.ocupacaoAtual = ocupacaoAtual;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public void adicionarRecursos(List<Recurso> novosRecursos) {
        this.recursos.addAll(novosRecursos);
    }

    public void removerRecursos(List<Recurso> recursosParaRemover) {
        this.recursos.removeAll(recursosParaRemover);
    }

}
