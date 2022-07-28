package com.marcus.usersmanagement.common.util.data;

import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter<RoleEntity, Role>{
    /**
     * @param dto A convertir a entidad
     * @return RoleEntity
     */
    @Override
    public RoleEntity dto2Entity(Role dto) {
        RoleEntity result = new RoleEntity();
        result.setAuthority(dto.getAuthority());
        return result;
    }

    /**
     * @param entity A convertir a un dto
     * @return Role
     */
    @Override
    public Role entity2DTO(RoleEntity entity) {
        Role result = new Role();
        result.setAuthority(entity.getAuthority());
        return result;
    }
}
