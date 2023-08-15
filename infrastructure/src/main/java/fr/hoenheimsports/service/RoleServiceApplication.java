package fr.hoenheimsports.service;

import fr.hoenheimsports.dto.user.RoleDTO;
import fr.hoenheimsports.service.mapper.RoleMapper;
import fr.hoenheimsports.userdomain.api.RoleDisplay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceApplication {
    private final RoleDisplay roleDisplay;
    private final RoleMapper roleMapper;


    public RoleServiceApplication(RoleDisplay roleDisplay, RoleMapper roleMapper) {
        this.roleDisplay = roleDisplay;
        this.roleMapper = roleMapper;
    }

    public List<RoleDTO> displayRoles() {
        return this.roleDisplay.findAll().stream().map(this.roleMapper::roleToRoleDTO).collect(Collectors.toList());
    }
}
