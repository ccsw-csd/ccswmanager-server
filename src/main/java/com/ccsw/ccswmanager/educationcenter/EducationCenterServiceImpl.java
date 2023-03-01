package com.ccsw.ccswmanager.educationcenter;

import com.ccsw.ccswmanager.educationcenter.model.EducationCenterEntity;
import com.ccsw.ccswmanager.intern.InternService;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.utils.InternInUseException;
import com.ccsw.ccswmanager.utils.ItemInUseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public EducationCenterEntity save(EducationCenterEntity entity) {

        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) throws ItemInUseException{

        List<InternEntity> interns = this.internService.findAllByEducationCenterId(id);
        if(interns.isEmpty()) {
            repository.deleteById(id);   
        }else {
            throw new ItemInUseException();
        }
    }

}
