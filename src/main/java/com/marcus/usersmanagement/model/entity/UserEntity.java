package com.marcus.usersmanagement.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user", indexes = {
        @Index(name = "indexUser", columnList = "name")
})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5974397189766063929L;

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
    @Column(name = "email", columnDefinition="MEDIUMTEXT")
    private String email;

    @NotEmpty(message = "El genero es requerido")
    @Column(name = "gender", length=10)
    private String gender;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PhotoEntity> photos = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserAccountEntity account;
}
