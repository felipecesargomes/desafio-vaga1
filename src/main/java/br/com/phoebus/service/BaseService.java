package br.com.phoebus.service;

import br.com.phoebus.DTO.BaseDTO;
import br.com.phoebus.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.MongoRepository;


public abstract class BaseService<ENTITY extends BaseEntity, DTO extends BaseDTO> implements IBaseService<DTO> {

    private MongoRepository<ENTITY, String> repository;
    private Class<DTO> dtoClass;
    private Class<ENTITY> entityClass;

    public BaseService(MongoRepository<ENTITY, String> repository, Class<DTO> dtoClass, Class<ENTITY> entityClass) {
        this.repository = repository;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
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

}
