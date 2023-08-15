package fr.hoenheimsports.controller;

import fr.hoenheimsports.service.RoleServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    public RoleController(RoleServiceApplication roleServiceApplication) {
        this.roleServiceApplication = roleServiceApplication;
    }

    private final RoleServiceApplication roleServiceApplication;

    @GetMapping("")
    public ResponseEntity<Map<String,?>> displayRoles() {
       var response = Map.of("roles", this.roleServiceApplication.displayRoles());
       return ResponseEntity.ok(response);
    }
}
