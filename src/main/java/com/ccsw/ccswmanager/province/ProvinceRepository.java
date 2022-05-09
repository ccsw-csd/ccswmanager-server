package com.ccsw.ccswmanager.province;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.province.model.ProvinceEntity;

/**
 * @author dapalmie
 *
 */
public interface ProvinceRepository extends CrudRepository<ProvinceEntity, Long> {

    List<ProvinceEntity> findByOrderByProvinceAsc();

    ProvinceEntity findById(Integer id);

}
