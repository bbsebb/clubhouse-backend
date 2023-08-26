package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;
import java.util.UUID;

public abstract class HallUser {
    private UUID id;
    private String username;
    private String email;

    public HallUser(UUID id, String username, String email) {
        Objects.requireNonNull(id, "id should be not null");
        Objects.requireNonNull(username, "username should be not null");
        Objects.requireNonNull(email, "email should be not null");
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
