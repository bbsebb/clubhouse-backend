package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Score;

public class ScoreBuilder {
    public static ScoreBuilder builder() {
        return new ScoreBuilder();
    }
    private int homeScore;
    private int visitingScore;

    public ScoreBuilder withHomeScore(int homeScore) {
        this.homeScore = homeScore;
        return this;
    }

    public ScoreBuilder withVisitingScore(int visitingScore) {
        this.visitingScore = visitingScore;
        return this;
    }

    public Score build() {
        return new Score(homeScore, visitingScore);
    }
}
