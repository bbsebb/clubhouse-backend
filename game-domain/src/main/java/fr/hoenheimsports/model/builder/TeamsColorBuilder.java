package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.TeamColor;
import fr.hoenheimsports.model.TeamsColor;

import java.util.function.Consumer;

public class TeamsColorBuilder {
    private TeamColor shirtColor1;
    private TeamColor shirtColor2;
    private TeamColor goalkeeperColor1;
    private TeamColor goalkeeperColor2;

    public TeamsColorBuilder withShirtColor1(Consumer<TeamColorBuilder> shirtColor1BuilderConsumer) {
        TeamColorBuilder shirtColor1Builder = new TeamColorBuilder();
        shirtColor1BuilderConsumer.accept(shirtColor1Builder);
        this.shirtColor1 = shirtColor1Builder.build();
        return this;
    }

    public TeamsColorBuilder withShirtColor1(TeamColor shirtColor1) {
        this.shirtColor1 = shirtColor1;
        return this;
    }

    public TeamsColorBuilder withShirtColor2(Consumer<TeamColorBuilder> shirtColor2BuilderConsumer) {
        TeamColorBuilder shirtColor2Builder = new TeamColorBuilder();
        shirtColor2BuilderConsumer.accept(shirtColor2Builder);
        this.shirtColor2 = shirtColor2Builder.build();
        return this;
    }

    public TeamsColorBuilder withShirtColor2(TeamColor shirtColor2) {
        this.shirtColor2 = shirtColor2;
        return this;
    }

    public TeamsColorBuilder withGoalkeeperColor1(Consumer<TeamColorBuilder> goalkeeperColor1BuilderConsumer) {
        TeamColorBuilder goalkeeperColor1Builder = new TeamColorBuilder();
        goalkeeperColor1BuilderConsumer.accept(goalkeeperColor1Builder);
        this.goalkeeperColor1 = goalkeeperColor1Builder.build();
        return this;
    }

    public TeamsColorBuilder withGoalkeeperColor1(TeamColor goalkeeperColor1) {
        this.goalkeeperColor1 = goalkeeperColor1;
        return this;
    }

    public TeamsColorBuilder withGoalkeeperColor2(Consumer<TeamColorBuilder> goalkeeperColor2BuilderConsumer) {
        TeamColorBuilder goalkeeperColor2Builder = new TeamColorBuilder();
        goalkeeperColor2BuilderConsumer.accept(goalkeeperColor2Builder);
        this.goalkeeperColor2 = goalkeeperColor2Builder.build();
        return this;
    }

    public TeamsColorBuilder withGoalkeeperColor2(TeamColor goalkeeperColor2) {
        this.goalkeeperColor2 = goalkeeperColor2;
        return this;
    }

    public TeamsColor build() {
        return new TeamsColor(shirtColor1, shirtColor2, goalkeeperColor1, goalkeeperColor2);
    }
}
