package com.marcus.usersmanagement.common.util.data.converter;

import com.marcus.usersmanagement.model.business.UserAccount;
import com.marcus.usersmanagement.model.entity.UserAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAccountConverter extends AbstractConverter<UserAccountEntity, UserAccount> {

    @Autowired
    private RoleConverter roleConverter;

    /**
     * @param dto A convertir a entidad
     * @return UserAccountEntity
     */
    @Override
    public UserAccountEntity dto2Entity(UserAccount dto) {
        UserAccountEntity result = new UserAccountEntity();
        result.setId(dto.getId());
        result.setUsername(dto.getUsername());
        result.setPassword(dto.getPassword());
        result.setRoles(roleConverter.dto2Entity(dto.getRoles()));
        result.setCreatedAt(dto.getCreatedAt());
        result.setModifiedAt(dto.getModifiedAt());
        result.setEnabled(dto.isEnabled());
        result.setAccountNonExpired(dto.isAccountNonExpired());
        result.setAccountNonLocked(dto.isAccountNonLocked());
        result.setCredentialsNonExpired(dto.isCredentialsNonExpired());

        return result;
    }

    /**
     * @param entity A convertir a un dto
     * @return UserAccount
     */
    @Override
    public UserAccount entity2DTO(UserAccountEntity entity) {
        UserAccount result = new UserAccount();
        result.setId(entity.getId());
        result.setUsername(entity.getUsername());
        result.setPassword(entity.getPassword());
        result.setRoles(roleConverter.entity2DTO(entity.getRoles()));
        result.setAuthorities();
        result.setCreatedAt(entity.getCreatedAt());
        result.setModifiedAt(entity.getModifiedAt());
        result.setEnabled(entity.isEnabled());
        result.setAccountNonExpired(entity.isAccountNonExpired());
        result.setAccountNonLocked(entity.isAccountNonLocked());
        result.setCredentialsNonExpired(entity.isCredentialsNonExpired());

        return result;
    }
}
