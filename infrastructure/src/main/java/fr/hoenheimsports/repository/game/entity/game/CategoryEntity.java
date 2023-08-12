package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class CategoryEntity {
    @Id
    private String name;

    private int age;
    private boolean isMaxAge;

    public CategoryEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsMaxAge() {
        return isMaxAge;
    }

    public void setIsMaxAge(boolean maxAge) {
        isMaxAge = maxAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity that)) return false;
        return age == that.age && isMaxAge == that.isMaxAge && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, isMaxAge);
    }
}


