package fr.hoenheimsports.dto.user;

import java.util.Set;

public record UserDTO(String id, String username, String email, Set<RoleDTO> roles) {
}
