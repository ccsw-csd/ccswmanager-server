package com.ccsw.ccswmanager.photo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.ccswmanager.photo.model.PhotoEntity;

public interface PhotoRepository extends CrudRepository<PhotoEntity, Long> {

    @Override
    @Modifying
    @Query("delete from PhotoEntity")
    void deleteAll();
}
