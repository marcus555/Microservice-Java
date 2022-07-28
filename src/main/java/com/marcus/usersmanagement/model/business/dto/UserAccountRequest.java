package com.marcus.usersmanagement.model.business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class UserAccountRequest implements Serializable {
    private static final long serialVersionUID = 7260775648734279159L;

    private String username;

    private String password;

    private int status;
}
