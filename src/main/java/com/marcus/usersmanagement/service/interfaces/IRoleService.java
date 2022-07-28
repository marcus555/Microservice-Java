package com.marcus.usersmanagement.service.interfaces;

import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.entity.RoleEntity;

import java.util.Set;

public interface IRoleService {

    Role getRoleById(String id);

    Role createRole(Role userAccount);

    Role updateRole(String id, Role userAccount);

    void deleteRole(String id);

    Role convert2DTO(RoleEntity roleEntity);

    RoleEntity convert2Entity(Role role);

    Set<Role> convert2DTO(Set<RoleEntity> roleEntities);

    Set<RoleEntity> convert2Entity(Set<Role> roles);
}
