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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    /**
     * @param id User id
     * @return User
     */
    @Override
    public User getUserById(String id) {
        LOGGER.info("--- Obteniendo usuario por ID ---");
        User result = userService.getUserById(id);
        Photo photo = photoService.getPhotoByUserId(id);
        if (photo != null) {
            result.setPhoto(photo);
        }
        return result;
    }

    /**
     * @param pageRequest Request to get the data on a pagination
     * @return PageResponse<User>
     */
    @Override
    public PageResponse<User> getAllUsers(PageRequest pageRequest) {
        LOGGER.info("--- Obteniendo usuarios ---");

        Set<Role> roles = new HashSet<>();
        if (authenticatedUserService.hasRole(Role.ROLE_ADMIN)) {
            roles.add(new Role(Role.ROLE_ADMIN));
        }
        roles.add(new Role(Role.ROLE_USER));

        Set<RoleEntity> roleEntities = roleService.convert2Entity(roles);
        return userService.getAllByRole(pageRequest, roleEntities);
    }

    /**
     * @param user  of the user to save (by defaul with ROLE_USER)
     * @return User
     */
    @Override
    public User createUser(User user) {
        LOGGER.info("--- Creando usuario ---");

        UserEntity userEntity = userService.createUser(user);
        User result = userService.convert2DTO(userEntity);
        result.setAccount(userAccountService.convert2DTO(userEntity.getAccount()));
        if (user.getPhoto() != null) {
            Photo photo = photoService.createPhoto(user.getPhoto(), userEntity);
            result.setPhoto(photo);
        }
        return result;
    }

    /**
     * @param user  of the user to update
     * @return User
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
                photoService.deactivatePhoto(id);
                photo = photoService.updatePhoto(user.getPhoto().getId(), user.getPhoto());
                result.setPhoto(photo);
            } else {
                photoService.deactivatePhoto(id);
                photo = photoService.createPhoto(user.getPhoto(), userService.convert2Entity(user));
                result.setPhoto(photo);
            }
        }
        result.setPhoto(photoService.getPhotoByUserId(id));

        userAccount = userAccountService.getUserAccountById(id);
        if (user.getStatus() == 200 && !userAccount.isEnabled()){
            userAccount.setEnabled(true);
            userAccountService.updateUserAccount(id, userAccount);
        } else if (user.getStatus() != 200 && userAccount.isEnabled()){
            userAccount.setEnabled(false);
            userAccountService.updateUserAccount(id, userAccount);
        }
        result.setAccount(userAccount);
        return result;
    }

    /**
     * @param id User id to delete
     */
    @Override
    public void deleteUser(String id) {
        LOGGER.info("--- Eliminando usuario por ID ---");
        userService.deleteUser(id);
    }

    /**
     * @param id User id to activate
     * @return User
     */
    @Override
    public User deactivateUser(String id) {
        LOGGER.info("--- Desactivando usuario por ID ---");
        User result = userService.deactivateUser(id);
        userAccountService.deactivateUserAccount(id);

        return result;
    }
}
