package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Category;

public class CategoryBuilder {
    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }


    private int age;
    private boolean isMaxAge;

    public CategoryBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public CategoryBuilder withIsMaxAge(boolean isMaxAge) {
        this.isMaxAge = isMaxAge;
        return this;
    }

    public Category build() {
    String name;
        if(isMaxAge) {
            name = "-"+this.age+" ans";
        } else {
            name = "senior";
        }

        return new Category(name,this.age,this.isMaxAge);
    }
}
