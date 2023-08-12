package fr.hoenheimsports.userdomain.model;

import java.util.UUID;

public class Role {
    private final UUID id;
    private String roleName;

    public Role(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
