package fr.hoenheimsports.gamedomain.model;

import java.util.*;

public record Club(String code, String name, Set<Hall> halles) {
    public static final Club UNKNOWN = new Club(UUID.fromString("00000000-0000-0000-0000-000000000000").toString(),"unknown", Set.of(Hall.UNKNOWN));
    public Club {
        Objects.requireNonNull(code, "code should not be null");
        Objects.requireNonNull(name, "name should not be null");
        Objects.requireNonNull(halles,"halles should not be null");
        halles = Set.copyOf(halles);
    }
}
