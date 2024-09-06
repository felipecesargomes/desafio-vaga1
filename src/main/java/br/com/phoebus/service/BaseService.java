package br.com.phoebus.service;

import br.com.phoebus.DTO.BaseDTO;
import br.com.phoebus.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class BaseService<ENTITY extends BaseEntity, DTO extends BaseDTO> implements IBaseService<DTO> {

    private MongoRepository<ENTITY, String> repository;
    private Class<DTO> dtoClass;
    private Class<ENTITY> entityClass;

    public BaseService(MongoRepository<ENTITY, String> repository, Class<DTO> dtoClass, Class<ENTITY> entityClass) {
        this.repository = repository;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public List<DTO> findAll() throws Exception {
        List<DTO> dtos = new ArrayList<>();

        try {
            ModelMapper modelMapper = new ModelMapper();
            List<ENTITY> entities = repository.findAll();
            for (ENTITY item : entities) {
                DTO dto = (DTO) modelMapper.map(item, dtoClass);
                dtos.add(dto);
            }
        } catch (Exception e) {
            throw new Exception("Erro na listagem.", e);
        }
        return dtos;
    }

    public DTO findById(String id) throws Exception {
        Optional<ENTITY> entityOptional = repository.findById(id);
        try {
            ENTITY entity = entityOptional.get();
            ModelMapper modelMapper = new ModelMapper();
            return (DTO) modelMapper.map(entity, dtoClass);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public DTO save(DTO dto) throws Exception {
        ENTITY entity;
        ModelMapper modelMapper = new ModelMapper();
              try {
            entity = (ENTITY) modelMapper.map(dto, entityClass);
            entity = (ENTITY) repository.save(entity);
            return (DTO) modelMapper.map(entity, dtoClass);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean delete(String id) throws Exception {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                return true;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }


}