package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"category_name", "number","club_code","gender"})})
public class TeamEntity {
    @Id
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    private CategoryEntity category;
    private GenderEntity gender;
    private int number;
    @ManyToOne(cascade = CascadeType.ALL)
    private ClubEntity club;
    private TeamsColorEntity teamsColor;
    @ManyToOne(cascade = CascadeType.ALL)
    private CoachEntity coach;

    public TeamEntity() {
    }

    public TeamEntity(UUID id, CategoryEntity category, GenderEntity gender, int number, ClubEntity club, TeamsColorEntity teamsColor, CoachEntity coach) {
        this.id = id;
        this.category = category;
        this.gender = gender;
        this.number = number;
        this.club = club;
        this.teamsColor = teamsColor;
        this.coach = coach;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(GenderEntity gender) {
        this.gender = gender;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ClubEntity getClub() {
        return club;
    }

    public void setClub(ClubEntity club) {
        this.club = club;
    }

    public TeamsColorEntity getTeamsColor() {
        return teamsColor;
    }

    public void setTeamsColor(TeamsColorEntity teamsColor) {
        this.teamsColor = teamsColor;
    }

    public CoachEntity getCoach() {
        return coach;
    }

    public void setCoach(CoachEntity coach) {
        this.coach = coach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamEntity that)) return false;
        return number == that.number && Objects.equals(id, that.id) && Objects.equals(category, that.category) && gender == that.gender && Objects.equals(club, that.club) && Objects.equals(teamsColor, that.teamsColor) && Objects.equals(coach, that.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, gender, number, club, teamsColor, coach);
    }
}
