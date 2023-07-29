package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;

public record Category(String name,int age, boolean isMaxAge) {
    public static final Category UNKNOWN = new Category("unknown",0,true);
    public Category {
        Objects.requireNonNull(name, "name should not be null");
        if(age<0) {
            throw new IllegalArgumentException("age shoud be greater than 0");
        }
    }
}


