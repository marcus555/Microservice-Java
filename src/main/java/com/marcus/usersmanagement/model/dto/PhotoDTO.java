package com.marcus.usersmanagement.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDTO implements Serializable {

    private static final long serialVersionUID = -1692795235673127532L;

    @ApiModelProperty(notes = "id", example = "BFD73385-CD97-43AA-8779-FF5874322A12")
    private String id;

    @NonNull
    @ApiModelProperty(notes = "name of the file", example = "photo.jpg")
    private String name;

    @NonNull
    @ApiModelProperty(notes = "type of the file", example = "jpg")
    private String contentType;

    @NonNull
    @ApiModelProperty(notes = "size of the file in bytes", example = "3850")
    private int size;

    @NonNull
    @ApiModelProperty(notes = "data", example = "Image in base64")
    private String data;

    @ApiModelProperty(notes = "path of the file", example = "https://s3.amazonaws.com/marcus-users-management/photo.jpg")
    private String path;
}
