package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Pool;

public class PoolBuilder {
    public static PoolBuilder builder() {
        return new PoolBuilder();
    }
    private String code;
    private String name;

    public PoolBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public PoolBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Pool build() {
        return new Pool(code, name);
    }
}
