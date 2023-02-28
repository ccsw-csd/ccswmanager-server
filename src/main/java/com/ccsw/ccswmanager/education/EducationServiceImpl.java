package com.ccsw.ccswmanager.education;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.education.model.EducationDto;
import com.ccsw.ccswmanager.education.model.EducationEntity;
import com.ccsw.ccswmanager.intern.InternService;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationRepository repository;

    @Autowired
    private InternService internService;

    @Autowired
    BeanMapper beanMapper;

    @Override
    public List<EducationEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public EducationEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public EducationEntity save(EducationDto educationDto) throws AlreadyExistsException {

        EducationEntity existsEducation = this.repository.getByName(educationDto.getName());

        if (existsEducation != null && (educationDto.getId() == null || !existsEducation.getId().equals(educationDto.getId()))) {
            throw new AlreadyExistsException("El nombre ya existe en la BBDD");
        }

        EducationEntity educationEntity = this.beanMapper.map(educationDto, EducationEntity.class);
        return repository.save(educationEntity);

    }

    @Override
    public void deleteById(Long id) throws ConflictOnDeletionException {

        if (this.internService.existsByEducationId(id)) {
            throw new ConflictOnDeletionException("No se puede borrar la titulación porque está relacionada con un becario ");
        }
        repository.deleteById(id);

    }

}
