package com.marcus.usersmanagement.model.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageResponse<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 3282930711054899797L;

    @ApiModelProperty(notes = "pageSize", example = "10")
    private int pageSize;
    @ApiModelProperty(notes = "totalElements", example = "1")
    private long totalElements;
    @ApiModelProperty(notes = "totalPages", example = "1")
    private int totalPages;
    @ApiModelProperty(notes = "number", example = "0")
    private int number;
    @ApiModelProperty(notes = "id", example = "[{\"id\":\"BFD73385-CD97-43AA-8779-FF5874322A12\",\"name\":\"Marcos Raul\",\"lastName\":\"Machorro\",\"email\":\"marcos.phoenix.555@gmail.com\"}]")
    private List<T> content;
}
