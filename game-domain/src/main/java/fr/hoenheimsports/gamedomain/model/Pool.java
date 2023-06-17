package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;

public record Pool(String code, String name) {
    public static final Pool UNKNOWN = new Pool("unknown","unknown");
    public Pool {
        Objects.requireNonNull(code,"code should be not null");
        Objects.requireNonNull(name,"name should be not null");
    }
}
