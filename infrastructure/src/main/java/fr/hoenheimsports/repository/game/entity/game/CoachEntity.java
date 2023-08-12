package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;
@Entity
public class CoachEntity implements ContributorEntity {
    @Id
    private UUID id;

    @Column(unique = true)
    private String name;
    @Embedded
    private PhoneNumberEntity phoneNumber;

    public CoachEntity() {
    }

    public CoachEntity(UUID id, String name, PhoneNumberEntity phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public PhoneNumberEntity getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberEntity phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoachEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber);
    }
}
