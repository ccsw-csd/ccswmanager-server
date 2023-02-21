package com.ccsw.ccswmanager.province;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.province.model.ProvinceDto;
import com.ccsw.ccswmanager.province.model.ProvinceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public List<ProvinceDto> findProvinces() {

        return this.beanMapper.mapList(this.provinceRepository.findByOrderByProvinceAsc(), ProvinceDto.class);
    }

    @Override
    public ProvinceEntity getById(Integer id) {

        return this.provinceRepository.findById(id);
    }

}
