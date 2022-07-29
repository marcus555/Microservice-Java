package com.marcus.usersmanagement.controller.interfaces;

import com.marcus.usersmanagement.common.config.SwaggerConfig;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = { SwaggerConfig.TAG_TOKEN })
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/token")
public interface ITokenController {
    @PostMapping("/")
    @ApiOperation(value = "Obtener token", notes = "Devuelve una respuesta del tipo JWT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "base64(user:password)", required = true, paramType = "header", dataTypeClass = String.class, example = "Basic TWFyY29zLk1hY2hvcnJvNTU1Om1hcmNvczEyMw==")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")})
    ResponseEntity<String> token(@ApiIgnore Authentication authentication);
}
