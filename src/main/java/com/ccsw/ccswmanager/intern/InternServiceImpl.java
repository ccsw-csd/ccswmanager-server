package com.ccsw.ccswmanager.intern;

import static com.ccsw.ccswmanager.ldap.LdapServiceImpl.ACTIVE_TRUE;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.ccswmanager.common.SearchCriteria;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.intern.model.TimeLineDto;
import com.ccsw.ccswmanager.intern.model.TimeLineSearchDto;

@Service
public class InternServiceImpl implements InternService {

    static final String ACTION_OUT_INT = "Rechazo por CCA";
    static final String ACTION_OUT_EXT = "Rechazo por becario";
    static final String ACTION_CONTRACT = "Contrato";
    static final String ACTION_CONTINUE = "Continuar";

    static final String GREEN = "#00E396";
    static final String RED = "#FF4560";
    static final String BLUE = "#008FFB";

    public static final String EMPTY_STRING = " ";
    public static final String SEPARATOR = " | ";
    public static final String EMPTY_VALUE = "";

    @Autowired
    InternRepository repository;

    @Override
    public List<InternEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public List<InternEntity> findNotEmptyActives() {

        return repository.findByUsernameIsNotNullAndUsernameIsNotAndActive(EMPTY_STRING, ACTIVE_TRUE);
    }

    @Override
    public InternEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public InternEntity save(InternEntity entity) {

        return repository.save(entity);
    }

    @Override
    public List<InternEntity> saveAll(List<InternEntity> entities) {

        repository.saveAll(entities);

        return findAll();
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<InternEntity> entities) {

        repository.deleteAll(entities);
    }

    @Override
    public Set<String> getAllUsernames() {

        return findAll().stream().map(InternEntity::getUsername).collect(Collectors.toSet());
    }

    @Override
    public List<TimeLineDto> findTimelineByDate(TimeLineSearchDto date) {

        List<InternEntity> interns = getAllByDates(date.getStartDate(), date.getEndDate());

        List<TimeLineDto> internsTimeLine = new ArrayList<>();

        for (InternEntity intern : interns) {
            TimeLineDto internTimeline = new TimeLineDto();
            List<Long> axisY = new ArrayList<>();

            internTimeline.setAxisX(getAxisX(intern));
            if (intern.getStartDate() != null) {
                axisY.add(getParsedTimestamp(intern.getStartDate()));
            }
            if (intern.getEndDate() != null) {
                axisY.add(getParsedTimestamp(intern.getEndDate()));
            }
            internTimeline.setAxisY(axisY);

            if (intern.getAction() != null && (ACTION_CONTINUE.equals(intern.getAction().getName()) || ACTION_CONTRACT.equals(intern.getAction().getName()))) {
                internTimeline.setFillColor(GREEN);
            } else if (intern.getAction() != null && (ACTION_OUT_INT.equals(intern.getAction().getName()) || ACTION_OUT_EXT.equals(intern.getAction().getName()))) {
                internTimeline.setFillColor(RED);
            } else {
                internTimeline.setFillColor(BLUE);
            }

            internsTimeLine.add(internTimeline);
        }

        return internsTimeLine;
    }

    private List<InternEntity> getAllByDates(Date startDate, Date endDate) {

        InternSpecification active = new InternSpecification(new SearchCriteria(InternEntity.ATT_ACTIVE, ":", ACTIVE_TRUE, null));
        InternSpecification startDateGrThEq = new InternSpecification(new SearchCriteria(InternEntity.ATT_START_DATE, ">=", startDate, null));
        InternSpecification endDateLsThEq = new InternSpecification(new SearchCriteria(InternEntity.ATT_END_DATE, "<=", endDate, null));
        InternSpecification startDateLsThEq = new InternSpecification(new SearchCriteria(InternEntity.ATT_START_DATE, "<=", startDate, null));
        InternSpecification endDateGrThEq = new InternSpecification(new SearchCriteria(InternEntity.ATT_END_DATE, ">=", endDate, null));
        InternSpecification startDateBtw = new InternSpecification(new SearchCriteria(InternEntity.ATT_START_DATE, "<>", startDate, endDate));
        InternSpecification endDateBtw = new InternSpecification(new SearchCriteria(InternEntity.ATT_END_DATE, "<>", startDate, endDate));

        Specification<InternEntity> firstRange = startDateGrThEq.and(endDateLsThEq);
        Specification<InternEntity> secondRange = startDateLsThEq.and(endDateGrThEq);
        Specification<InternEntity> dateSpecs = firstRange.or(secondRange).or(startDateBtw).or(endDateBtw);

        return repository.findAll(Specification.where(active).and(dateSpecs), Sort.by(InternEntity.ATT_START_DATE));
    }

    private String getAxisX(InternEntity intern) {

        return intern.getName() + EMPTY_STRING + intern.getLastname() + getUsername(intern.getUsername()) + getCustomer(intern.getCustomer()) + getMentor(intern.getMentor());
    }

    private String getUsername(String username) {

        return username != null && !EMPTY_STRING.equals(username) ? " (" + username + ")" : EMPTY_VALUE;
    }

    private String getCustomer(String customer) {

        return customer != null && !EMPTY_STRING.equals(customer) ? SEPARATOR + customer : EMPTY_VALUE;
    }

    private String getMentor(String mentor) {

        return mentor != null && !EMPTY_STRING.equals(mentor) ? SEPARATOR + mentor : EMPTY_VALUE;
    }

    private Long getParsedTimestamp(Date date) {

        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ZonedDateTime zdtAtUtc = ld.atStartOfDay().atZone(ZoneId.of("UTC"));
        return zdtAtUtc.toInstant().toEpochMilli();
    }

    @Override
    public boolean existsByEducationId(Long educationId) {
        return this.repository.existsByEducationId(educationId);

    }

    @Override
    public boolean existsByTechnologiesId(Long technologyId) {

        return this.repository.existsByTechnologiesId(technologyId);

    }

}
