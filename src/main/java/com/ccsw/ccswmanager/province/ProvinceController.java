package com.ccsw.ccswmanager.province;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.province.model.ProvinceDto;

/**
 * @author dapalmie
 *
 */

@RequestMapping(value = "/province/")
@RestController
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<ProvinceDto> findPprovinces() {
        return this.provinceService.findProvinces();
    }

}
