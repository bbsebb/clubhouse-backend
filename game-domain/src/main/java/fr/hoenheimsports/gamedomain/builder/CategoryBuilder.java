package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Category;

public class CategoryBuilder {

    private String name;


    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Category build() {

        return new Category(name);
    }
}
