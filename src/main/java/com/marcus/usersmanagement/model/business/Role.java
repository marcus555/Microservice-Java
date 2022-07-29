package com.marcus.usersmanagement.model.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    private String authority;

    public static String toString(Set<Role> roles) {
        StringBuilder result = new StringBuilder();
        for (Role role : roles) {
            result.append(role.getAuthority()).append(" ");
        }
        return result.toString();
    }
}
