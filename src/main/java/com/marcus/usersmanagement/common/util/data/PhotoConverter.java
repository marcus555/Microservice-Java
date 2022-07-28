package com.marcus.usersmanagement.common.util.data;

import com.marcus.usersmanagement.model.business.dto.Photo;
import com.marcus.usersmanagement.model.entity.PhotoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhotoConverter extends AbstractConverter<PhotoEntity, Photo> {


    /**
     * @param dto DTO to convert
     * @return Photo
     */
    @Override
    public PhotoEntity dto2Entity(Photo dto) {
        PhotoEntity result = new PhotoEntity();

        if (dto.getId() != null && !dto.getId().isEmpty()) {
            result.setId(dto.getId());
        } else {
            result.setId(UUID.randomUUID().toString());
        }

        result.setName(dto.getName());
        result.setContentType(dto.getContentType());
        result.setSize(dto.getSize());
        result.setData(dto.getData());
        if (dto.getPath() != null && !dto.getPath().isEmpty()) {
            result.setPath(dto.getPath());
        }
        result.setActive(dto.isActive());

        return result;
    }

    /**
     * @param entity Entity to convert
     * @return PhotoDTO
     */
    @Override
    public Photo entity2DTO(PhotoEntity entity) {
        Photo result = new Photo();

        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setContentType(entity.getContentType());
        result.setSize(entity.getSize());
        result.setData(entity.getData());
        if (entity.getPath() != null && !entity.getPath().isEmpty()) {
            result.setPath(entity.getPath());
        }
        result.setActive(entity.isActive());

        return result;
    }
}
