package com.marcus.usersmanagement.model.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Photo implements Serializable {

    private static final long serialVersionUID = -1692795235673127532L;

    @ApiModelProperty(notes = "id", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private String id;

    @NotEmpty
    @ApiModelProperty(notes = "name of the file", example = "photo.jpg")
    private String name;

    @NotEmpty
    @ApiModelProperty(notes = "type of the file", example = "jpg")
    private String contentType;

    @NotEmpty
    @ApiModelProperty(notes = "size of the file in bytes", example = "3850")
    private int size;

    @NotEmpty
    @ApiModelProperty(notes = "data", example = "Image in base64")
    private String data;

    @ApiModelProperty(notes = "path of the file", example = "https://s3.amazonaws.com/marcus-users-management/photo.jpg")
    private String path;

    @ApiModelProperty(notes = "denotes if the current image will be the active one", example = "true")
    private boolean active;

    @ApiModelProperty(notes = "user which the photo belongs", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private User user;
}
