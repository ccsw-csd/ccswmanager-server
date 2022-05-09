package com.ccsw.ccswmanager.center;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.center.model.CenterEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    CenterRepository centerRepository;

    @Override
    public List<CenterEntity> findAll() {

        return (List<CenterEntity>) centerRepository.findAll();
    }

    @Override
    public CenterEntity getById(Integer id) {

        return this.centerRepository.findById(id);
    }

}
