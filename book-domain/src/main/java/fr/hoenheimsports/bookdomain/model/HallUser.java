package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * HallUser class is the main class of the domain. It represents a user of the application. It contains the following attributes:
 * <ul>
 *      <li>id: the unique identifier of the user</li>
 *      <li>username: the username of the user</li>
 *      <li>email: the email of the user</li>
 * </ul>
 */
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
