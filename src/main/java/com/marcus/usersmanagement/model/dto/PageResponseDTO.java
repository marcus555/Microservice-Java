package com.marcus.usersmanagement.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageResponseDTO<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 3282930711054899797L;

    private int pageSize;
    private long totalElements;
    private int totalPages;
    private int number;
    private List<T> content;
}
