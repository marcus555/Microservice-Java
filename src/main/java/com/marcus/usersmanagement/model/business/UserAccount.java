package com.marcus.usersmanagement.model.business;

import com.marcus.usersmanagement.model.entity.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserAccount implements UserDetails, CredentialsContainer {

    private String id;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private Collection<? extends GrantedAuthority> authorities;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private boolean enabled = true;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    @Override
    public void eraseCredentials() {
        password = null;
    }

    public void setAuthorities() {
        authorities = roles
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getAuthority()))
                .collect(Collectors.toList());
    }
}
