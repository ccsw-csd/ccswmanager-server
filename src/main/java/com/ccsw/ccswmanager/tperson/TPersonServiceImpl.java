package com.ccsw.ccswmanager.tperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.tperson.model.TPersonEntity;

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
    public List<TPersonEntity> findAllTpersonsFromFilters(String filter) {

        return this.tpersonRepository.findTpersonsLikeFilter(filter, PageRequest.of(0, 15));
    }

    @Override
    public List<TPersonEntity> matchedTPersonWithPersonUsernameAndSaga(List<String> username, List<String> sagaCode) {
        return tpersonRepository.findByUsernameInOrSagaIn(username, sagaCode);
    }
}
