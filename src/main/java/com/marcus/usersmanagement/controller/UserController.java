package com.marcus.usersmanagement.controller;

import com.marcus.usersmanagement.controller.interfaces.IUserController;
import com.marcus.usersmanagement.model.dto.PageRequestDTO;
import com.marcus.usersmanagement.model.dto.PageResponseDTO;
import com.marcus.usersmanagement.model.dto.UserDTO;
import com.marcus.usersmanagement.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserController implements IUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public IUserService userService;

    /**
     * @param request Request to get the data on a pagination
     * @return PageResponseDTO<UserDTO>
     */
    @Override
    public ResponseEntity<PageResponseDTO<UserDTO>> getUsers(PageRequestDTO request) {
        LOGGER.info("--- Obteniendo usuarios ---");
        PageResponseDTO<UserDTO> result = userService.getAll(request);
        return ResponseEntity.ok(result);
    }

    /**
     * @param id User id
     * @return UserDTO
     */
    @Override
    public ResponseEntity<UserDTO> getUser(String id) {
        LOGGER.info("--- Obteniendo usuario por ID ---");
        UserDTO result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * @param request Request to create a new user
     * @return UserDTO
     */
    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO request) {
        LOGGER.info("--- Creando usuario ---");
        UserDTO result = userService.createUser(request);
        return ResponseEntity.ok(result);
    }

    /**
     * @param id User id
     * @param request Request to update a user
     * @return UserDTO
     */
    @Override
    public ResponseEntity<UserDTO> updateUser(String id, UserDTO request) {
        LOGGER.info("--- Actualizando usuario por ID ---");

        if (id.equals(request.getId())) {
            UserDTO result = userService.updateUser(id, request);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @param id User id
     * @return UserDTO
     */
    @Override
    public ResponseEntity<UserDTO> deleteUser(String id) {
        LOGGER.info("--- Eliminando usuario por ID ---");
        UserDTO result = userService.getUserById(id);
        userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }
}
