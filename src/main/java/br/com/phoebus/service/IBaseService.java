package br.com.phoebus.service;

import br.com.phoebus.DTO.BaseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<ENTITY extends Base, DTO extends BaseDTO> implements IBaseService<DTO> {

    private JpaRepository repository;
    private Class dtoClass;
    private Class entityClass;

    public BaseService(JpaRepository repository, Class dtoClass, Class entityClass) {
        this.repository = repository;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll() throws Exception {
        List<DTO> dtos = new ArrayList<>();

        try {
            ModelMapper modelMapper = new ModelMapper();
            // modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
            List<ENTITY> entities = repository.findAll();

            for (ENTITY item : entities) {
                DTO dto = (DTO) modelMapper.map(item, dtoClass);
                dtos.add(dto);
            }

        } catch (Exception e) {
            throw new Exception("Error fetching all entities", e);
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public DTO findById(Long id) throws Exception {

        Optional<ENTITY> entityOptional = repository.findById(id);

        try {

            ENTITY entity = entityOptional.get();
            ModelMapper modelMapper = new ModelMapper();
            //modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
            return (DTO) modelMapper.map(entity, dtoClass);

        } catch (Exception e) {

            throw new Exception();

        }

    }

    @Transactional
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

    @Transactional
    public DTO update(Long id, DTO dto) throws Exception {

        Optional<ENTITY> entityOptional = repository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

        try {

            ENTITY entity = entityOptional.get();
            ENTITY entityParams = (ENTITY) modelMapper.map(dto, entityClass);

            try {

                if (repository.existsById(id)) {

                    entityParams.setId(id);
                    entity = (ENTITY) repository.save(entityParams);
                    return (DTO) modelMapper.map(entity, dtoClass);

                } else {

                    throw new Exception();

                }

            } catch (Exception e) {

                throw new Exception();

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    @Transactional
    public boolean delete(Long id) throws Exception {

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
