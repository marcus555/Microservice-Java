package com.marcus.usersmanagement.startup;

import com.marcus.usersmanagement.common.util.data.PhotoConverter;
import com.marcus.usersmanagement.common.util.data.RoleConverter;
import com.marcus.usersmanagement.common.util.data.UserConverter;
import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.business.dto.Photo;
import com.marcus.usersmanagement.model.business.dto.User;
import com.marcus.usersmanagement.model.entity.PhotoEntity;
import com.marcus.usersmanagement.model.entity.RoleEntity;
import com.marcus.usersmanagement.model.entity.UserEntity;
import com.marcus.usersmanagement.model.entity.UserAccountEntity;
import com.marcus.usersmanagement.model.repository.PhotoRepository;
import com.marcus.usersmanagement.model.repository.RoleRepository;
import com.marcus.usersmanagement.model.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@NoArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Value("${resources.image.shepard-face}")
    private String imgFace;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoConverter photoConverter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.count() == 0) {
            LOGGER.info("----------- Creando Roles -----------");
            List<RoleEntity> roleEntities = getInitRoles();
            roleRepository.saveAllAndFlush(roleEntities);
            LOGGER.info("Roles: {}", roleEntities);
        }
        if (userRepository.count() == 0) {
            LOGGER.info("----------- Creando Usuarios -----------");
            List<User> usersDto = getInitUsers();
            for (User user : usersDto) {

                UserEntity userEntity = userConverter.dto2Entity(user);
                UserAccountEntity userAcc = getInitUserAccount(userEntity);
                PhotoEntity photoEntity = photoConverter.dto2Entity(user.getPhoto());

                userEntity.setAccount(userAcc);
                userRepository.saveAndFlush(userEntity);

                photoEntity.setUser(userEntity);
                photoRepository.saveAndFlush(photoEntity);
                LOGGER.info("Usuario: {}", userEntity.getAccount().getUsername());
                LOGGER.info("Contraseña: {}", userEntity.getAccount().getPassword());
                LOGGER.info("Roles: {}", userEntity.getAccount().getRoles());
            }
        }
    }

    private List<RoleEntity> getInitRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_ADMIN"));
        roles.add(new Role("ROLE_USER"));
        return roleConverter.dto2Entity(roles);
    }

    private List<User> getInitUsers() {
        List<User> usersDto = new ArrayList<>();
        User user = new User();

        user.setName("Marcos");
        user.setLastName("Machorro");
        user.setEmail(new ArrayList<>(Arrays.asList("admin@mail.com","user@mail.com")));
        user.setGender("male");
        user.setPhoto(getInitPhoto());
        user.setStatus(200);
        usersDto.add(user);

        return usersDto;
    }

    private Photo getInitPhoto(){
        Photo photo = new Photo();

        photo.setName("shepard.jpg");
        photo.setContentType("image/jpeg");
        photo.setSize(47650);
        photo.setData(imgFace);
        photo.setPath("");

        return photo;
    }

    private UserAccountEntity getInitUserAccount(UserEntity userEntity) {
        UserAccountEntity account = new UserAccountEntity();
        HashSet<RoleEntity> roleEntities = new HashSet<>(getInitRoles());

        account.setId(userEntity.getId());
        account.setUsername(userEntity.getName() + "." + userEntity.getLastName() + 555);
        account.setPassword(encoder.encode(userEntity.getName().toLowerCase() + "123"));
        account.setRoles(roleEntities);
        account.setCreatedAt(LocalDateTime.now());
        account.setEnabled(userEntity.getStatus() == 200);
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);
        return account;
    }

}
