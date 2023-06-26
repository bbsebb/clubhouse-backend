package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.Embeddable;

import java.util.Objects;
@Embeddable
public class ScoreEntity {
    public static final ScoreEntity DEFAULT = new ScoreEntity(0, 0);
    private int homeScore;
    private int visitingScore;

    public ScoreEntity() {
    }

    public ScoreEntity(int homeScore, int visitingScore) {
        this.homeScore = homeScore;
        this.visitingScore = visitingScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getVisitingScore() {
        return visitingScore;
    }

    public void setVisitingScore(int visitingScore) {
        this.visitingScore = visitingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreEntity that)) return false;
        return homeScore == that.homeScore && visitingScore == that.visitingScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeScore, visitingScore);
    }
}
