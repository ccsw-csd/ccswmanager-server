package com.ccsw.ccswmanager.pyramid;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.customer.CustomerService;
import com.ccsw.ccswmanager.customer.model.CustomerEntity;
import com.ccsw.ccswmanager.person.PersonService;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.pyramid.model.PyramidCostEntity;
import com.ccsw.ccswmanager.pyramid.model.PyramidCountDto;
import com.ccsw.ccswmanager.pyramid.model.PyramidDto;
import com.ccsw.ccswmanager.pyramid.model.PyramidTeamsListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jchengli
 *
 */
@Service
public class PyramidServiceImpl implements PyramidService {

    private static final String ROW_NAME = "rowName";
    private static final Double INDEX_ROW_NAME = 0.0;
    private static final Double COST_ROW_NAME = 1.0;
    private static final String COLUMN_B2 = "B2";
    private static final Double VALUE_B2 = 100.00;
    private static final String DEPARTMENT = "CCSw";
    private static final String SCHOLAR = "";
    private static final String TOTAL = "TOTAL";
    private static final String SCHOLAR_PROFILE = "BEC";
    private static final Double DEFAULT_VALUE = 0.0;

    public static final String GRADE_REGEX = "[a-zA-z][1-9]+";

    @Autowired
    PyramidRepository pyramidRepository;

    @Autowired
    PersonService personService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public List<Map<String, Double>> getPyramidIndexCost() {

        List<PyramidCostEntity> pyramidCostEntityList = this.pyramidRepository.findAll();
        Map<String, Double> gradeIndexMap = new HashMap<>();
        Map<String, Double> gradeCostMap = new HashMap<>();
        List<Map<String, Double>> gradeIndexCostMapList = new ArrayList<>();

        gradeIndexMap.put(ROW_NAME, INDEX_ROW_NAME);
        gradeCostMap.put(ROW_NAME, COST_ROW_NAME);

        Optional<PyramidCostEntity> pyramidCostB2Entity = pyramidCostEntityList.stream().filter(cost -> COLUMN_B2.equals(cost.getGrade())).findFirst();
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

        Optional<Map<String, Double>> gradeIndexCostMap = gradeIndexCostMapList.stream().filter(cost -> COST_ROW_NAME.equals(cost.get(ROW_NAME))).findAny();

        for (PyramidCostEntity pyramidCostEntity : pyramidCostEntityList) {
            pyramidCostEntity.setCost(gradeIndexCostMap.get().get(pyramidCostEntity.getGrade()));
        }

        this.pyramidRepository.saveAll(pyramidCostEntityList);

        return getPyramidIndexCost();
    }

    @Override
    public List<PyramidDto> getPyramidsProfileCountIndex() {

        List<PersonEntity> personEntityList = this.personService.findByDepartmentActives(DEPARTMENT);
        List<PyramidDto> pyramidDtoList = new ArrayList<>();
        long countTotal = 0;
        Double indexTotal = 0.0;

        Optional<Map<String, Double>> gradeIndexMap = this.getGradeIndexMap();

        Map<String, Long> countMap = personEntityList.stream().filter(person -> person.getGrade() != null && person.getGrade().matches(GRADE_REGEX)).collect(Collectors.groupingBy(PersonEntity::getGrade, Collectors.counting()));

        countTotal = gradeIndexMap.get().entrySet().stream().filter(grade -> countMap.get(grade.getKey()) != null).mapToLong(grade -> countMap.get(grade.getKey())).sum();

        for (Map.Entry<String, Double> entry : gradeIndexMap.get().entrySet()) {
            PyramidDto pyramidGraphDto = new PyramidDto();

            pyramidGraphDto.setProfile(entry.getKey());

            if (countMap.get(entry.getKey()) != null) {
                pyramidGraphDto.setCount(countMap.get(entry.getKey()));
            } else {
                pyramidGraphDto.setCount((long) 0);
            }

            pyramidGraphDto.setIndex((pyramidGraphDto.getCount() / Double.valueOf(countTotal)) * entry.getValue());
            indexTotal += pyramidGraphDto.getIndex();

            pyramidDtoList.add(pyramidGraphDto);
        }

        Collections.sort(pyramidDtoList, (sortBottom, sortTop) -> sortTop.getProfile().compareTo(sortBottom.getProfile()));

        PyramidDto pyramidTotalGraphDto = new PyramidDto();
        pyramidTotalGraphDto.setProfile(TOTAL);
        pyramidTotalGraphDto.setCount(countTotal);
        pyramidTotalGraphDto.setIndex(indexTotal);
        pyramidDtoList.add(pyramidTotalGraphDto);

        return this.beanMapper.mapList(pyramidDtoList, PyramidDto.class);
    }

    @Override
    public List<PyramidCountDto> getPyramidsProfileCount() {

        List<PyramidCountDto> pyramidCountDtoList = new ArrayList<>();
        List<PersonEntity> personEntityList = this.personService.findByDepartmentActives(DEPARTMENT);
        long countTotal = 0;

        Optional<Map<String, Double>> gradeIndexMap = this.getGradeIndexMap();

        Map<String, Long> countMap = personEntityList.stream().filter(person -> person.getGrade() != null && person.getGrade().matches(GRADE_REGEX)).collect(Collectors.groupingBy(person -> String.valueOf(person.getGrade().charAt(0)), Collectors.counting()));

        long countScholars = personEntityList.stream().filter(person -> person.getGrade() != null && person.getGrade().equals(SCHOLAR)).count();

        countTotal = personEntityList.stream().filter(person -> person.getGrade() != null && (person.getGrade().matches(GRADE_REGEX) || person.getGrade().equals(SCHOLAR))).count();

        gradeIndexMap.get().entrySet().stream().filter(grade -> countMap.get(String.valueOf(grade.getKey().charAt(0))) == null).forEach(grade -> countMap.put(String.valueOf(grade.getKey().charAt(0)), (long) 0));

        for (Map.Entry<String, Long> entry : countMap.entrySet()) {
            PyramidCountDto pyramidCountDto = new PyramidCountDto();
            pyramidCountDto.setProfile(entry.getKey());
            pyramidCountDto.setCount(entry.getValue());
            pyramidCountDtoList.add(pyramidCountDto);
        }

        Collections.sort(pyramidCountDtoList, (sortBottom, sortTop) -> sortTop.getProfile().compareTo(sortBottom.getProfile()));

        PyramidCountDto pyramidCountDtoScholar = new PyramidCountDto();
        pyramidCountDtoScholar.setProfile(SCHOLAR_PROFILE);
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

        Optional<Map<String, Double>> gradeIndexMap = gradeIndexCostMapList.stream().filter(cost -> INDEX_ROW_NAME.equals(cost.get(ROW_NAME))).findAny();
        gradeIndexMap.get().remove(ROW_NAME);

        return gradeIndexMap;
    }

    @Override
    public List<PyramidTeamsListDto> getPyramidTeamCost() {

        List<PyramidTeamsListDto> customerCosts = new ArrayList<>();

        Set<String> customers = customerService.findByUserRoles().stream().map(CustomerEntity::getName).collect(Collectors.toSet());

        List<PersonEntity> personList = personService.findAllContractsActivesByUserRoles();

        List<Map<String, Double>> gradeIndexCostMapList = getPyramidIndexCost();
        Map<String, Double> gradeMap = gradeIndexCostMapList.get(0);
        List<String> gradeList = getGradesFromIndexCostMapSorted(gradeMap);

        for (String customer : customers) {
            List<PyramidDto> customerList = new ArrayList<>();
            Map<String, Long> gradeCountMap = new HashMap<>();
            Map<String, Double> gradeIndexMap = new HashMap<>();

            gradeCountMap = personList.stream().filter(person -> person.getCustomers().stream().anyMatch(c -> c.getName().equals(customer))).collect(Collectors.groupingBy(PersonEntity::getGrade, Collectors.counting()));

            Long mapSize = gradeCountMap.values().stream().mapToLong(Long::longValue).sum();
            for (String grade : gradeList) {
                if (gradeCountMap.containsKey(grade)) {
                    gradeIndexMap.put(grade, (gradeCountMap.get(grade) / Double.valueOf(mapSize)) * gradeMap.get(grade));
                } else {
                    gradeIndexMap.put(grade, DEFAULT_VALUE);
                }

                customerList.add(new PyramidDto(grade, gradeCountMap.get(grade), gradeIndexMap.get(grade)));
            }
            Double indexTotal = gradeIndexMap.values().stream().mapToDouble(Double::doubleValue).sum();
            customerList.add(new PyramidDto(TOTAL, mapSize, indexTotal));

            customerCosts.add(new PyramidTeamsListDto(customer, customerList, mapSize));
        }

        customerCosts.sort(Comparator.comparing(PyramidTeamsListDto::getCustomerSize).reversed());
        return customerCosts;
    }

    private List<String> getGradesFromIndexCostMapSorted(Map<String, Double> gradeIndexCostMapList) {
        List<String> gradeList = new ArrayList<>();
        Map<String, Double> gradeMap = gradeIndexCostMapList;
        gradeMap.remove(ROW_NAME);
        gradeMap.entrySet().stream().forEach(keyEntry -> gradeList.add(keyEntry.getKey()));

        gradeList.sort(Comparator.comparing(String::toString).reversed());

        return gradeList;
    }

}
