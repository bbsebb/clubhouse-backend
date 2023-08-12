package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "street","city","postalCode"})})
public class HalleEntity {
    @Id
    private UUID id;
    private String name;
    @Embedded
    private AddressEntity address;
    @Enumerated(EnumType.STRING)
    private GlueAuthorizationEntity glueAuthorization;

    public HalleEntity() {
    }

    public HalleEntity(UUID id, String name, AddressEntity address, GlueAuthorizationEntity glueAuthorization) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.glueAuthorization = glueAuthorization;

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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public GlueAuthorizationEntity getGlueAuthorization() {
        return glueAuthorization;
    }

    public void setGlueAuthorization(GlueAuthorizationEntity glueAuthorization) {
        this.glueAuthorization = glueAuthorization;
    }


}
