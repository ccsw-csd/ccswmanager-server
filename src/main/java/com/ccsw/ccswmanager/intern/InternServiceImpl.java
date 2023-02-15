package com.ccsw.ccswmanager.intern;

import com.ccsw.ccswmanager.common.SearchCriteria;
import com.ccsw.ccswmanager.intern.model.InternEntity;
import com.ccsw.ccswmanager.intern.model.TimeLineDto;
import com.ccsw.ccswmanager.intern.model.TimeLineSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ccsw.ccswmanager.ldap.LdapServiceImpl.ACTIVE_TRUE;
import static com.ccsw.ccswmanager.ldap.LdapServiceImpl.EMPTY_STRING;

@Service
public class InternServiceImpl implements InternService {

    static final String ACTION_OUT_INT = "Rechazo por CCA";
    static final String ACTION_OUT_EXT = "Rechazo por becario";
    static final String ACTION_CONTRACT = "Contrato";
    static final String ACTION_CONTINUE = "Continuar";

    static final String GREEN = "#00E396";
    static final String RED = "#FF4560";
    static final String BLUE = "#008FFB";

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

            internTimeline.setAxisX(intern.getName() + " " + intern.getLastname() + getUsername(intern.getUsername()));
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

        InternSpecification startDateGrThEq = new InternSpecification(
                new SearchCriteria(InternEntity.ATT_START_DATE, ">=", startDate, null));
        InternSpecification endDateLsThEq = new InternSpecification(
                new SearchCriteria(InternEntity.ATT_END_DATE, "<=", endDate, null));
        InternSpecification startDateLsThEq = new InternSpecification(
                new SearchCriteria(InternEntity.ATT_START_DATE, "<=", startDate, null));
        InternSpecification endDateGrThEq = new InternSpecification(
                new SearchCriteria(InternEntity.ATT_END_DATE, ">=", endDate, null));
        InternSpecification startDateBtw = new InternSpecification(
                new SearchCriteria(InternEntity.ATT_START_DATE, "<>", startDate, endDate));
        InternSpecification endDateBtw = new InternSpecification(
                new SearchCriteria(InternEntity.ATT_END_DATE, "<>", startDate, endDate));

        return repository.findAll(
                Specification.where(startDateGrThEq).and(endDateLsThEq).or(startDateLsThEq).and(endDateGrThEq).or(startDateBtw).or(endDateBtw),
                Sort.by(InternEntity.ATT_START_DATE));
    }

    private String getUsername(String username) {

        return username != null ? " (" + username + ")": "";
    }

    private Long getParsedTimestamp(Date date) {

        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ZonedDateTime zdtAtUtc = ld.atStartOfDay().atZone(ZoneId.of("UTC"));
        return zdtAtUtc.toInstant().toEpochMilli();
    }

}
