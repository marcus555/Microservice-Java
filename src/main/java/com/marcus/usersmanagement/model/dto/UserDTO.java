package com.marcus.usersmanagement.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7109844518846766277L;

    @ApiModelProperty(notes = "id", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private String id;

    @NonNull
    @ApiModelProperty(notes = "name", example = "Marcos Raul")
    private String name;

    @NonNull
    @ApiModelProperty(notes = "lastName", example = "Machorro")
    private String lastName;

    @NonNull
    @ApiModelProperty(notes = "email", example = "marcos.phoenix.555@gmail.com")
    private ArrayList<String> email;

    @NonNull
    @ApiModelProperty(notes = "gender", example = "male")
    private String gender;

    @ApiModelProperty(notes = "photo", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private PhotoDTO photo;
}
