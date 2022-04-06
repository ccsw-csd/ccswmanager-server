package com.capgemini.ccsw.ccswmanager.pyramid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        return getPyramidIndexCost();
    }

    /*
     * Primera funcion, consigues el count para cada index con la consulta y luego
     * ya añades el index
     * 
     * Segunda funcion, misma consulta que arriba para conseguir el count, pero
     * luego se suma
     * 
     * D = D1 + D2
     * 
     * 
     * for(....) map(pyramid.grade) ++; segun el grado que sea del que recoja de la
     * BD se sumara 1
     * 
     */

    @Override
    public List<PyramidDto> getPyramidsProfileCountIndex() {
        List<Map<String, Double>> gradeIndexCostMapList = this.getPyramidIndexCost();
        List<PersonEntity> personEntityList = this.personRepository.findByDepartment(DEPARTMENT);
        List<PyramidDto> pyramidDtoList = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        int countTotal = 0;
        Double indexTotal = 0.0;

        // con esto obtengo el index
        Optional<Map<String, Double>> gradeIndexMap = gradeIndexCostMapList.stream()
                .filter(cost -> INDEX_ROWNAME.equals(cost.get(ROWNAME))).findAny();
        gradeIndexMap.get().remove("rowName");

        // con esto obtengo el count de cada grade
        for (PersonEntity personEntity : personEntityList) {
            int count = 1;

            if (countMap.get(personEntity.getGrade()) != null)
                count = countMap.get(personEntity.getGrade()) + 1;

            countMap.put(personEntity.getGrade(), count);
        }

        // con esto obtengo el count total
        // EL NUMERO DE PERSONAS DE personEntityList ES EL COUNT TOTAL, PERO NO TIENE EN
        // CUENTA LOS QUE NO COINCIDA
        for (Map.Entry<String, Double> entry : gradeIndexMap.get().entrySet()) {
            if (countMap.get(entry.getKey()) != null) {
                countTotal += countMap.get(entry.getKey());
            }
        }

        // con esto obtengo cada fila de la tabla
        for (Map.Entry<String, Double> entry : gradeIndexMap.get().entrySet()) {
            PyramidDto pyramidGraphDto = new PyramidDto();

            pyramidGraphDto.setProfile(entry.getKey());

            if (countMap.get(entry.getKey()) != null)
                pyramidGraphDto.setCount(countMap.get(entry.getKey()));
            else
                pyramidGraphDto.setCount(0);

            pyramidGraphDto.setIndex((pyramidGraphDto.getCount() / Double.valueOf(countTotal)) * entry.getValue());
            indexTotal += pyramidGraphDto.getIndex();

            pyramidDtoList.add(pyramidGraphDto);
        }

        Collections.sort(pyramidDtoList, (o1, o2) -> o2.getProfile().compareTo(o1.getProfile()));

        PyramidDto pyramidTotalGraphDto = new PyramidDto();
        pyramidTotalGraphDto.setProfile("TOTAL");
        pyramidTotalGraphDto.setCount(countTotal);
        pyramidTotalGraphDto.setIndex(indexTotal);
        pyramidDtoList.add(pyramidTotalGraphDto);

        return this.beanMapper.mapList(pyramidDtoList, PyramidDto.class);
    }

    public List<PyramidCountDto> getPyramidsProfileCount() {
        List<Map<String, Double>> gradeIndexCostMapList = this.getPyramidIndexCost();
        List<PyramidCountDto> pyramidCountDtoList = new ArrayList<>();
        List<PersonEntity> personEntityList = this.personRepository.findByDepartment(DEPARTMENT);
        Map<String, Integer> countMap = new HashMap<>();
        int countTotal = 0;

        Optional<Map<String, Double>> gradeIndexMap = gradeIndexCostMapList.stream()
                .filter(cost -> INDEX_ROWNAME.equals(cost.get(ROWNAME))).findAny();
        gradeIndexMap.get().remove("rowName");

        for (PersonEntity personEntity : personEntityList) {
            int count = 1;

            if (countMap.get(personEntity.getGrade()) != null)
                count = countMap.get(personEntity.getGrade()) + 1;

            countMap.put(personEntity.getGrade(), count);
        }

        for (Map.Entry<String, Double> entry : gradeIndexMap.get().entrySet()) {
            if (countMap.get(entry.getKey()) != null) {
                countTotal += countMap.get(entry.getKey());
            } else
                countMap.put(entry.getKey(), 0);
        }
        if (countMap.get(SCHOLAR) != null)
            countTotal += countMap.get(SCHOLAR);
        else
            countMap.put(SCHOLAR, 0);

        PyramidCountDto pyramidCountDtoD = new PyramidCountDto();
        pyramidCountDtoD.setProfile("D");
        pyramidCountDtoD.setCount(countMap.get("D1") + countMap.get("D2"));
        pyramidCountDtoList.add(pyramidCountDtoD);

        PyramidCountDto pyramidCountDtoC = new PyramidCountDto();
        pyramidCountDtoC.setProfile("C");
        pyramidCountDtoC.setCount(countMap.get("C1") + countMap.get("C2") + countMap.get("C3"));
        pyramidCountDtoList.add(pyramidCountDtoC);

        PyramidCountDto pyramidCountDtoB = new PyramidCountDto();
        pyramidCountDtoB.setProfile("B");
        pyramidCountDtoB.setCount(countMap.get("B1") + countMap.get("B2") + countMap.get("B3"));
        pyramidCountDtoList.add(pyramidCountDtoB);

        PyramidCountDto pyramidCountDtoA = new PyramidCountDto();
        pyramidCountDtoA.setProfile("A");
        pyramidCountDtoA.setCount(countMap.get("A1") + countMap.get("A2"));
        pyramidCountDtoList.add(pyramidCountDtoA);

        PyramidCountDto pyramidCountDtoScholar = new PyramidCountDto();
        pyramidCountDtoScholar.setProfile("BEC");
        pyramidCountDtoScholar.setCount(countMap.get(SCHOLAR));
        pyramidCountDtoList.add(pyramidCountDtoScholar);

        PyramidCountDto pyramidCountDtoTotal = new PyramidCountDto();
        pyramidCountDtoTotal.setProfile("TOTAL");
        pyramidCountDtoTotal.setCount(countTotal);
        pyramidCountDtoList.add(pyramidCountDtoTotal);

        return this.beanMapper.mapList(pyramidCountDtoList, PyramidCountDto.class);
    }

}
