package fr.hoenheimsports.repository.user.entity;

import fr.hoenheimsports.userdomain.model.Role;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
public class UserEntity {

    @Id
    private UUID id;
    @Column(unique = true)
    private String username;

    private String password;
    @Column(unique = true)
    private String email;
    @ManyToMany
    private List<RoleEntity> roles;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
