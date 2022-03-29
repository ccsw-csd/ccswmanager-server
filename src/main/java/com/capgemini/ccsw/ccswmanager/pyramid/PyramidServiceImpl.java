package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCostEntity;

/**
 * @author jchengli
 *
 */

@Service
public class PyramidServiceImpl implements PyramidService {
    static final Double COST_ROWNAME = 1.0;
    static final String COLUMN_B2 = "B2";
    static final Double VALUE_B2 = 100.00;

    @Autowired
    PyramidRepository pyramidRepository;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public List<HashMap<String, Double>> getPyramidIndexCost() {

        List<PyramidCostEntity> pyramidCostEntity = this.pyramidRepository.findAll();
        HashMap<String, Double> gradeIndexMap = new HashMap<String, Double>();
        HashMap<String, Double> gradeCostMap = new HashMap<String, Double>();
        List<HashMap<String, Double>> gradeIndexCostMapList = new ArrayList<HashMap<String, Double>>();

        gradeIndexMap.put("rowName", 0.0);
        gradeCostMap.put("rowName", 1.0);
        Double costValueB2 = null;

        for (PyramidCostEntity pyramidEntity : pyramidCostEntity) {
            if (COLUMN_B2.equals(pyramidEntity.getGrade()))
                costValueB2 = pyramidEntity.getCost();
        }

        for (PyramidCostEntity pyramidEntity : pyramidCostEntity) {

            gradeCostMap.put(pyramidEntity.getGrade(), pyramidEntity.getCost());

            if (COLUMN_B2.equals(pyramidEntity.getGrade()))
                gradeIndexMap.put(pyramidEntity.getGrade(), VALUE_B2);
            else
                gradeIndexMap.put(pyramidEntity.getGrade(), (pyramidEntity.getCost() * 100) / costValueB2);

        }
        gradeIndexCostMapList.add(gradeIndexMap);
        gradeIndexCostMapList.add(gradeCostMap);

        return gradeIndexCostMapList;
    }

    @Override
    public List<HashMap<String, Double>> saveOrUpdatePyramidCosts(List<HashMap<String, Double>> dtoList) {

        List<PyramidCostEntity> pyramidCostEntity = this.pyramidRepository.findAll();

        dtoList.forEach(dto -> {
            Objects.requireNonNull(dto, "pyramid");

            if (COST_ROWNAME.equals(dto.get("rowName"))) {

                for (PyramidCostEntity pyramid : pyramidCostEntity) {
                    pyramid.setCost(dto.get(pyramid.getGrade()));
                }

                this.pyramidRepository.saveAll(pyramidCostEntity);
            }
        });
        return getPyramidIndexCost();
    }
}
