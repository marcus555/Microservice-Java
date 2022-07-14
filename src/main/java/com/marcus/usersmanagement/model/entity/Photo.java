package com.marcus.usersmanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Photo {

    @NotEmpty(message = "El id de la foto es requerido")
    @Id
    @Column(name = "user_id", length=36)
    private String id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Column(name = "name", length=100)
    private String name;

    @NotEmpty(message = "contenType no puede estar vacío")
    @Column(name = "content_type", length=50)
    private String contentType;

    @NotNull(message = "size no puede estar vacío")
    @Column(name = "size")
    private int size;

    @NotEmpty(message = "data no puede estar vacío")
    @Column(name = "data", columnDefinition="MEDIUMTEXT")
    private String data;

    @Column(name = "path", length=1024)
    private String path;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
