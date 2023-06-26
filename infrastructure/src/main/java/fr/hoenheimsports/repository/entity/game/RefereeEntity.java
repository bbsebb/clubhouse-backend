package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class RefereeEntity implements ContributorEntity {
    public static final RefereeEntity UNKNOWN = new RefereeEntity(UUID.fromString("00000000-0000-0000-0000-000000000000"), "unknown");
    @Id
    private UUID id;
    @Column(unique = true)
    private String name;

    public RefereeEntity() {
    }

    public RefereeEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefereeEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
