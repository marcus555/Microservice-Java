package com.marcus.usersmanagement.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PageRequestDTO implements Serializable {

    private static final long serialVersionUID = 5186126300138201203L;

    private int page;
    private int size;
    private String sort;
    private String order;
}
