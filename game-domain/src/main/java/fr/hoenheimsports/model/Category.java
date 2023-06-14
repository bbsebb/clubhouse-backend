package fr.hoenheimsports.model;

import java.util.Objects;

public record Category(String name) {
    public static final Category UNKNOWN = new Category("unknown");
    public Category {
        Objects.requireNonNull(name, "name should not be null");
    }
}


