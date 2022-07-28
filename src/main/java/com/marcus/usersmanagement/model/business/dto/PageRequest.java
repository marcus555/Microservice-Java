package com.marcus.usersmanagement.model.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 5186126300138201203L;

    @ApiModelProperty(notes = "page", example = "0")
    private int page;
    @ApiModelProperty(notes = "size", example = "10")
    private int size;
    @ApiModelProperty(notes = "sort", example = "name")
    private String sort;
    @ApiModelProperty(notes = "order", example = "asc")
    private String order;
}
