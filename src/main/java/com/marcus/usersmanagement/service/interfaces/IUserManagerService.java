package com.marcus.usersmanagement.service.interfaces;

import com.marcus.usersmanagement.model.business.dto.PageRequest;
import com.marcus.usersmanagement.model.business.dto.PageResponse;
import com.marcus.usersmanagement.model.business.dto.User;

public interface IUserManagerService {

    User getUserById(String id);

    PageResponse<User> getAllUsers(PageRequest pageRequest);

    User createUser(User user);

    User updateUser(String id, User user);

    void deleteUser(String id);

    User deactivateUser(String id);

}
