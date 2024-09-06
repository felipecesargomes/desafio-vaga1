package br.com.phoebus.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

//Entity Padrão, para reaproveitamento em outros objetos Entity. Semelhante ao Padrão DAO.
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;


}