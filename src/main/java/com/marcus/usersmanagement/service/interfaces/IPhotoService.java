package com.marcus.usersmanagement.service.interfaces;

import com.marcus.usersmanagement.model.business.dto.Photo;
import com.marcus.usersmanagement.model.entity.PhotoEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;

import java.util.List;

public interface IPhotoService {
    List<Photo> getAll(String id);
    Photo getPhotoById(String id);

    Photo createPhoto(Photo photo, UserEntity userEntity);

    Photo updatePhoto(String id, Photo photo);

    void deletePhoto(String id);

    Photo convert2DTO(PhotoEntity photoEntity);

    List<Photo> convert2DTO(List<PhotoEntity> photoEntities);

    PhotoEntity convert2Entity(Photo photo);

    Photo deactivatePhoto(String id);
}
