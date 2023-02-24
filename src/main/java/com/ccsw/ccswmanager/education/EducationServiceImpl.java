package com.ccsw.ccswmanager.education;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.education.model.EducationDto;
import com.ccsw.ccswmanager.education.model.EducationEntity;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.intern.model.InternEntity;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    EducationRepository repository;

    @Autowired
    private InternService internService;

    @Override
    public List<EducationEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public EducationEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public EducationEntity save(Long id, EducationDto educationDto) throws AlreadyExistsException {

        EducationEntity education = null;

        if (id == null)
            education = new EducationEntity();
        else
            education = repository.findById(id).orElse(null);

        EducationEntity educationDb = repository.findByName(educationDto.getName());
        if (educationDb != null) {
            throw new AlreadyExistsException("El nombre ya existe en la BBDD");
        }

        BeanUtils.copyProperties(educationDto, education, "id", "name");

        education.setName(educationDto.getName());

        return repository.save(education);
    }

    @Override
    public void deleteById(Long id) throws ConflictOnDeletionException {

        InternEntity internDb = this.internService.findByEducationId(id);

        if (internDb != null) {
            throw new ConflictOnDeletionException("No se puede borrar la titulación porque está relacionada con un becario ");
        } else {
            repository.deleteById(id);
        }

    }

}
