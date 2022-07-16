package com.marcus.usersmanagement.controller.interfaces;

import com.marcus.usersmanagement.common.config.SwaggerConfig;
import com.marcus.usersmanagement.model.dto.PageRequestDTO;
import com.marcus.usersmanagement.model.dto.PageResponseDTO;
import com.marcus.usersmanagement.model.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = { SwaggerConfig.TAG_USER })
@RequestMapping("/api/v1/user")
public interface IUserController {
    @GetMapping("/")
    @ApiOperation(value = "Obtener usuarios", notes = "Devuelve una lista de usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PageResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 401, message = "Jwt is expired"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error"),})
    ResponseEntity<PageResponseDTO<UserDTO>> getUsers(@RequestBody PageRequestDTO request);

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un usuario", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 401, message = "Jwt is expired"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error"),})
    ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") String id);

    @PostMapping("/")
    @ApiOperation(value = "Crear un usuario", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 401, message = "Jwt is expired"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error"),})
    ResponseEntity<UserDTO> createUser(@RequestBody UserDTO request);

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza un usuario", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 401, message = "Jwt is expired"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error"),})
    ResponseEntity<UserDTO> updateUser(@PathVariable("id") String id, @RequestBody UserDTO request);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un usuario", notes = "Devuelve una respuesta del tipo UserResponse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 401, message = "Jwt is expired"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error"),})
    ResponseEntity<UserDTO> deleteUser(@PathVariable("id") String id);

}

