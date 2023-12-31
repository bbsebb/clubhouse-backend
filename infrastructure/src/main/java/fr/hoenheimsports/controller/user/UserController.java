package fr.hoenheimsports.controller.user;

import fr.hoenheimsports.dto.user.AddRolesToUserDTOs;
import fr.hoenheimsports.dto.user.UserCreateDTO;
import fr.hoenheimsports.dto.user.UserDTO;
import fr.hoenheimsports.service.user.UserServiceApplication;
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
        UserDTO userDTO = this.userServiceApplication.createUser(userCreateDTO);
        return ResponseEntity.ok(Map.of("userId", userDTO.id()));
    }

    @PostMapping("/{id}/addRole")
    public ResponseEntity<Map<String, String>> addRole(@PathVariable String id, @RequestBody AddRolesToUserDTOs addRoleToUserDTO) {
        UserDTO userDTO = this.userServiceApplication.addRole(id,addRoleToUserDTO.ids());
        return ResponseEntity.ok(Map.of("userId",userDTO.id()));
    }

    @GetMapping("/{id}/activate")
    public ResponseEntity<Map<String, String>> activateUser(@PathVariable String id) {
        UserDTO userDTO = this.userServiceApplication.activateUser(id);
        return ResponseEntity.ok(Map.of("userId",userDTO.id()));
    }





}
