package com.marcus.usersmanagement.controller;

import com.marcus.usersmanagement.controller.interfaces.ITokenController;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class TokenController implements ITokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    JwtEncoder encoder;

    @Value("${jwt.private.key}")
    private RSAPrivateKey rsaPrivateKey;

    @Override
    public ResponseEntity<String> token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        //Alternative
        JWTClaimsSet claimsNimbus = new JWTClaimsSet.Builder()
                .issuer("self")
                .issueTime(new Date(now.toEpochMilli()))
                .expirationTime(new Date(now.plusSeconds(expiry).toEpochMilli()))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).build();
        SignedJWT jwt = new SignedJWT(header, claimsNimbus);
        String signJwt = sign(jwt).serialize();
        LOGGER.debug("--- Nimbus ---");
        LOGGER.debug("JWT: {}", signJwt);


        String result = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        LOGGER.debug("--- Spring Security Oauth2 ---");
        LOGGER.debug("JWT: {}", result);

        return ResponseEntity.ok(result);
    }

    SignedJWT sign(SignedJWT jwt) {
        try {
            jwt.sign(new RSASSASigner(rsaPrivateKey));
            return jwt;
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
