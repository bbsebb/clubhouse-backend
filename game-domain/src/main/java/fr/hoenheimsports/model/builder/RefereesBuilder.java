package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Referee;
import fr.hoenheimsports.model.Referees;

import java.util.function.Consumer;

public class RefereesBuilder {
    private Referee designatedReferee1;
    private Referee designatedReferee2;
    private Referee officiatingReferee1;
    private Referee officiatingReferee2;

    public RefereesBuilder withDesignatedReferee1(Consumer<RefereeBuilder> refereeBuilderFunction) {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        refereeBuilderFunction.accept(refereeBuilder);
        this.designatedReferee1 = refereeBuilder.build();
        return this;
    }

    public RefereesBuilder withDesignatedReferee1(Referee referee) {
        this.designatedReferee1 = referee;
        return this;
    }

    public RefereesBuilder withDesignatedReferee2(Consumer<RefereeBuilder> refereeBuilderFunction) {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        refereeBuilderFunction.accept(refereeBuilder);
        this.designatedReferee2 = refereeBuilder.build();
        return this;
    }

    public RefereesBuilder withDesignatedReferee2(Referee referee) {
        this.designatedReferee2 = referee;
        return this;
    }

    public RefereesBuilder withOfficiatingReferee1(Consumer<RefereeBuilder> refereeBuilderFunction) {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        refereeBuilderFunction.accept(refereeBuilder);
        this.officiatingReferee1 = refereeBuilder.build();
        return this;
    }

    public RefereesBuilder withOfficiatingReferee1(Referee referee) {
        this.officiatingReferee1 = referee;
        return this;
    }

    public RefereesBuilder withOfficiatingReferee2(Consumer<RefereeBuilder> refereeBuilderFunction) {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        refereeBuilderFunction.accept(refereeBuilder);
        this.officiatingReferee2 = refereeBuilder.build();
        return this;
    }

    public RefereesBuilder withOfficiatingReferee2(Referee referee) {
        this.officiatingReferee2 = referee;
        return this;
    }

    public Referees build() {
        return new Referees(designatedReferee1, designatedReferee2, officiatingReferee1, officiatingReferee2);
    }
}
