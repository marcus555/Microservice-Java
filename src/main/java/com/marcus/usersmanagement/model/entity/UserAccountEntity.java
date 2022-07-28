package com.marcus.usersmanagement.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_account", indexes = {
        @Index(name = "uniqueIndexUsername", columnList = "username", unique = true)
})
public class UserAccountEntity implements Serializable {

    private static final long serialVersionUID = -5889176653013669862L;

    @NotEmpty(message = "El id es requerido")
    @Id
    @Column(name = "user_id", length = 36)
    private String id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", length = 60)
    private String password;

    @ManyToMany
    private Set<RoleEntity> roles = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean enabled = true;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

}
