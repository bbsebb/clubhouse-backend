package fr.hoenheimsports.model;

import java.util.Objects;

public record Pool(String number, String name) {
    public static final Pool UNKNOWN = new Pool("unknown","unknown");
    public Pool {
        Objects.requireNonNull(number,"number should be not null");
        Objects.requireNonNull(name,"name should be not null");
    }
}
