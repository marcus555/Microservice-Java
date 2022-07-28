package com.marcus.usersmanagement.controller.interfaces;

import com.marcus.usersmanagement.common.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = { SwaggerConfig.TAG_TOKEN })
@RequestMapping("/api/v1/token")
public interface ITokenController {
    @PostMapping("/")
    @ApiOperation(value = "Obtener token", notes = "Devuelve una respuesta del tipo JWT")
    ResponseEntity<String> token(Authentication authentication);
}
