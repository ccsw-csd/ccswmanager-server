package com.ccsw.ccswmanager.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.technology.model.TechnologyDto;
import com.ccsw.ccswmanager.technology.model.TechnologyEntity;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyRepository repository;

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private InternService internService;

    @Override
    public List<TechnologyEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public TechnologyEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public TechnologyEntity save(TechnologyDto technologyDto) throws AlreadyExistsException {

        TechnologyEntity existsTechnology = this.repository.getByName(technologyDto.getName());

        if (existsTechnology != null && (technologyDto.getId() == null || !existsTechnology.getId().equals(technologyDto.getId()))) {
            throw new AlreadyExistsException("El nombre ya existe en la BBDD");
        }

        TechnologyEntity technologyEntity = this.beanMapper.map(technologyDto, TechnologyEntity.class);
        return repository.save(technologyEntity);

    }

    @Override
    public void deleteById(Long id) throws ConflictOnDeletionException {

        TechnologyEntity technology = this.repository.findById(id).orElse(null);

        List<InternEntity> interns = internService.findByTechnologiesContaining(technology);
        if (!interns.isEmpty()) {
            throw new ConflictOnDeletionException("No se puede borrar la tecnología porque está relacionada con un becario ");
        }

        repository.deleteById(id);
    }

}
