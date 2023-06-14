package fr.hoenheimsports.model;

import java.util.Objects;
import java.util.UUID;

public record Halle(UUID id,String name,Address address,GlueAuthorization glueAuthorization) {
    public static final Halle UNKNOWN = new Halle(UUID.fromString("00000000-0000-0000-0000-000000000000"),"unknown",Address.UNKNOWN,GlueAuthorization.UNKNOWN);
    public Halle {
        Objects.requireNonNull(id,"id should be not null");
        Objects.requireNonNull(name,"name should be not null");
        Objects.requireNonNull(address,"address should be not null");
        Objects.requireNonNull(glueAuthorization,"glueAuthorization should be not null");
    }
}
