package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public  class CompetitionEntity {
    @Id
    private String name;


    public CompetitionEntity() {
    }

    public CompetitionEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitionEntity that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
