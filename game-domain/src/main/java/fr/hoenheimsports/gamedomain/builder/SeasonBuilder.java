package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Season;

import java.time.LocalDate;

public class SeasonBuilder {
    public static SeasonBuilder builder() {
        return new SeasonBuilder();
    }
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public SeasonBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SeasonBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public SeasonBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }


    public Season build() {
        return new Season(this.name,this.startDate,this.endDate);
    }
}
