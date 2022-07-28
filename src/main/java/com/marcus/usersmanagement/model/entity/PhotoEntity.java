package com.marcus.usersmanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "photo")
public class PhotoEntity implements Serializable {

    private static final long serialVersionUID = 768559265386690595L;

    @NotEmpty(message = "El id de la foto es requerido")
    @Id
    @Column(name = "id", length=36)
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

    @Column(name = "active")
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
