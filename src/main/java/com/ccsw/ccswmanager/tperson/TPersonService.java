package com.ccsw.ccswmanager.tperson;

import java.util.List;

import com.ccsw.ccswmanager.tperson.model.TPersonEntity;

public interface TPersonService {

    List<TPersonEntity> findAll();

    List<TPersonEntity> findAllTpersonsFromFilters(String filter);

    List<TPersonEntity> matchedTPersonWithPersonUsernameAndSaga(List<String> username, List<String> sagaCode);

}
