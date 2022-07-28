package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.common.util.data.RoleConverter;
import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.entity.RoleEntity;
import com.marcus.usersmanagement.model.repository.RoleRepository;
import com.marcus.usersmanagement.service.interfaces.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author marcus
 * @apiNote Este servicio se encarga de realizar las operaciones de CRUD de los roles
 */
@Service
public class RoleService implements IRoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;


    /**
     * @param id role id
     * @return role
     */
    @Override
    public Role getRoleById(String id) {
        try {
            return roleRepository.findById(id).map(this::convert2DTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Error al obtener el rol por id: {}", id, e);
            return null;
        }
    }

    /**
     * @param role to create
     * @return created role
     */
    @Override
    public Role createRole(Role role) {
        try {
            RoleEntity result = roleRepository.saveAndFlush(convert2Entity(role));
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al crear el rol: {}", role, e);
            return null;
        }
    }

    /**
     * @param id role id
     * @param roleEntity role to update
     * @return updated role
     */
    @Override
    public Role updateRole(String id, Role roleEntity) {
        try {
            RoleEntity result;
            RoleEntity original;

            original = roleRepository.findById(id).orElse(null);
            if (original != null) {
                if (id.equals(original.getAuthority())) {
                    result = roleRepository.saveAndFlush(convert2Entity(roleEntity));
                    return convert2DTO(result);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el rol: {}", roleEntity, e);
            return null;
        }
    }

    /**
     * @param id role id to delete
     */
    @Override
    public void deleteRole(String id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el rol: {}", id, e);
        }
    }

    /**
     * @param roleEntity RoleEntity to convert
     * @return Role
     */
    @Override
    public Role convert2DTO(RoleEntity roleEntity) {
        return roleConverter.entity2DTO(roleEntity);
    }

    /**
     * @param role to convert
     * @return RoleEntity
     */
    @Override
    public RoleEntity convert2Entity(Role role) {
        return roleConverter.dto2Entity(role);
    }

    /**
     * @param roleEntities RoleEntity to convert
     * @return Role
     */
    @Override
    public Set<Role> convert2DTO(Set<RoleEntity> roleEntities) {
        return roleConverter.entity2DTO(roleEntities);
    }

    /**
     * @param roles Role to convert
     * @return RoleEntity
     */
    @Override
    public Set<RoleEntity> convert2Entity(Set<Role> roles) {
        return roleConverter.dto2Entity(roles);
    }
}
