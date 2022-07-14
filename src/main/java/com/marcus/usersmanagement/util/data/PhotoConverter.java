package com.marcus.usersmanagement.util.data;

import com.marcus.usersmanagement.model.dto.PhotoDTO;
import com.marcus.usersmanagement.model.entity.Photo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhotoConverter extends AbstractConverter<Photo, PhotoDTO> {


    /**
     * @param dto DTO to convert
     * @return Photo
     */
    @Override
    public Photo dto2Entity(PhotoDTO dto) {
        Photo result = new Photo();
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

        return result;
    }

    /**
     * @param entity Entity to convert
     * @return PhotoDTO
     */
    @Override
    public PhotoDTO entity2DTO(Photo entity) {
        PhotoDTO result = new PhotoDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setContentType(entity.getContentType());
        result.setSize(entity.getSize());
        result.setData(entity.getData());
        if (entity.getPath() != null && !entity.getPath().isEmpty()) {
            result.setPath(entity.getPath());
        }

        return result;
    }
}
