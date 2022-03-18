package com.capgemini.ccsw.ccswmanager.tperson;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.tperson.model.TPersonEntity;

public interface TPersonService {

    List<TPersonEntity> findAll();

    List<TPersonEntity> findFromFilters(String filter1, String filter2, String filter3);
}
