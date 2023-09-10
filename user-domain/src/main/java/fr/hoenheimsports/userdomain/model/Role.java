package fr.hoenheimsports.userdomain.model;

import java.util.UUID;

public class Role {

    private UUID id;
    private String roleName;

    public Role() {
    }

    public Role(UUID id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
