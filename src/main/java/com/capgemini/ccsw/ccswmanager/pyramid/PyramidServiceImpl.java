package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCostEntity;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidIndexCostDto;

/**
 * @author jchengli
 *
 */

@Service
public class PyramidServiceImpl implements PyramidService {
    static final String costRowName = "COST";
    static final String COLUMN_A1 = "A1";
    static final String COLUMN_A2 = "A2";
    static final String COLUMN_B1 = "B1";
    static final String COLUMN_B2 = "B2";
    static final String COLUMN_B3 = "B3";
    static final String COLUMN_C1 = "C1";
    static final String COLUMN_C2 = "C2";
    static final String COLUMN_C3 = "C3";
    static final String COLUMN_D1 = "D1";
    static final String COLUMN_D2 = "D2";
    static final Double VALUE_B2 = 100.00;

    @Autowired
    PyramidRepository pyramidRepository;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public List<PyramidIndexCostDto> returnPyramidIndexCost() {

        List<PyramidCostEntity> pyramidCostEntity = this.pyramidRepository.findAll();
        List<PyramidIndexCostDto> pyramidIndexCostDto = new ArrayList<PyramidIndexCostDto>();
        PyramidIndexCostDto index = new PyramidIndexCostDto();
        PyramidIndexCostDto cost = new PyramidIndexCostDto();
        index.setRowName("INDEX");
        cost.setRowName(costRowName);

        cost.setValueA1(pyramidCostEntity.get(0).getCost());
        cost.setValueA2(pyramidCostEntity.get(1).getCost());
        cost.setValueB1(pyramidCostEntity.get(2).getCost());
        cost.setValueB2(pyramidCostEntity.get(3).getCost());
        cost.setValueB3(pyramidCostEntity.get(4).getCost());
        cost.setValueC1(pyramidCostEntity.get(5).getCost());
        cost.setValueC2(pyramidCostEntity.get(6).getCost());
        cost.setValueC3(pyramidCostEntity.get(7).getCost());
        cost.setValueD1(pyramidCostEntity.get(8).getCost());
        cost.setValueD2(pyramidCostEntity.get(9).getCost());

        Double costValueB2 = cost.getValueB2();

        index.setValueA1((cost.getValueA1() * 100) / costValueB2);
        index.setValueA2((cost.getValueA2() * 100) / costValueB2);
        index.setValueB1((cost.getValueB1() * 100) / costValueB2);
        index.setValueB2(VALUE_B2);
        index.setValueB3((cost.getValueB3() * 100) / costValueB2);
        index.setValueC1((cost.getValueC1() * 100) / costValueB2);
        index.setValueC2((cost.getValueC2() * 100) / costValueB2);
        index.setValueC3((cost.getValueC3() * 100) / costValueB2);
        index.setValueD1((cost.getValueD1() * 100) / costValueB2);
        index.setValueD2((cost.getValueD2() * 100) / costValueB2);

        pyramidIndexCostDto.add(index);
        pyramidIndexCostDto.add(cost);

        return this.beanMapper.mapList(pyramidIndexCostDto, PyramidIndexCostDto.class);
    }

    @Override
    public List<PyramidIndexCostDto> saveOrUpdatePyramidCosts(List<PyramidIndexCostDto> dtoList) {

        List<PyramidCostEntity> pyramidCostEntity = this.pyramidRepository.findAll();

        dtoList.forEach(dto -> {
            Objects.requireNonNull(dto, "pyramid");

            if (costRowName.equals(dto.getRowName())) {

                pyramidCostEntity.get(0).setCost(dto.getValueA1());
                pyramidCostEntity.get(1).setCost(dto.getValueA2());
                pyramidCostEntity.get(2).setCost(dto.getValueB1());
                pyramidCostEntity.get(3).setCost(dto.getValueB2());
                pyramidCostEntity.get(4).setCost(dto.getValueB3());
                pyramidCostEntity.get(5).setCost(dto.getValueC1());
                pyramidCostEntity.get(6).setCost(dto.getValueC2());
                pyramidCostEntity.get(7).setCost(dto.getValueC3());
                pyramidCostEntity.get(8).setCost(dto.getValueD1());
                pyramidCostEntity.get(9).setCost(dto.getValueD2());

                this.pyramidRepository.saveAll(pyramidCostEntity);
            }
        });
        return returnPyramidIndexCost();
    }
}
