package fr.hoenheimsports.gamedomain.model;

import java.util.Objects;
import java.util.UUID;
/**
 * Represents a team with a unique ID, category, gender, team number, club, teams color, and coach.
 * <p>
 * The {@code id} field is considered as the unique identifier for this object. However, a composite key composed of {@code category}, {@code gender}, {@code number}, and {@code club} ensures that each team is unique with respect to these combined attributes.
 * </p>
 *
 * <h2>Constraints:</h2>
 * <ul>
 *   <li>The {@code id} field must not be {@code null}.</li>
 *   <li>The {@code category} field must not be {@code null}.</li>
 *   <li>The {@code gender} field must not be {@code null}.</li>
 *   <li>The team {@code number} should be greater than 0.</li>
 *   <li>The {@code club} field must not be {@code null}.</li>
 *   <li>The {@code teamsColor} field must not be {@code null}.</li>
 *   <li>The {@code coach} field must not be {@code null}.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 * Club clubInstance = new Club(...);
 * Category categoryInstance = new Category(...);
 * Coach coachInstance = new Coach(...);
 *
 * Team handballTeam = new Team(UUID.randomUUID(), categoryInstance, Gender.MALE, 1, clubInstance, TeamsColor.RED, coachInstance);
 * }
 * </pre>
 */
public final class Team {
    /**
     * A constant representing an unknown team.
     */
    public static final Team UNKNOWN = new Team(UUID.fromString("00000000-0000-0000-0000-000000000000"), Category.UNKNOWN, Gender.UNKNOWN, 1, Club.UNKNOWN, TeamsColor.UNKNOWN, Coach.UNKNOWN);
    private final UUID id;
    private final Category category;
    private final Gender gender;
    private final int number;
    private final Club club;
    private TeamsColor teamsColor;
    private Coach coach;
    /**
     * Constructs a new Team instance with the given attributes.
     *
     * @param id the unique identifier of the team
     * @param category the category of the team
     * @param gender the gender designation of the team
     * @param number the team number
     * @param club the associated club of the team
     * @param teamsColor the team's color
     * @param coach the assigned coach to the team
     */
    public Team(UUID id, Category category, Gender gender, int number, Club club, TeamsColor teamsColor, Coach coach) {
        Objects.requireNonNull(id, "id should not be null");
        Objects.requireNonNull(category, "category should not be null");
        Objects.requireNonNull(gender, "gender should not be null");
        if (number < 1) {
            throw new IllegalArgumentException("Team code should be greater than 0");
        }
        Objects.requireNonNull(club, "club should not be null");
        Objects.requireNonNull(teamsColor, "teamsColor should not be null");
        Objects.requireNonNull(coach, "coach should not be null");
        this.id = id;
        this.category = category;
        this.gender = gender;
        this.number = number;
        this.club = club;
        this.teamsColor = teamsColor;
        this.coach = coach;
    }

    /**
     * Returns the unique identifier of the team.
     * @return the unique identifier of the team
     */
    public UUID getId() {
        return id;
    }
    /**
     * Returns the category of the team.
     * @return the category of the team
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Retrieves the gender specification of this team.
     *
     * @return the gender of this team.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Retrieves the team number.
     *
     * @return the number of this team.
     */
    public int getNumber() {
        return number;
    }


    /**
     * Retrieves the club to which this team belongs.
     *
     * @return the club of this team.
     */
    public Club getClub() {
        return club;
    }
    /**
     * Retrieves the color associated with this team.
     *
     * @return the teamsColor of this team.
     */
    public TeamsColor getTeamsColor() {
        return teamsColor;
    }
    /**
     * Updates the team's color.
     *
     * @param teamsColor new color for the team.
     */
    public void setTeamsColor(TeamsColor teamsColor) {
        Objects.requireNonNull(teamsColor, "teamsColor should not be null");
        this.teamsColor = teamsColor;
    }
    /**
     * Retrieves the coach assigned to this team.
     *
     * @return the coach of this team.
     */
    public Coach getCoach() {
        return coach;
    }
    /**
     * Assigns a new coach to this team.
     *
     * @param coach new coach for the team.
     */
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
                "code=" + number + ", " +
                "club=" + club + ", " +
                "teamsColor=" + teamsColor + ", " +
                "coach=" + coach + ']';
    }

}
