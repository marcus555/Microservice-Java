package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.business.UserAccount;
import com.marcus.usersmanagement.model.business.dto.PageRequest;
import com.marcus.usersmanagement.model.business.dto.PageResponse;
import com.marcus.usersmanagement.model.business.dto.Photo;
import com.marcus.usersmanagement.model.business.dto.User;
import com.marcus.usersmanagement.model.entity.RoleEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;
import com.marcus.usersmanagement.service.interfaces.IUserManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author marcus
 * @apiNote Este servicio se encarga de realizar el manejo general de usuarios
 */
@Transactional
@Service
public class UserManagerService implements IUserManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAccountService userAccountService;

    /**
     * @param id User id
     * @return UserDTO
     */
    @Override
    public User getUserById(String id) {
        LOGGER.info("--- Obteniendo usuario por ID ---");
        return userService.getUserById(id);
    }

    /**
     * @param pageRequest Request to get the data on a pagination
     * @return PageResponseDTO<UserDTO>
     */
    @Override
    public PageResponse<User> getAllUsers(PageRequest pageRequest) {
        LOGGER.info("--- Obteniendo usuarios ---");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Set<Role> roles = new HashSet<>();

        String ROLE_PREFIX = "SCOPE_";
        if (authorities.contains(new SimpleGrantedAuthority(ROLE_PREFIX + Role.ROLE_ADMIN))) {
            roles.add(new Role(Role.ROLE_ADMIN));
        }
        roles.add(new Role(Role.ROLE_USER));

        Set<RoleEntity> roleEntities = roleService.convert2Entity(roles);
        return userService.getAllByRole(pageRequest, roleEntities);
    }

    /**
     * @param user DTO of the user to save (by defaul with ROLE_USER)
     * @return UserDTO
     */
    @Override
    public User createUser(User user) {
        LOGGER.info("--- Creando usuario ---");

        UserEntity userEntity = userService.convert2Entity(user);
        User result = userService.createUser(user);
        if (user.getPhoto() != null) {
            Photo photo = photoService.createPhoto(user.getPhoto(), userEntity);
            result.setPhoto(photo);
        }
        return result;
    }

    /**
     * @param user DTO of the user to update
     * @return UserDTO
     */
    @Override
    public User updateUser(String id, User user) {
        LOGGER.info("--- Actualizando usuario por ID ---");
        UserAccount userAccount;
        User result;
        Photo photo;

        result = userService.updateUser(id, user);
        if (result == null){
            return null;
        }

        if (user.getPhoto() != null) {
            if (user.getPhoto().getId() != null) {
                photoService.deactivatePhoto(user.getId());
                photo = photoService.updatePhoto(user.getPhoto().getId(), user.getPhoto());
                result.setPhoto(photo);
            } else {
                photoService.deactivatePhoto(user.getId());
                photo = photoService.createPhoto(user.getPhoto(), userService.convert2Entity(user));
                result.setPhoto(photo);
            }
        }

        userAccount = userAccountService.getUserAccountById(id);
        if (user.getStatus() == 200 && !userAccount.isEnabled()){
            userAccount.setEnabled(true);
            userAccountService.updateUserAccount(id, userAccount);
        } else if (user.getStatus() != 200 && userAccount.isEnabled()){
            userAccount.setEnabled(false);
            userAccountService.updateUserAccount(id, userAccount);
        }
        return result;
    }

    /**
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        LOGGER.info("--- Eliminando usuario por ID ---");
        userService.deleteUser(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User deactivateUser(String id) {
        LOGGER.info("--- Desactivando usuario por ID ---");
        User result = userService.deactivateUser(id);
        userAccountService.deactivateUserAccount(id);

        return result;
    }
}
