package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.common.util.data.converter.PhotoConverter;
import com.marcus.usersmanagement.model.business.dto.Photo;
import com.marcus.usersmanagement.model.entity.PhotoEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;
import com.marcus.usersmanagement.model.repository.PhotoRepository;
import com.marcus.usersmanagement.service.interfaces.IPhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author marcus
 *
 * Este servicio se encarga de realizar las operaciones de CRUD de las fotos
 */
@Transactional
@Service
public class PhotoService implements IPhotoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoConverter photoConverter;

    /**
     * @param id user id
     * @return List of Photo from user id
     */
    @Override
    public List<Photo> getAll(String id) {
        try {
            List<PhotoEntity> result = photoRepository.findByUserId(id);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al obtener las fotos del usuario {}", id, e);
            return Collections.emptyList();
        }
    }

    /**
     * @param id User id
     * @return List of Photo
     */
    @Override
    public List<Photo> getAllByUserId(String id) {
        try {
            List<PhotoEntity> result = photoRepository.findByUserId(id);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al obtener las fotos del usuario {}", id, e);
            return Collections.emptyList();
        }
    }

    /**
     * @param id The id of the photo
     * @return PhotoDTO
     */
    @Override
    public Photo getPhotoById(String id) {
        try {
            return photoRepository.findById(id).map(this::convert2DTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Error al obtener la foto con id {}", id, e);
            return null;
        }
    }

    /**
     * @param id User id
     * @return Photo
     */
    @Override
    public Photo getPhotoByUserId(String id) {
        try {
            return convert2DTO(photoRepository.findByUserIdAndActive(id,true));
        } catch (Exception e) {
            LOGGER.error("Error al obtener la foto del usuario {}", id, e);
            return null;
        }
    }

    /**
     * @param photo The photo to save
     * @param userEntity User to be associated with the photo
     * @return The saved photo
     */
    @Override
    public Photo createPhoto(Photo photo, UserEntity userEntity) {
        try {
            PhotoEntity photoEntity = convert2Entity(photo);
            photoEntity.setUser(userEntity);

            PhotoEntity result = photoRepository.saveAndFlush(photoEntity);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al crear la foto {}", photo, e);
            return null;
        }
    }

    /**
     * @param id ID of the photo to be updated
     * @param photo The photo to update
     * @return The updated photo
     */
    @Override
    public Photo updatePhoto(String id, Photo photo) {
        try {
            PhotoEntity result;
            PhotoEntity original;

            original = photoRepository.findById(id).orElse(null);
            if (original == null) {
                LOGGER.error("No se encontró la foto");
                return null;
            }
            if (!id.equals(original.getId())) {
                LOGGER.error("El id de la foto no coincide id: {} original: {}", id, original.getId());
                return null;
            }
            PhotoEntity photoEntity = convert2Entity(photo);
            result = photoRepository.saveAndFlush(photoEntity);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar la foto {}", photo, e);
            return null;
        }

    }

    /**
     * @param id ID of the photo to be deleted
     */
    @Override
    public void deletePhoto(String id) {
        try {
            photoRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar la foto con id {}", id, e);
        }
    }

    @Override
    public void deactivatePhoto(String id) {
        try {
            PhotoEntity result;
            PhotoEntity original;

            original = photoRepository.findByUserIdAndActive(id,true);
            if (original == null) {
                LOGGER.error("No se encontró la foto");
                return;
            }
            if (!id.equals(original.getId())) {
                LOGGER.error("El id de la foto no coincide id: {} original: {}", id, original.getId());
                return;
            }
            original.setActive(false);
            result = photoRepository.saveAndFlush(original);
            convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al desactivar la foto con id {}", id, e);
        }
    }

    /**
     * @param photoEntity The photo to convert
     * @return The converted photo
     */
    @Override
    public Photo convert2DTO(PhotoEntity photoEntity) {
        return photoConverter.entity2DTO(photoEntity);
    }

    @Override
    public List<Photo> convert2DTO(List<PhotoEntity> photoEntities) {
        return photoConverter.entity2DTO(photoEntities);
    }

    /**
     * @param photo The photo to convert
     * @return The converted photo
     */
    @Override
    public PhotoEntity convert2Entity(Photo photo) {
        return photoConverter.dto2Entity(photo);
    }
}
