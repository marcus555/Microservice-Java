package com.marcus.usersmanagement.service.interfaces;

import com.marcus.usersmanagement.model.dto.PageRequestDTO;
import com.marcus.usersmanagement.model.dto.PageResponseDTO;
import com.marcus.usersmanagement.model.dto.UserDTO;
import com.marcus.usersmanagement.model.entity.User;

public interface IUserService {
    PageResponseDTO<UserDTO> getAll(PageRequestDTO pageRequest);
    UserDTO getUserById(String id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(String id, UserDTO userDTO);

    void deleteUser(String id);

    UserDTO convert2DTO(User user);
}
