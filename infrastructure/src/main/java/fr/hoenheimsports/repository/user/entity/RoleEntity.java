package fr.hoenheimsports.repository.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;
@Entity
public class RoleEntity {
    @Id
    private  UUID id;
    private String roleName;


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
