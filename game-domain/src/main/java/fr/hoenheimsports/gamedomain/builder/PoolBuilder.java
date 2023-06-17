package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Pool;

public class PoolBuilder {
    private String number;
    private String name;

    public PoolBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public PoolBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Pool build() {
        return new Pool(number, name);
    }
}
