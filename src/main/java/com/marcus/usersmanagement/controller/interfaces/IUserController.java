package com.marcus.usersmanagement.controller.interfaces;

import com.marcus.usersmanagement.common.config.SwaggerConfig;
import com.marcus.usersmanagement.model.business.dto.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = { SwaggerConfig.TAG_USER })
@CrossOrigin(origins = "*")
@PreAuthorize("isAuthenticated()") // Solo a modo de ejemplo, ya esta configurado en el securityConfig.
@RequestMapping("/api/v1/user")
public interface IUserController {
    @GetMapping("/")
    @ApiOperation(value = "Obtener usuarios", notes = "Devuelve una lista de usuarios")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PageResponse.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<PageResponse<User>> getUsers(@RequestBody PageRequest request);

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un usuario", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<User> getUser(@PathVariable(name = "id") String id);

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping("/")
    @ApiOperation(value = "Crear un usuario", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<User> createUser(@RequestBody User request);

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN') or @authenticatedUserService.hasId(#id)")
    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza un usuario", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User request);

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN') or @authenticatedUserService.hasId(#id)")
    @PutMapping("/account/{id}")
    @ApiOperation(value = "Actualiza cuenta de usuario (username/password)", notes = "Devuelve una respuesta del tipo UserDTO")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<String> updateUserAccount(@PathVariable("id") String id, @RequestBody UserAccountRequest request);

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @PutMapping("/credentials/{id}")
    @ApiOperation(value = "Actualiza credenciales de usuario (Roles)", notes = "Devuelve una respuesta de exito")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<String> updateUserCredentials(@PathVariable("id") String id, @RequestBody CredentialChangueRequest request);

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN') or @authenticatedUserService.hasId(#id)")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un usuario", notes = "Devuelve una respuesta del tipo UserResponse")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 401, message = "Not authenticated"),
            @ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 403, message = "Not authorized"),
            @ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<User> deleteUser(@PathVariable("id") String id);

}

