package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.ArrayList;
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
        int accionContinuar = 2;
        int accionContrato = 1;
        int accionOut = 0;
        String verde = "#00E396";
        String rojo = "#FF4560";
        String azul = "#008FFB";
        List<VScholarEntity> vscholars = vScholarRepository
                .findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(date.getStartDate(), date.getEndDate());
        List<VScholarEntity> vscholars2 = vScholarRepository
                .findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(date.getStartDate(), date.getEndDate());
        List<VScholarEntity> vscholars3 = vScholarRepository.findAllByStartDateBetweenOrEndDateBetween(
                date.getStartDate(), date.getEndDate(), date.getStartDate(), date.getEndDate());
        vscholars.addAll(vscholars2);
        vscholars.addAll(vscholars3);
        List<VScholarTimeLineDto> vscholarsTimeLine = new ArrayList<VScholarTimeLineDto>();

        for (VScholarEntity vscholar : vscholars) {
            VScholarTimeLineDto vscholarTimeline = new VScholarTimeLineDto();
            ArrayList<Long> axisY = new ArrayList<Long>();

            vscholarTimeline.setX(vscholar.getUsername());
            if (vscholar.getStartDate() != null) {
                axisY.add(vscholar.getStartDate().getTime());
            }
            if (vscholar.getEndDate() != null) {
                axisY.add(vscholar.getEndDate().getTime());
            }
            vscholarTimeline.setY(axisY);

            if (vscholar.getAction() != null) {
                if ((Integer) vscholar.getAction() == accionContinuar
                        || (Integer) vscholar.getAction() == accionContrato) {
                    vscholarTimeline.setFillColor(verde);
                } else if ((Integer) vscholar.getAction() == accionOut) {
                    vscholarTimeline.setFillColor(rojo);
                } else {
                    vscholarTimeline.setFillColor(azul);
                }
            } else {
                vscholarTimeline.setFillColor(azul);
            }

            vscholarsTimeLine.add(vscholarTimeline);
        }

        return vscholarsTimeLine;
    }

    @Override
    public void deleteById(long id) {

        this.scholarRepository.deleteById(id);
    }
}
