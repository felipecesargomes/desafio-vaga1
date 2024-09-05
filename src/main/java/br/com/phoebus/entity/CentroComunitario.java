package br.com.phoebus.entity;

import jakarta.persistence.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "centros_comunitarios")
public class CentroComunitario extends BaseEntity {

    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int ocupacaoAtual;
   // private List<Recurso> recursos;

}
