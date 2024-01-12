package com.ccsw.ccswmanager.costcenter;

import com.ccsw.ccswmanager.center.model.CenterEntity;
import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.costcenter.model.CostCenterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostCenterServiceImpl implements CostCenterService {

    @Autowired
    CostCenterRepository repository;

    @Override
    public List<CostCenterEntity> findAll() {

        return repository.findAll();
    }

    @Override
    public CostCenterEntity getById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public CostCenterEntity save(CostCenterEntity entity) throws AlreadyExistsException {

        CostCenterEntity costCenter = this.repository.findByName(entity.getName());

        if (costCenter != null && (entity.getId() == null || !costCenter.getId().equals(entity.getId()))) {
            throw new AlreadyExistsException("El nombre ya existe en la BBDD");
        }

        this.checkCenters(entity, this.repository.findByCentersIn(entity.getCenters()));

        entity.addCostCenterToCost();
        return repository.save(entity);
    }

    private void checkCenters(CostCenterEntity entity, List<CostCenterEntity> costCenters) throws AlreadyExistsException {

        for(CenterEntity center : entity.getCenters()){
            Optional<CostCenterEntity> match = costCenters.stream().filter(cc -> cc.getCenters().stream().anyMatch(elem -> elem.getId().equals(center.getId()))).findAny();

            if(match.isPresent() && !match.get().getId().equals(entity.getId())){
                throw new AlreadyExistsException("El centro " + center.getName() + " ya pertenece al centro de coste " + match.get().getName());
            }
        }
	}

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

}
