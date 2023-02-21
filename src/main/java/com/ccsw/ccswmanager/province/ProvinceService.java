package com.ccsw.ccswmanager.province;

import com.ccsw.ccswmanager.province.model.ProvinceDto;
import com.ccsw.ccswmanager.province.model.ProvinceEntity;

import java.util.List;

public interface ProvinceService {

    List<ProvinceDto> findProvinces();

    ProvinceEntity getById(Integer id);

}
