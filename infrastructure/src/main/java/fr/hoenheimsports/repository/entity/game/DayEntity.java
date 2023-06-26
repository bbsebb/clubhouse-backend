package fr.hoenheimsports.repository.entity.game;

import jakarta.persistence.Embeddable;

import java.util.Objects;
@Embeddable
public final class DayEntity {
    private int number;

    public DayEntity() {
    }

    public DayEntity(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DayEntity dayEntity)) return false;
        return number == dayEntity.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
