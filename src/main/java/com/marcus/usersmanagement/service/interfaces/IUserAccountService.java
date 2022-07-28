package com.marcus.usersmanagement.service.interfaces;

import com.marcus.usersmanagement.model.business.UserAccount;
import com.marcus.usersmanagement.model.entity.UserAccountEntity;

public interface IUserAccountService {
    UserAccount findByUsername(String username);

    UserAccount getUserAccountById(String id);

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount updateUserAccount(String id, UserAccount userAccount);

    void deleteUserAccount(String id);

    UserAccount deactivateUserAccount(String id);

    UserAccount convert2DTO(UserAccountEntity userAccountEntity);

    UserAccountEntity convert2Entity(UserAccount userAccount);
}
