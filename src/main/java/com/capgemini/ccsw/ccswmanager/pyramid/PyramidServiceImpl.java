package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.PersonRepository;
import com.capgemini.ccsw.ccswmanager.person.model.PersonEntity;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCostEntity;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidCountDto;
import com.capgemini.ccsw.ccswmanager.pyramid.model.PyramidDto;

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
    static final String DEPARTMENT = "CCSw";
    static final String SCHOLAR = "";
    static final String TOTAL = "TOTAL";

    @Autowired
    PyramidRepository pyramidRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private BeanMapper beanMapper;

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

            if (COLUMN_B2.equals(pyramidEntity.getGrade())) {
                gradeIndexMap.put(pyramidEntity.getGrade(), VALUE_B2);
            } else {
                gradeIndexMap.put(pyramidEntity.getGrade(), (pyramidEntity.getCost() * 100) / costValueB2);
            }
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

        return getPyramidIndexCost();
    }

    @Override
    public List<PyramidDto> getPyramidsProfileCountIndex() {

        List<PersonEntity> personEntityList = this.personRepository.findByDepartment(DEPARTMENT);
        List<PyramidDto> pyramidDtoList = new ArrayList<>();
        long countTotal = 0;
        Double indexTotal = 0.0;

        Optional<Map<String, Double>> gradeIndexMap = this.getGradeIndexMap();

        Map<String, Long> countMap = personEntityList.stream()
                .filter(person -> person.getGrade().matches("[a-zA-z][1-9]+"))
                .collect(Collectors.groupingBy(person -> person.getGrade(), Collectors.counting()));

        countTotal = gradeIndexMap.get().entrySet().stream().filter(grade -> countMap.get(grade.getKey()) != null)
                .mapToLong(grade -> countMap.get(grade.getKey())).sum();

        for (Map.Entry<String, Double> entry : gradeIndexMap.get().entrySet()) {
            PyramidDto pyramidGraphDto = new PyramidDto();

            pyramidGraphDto.setProfile(entry.getKey());

            if (countMap.get(entry.getKey()) != null) {
                pyramidGraphDto.setCount(countMap.get(entry.getKey()));
            } else {
                pyramidGraphDto.setCount(0);
            }

            pyramidGraphDto.setIndex((pyramidGraphDto.getCount() / Double.valueOf(countTotal)) * entry.getValue());
            indexTotal += pyramidGraphDto.getIndex();

            pyramidDtoList.add(pyramidGraphDto);
        }

        Collections.sort(pyramidDtoList,
                (sortBottom, sortTop) -> sortTop.getProfile().compareTo(sortBottom.getProfile()));

        PyramidDto pyramidTotalGraphDto = new PyramidDto();
        pyramidTotalGraphDto.setProfile(TOTAL);
        pyramidTotalGraphDto.setCount(countTotal);
        pyramidTotalGraphDto.setIndex(indexTotal);
        pyramidDtoList.add(pyramidTotalGraphDto);

        return this.beanMapper.mapList(pyramidDtoList, PyramidDto.class);
    }

    public List<PyramidCountDto> getPyramidsProfileCount() {

        List<PyramidCountDto> pyramidCountDtoList = new ArrayList<>();
        List<PersonEntity> personEntityList = this.personRepository.findByDepartment(DEPARTMENT);
        long countTotal = 0;

        Optional<Map<String, Double>> gradeIndexMap = this.getGradeIndexMap();

        Map<String, Long> countMap = personEntityList.stream()
                .filter(person -> person.getGrade().matches("[a-zA-z][1-9]+")).collect(Collectors
                        .groupingBy(person -> String.valueOf(person.getGrade().charAt(0)), Collectors.counting()));

        long countScholars = personEntityList.stream().filter(person -> person.getGrade().equals(SCHOLAR))
                .collect(Collectors.counting());

        countTotal = personEntityList.stream()
                .filter(person -> person.getGrade().matches("[a-zA-z][1-9]+") || person.getGrade().equals(SCHOLAR))
                .collect(Collectors.counting());

        gradeIndexMap.get().entrySet().stream()
                .filter(grade -> countMap.get(String.valueOf(grade.getKey().charAt(0))) == null)
                .forEach(grade -> countMap.put(String.valueOf(grade.getKey().charAt(0)), (long) 0));

        for (Map.Entry<String, Long> entry : countMap.entrySet()) {
            PyramidCountDto pyramidCountDto = new PyramidCountDto();
            pyramidCountDto.setProfile(entry.getKey());
            pyramidCountDto.setCount(entry.getValue());
            pyramidCountDtoList.add(pyramidCountDto);
        }

        Collections.sort(pyramidCountDtoList,
                (sortBottom, sortTop) -> sortTop.getProfile().compareTo(sortBottom.getProfile()));

        PyramidCountDto pyramidCountDtoScholar = new PyramidCountDto();
        pyramidCountDtoScholar.setProfile("BEC");
        pyramidCountDtoScholar.setCount(countScholars);
        pyramidCountDtoList.add(pyramidCountDtoScholar);

        PyramidCountDto pyramidCountDtoTotal = new PyramidCountDto();
        pyramidCountDtoTotal.setProfile(TOTAL);
        pyramidCountDtoTotal.setCount(countTotal);
        pyramidCountDtoList.add(pyramidCountDtoTotal);

        return this.beanMapper.mapList(pyramidCountDtoList, PyramidCountDto.class);
    }

    Optional<Map<String, Double>> getGradeIndexMap() {

        List<Map<String, Double>> gradeIndexCostMapList = this.getPyramidIndexCost();

        Optional<Map<String, Double>> gradeIndexMap = gradeIndexCostMapList.stream()
                .filter(cost -> INDEX_ROWNAME.equals(cost.get(ROWNAME))).findAny();
        gradeIndexMap.get().remove("rowName");

        return gradeIndexMap;
    }

}
