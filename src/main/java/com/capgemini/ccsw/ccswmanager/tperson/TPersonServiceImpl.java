package com.capgemini.ccsw.ccswmanager.tperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

@Service
public class TPersonServiceImpl implements TPersonService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    TPersonRepository tpersonRepository;

    @Override
    public List<TPersonEntity> findAll() {
        return this.tpersonRepository.findAll();
    }

    @Override
    public List<TPersonEntity> findFromFilters(String filter) {

        return this.tpersonRepository.findFirst15ByOrderByUsernameAsc(filter, PageRequest.of(0, 15));
    }

}
