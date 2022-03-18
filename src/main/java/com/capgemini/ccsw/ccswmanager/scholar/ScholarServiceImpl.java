package com.capgemini.ccsw.ccswmanager.scholar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.capgemini.ccsw.ccswmanager.person.PersonService;
import com.capgemini.ccsw.ccswmanager.scholar.model.ScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarEntity;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarTimeLineDto;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarTimeLineSearchDto;

/**
 * @author jchengli
 *
 */

@Service
public class ScholarServiceImpl implements ScholarService {
    static final Integer ACTION_CONTINUE = 2;
    static final Integer ACTION_CONTRACT = 1;
    static final Integer ACTION_OUT = 0;
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
    public ScholarEntity get(long id) {

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
            ScholarEntity scholar = null;

            if (dto.getId() != null)
                scholar = get(dto.getId());
            if (scholar == null)
                scholar = new ScholarEntity();

            BeanUtils.copyProperties(dto, scholar, "id", "person");
            scholar.setPerson(this.personService.get(dto.getId()));

            this.scholarRepository.save(scholar);
        });
        return findScholars();
    }

    @Override
    public List<VScholarTimeLineDto> findScholarsTimelineByDate(VScholarTimeLineSearchDto date) {

        List<VScholarEntity> vscholars = vScholarRepository
                .findByStartDateGreaterThanEqualAndEndDateLessThanEqualOrStartDateLessThanEqualAndEndDateGreaterThanEqualOrStartDateBetweenOrEndDateBetweenOrderByStartDateAsc(
                        date.getStartDate(), date.getEndDate(), date.getStartDate(), date.getEndDate(),
                        date.getStartDate(), date.getEndDate(), date.getStartDate(), date.getEndDate());
        List<VScholarTimeLineDto> vscholarsTimeLine = new ArrayList<VScholarTimeLineDto>();

        for (VScholarEntity vscholar : vscholars) {
            VScholarTimeLineDto vscholarTimeline = new VScholarTimeLineDto();
            List<Long> axisY = new ArrayList<Long>();

            vscholarTimeline
                    .setAxisX(vscholar.getName() + " " + vscholar.getLastname() + "(" + vscholar.getUsername() + ")");
            if (vscholar.getStartDate() != null) {
                axisY.add(getParsedTimestamp(vscholar.getStartDate()));
            }
            if (vscholar.getEndDate() != null) {
                axisY.add(getParsedTimestamp(vscholar.getEndDate()));
            }
            vscholarTimeline.setAxisY(axisY);

            if (ACTION_CONTINUE.equals(vscholar.getAction()) || ACTION_CONTRACT.equals(vscholar.getAction())) {
                vscholarTimeline.setFillColor(GREEN);
            } else if (ACTION_OUT.equals(vscholar.getAction())) {
                vscholarTimeline.setFillColor(RED);
            } else {
                vscholarTimeline.setFillColor(BLUE);
            }

            vscholarsTimeLine.add(vscholarTimeline);
        }

        return vscholarsTimeLine;
    }

    private Long getParsedTimestamp(Date date) {
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ZonedDateTime zdtAtUtc = ld.atStartOfDay().atZone(ZoneId.of("UTC"));
        return zdtAtUtc.toInstant().toEpochMilli();
    }

    @Override
    public void deleteById(long id) {

        this.scholarRepository.deleteById(id);
    }
}
