package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.model.dto.PageRequestDTO;
import com.marcus.usersmanagement.model.dto.PageResponseDTO;
import com.marcus.usersmanagement.model.dto.UserDTO;
import com.marcus.usersmanagement.model.entity.User;
import com.marcus.usersmanagement.model.repository.UserRepository;
import com.marcus.usersmanagement.service.interfaces.IUserService;
import com.marcus.usersmanagement.util.data.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    /**
     * @param pageRequest Request to get the data on a pagination
     * @return PageResponseDTO<UserDTO>
     */
    public PageResponseDTO<UserDTO> getAll(PageRequestDTO pageRequest) {
        LOGGER.info("--- Obteniendo usuarios ---");
        Pageable pageable = PageRequest.of(
                pageRequest.getPage(),
                pageRequest.getSize(),
                Sort.by(pageRequest.getSort(), pageRequest.getOrder())
        );

        Page<User> pageInfo = userRepository.findAll(pageable);
        Page<UserDTO> aux = pageInfo.map(this::convert2DTO);

        PageResponseDTO<UserDTO> result = new PageResponseDTO<>();
        result.setNumber(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotalPages(aux.getTotalPages());
        result.setTotalElements(pageInfo.getTotalElements());
        result.setContent(aux.getContent());
        return result;
    }

    /**
     * @param id User id
     * @return UserDTO
     */
    @Override
    public UserDTO getUserById(String id) {
        return userRepository.findById(id).map(this::convert2DTO).orElse(null);
    }

    /**
     * @param userDTO DTO of the user to save
     * @return UserDTO
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User result = userRepository.save(userConverter.dto2Entity(userDTO));
        return userConverter.entity2DTO(result);
    }

    /**
     * @param userDTO DTO of the user to update
     * @return UserDTO
     */
    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        User result;
        User original;

        original = userRepository.findById(id).orElse(null);
        if (original != null) {
            if (id.equals(userDTO.getId())) {
                result = userRepository.save(userConverter.dto2Entity(userDTO));
                return userConverter.entity2DTO(result);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param id User id
     */
    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    /**
     * @param user User to convert
     * @return UserDTO
     */
    @Override
    public UserDTO convert2DTO(User user) {
        return userConverter.entity2DTO(user);
    }
}
