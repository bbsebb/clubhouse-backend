package fr.hoenheimsports.controller.user;

import fr.hoenheimsports.dto.user.LoginRequestDTO;
import fr.hoenheimsports.dto.user.UserDTO;
import fr.hoenheimsports.service.user.TokenService;
import fr.hoenheimsports.service.user.UserServiceApplication;
import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserServiceApplication userServiceApplication;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserServiceApplication userServiceApplication) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userServiceApplication = userServiceApplication;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.login(),loginRequestDTO.password());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        return this.tokenService.generateJWTToken(authentication);
    }

    @GetMapping ("/info")
    public ResponseEntity<Map<String, ?>> test(Authentication authentication) {
        try {
            UserDTO userDTO = this.userServiceApplication.displayUser(authentication.getName());
            return ResponseEntity.ok(Map.of("user", userDTO));
        } catch (UserAlreadyExistException uaee) {
            return new ResponseEntity<>(Map.of("error","User didn't find"), HttpStatus.UNAUTHORIZED);
        }
    }
}
