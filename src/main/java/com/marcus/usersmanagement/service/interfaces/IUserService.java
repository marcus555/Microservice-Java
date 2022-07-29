package com.marcus.usersmanagement.service.interfaces;

import com.marcus.usersmanagement.model.business.dto.PageRequest;
import com.marcus.usersmanagement.model.business.dto.PageResponse;
import com.marcus.usersmanagement.model.business.dto.User;
import com.marcus.usersmanagement.model.entity.RoleEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;

import java.util.Set;

public interface IUserService {
    PageResponse<User> getAllByRole(PageRequest pageRequest, Set<RoleEntity> roleEntities);
    User getUserById(String id);

    UserEntity createUser(User user);

    User updateUser(String id, User user);

    void deleteUser(String id);

    User deactivateUser(String id);

    User convert2DTO(UserEntity userEntity);

    UserEntity convert2Entity(User user);
}
