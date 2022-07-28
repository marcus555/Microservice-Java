package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.common.util.data.UserConverter;
import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.business.dto.PageRequest;
import com.marcus.usersmanagement.model.business.dto.PageResponse;
import com.marcus.usersmanagement.model.business.dto.User;
import com.marcus.usersmanagement.model.entity.RoleEntity;
import com.marcus.usersmanagement.model.entity.UserAccountEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;
import com.marcus.usersmanagement.model.repository.UserRepository;
import com.marcus.usersmanagement.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 * @author marcus
 * @apiNote Este servicio se encarga de realizar las operaciones de CRUD de los usuarios
 */
@Transactional
@Service
public class UserService implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    PasswordEncoder encoder;

    /**
     * @param pageRequest Request to get the data on a pagination
     * @return PageResponseDTO<UserDTO>
     */
    public PageResponse<User> getAllByRole(PageRequest pageRequest, Set<RoleEntity> roleEntities) {
        try {
            Pageable pageable = org.springframework.data.domain.PageRequest.of(
                    pageRequest.getPage(),
                    pageRequest.getSize(),
                    Sort.by(getOrder(pageRequest.getOrder()), pageRequest.getSort())
            );

            Page<UserEntity> pageInfo = userRepository.findDistinctByAccountRolesIn(roleEntities, pageable);
            Page<User> aux = pageInfo.map(this::convert2DTO);

            PageResponse<User> result = new PageResponse<>();
            result.setNumber(pageInfo.getNumber());
            result.setPageSize(pageInfo.getSize());
            result.setTotalPages(aux.getTotalPages());
            result.setTotalElements(pageInfo.getTotalElements());
            result.setContent(aux.getContent());
            return result;
        } catch (Exception e) {
            LOGGER.error("Error al obtener los usuarios {}", pageRequest, e);
            return null;
        }
    }


    private Sort.Direction getOrder(String pageRequestOrder) {
        try {
            if (pageRequestOrder.equals("asc")) {
                return Sort.Direction.ASC;
            } else {
                return Sort.Direction.DESC;
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener el orden", e);
            return Sort.Direction.ASC;
        }
    }

    /**
     * @param id of the user
     * @return User
     */
    @Override
    public User getUserById(String id) {
        try {
            return userRepository.findById(id).map(this::convert2DTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Error al obtener el usuario por id {}", id, e);
            return null;
        }
    }

    /**
     * @param user DTO of the user to create (by defaul with ROLE_USER)
     * @return User created
     */
    @Override
    public User createUser(User user) {
        try {
            UserEntity userEntity = convert2Entity(user);
            UserAccountEntity userAcc = createUserAccount(userEntity);

            userEntity.setAccount(userAcc);
            UserEntity result = userRepository.saveAndFlush(userEntity);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al crear el usuario {}", user, e);
            return null;
        }
    }


    /**
     * @param user DTO of the user to update
     * @return User updated
     */
    @Override
    public User updateUser(String id, User user) {
        try {
            UserEntity result;
            UserEntity original;

            original = userRepository.findById(id).orElse(null);
            if (original == null){
                LOGGER.error("No se encontró el usuario");
                return null;
            }
            if (!id.equals(original.getId())){
                LOGGER.error("El id del usuario no coincide id: {} original: {}", id, original.getId());
                return null;
            }
            UserEntity userEntity = convert2Entity(user);
            result = userRepository.saveAndFlush(userEntity);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el usuario {}", user, e);
            return null;
        }
    }

    /**
     * @param id User id
     */
    @Override
    public void deleteUser(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el usuario {}", id, e);
        }
    }

    /**
     * @param id of the user to deactivate
     * @return User deactivated
     */
    @Override
    public User deactivateUser(String id) {
        try {
            UserEntity original;
            UserEntity result;

            original = userRepository.findById(id).orElse(null);
            if (original == null){
                LOGGER.error("No se encontró el usuario");
                return null;
            }
            original.setStatus(500);
            result = userRepository.saveAndFlush(original);
            return convert2DTO(result);
        } catch (Exception e) {
            LOGGER.error("Error al desactivar el usuario {}", id, e);
            return null;
        }
    }

    /**
     * @param userEntity UserEntity to convert
     * @return User
     */
    @Override
    public User convert2DTO(UserEntity userEntity) {
        return userConverter.entity2DTO(userEntity);
    }

    /**
     * @param user User to convert
     * @return UserEntity
     */
    @Override
    public UserEntity convert2Entity(User user) {
        return userConverter.dto2Entity(user);
    }

    private UserAccountEntity createUserAccount(UserEntity userEntity) {
        UserAccountEntity account = new UserAccountEntity();
        HashSet<RoleEntity> roleEntities = new HashSet<>();
        RoleEntity role = new RoleEntity();

        role.setAuthority(Role.ROLE_USER);
        roleEntities.add(role);

        account.setId(userEntity.getId());
        account.setUsername(userEntity.getName() + "." + userEntity.getLastName() + getThreeDigitsNumber());
        account.setPassword(encoder.encode(userEntity.getName().toLowerCase() + "123"));
        account.setRoles(roleEntities);
        account.setCreatedAt(LocalDateTime.now());
        account.setEnabled(true);
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);
        account.setEnabled(userEntity.getStatus() == 200);
        return account;
    }

    public int getThreeDigitsNumber() {
        double threeDigits = 100 + Math.random() * 900;
        return (int) threeDigits;
    }
}
