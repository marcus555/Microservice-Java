package com.marcus.usersmanagement.common.util.data;

import com.marcus.usersmanagement.model.dto.UserDTO;
import com.marcus.usersmanagement.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO> {

    @Autowired
    private PhotoConverter photoConverter;

    /**
     * @param dto DTO to convert
     * @return User
     */
    @Override
    public User dto2Entity(UserDTO dto) {
        User result = new User();
        if (dto.getId() != null && !dto.getId().isEmpty()) {
            result.setId(dto.getId());
        } else {
            result.setId(UUID.randomUUID().toString());
        }
        result.setName(dto.getName());
        result.setLastName(dto.getLastName());
        if (!dto.getEmail().isEmpty()) {
            StringBuilder aux = new StringBuilder();
            for (String email : dto.getEmail()) {
                aux.append(email).append(",");
            }
            result.setEmail(aux.toString());
        }
        result.setGender(dto.getGender());
        result.setPhoto(photoConverter.dto2Entity(dto.getPhoto()));

        return result;

    }

    /**
     * @param entity Entity to convert
     * @return UserDTO
     */
    @Override
    public UserDTO entity2DTO(User entity) {
        UserDTO result = new UserDTO();

        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setLastName(entity.getLastName());
        String[] aux = entity.getEmail().split(",");
        for (String email : aux) {
            result.getEmail().add(email);
        }
        result.setGender(entity.getGender());
        result.setPhoto(photoConverter.entity2DTO(entity.getPhoto()));

        return result;
    }
}
