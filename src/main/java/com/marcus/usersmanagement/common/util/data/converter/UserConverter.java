package com.marcus.usersmanagement.common.util.data.converter;

import com.marcus.usersmanagement.model.business.dto.User;
import com.marcus.usersmanagement.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class UserConverter extends AbstractConverter<UserEntity, User> {

    /**
     * @param dto DTO to convert
     * @return User
     */
    @Override
    public UserEntity dto2Entity(User dto) {
        UserEntity result = new UserEntity();

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
            aux = aux.delete(aux.length() - 1, aux.length());
            result.setEmail(aux.toString());
        }

        result.setGender(dto.getGender());
        result.setStatus(dto.getStatus());

        return result;

    }

    /**
     * @param entity Entity to convert
     * @return UserDTO
     */
    @Override
    public User entity2DTO(UserEntity entity) {
        User result = new User();

        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setLastName(entity.getLastName());

        result.setEmail(new ArrayList<>());
        String[] aux = entity.getEmail().split(",");
        for (String email : aux) {
            result.getEmail().add(email);
        }

        result.setGender(entity.getGender());
        result.setStatus(entity.getStatus());

        return result;
    }
}
