package com.capgemini.ccsw.ccswmanager.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.province.model.ProvinceDto;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public List<ProvinceDto> findProvinces() {
        return this.beanMapper.mapList(this.provinceRepository.findAll(), ProvinceDto.class);
    }

}
