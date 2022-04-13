package com.capgemini.ccsw.ccswmanager.province;

import java.util.List;

import com.capgemini.ccsw.ccswmanager.province.model.ProvinceDto;

public interface ProvinceService {

    /**
     * @return
     */
    List<ProvinceDto> findProvinces();

}
