package fr.hoenheimsports.model;

import java.util.List;
import java.util.Objects;

public record Competition(String name, List<Pool> pools) {
    public static final Competition UNKNOWN = new Competition("unknown", List.of(Pool.UNKNOWN));

    /**
     *
     * @param name is Competition ID
     * @param pools
     */
    public Competition {
        Objects.requireNonNull(name,"name should be not null");
        List.copyOf(pools);
        Objects.requireNonNull(pools,"pool should be not null");
    }
}
