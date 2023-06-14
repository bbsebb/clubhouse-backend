package fr.hoenheimsports.model;

import java.util.Objects;
import java.util.UUID;

public final class Team {
    public static final Team UNKNOWN = new Team(UUID.fromString("00000000-0000-0000-0000-000000000000"), Category.UNKNOWN, Gender.UNKNOWN, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
    private final UUID id;
    private final Category category;
    private final Gender gender;
    private final int number;
    private final Club club;
    private TeamsColor teamsColor;
    private Coach coach;

    public Team(UUID id, Category category, Gender gender, int number, Club club, TeamsColor teamsColor, Coach coach) {
        Objects.requireNonNull(id, "id should not be null");
        Objects.requireNonNull(category, "category should not be null");
        Objects.requireNonNull(gender, "gender should not be null");
        if (number < 1) {
            throw new IllegalArgumentException("Team number should be greater than 0");
        }
        Objects.requireNonNull(club, "club should not be null");
        Objects.requireNonNull(teamsColor, "teamsColor should not be null");
        Objects.requireNonNull(coach, "name should not be null");
        this.id = id;
        this.category = category;
        this.gender = gender;
        this.number = number;
        this.club = club;
        this.teamsColor = teamsColor;
        this.coach = coach;
    }

    public UUID id() {
        return id;
    }

    public Category category() {
        return category;
    }

    public Gender gender() {
        return gender;
    }

    public int number() {
        return number;
    }

    public Club club() {
        return club;
    }

    public TeamsColor teamsColor() {
        return teamsColor;
    }

    public void setTeamsColor(TeamsColor teamsColor) {
        Objects.requireNonNull(teamsColor, "teamsColor should not be null");
        this.teamsColor = teamsColor;
    }

    public Coach coach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        Objects.requireNonNull(coach, "name should not be null");
        this.coach = coach;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Team) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.gender, that.gender) &&
                this.number == that.number &&
                Objects.equals(this.club, that.club) &&
                Objects.equals(this.teamsColor, that.teamsColor) &&
                Objects.equals(this.coach, that.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, gender, number, club, teamsColor, coach);
    }

    @Override
    public String toString() {
        return "Team[" +
                "id=" + id + ", " +
                "category=" + category + ", " +
                "gender=" + gender + ", " +
                "number=" + number + ", " +
                "club=" + club + ", " +
                "teamsColor=" + teamsColor + ", " +
                "coach=" + coach + ']';
    }

}
