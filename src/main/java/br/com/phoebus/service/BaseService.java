package br.com.phoebus.service;

import br.com.phoebus.DTO.BaseDTO;
import br.com.phoebus.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.repository.MongoRepository;


public abstract class BaseService<ENTITY extends BaseEntity, DTO extends BaseDTO> implements IBaseService<DTO> {

    private MongoRepository repository;
    private Class dtoClass;
    private Class entityClass;

    public BaseService(MongoRepository repository, Class dtoClass, Class entityClass) {
        this.repository = repository;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public DTO save(DTO dto) throws Exception {

        ENTITY entity;
        ModelMapper modelMapper = new ModelMapper();
        // modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

        try {
            entity = (ENTITY) modelMapper.map(dto, entityClass);
            entity = (ENTITY) repository.save(entity);
            return (DTO) modelMapper.map(entity, dtoClass);
        } catch (Exception e) {
            throw e; // Relança a exceção original
        }

    }

}
