package com.ccsw.ccswmanager.province;

import java.util.List;

import com.ccsw.ccswmanager.province.model.ProvinceDto;
import com.ccsw.ccswmanager.province.model.ProvinceEntity;

public interface ProvinceService {

    /**
     * @return
     */
    List<ProvinceDto> findProvinces();

    ProvinceEntity getById(Integer id);

}
