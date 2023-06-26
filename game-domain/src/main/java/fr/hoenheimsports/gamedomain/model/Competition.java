package fr.hoenheimsports.gamedomain.model;

import java.util.List;
import java.util.Objects;

public record Competition(String name, Pool pool) {
    public static final Competition UNKNOWN = new Competition("unknown", Pool.UNKNOWN);

    /**
     *
     * @param name is Competition ID
     * @param pool
     */
    public Competition {
        Objects.requireNonNull(name,"name should be not null");
        Objects.requireNonNull(pool,"pool should be not null");
    }
}
