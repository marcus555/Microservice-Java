package com.marcus.usersmanagement.controller;

import com.marcus.usersmanagement.controller.interfaces.IUserController;
import com.marcus.usersmanagement.model.business.dto.*;
import com.marcus.usersmanagement.service.interfaces.IUserManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public IUserManagerService userManagerService;

    /**
     * @param request Request to get the data on a pagination
     * @return PageResponseDTO<UserDTO>
     */
    @Override
    public ResponseEntity<PageResponse<User>> getUsers(PageRequest request) {
        LOGGER.info("--- Paginación de usuarios---");
        PageResponse<User> result = userManagerService.getAllUsers(request);
        return ResponseEntity.ok(result);
    }

    /**
     * @param id User id
     * @return UserDTO
     */
    @Override
    public ResponseEntity<User> getUser(String id) {
        LOGGER.info("--- Obtención de usuario ---");
        User result = userManagerService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * @param request Request to create a new user
     * @return UserDTO
     */
    @Override
    public ResponseEntity<User> createUser(User request) {
        LOGGER.info("--- Creación de usuario ---");
        User result = userManagerService.createUser(request);
        return ResponseEntity.ok(result);
    }

    /**
     * @param id User id
     * @param request Request to update a user
     * @return UserDTO
     */
    @Override
    public ResponseEntity<User> updateUser(String id, User request) {
        LOGGER.info("--- Actualización de usuario ---");

        if (id.equals(request.getId())) {
            User result = userManagerService.updateUser(id, request);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @param id User id
     * @param request Request to update a userAccount
     * @return String ("OK" or "ERROR")
     */
    @Override
    public ResponseEntity<String> updateUserAccount(String id, UserAccountRequest request) {
        return null;
    }

    /**
     * @param id User id
     * @param request Request to update a user credentials
     * @return String ("OK" or "ERROR")
     */
    @Override
    public ResponseEntity<String> updateUserCredentials(String id, CredentialChangueRequest request) {
        return null;
    }

    /**
     * @param id User id
     * @return UserDTO
     */
    @Override
    public ResponseEntity<User> deleteUser(String id) {
        LOGGER.info("--- Eliminación de usuario ---");
        User result = userManagerService.getUserById(id);
        userManagerService.deleteUser(id);
        return ResponseEntity.ok(result);
    }
}
