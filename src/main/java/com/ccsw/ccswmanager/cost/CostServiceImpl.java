package com.ccsw.ccswmanager.cost;

import com.ccsw.ccswmanager.common.exception.ConflictException;
import com.ccsw.ccswmanager.cost.model.CostCenterCostIndexDto;
import com.ccsw.ccswmanager.cost.model.CostEntity;
import com.ccsw.ccswmanager.cost.model.PyramidGraphCustomerDto;
import com.ccsw.ccswmanager.cost.model.PyramidGraphDto;
import com.ccsw.ccswmanager.costcenter.model.CostCenterEntity;
import com.ccsw.ccswmanager.customer.CustomerService;
import com.ccsw.ccswmanager.customer.model.CustomerEntity;
import com.ccsw.ccswmanager.person.PersonService;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CostServiceImpl implements CostService {

	private static final String ROW_NAME = "rowName";
	private static final Double INDEX_ROW_NAME = 0.0;
	private static final Double COST_ROW_NAME = 1.0;
	private static final String COLUMN_B2 = "B2";
	private static final Double VALUE_B2 = 100.00;

	public static final String GRADE_REGEX = "[a-zA-z][1-9]+";

    @Autowired
	CostRepository repository;

	@Autowired
	PersonService personService;

	@Autowired
	CustomerService customerService;

	@Override
	public List<CostCenterCostIndexDto> getCostIndex() {

		List<CostCenterCostIndexDto> indexList = new ArrayList<>();

		List<CostEntity> costs = this.repository.findAll();

		List<CostCenterEntity> costCenters = costs.stream().map(CostEntity::getCostCenter).distinct().collect(Collectors.toList());

		costCenters.forEach(elem -> addCostCenter(indexList, elem, costs.stream().filter( c -> c.getCostCenter().equals(elem)).collect(Collectors.toList())));

		return indexList;
	}

	private void addCostCenter(List<CostCenterCostIndexDto> indexList, CostCenterEntity costCenter, List<CostEntity> costs) {

		CostCenterCostIndexDto index = new CostCenterCostIndexDto();
		index.setCostCenter(costCenter.getName());

		Map<String, Double> gradeIndexMap = new HashMap<>();
		Map<String, Double> gradeCostMap = new HashMap<>();
		List<Map<String, Double>> costIndex = new ArrayList<>();

		gradeIndexMap.put(ROW_NAME, INDEX_ROW_NAME);
		gradeCostMap.put(ROW_NAME, COST_ROW_NAME);

		Optional<CostEntity> costPivot = costs.stream().filter(cost -> COLUMN_B2.equals(cost.getGrade())).findFirst();
		Double costValuePivot = costPivot.get().getCost();

		for (CostEntity cost : costs) {

			gradeCostMap.put(cost.getGrade(), cost.getCost());

			if (COLUMN_B2.equals(cost.getGrade())) {
				gradeIndexMap.put(cost.getGrade(), VALUE_B2);
			} else {
				gradeIndexMap.put(cost.getGrade(), (cost.getCost() * 100) / costValuePivot);
			}
		}

		costIndex.add(gradeIndexMap);
		costIndex.add(gradeCostMap);

		index.setCostIndex(costIndex);

		indexList.add(index);
	}

	@Override
	public List<PyramidGraphDto> getPyramidGraph() throws ConflictException {

		List<PersonEntity> persons = this.personService.findAllContractsActives().stream().filter(person -> person.getGrade().matches(GRADE_REGEX)).collect(Collectors.toList());

		Map<Long, Map<String, Double>> costIndex = this.getCostIndexByCenterId();

		return this.getPyramid(persons, costIndex);
	}

	@Override
	public List<PyramidGraphCustomerDto> getPyramidGraphCustomer() throws ConflictException {

		List<PyramidGraphCustomerDto> customerPyramid = new ArrayList<>();

		Set<String> customers = customerService.findByUserRoles().stream().map(CustomerEntity::getName).collect(Collectors.toSet());

		List<PersonEntity> persons = personService.findAllContractsActivesByUserRoles().stream().filter(person -> person.getGrade().matches(GRADE_REGEX)).collect(Collectors.toList());

		Map<Long, Map<String, Double>> costIndex = this.getCostIndexByCenterId();

		for (String customer : customers) {

			List<PersonEntity> customerPersons = persons.stream().filter(person -> person.getCustomers().stream().anyMatch(c -> c.getName().equals(customer))).collect(Collectors.toList());

			customerPyramid.add(new PyramidGraphCustomerDto(customer, this.getPyramid(customerPersons, costIndex)));
		}

		customerPyramid.sort(Comparator.comparing(PyramidGraphCustomerDto::getCustomer));

		return customerPyramid;
	}

	private Map<Long, Map<String, Double>> getCostIndexByCenterId() {

		List<CostEntity> costs = this.repository.findAll();

		Map<Long, Map<String, Double>> costIndex = new HashMap<>();

		List<CostCenterEntity> costCenters = costs.stream().map(CostEntity::getCostCenter).distinct().collect(Collectors.toList());

		costCenters.forEach(elem -> addCenters(costIndex, elem, costs.stream().filter( c -> c.getCostCenter().equals(elem)).collect(Collectors.toList())));

		return costIndex;
	}

	private void addCenters(Map<Long, Map<String, Double>> map, CostCenterEntity costCenter, List<CostEntity> costs) {

		Map<String, Double> gradeIndexMap = new HashMap<>();

		Optional<CostEntity> costPivot = costs.stream().filter(cost -> COLUMN_B2.equals(cost.getGrade())).findFirst();
		Double costValuePivot = costPivot.get().getCost();

		for (CostEntity cost : costs) {

			if (COLUMN_B2.equals(cost.getGrade())) {
				gradeIndexMap.put(cost.getGrade(), VALUE_B2);
			} else {
				gradeIndexMap.put(cost.getGrade(), (cost.getCost() * 100) / costValuePivot);
			}
		}

		costCenter.getCenters().forEach( center -> map.put(center.getId().longValue(), gradeIndexMap));
	}

	private List<PyramidGraphDto> getPyramid(List<PersonEntity> persons, Map<Long, Map<String, Double>> costIndex) throws ConflictException {

		List<PyramidGraphDto> pyramid = new ArrayList<>();

		List<PersonEntity> personsWithCostCenter = persons.stream().filter(p -> this.personCenterHasCostCenter(p, costIndex)).collect(Collectors.toList());

		Integer total = personsWithCostCenter.size();
		Map<String, Long> gradeCount = personsWithCostCenter.stream().collect(Collectors.groupingBy(PersonEntity::getGrade, Collectors.counting()));
		Map<String, Double> gradeIndex = new HashMap<>();

		personsWithCostCenter.forEach( p -> {
			if(gradeIndex.containsKey(p.getGrade())){
				gradeIndex.put(p.getGrade(), gradeIndex.get(p.getGrade()) + this.getPersonIndex(p, costIndex));
			} else {
				gradeIndex.put(p.getGrade(), this.getPersonIndex(p, costIndex));
			}
		});

		List<String> grades = this.getGradeListSorted(costIndex);

		grades.forEach(grade -> {
			if (gradeCount.containsKey(grade)) {
				pyramid.add(new PyramidGraphDto(grade, gradeCount.get(grade), gradeIndex.get(grade) / total));
			} else {
				pyramid.add(new PyramidGraphDto(grade, 0L, 0.0));
			}
		});

		pyramid.add(new PyramidGraphDto("TOTAL", pyramid.stream().mapToLong(PyramidGraphDto::getCount).sum(), pyramid.stream().mapToDouble(PyramidGraphDto::getIndex).sum()));

		return pyramid;
	}

	private boolean personCenterHasCostCenter(PersonEntity person, Map<Long, Map<String, Double>> map){

		return map.containsKey(person.getCenter().getId().longValue());
	}

	private Double getPersonIndex(PersonEntity person, Map<Long, Map<String, Double>> map) {

		return map.get(person.getCenter().getId().longValue()).get(person.getGrade());
	}

	private List<String> getGradeListSorted(Map<Long, Map<String, Double>> map) throws ConflictException {

		List<String> gradeList = new ArrayList<>();

		if(map.isEmpty()){
			throw new ConflictException("La configuración de centro de coste no se ha realizado correctamente");
		}

		map.entrySet().iterator().next().getValue().forEach((key, value) -> gradeList.add(key));

		gradeList.sort(Comparator.comparing(String::toString).reversed());

		return gradeList;
	}
}
