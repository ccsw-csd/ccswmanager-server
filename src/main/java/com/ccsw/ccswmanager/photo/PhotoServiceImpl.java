package com.ccsw.ccswmanager.photo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.ccsw.ccswmanager.person.PersonService;
import com.ccsw.ccswmanager.person.model.PersonEntity;
import com.ccsw.ccswmanager.photo.model.PhotoEntity;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PersonService personService;

    @Override
    public byte[] getPhoto(Long idPerson) {

        Optional<PhotoEntity> optionalPhoto = photoRepository.findById(idPerson);

        if (optionalPhoto.isPresent()) {
            return optionalPhoto.get().getData();
        }

        try {
            return IOUtils.toByteArray(getClass().getResourceAsStream("/userphoto.jpg"));
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    @Transactional
    public void generatePhotos(String query) throws UnsupportedEncodingException {

        MultiValueMap<String, String> params = UriComponentsBuilder.fromUriString(query).build().getQueryParams();

        String _oat_ = params.get("_oat_").get(0);
        String P1 = URLEncoder.encode(params.get("P1").get(0), "UTF-8");
        String P2 = URLEncoder.encode(params.get("P2").get(0), "UTF-8");
        String P3 = URLEncoder.encode(params.get("P3").get(0), "UTF-8");
        String P4 = URLEncoder.encode(params.get("P4").get(0), "UTF-8");

        List<PhotoEntity> photos = new ArrayList<>();
        List<PersonEntity> persons = personService.findAllContractsActives();

        for (PersonEntity person : persons) {

            String email = person.getEmail();
            if (StringUtils.hasText(email) == false || email.contains("email_"))
                continue;

            URL imageURL;
            try {
                imageURL = new URL("https://capgemini.sharepoint.com/_vti_bin/afdcache.ashx/_userprofile/userphoto.jpg?_oat_=" + _oat_ + "&P1=" + P1 + "&P2=" + P2 + "&P3=" + P3 + "&P4=" + P4 + "&size=M&accountName=" + email);
                BufferedImage image = ImageIO.read(imageURL);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", byteArrayOutputStream);
                byteArrayOutputStream.flush();

                PhotoEntity photo = new PhotoEntity();
                photo.setPersonId(person.getId());
                photo.setData(byteArrayOutputStream.toByteArray());

                photos.add(photo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (photos.size() > 0) {
            photoRepository.deleteAll();
            photoRepository.saveAll(photos);
        }
    }

}
