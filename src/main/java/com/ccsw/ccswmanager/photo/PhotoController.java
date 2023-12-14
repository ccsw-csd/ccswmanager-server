package com.ccsw.ccswmanager.photo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dapalmie
 *
 */
@RequestMapping(value = "/photo")
@RestController
public class PhotoController {

    @Autowired
    PhotoService photoService;

    //https://capgemini.sharepoint.com/_layouts/15/sharepoint.aspx
    @RequestMapping(path = "/generate", method = RequestMethod.GET)
    public void generatePhotos(@RequestParam String query) throws IOException {

        photoService.generatePhotos(query);

    }

}
