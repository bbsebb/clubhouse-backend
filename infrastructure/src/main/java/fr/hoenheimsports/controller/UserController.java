package fr.hoenheimsports.controller;

import fr.hoenheimsports.dto.user.AddRolesToUserDTOs;
import fr.hoenheimsports.dto.user.UserCreateDTO;
import fr.hoenheimsports.dto.user.UserDTO;
import fr.hoenheimsports.service.UserServiceApplication;
import fr.hoenheimsports.userdomain.exception.RoleNotFoundException;
import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceApplication userServiceApplication;

    public UserController(UserServiceApplication userServiceApplication) {
        this.userServiceApplication = userServiceApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> displayUsers() {
        return ResponseEntity.ok(this.userServiceApplication.displayUser());
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        Map<String,String> response;
        try {
            UserDTO userDTO = this.userServiceApplication.createUser(userCreateDTO);
            response = Map.of("userId", userDTO.id());
        } catch (UserAlreadyExistException uaee) {
            response = Map.of("error", "user with username : %s or email : %s exist already".formatted(userCreateDTO.username(),userCreateDTO.email()));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/addRole")
    public ResponseEntity<Map<String, String>> addRole(@PathVariable String id, @RequestBody AddRolesToUserDTOs addRoleToUserDTO) {
        Map<String,String> response;
        try {
            UserDTO userDTO = this.userServiceApplication.addRole(id,addRoleToUserDTO.ids());
            response = Map.of("userId",userDTO.id());
        } catch (UserNotFoundException | RoleNotFoundException e) {
            response = Map.of("error","user or role didn't find");
        }
        return ResponseEntity.ok(response);
    }



}
