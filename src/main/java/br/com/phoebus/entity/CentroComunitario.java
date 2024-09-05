package br.com.phoebus.entity;

import jakarta.persistence.Entity;

@Entity
public class CentroComunitario extends BaseEntity {

    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int ocupacaoAtual;
   // private List<Recurso> recursos;

}
