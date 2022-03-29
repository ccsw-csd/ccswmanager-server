package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCostEntity;

/**
 * @author jchengli
 *
 */

@Service
public class PyramidServiceImpl implements PyramidService {
    static final String ROWNAME = "rowName";
    static final Double INDEX_ROWNAME = 0.0;
    static final Double COST_ROWNAME = 1.0;
    static final String COLUMN_B2 = "B2";
    static final Double VALUE_B2 = 100.00;

    @Autowired
    PyramidRepository pyramidRepository;

    @Override
    public List<Map<String, Double>> getPyramidIndexCost() {

        List<PyramidCostEntity> pyramidCostEntityList = this.pyramidRepository.findAll();
        Map<String, Double> gradeIndexMap = new HashMap<>();
        Map<String, Double> gradeCostMap = new HashMap<>();
        List<Map<String, Double>> gradeIndexCostMapList = new ArrayList<>();

        gradeIndexMap.put(ROWNAME, INDEX_ROWNAME);
        gradeCostMap.put(ROWNAME, COST_ROWNAME);

        Optional<PyramidCostEntity> pyramidCostB2Entity = pyramidCostEntityList.stream()
                .filter(cost -> COLUMN_B2.equals(cost.getGrade())).findFirst();
        Double costValueB2 = pyramidCostB2Entity.get().getCost();

        for (PyramidCostEntity pyramidEntity : pyramidCostEntityList) {

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
    public List<Map<String, Double>> saveOrUpdatePyramidCosts(List<Map<String, Double>> gradeIndexCostMapList) {

        List<PyramidCostEntity> pyramidCostEntityList = this.pyramidRepository.findAll();

        Optional<Map<String, Double>> gradeIndexCostMap = gradeIndexCostMapList.stream()
                .filter(cost -> COST_ROWNAME.equals(cost.get(ROWNAME))).findAny();

        for (PyramidCostEntity pyramidCostEntity : pyramidCostEntityList) {
            pyramidCostEntity.setCost(gradeIndexCostMap.get().get(pyramidCostEntity.getGrade()));
        }

        this.pyramidRepository.saveAll(pyramidCostEntityList);

        return

        getPyramidIndexCost();
    }
}
