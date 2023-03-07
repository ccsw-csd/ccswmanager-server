package com.ccsw.ccswmanager.educationcenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.utils.ItemInUseException;

@Service
public class EducationCenterServiceImpl implements EducationCenterService {

    @Autowired
    EducationCenterRepository repository;

    @Autowired
    InternService internService;

    @Override
    public List<EducationCenterEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public EducationCenterEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public EducationCenterEntity save(EducationCenterEntity entity) throws ItemInUseException {

        if (this.repository.existsEducationCenterByName(entity.getName())) {
            throw new ItemInUseException();
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws ItemInUseException {

        if (this.internService.existsByEducationCenterId(id)) {
            throw new ItemInUseException();
        }
        repository.deleteById(id);
    }

}
