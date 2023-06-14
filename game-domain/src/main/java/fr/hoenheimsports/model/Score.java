package fr.hoenheimsports.model;

public record Score(int homeScore, int visitingScore) {
    public static final Score DEFAULT = new Score(0,0);
    public Score {
        if(homeScore<0 || visitingScore<0) {
            throw new IllegalArgumentException("Score must be equal or greater than 0");
        }
    }
}
