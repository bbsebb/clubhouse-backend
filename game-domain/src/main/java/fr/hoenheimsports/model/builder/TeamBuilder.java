package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.*;

import java.util.UUID;
import java.util.function.Consumer;

public class TeamBuilder {
    private UUID id;
    private Category category;
    private Gender gender;
    private int number;
    private Club club;
    private TeamsColor teamsColor;
    private Coach coach;

    public TeamBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public TeamBuilder withCategory(Consumer<CategoryBuilder> categoryBuilderFunction) {
        CategoryBuilder categoryBuilder = new CategoryBuilder();
        categoryBuilderFunction.accept(categoryBuilder);
        this.category = categoryBuilder.build();
        return this;
    }

    public TeamBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public TeamBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public TeamBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public TeamBuilder withClub(Consumer<ClubBuilder> clubBuilderFunction) {
        ClubBuilder clubBuilder = new ClubBuilder();
        clubBuilderFunction.accept(clubBuilder);
        this.club = clubBuilder.build();
        return this;
    }

    public TeamBuilder withClub(Club club) {
        this.club = club;
        return this;
    }

    public TeamBuilder withTeamsColor(Consumer<TeamsColorBuilder> teamsColorBuilderFunction) {
        TeamsColorBuilder teamsColorBuilder = new TeamsColorBuilder();
        teamsColorBuilderFunction.accept(teamsColorBuilder);
        this.teamsColor = teamsColorBuilder.build();
        return this;
    }

    public TeamBuilder withTeamsColor(TeamsColor teamsColor) {
        this.teamsColor = teamsColor;
        return this;
    }

    public TeamBuilder withCoach(Consumer<CoachBuilder> coachBuilderFunction) {
        CoachBuilder coachBuilder = new CoachBuilder();
        coachBuilderFunction.accept(coachBuilder);
        this.coach = coachBuilder.build();
        return this;
    }

    public TeamBuilder withCoach(Coach coach) {
        this.coach = coach;
        return this;
    }

    public Team build() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Team(id, category, gender, number, club, teamsColor, coach);
    }
}
