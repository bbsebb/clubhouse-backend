package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Category;

import java.util.UUID;

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
