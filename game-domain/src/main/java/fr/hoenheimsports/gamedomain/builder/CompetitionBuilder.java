package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CompetitionBuilder {
    public static CompetitionBuilder builder() {
        return new CompetitionBuilder();
    }
    private String name;
    private final List<Pool> pools = new ArrayList<>();


    public CompetitionBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CompetitionBuilder withPool(Consumer<PoolBuilder> poolBuilderFunction) {
        PoolBuilder poolBuilder = new PoolBuilder();
        poolBuilderFunction.accept(poolBuilder);
        this.pools.add(poolBuilder.build());
        return this;
    }

    public CompetitionBuilder withPool(Pool pool) {
        this.pools.add(pool);
        return this;
    }

    public Competition build() {
        return new Competition(name, pools);
    }
}
