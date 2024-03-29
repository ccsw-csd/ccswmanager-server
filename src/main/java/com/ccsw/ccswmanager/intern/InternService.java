package com.ccsw.ccswmanager.intern;

import java.util.List;
import java.util.Set;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.intern.model.InternDto;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.intern.model.TimeLineDto;
import com.ccsw.ccswmanager.intern.model.TimeLineSearchDto;

public interface InternService {

    List<InternEntity> findAll();

    List<InternEntity> findNotEmptyActives();

    InternEntity getById(Long id);

    InternEntity save(InternEntity entity) throws AlreadyExistsException;

    void savePredict(InternDto dto, Long quantity);

    List<InternEntity> saveAll(List<InternEntity> entities);

    void deleteById(Long id);

    void deleteAll(List<InternEntity> entities);

    Set<String> getAllUsernames();

    List<TimeLineDto> findTimelineByDate(TimeLineSearchDto date);

    boolean existsByEducationId(Long educationId);

    boolean existsByTechnologiesId(Long technologyId);

    boolean existsByEducationCenterId(Long educationCenterId);

}
