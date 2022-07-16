package com.marcus.usersmanagement.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "indexUser", columnList = "name"),
        @Index(name = "uniqueIndexUsername", columnList = "username", unique = true)
})
public class User implements UserDetails {

    @NotEmpty(message = "El id es requerido")
    @Id
    @Column(name = "id", length=36)
    private String id;

    @NotEmpty(message = "El nombre es requerido")
    @Column(name = "name", length=100)
    private String name;

    @NotEmpty(message = "El apellido es requerido")
    @Column(name = "last_name", length=100)
    private String lastName;

    @NotEmpty(message = "El email es requerido")
    @Column(name = "email", columnDefinition="TEXT")
    private String email;

    @NotEmpty(message = "El genero es requerido")
    @Column(name = "gender", length=10)
    private String gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Photo photo;

    // System Attribute
    @CreatedDate
//    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
//    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "username", length=20)
    private String username;

    @Column(name = "password", length=36)
    private String password;

    @ElementCollection
//    @Column(name = "authorities")
    private Set<Role> authorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
