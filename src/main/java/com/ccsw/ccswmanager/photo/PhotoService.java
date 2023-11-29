package com.ccsw.ccswmanager.photo;

import java.io.UnsupportedEncodingException;

public interface PhotoService {

    byte[] getPhoto(Long idPerson);

    void generatePhotos(String query) throws UnsupportedEncodingException;

}
