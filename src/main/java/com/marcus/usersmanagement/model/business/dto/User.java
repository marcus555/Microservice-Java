package com.marcus.usersmanagement.model.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7109844518846766277L;

    @ApiModelProperty(notes = "id", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private String id;

    @NotEmpty
    @ApiModelProperty(notes = "name", example = "Marcos Raul")
    private String name;

    @NotEmpty
    @ApiModelProperty(notes = "lastName", example = "Machorro")
    private String lastName;

    @NotEmpty
    @ApiModelProperty(notes = "email", example = "marcos.phoenix.555@gmail.com")
    private ArrayList<String> email;

    @NotEmpty
    @ApiModelProperty(notes = "gender", example = "male")
    private String gender;

    @ApiModelProperty(notes = "status", example = "200")
    private int status;

    @ApiModelProperty(notes = "photo", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private Photo photo;
}
