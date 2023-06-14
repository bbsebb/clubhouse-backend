package fr.hoenheimsports.model;

import java.util.Objects;
import java.util.UUID;

public record Club(String code, String name) {
    public static final Club UNKNOWN = new Club(UUID.fromString("00000000-0000-0000-0000-000000000000").toString(),"unknown");
    public Club {
        Objects.requireNonNull(code, "code should not be null");
        Objects.requireNonNull(name, "name should not be null");
    }
}
