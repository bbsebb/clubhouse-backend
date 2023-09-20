package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public  class PoolEntity {
    @Id
    private String code;
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CompetitionEntity competition;

    public PoolEntity() {
    }

    public PoolEntity(String code, String name,CompetitionEntity competition) {
        this.code = code;
        this.name = name;
        this.competition = competition;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionEntity competition) {
        this.competition = competition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PoolEntity that)) return false;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(competition, that.competition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, competition);
    }
}
