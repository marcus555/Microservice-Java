package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.common.util.data.UserAccountConverter;
import com.marcus.usersmanagement.model.business.UserAccount;
import com.marcus.usersmanagement.model.entity.UserAccountEntity;
import com.marcus.usersmanagement.model.repository.UserAccountRepository;
import com.marcus.usersmanagement.service.interfaces.IUserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author marcus
 * @apiNote Este servicio se encarga de realizar las operaciones de CRUD de las cuentas de usuario
 */
@Transactional
@Service
public class UserAccountService implements IUserAccountService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountService.class);

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountConverter userAccountConverter;

    /**
     * @param username The username of the UserAcount to find
     * @return The UserAcount with the username provided
     */
    @Override
    public UserAccount findByUsername(String username) {
        try {
            UserAccount result = userAccountRepository.findByUsername(username).map(this::convert2DTO).get();
            result.setAuthorities();
            return result;
        } catch (Exception e) {
            LOGGER.error("Error al obtener la cuenta de usuario por username: {}", username, e);
            return null;
        }
    }

    /**
     * @param id of the UserAccount
     * @return UserAccount if exists, null otherwise
     */
    @Override
    public UserAccount getUserAccountById(String id) {
        try {
            UserAccount result = userAccountRepository.findById(id).map(this::convert2DTO).get();
            result.setAuthorities();
            return result;
        } catch (Exception e) {
            LOGGER.error("Error al obtener la cuenta de usuario por id: {}", id, e);
            return null;
        }
    }

    /**
     * @param userAccount UserAccount to be create
     * @return UserAccount created
     */
    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        try {
            UserAccountEntity result = userAccountRepository.saveAndFlush(convert2Entity(userAccount));
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al crear la cuenta de usuario: {}", userAccount, e);
            return null;
        }
    }

    /**
     * @param id id of the userAccount to be updated
     * @param userAccount userAccount to be updated
     * @return UserAccount updated
     */
    @Override
    public UserAccount updateUserAccount(String id, UserAccount userAccount) {
        try {
            UserAccountEntity result;
            UserAccountEntity original;

            original = userAccountRepository.findById(id).orElse(null);
            if (original == null){
                LOGGER.error("No se encontró la cuenta de usuario con id: {}", id);
                return null;
            }
            if (!id.equals(original.getId())) {
                LOGGER.error("El id de la cuenta de usuario no coincide con el id de la cuenta de usuario a actualizar: {}", id);
                return null;
            }
            result = userAccountRepository.saveAndFlush(convert2Entity(userAccount));
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar la cuenta de usuario: {}", userAccount, e);
            return null;
        }
    }

    /**
     * @param id of the userAccount to be deleted
     */
    @Override
    public void deleteUserAccount(String id) {
        try {
            userAccountRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar la cuenta de usuario: {}", id, e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UserAccount deactivateUserAccount(String id) {
        try {
            UserAccountEntity result;
            UserAccountEntity original;

            original = userAccountRepository.findById(id).orElse(null);
            if (original == null){
                LOGGER.error("No se encontró la cuenta de usuario con id: {}", id);
                return null;
            }
            original.setEnabled(false);
            original.setModifiedAt(LocalDateTime.now());

            result = userAccountRepository.saveAndFlush(original);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al desactivar la cuenta de usuario: {}", id, e);
            return null;
        }
    }

    /**
     * @param userAccountEntity UserAccountEntity to be converted
     * @return UserAccount converted
     */
    @Override
    public UserAccount convert2DTO(UserAccountEntity userAccountEntity) {
        return userAccountConverter.entity2DTO(userAccountEntity);
    }

    /**
     * @param userAccount UserAccount to be converted
     * @return UserAccountEntity converted
     */
    @Override
    public UserAccountEntity convert2Entity(UserAccount userAccount) {
        return userAccountConverter.dto2Entity(userAccount);
    }

    /**
     * @param username the username identifying the user whose data is required.
     * @return UserDetails for the user with the given username.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserAccount loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = findByUsername(username);
        return user;
    }
}
