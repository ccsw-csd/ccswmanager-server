package com.ccsw.ccswmanager.scholar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import com.ccsw.ccswmanager.person.model.PersonDto;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.person.PersonService;
import com.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.ccsw.ccswmanager.scholar.model.SearchCriteria;
import com.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.ccsw.ccswmanager.scholar.model.VScholarEntity;
import com.ccsw.ccswmanager.scholar.model.VScholarTimeLineDto;
import com.ccsw.ccswmanager.scholar.model.VScholarTimeLineSearchDto;

/**
 * @author jchengli
 *
 */
@Service
public class ScholarServiceImpl implements ScholarService {

    static final Integer ACTION_OUT = 0;
    static final Integer ACTION_CONTRACT = 1;
    static final Integer ACTION_CONTINUE = 2;

    static final String GREEN = "#00E396";
    static final String RED = "#FF4560";
    static final String BLUE = "#008FFB";

    @Autowired
    ScholarRepository scholarRepository;

    @Autowired
    VScholarRepository vScholarRepository;

    @Autowired
    PersonService personService;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public ScholarEntity get(Long id) {

        return this.scholarRepository.findById(id).orElse(null);
    }

    @Override
    public ScholarEntity getByPersonId(Long id) {

        return this.scholarRepository.getByPersonId(id);
    }

    @Override
    public List<VScholarDto> findScholars() {

        return this.beanMapper.mapList(this.vScholarRepository.findAll(), VScholarDto.class);
    }

    @Override
    public List<VScholarDto> saveOrUpdateScholars(List<VScholarDto> dtoList) {

        dtoList.forEach(dto -> {
            Objects.requireNonNull(dto, "scholar");

            PersonDto personDto = new PersonDto();
            BeanUtils.copyProperties(dto, personDto);
            Optional<PersonEntity> personOpt = personService.saveOrUpdatePerson(personDto);

            if (personOpt.isPresent()) {
                ScholarEntity scholar = dto.getScholarId() != null ? get(dto.getScholarId()) : new ScholarEntity();

                BeanUtils.copyProperties(dto, scholar, "id", "person");

                scholar.setPerson(personOpt.get());

                this.scholarRepository.save(scholar);
            }
        });

        return findScholars();
    }

    @Override
    public List<VScholarTimeLineDto> findScholarsTimelineByDate(VScholarTimeLineSearchDto date) {

        List<VScholarEntity> scholars = getAllByDates(date.getStartDate(), date.getEndDate());

        List<VScholarTimeLineDto> scholarsTimeLine = new ArrayList<>();

        for (VScholarEntity scholar : scholars) {
            VScholarTimeLineDto scholarTimeline = new VScholarTimeLineDto();
            List<Long> axisY = new ArrayList<>();

            scholarTimeline.setAxisX(scholar.getName() + " " + scholar.getLastname() + " (" + scholar.getUsername() + ")");
            if (scholar.getStartDate() != null) {
                axisY.add(getParsedTimestamp(scholar.getStartDate()));
            }
            if (scholar.getEndDate() != null) {
                axisY.add(getParsedTimestamp(scholar.getEndDate()));
            }
            scholarTimeline.setAxisY(axisY);

            if (ACTION_CONTINUE.equals(scholar.getAction()) || ACTION_CONTRACT.equals(scholar.getAction())) {
                scholarTimeline.setFillColor(GREEN);
            } else if (ACTION_OUT.equals(scholar.getAction())) {
                scholarTimeline.setFillColor(RED);
            } else {
                scholarTimeline.setFillColor(BLUE);
            }

            scholarsTimeLine.add(scholarTimeline);
        }

        return scholarsTimeLine;
    }

    private List<VScholarEntity> getAllByDates(Date startDate, Date endDate) {

        VScholarSpecification startDateGrThEq = new VScholarSpecification(
                new SearchCriteria(VScholarEntity.ATT_START_DATE, ">=", startDate, null));
        VScholarSpecification endDateLsThEq = new VScholarSpecification(
                new SearchCriteria(VScholarEntity.ATT_END_DATE, "<=", endDate, null));
        VScholarSpecification startDateLsThEq = new VScholarSpecification(
                new SearchCriteria(VScholarEntity.ATT_START_DATE, "<=", startDate, null));
        VScholarSpecification endDateGrThEq = new VScholarSpecification(
                new SearchCriteria(VScholarEntity.ATT_END_DATE, ">=", endDate, null));
        VScholarSpecification startDateBtw = new VScholarSpecification(
                new SearchCriteria(VScholarEntity.ATT_START_DATE, "<>", startDate, endDate));
        VScholarSpecification endDateBtw = new VScholarSpecification(
                new SearchCriteria(VScholarEntity.ATT_END_DATE, "<>", startDate, endDate));

        return vScholarRepository.findAll(Specification.where(startDateGrThEq)
                        .and(endDateLsThEq).or(startDateLsThEq).and(endDateGrThEq).or(startDateBtw).or(endDateBtw),
                Sort.by(VScholarEntity.ATT_START_DATE));
    }

    private Long getParsedTimestamp(Date date) {

        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ZonedDateTime zdtAtUtc = ld.atStartOfDay().atZone(ZoneId.of("UTC"));
        return zdtAtUtc.toInstant().toEpochMilli();
    }

    @Override
    public void deleteById(Long id) {

        this.scholarRepository.deleteById(id);
    }
}
