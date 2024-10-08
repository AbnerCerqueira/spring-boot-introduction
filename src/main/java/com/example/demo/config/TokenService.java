package com.example.demo.config;

import com.example.demo.models.dtos.SubTokenDto;
import com.example.demo.models.dtos.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    private final ObjectMapper objectMapper;

    public TokenService(JwtEncoder jwtEncoder,
                        ObjectMapper objectMapper
    ) {
        this.jwtEncoder = jwtEncoder;
        this.objectMapper = objectMapper;
    }

    public LoginResponse generateToken(String username, String id) {
        String sub;
        try {
            sub = objectMapper.writeValueAsString(new SubTokenDto(id, username));
        } catch (Exception e) {
            throw new RuntimeException("Error serializing token!");
        }

        var now = Instant.now();
        var expiersIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("backend fodase")
                .issuedAt(now)
                .subject(sub)
                .expiresAt(now.plusSeconds(expiersIn))
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(token, expiersIn);

    }
}
