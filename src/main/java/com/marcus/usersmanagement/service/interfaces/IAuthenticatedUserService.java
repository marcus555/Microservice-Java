package com.marcus.usersmanagement.service.interfaces;

public interface IAuthenticatedUserService {
    boolean hasId(String id);

    boolean hasRole(String role);

}
