package com.capgemini.ccsw.ccswmanager.center;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.center.model.CenterEntity;

/**
 * @author aolmosca
 *
 */
@Service
public class CenterServiceImpl implements CenterService {

   @Autowired
   CenterRepository centerRepository;

   private static final Logger LOG = LoggerFactory.getLogger(CenterServiceImpl.class);

   @Override
   public List<CenterEntity> findAll() {

      Iterable<CenterEntity> centersEntities = this.centerRepository.findAll();
      List<CenterEntity> centerEntityList = new ArrayList<CenterEntity>();
      centersEntities.forEach(centerEntityList::add);

      return centerEntityList;
   }

   @Override
   public CenterEntity getById(Integer id) {

      return this.centerRepository.findById(id);
   }

}
