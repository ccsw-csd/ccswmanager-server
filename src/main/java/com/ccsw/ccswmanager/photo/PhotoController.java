package com.ccsw.ccswmanager.photo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.ccswmanager.config.mapper.BeanMapper;

/**
 * @author dapalmie
 *
 */
@RequestMapping(value = "/photo")
@RestController
public class PhotoController {

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/{idPerson}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto(@PathVariable Long idPerson) throws IOException {

        InputStream in = getClass().getResourceAsStream("/userphoto.jpg");
        return IOUtils.toByteArray(in);

    }

    @RequestMapping(path = "/generatePhotos", method = RequestMethod.GET)
    public void generatePhotos() throws IOException {

        URL imageURL = new URL("");
        BufferedImage image = ImageIO.read(imageURL);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();

        //InputStream in = getClass().getResourceAsStream("/userphoto.jpg");
        //return IOUtils.toByteArray(in);

    }

}
