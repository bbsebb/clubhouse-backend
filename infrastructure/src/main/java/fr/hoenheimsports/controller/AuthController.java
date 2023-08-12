package fr.hoenheimsports.controller;

import fr.hoenheimsports.configuration.RSAKeyProperties;
import fr.hoenheimsports.dto.user.LoginRequestDTO;
import fr.hoenheimsports.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.username(),loginRequestDTO.password());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        return this.tokenService.generateJWTToken(authentication);
    }

    @GetMapping ("/test")
    public Map<String,String> test(Authentication authentication) {
        return Map.of("test",authentication.getName());
    }
}
