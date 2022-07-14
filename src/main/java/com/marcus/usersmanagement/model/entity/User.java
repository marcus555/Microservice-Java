package com.marcus.usersmanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {

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

}
