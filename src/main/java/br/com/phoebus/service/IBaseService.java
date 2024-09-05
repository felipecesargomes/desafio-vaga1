package br.com.phoebus.service;

import java.util.List;

//Interface de BaseService, cria um contrato que os Services terão que ter no minimo as seguintes operações no serviço
public interface IBaseService<DTO> {
    //public List<DTO> findAll() throws Exception;

    //public DTO findById(Long id) throws Exception;

    public DTO save (DTO dto) throws Exception;

    //public DTO update (Long id, DTO dto) throws Exception;

    //public boolean delete(Long id) throws Exception;
}