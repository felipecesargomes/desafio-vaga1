package br.com.phoebus.DTO;

import java.io.Serializable;

//DTO Padrão, para reaproveitamento em outros objetos DTO. Semelhante ao Padrão DAO.
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;

}
