package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public  class CompetitionEntity {
    @Id
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private PoolEntity pool;

    public CompetitionEntity() {
    }

    public CompetitionEntity(String name, PoolEntity pool) {
        this.name = name;
        this.pool = pool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PoolEntity getPool() {
        return pool;
    }

    public void setPool(PoolEntity pool) {
        this.pool = pool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitionEntity that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(pool, that.pool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pool);
    }
}
