package com.marcus.usersmanagement.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 7083926859688503681L;

    @Id
    private String authority;
}
