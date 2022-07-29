package com.marcus.usersmanagement.service;

import com.marcus.usersmanagement.model.business.Role;
import com.marcus.usersmanagement.model.business.UserAccount;
import com.marcus.usersmanagement.service.interfaces.IAuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService implements IAuthenticatedUserService {
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public boolean hasId(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserAccount user = userAccountService.findByUsername(username);
        return user.getId().equals(id);
    }

    @Override
    public boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserAccount user = userAccountService.findByUsername(username);
        return user.getRoles().contains(new Role(role));
    }
}
