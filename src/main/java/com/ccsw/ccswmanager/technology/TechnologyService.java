package com.ccsw.ccswmanager.technology;

import java.util.List;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.common.exception.ConflictOnDeletionException;
import com.ccsw.ccswmanager.technology.model.TechnologyDto;
import com.ccsw.ccswmanager.technology.model.TechnologyEntity;

public interface TechnologyService {

    List<TechnologyEntity> findAll();

    TechnologyEntity getById(Long id);

    TechnologyEntity save(TechnologyDto technologyDto) throws AlreadyExistsException;

    void deleteById(Long id) throws ConflictOnDeletionException;;

}
