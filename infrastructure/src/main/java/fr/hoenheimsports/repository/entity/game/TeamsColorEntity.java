package fr.hoenheimsports.repository.entity.game;



import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Embeddable
public class TeamsColorEntity {
    public static final TeamsColorEntity UNKNOWN = new TeamsColorEntity(null, null, null, null);
    @Enumerated(EnumType.STRING)
    private  TeamColorEntity shirtColor1;
    @Enumerated(EnumType.STRING)
    private  TeamColorEntity shirtColor2;
    @Enumerated(EnumType.STRING)
    private  TeamColorEntity goalkeeperColor1;
    @Enumerated(EnumType.STRING)
    private  TeamColorEntity goalkeeperColor2;

    public TeamsColorEntity(TeamColorEntity shirtColor1,
                            TeamColorEntity shirtColor2,
                            TeamColorEntity goalkeeperColor1,
                            TeamColorEntity goalkeeperColor2) {
        this.shirtColor1 = shirtColor1;
        this.shirtColor2 = shirtColor2;
        this.goalkeeperColor1 = goalkeeperColor1;
        this.goalkeeperColor2 = goalkeeperColor2;
    }

    public TeamsColorEntity() {

    }

    public TeamColorEntity getShirtColor1() {
        return shirtColor1;
    }

    public void setShirtColor1(TeamColorEntity shirtColor1) {
        this.shirtColor1 = shirtColor1;
    }

    public TeamColorEntity getShirtColor2() {
        return shirtColor2;
    }

    public void setShirtColor2(TeamColorEntity shirtColor2) {
        this.shirtColor2 = shirtColor2;
    }

    public TeamColorEntity getGoalkeeperColor1() {
        return goalkeeperColor1;
    }

    public void setGoalkeeperColor1(TeamColorEntity goalkeeperColor1) {
        this.goalkeeperColor1 = goalkeeperColor1;
    }

    public TeamColorEntity getGoalkeeperColor2() {
        return goalkeeperColor2;
    }

    public void setGoalkeeperColor2(TeamColorEntity goalkeeperColor2) {
        this.goalkeeperColor2 = goalkeeperColor2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamsColorEntity that)) return false;
        return shirtColor1 == that.shirtColor1 && shirtColor2 == that.shirtColor2 && goalkeeperColor1 == that.goalkeeperColor1 && goalkeeperColor2 == that.goalkeeperColor2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shirtColor1, shirtColor2, goalkeeperColor1, goalkeeperColor2);
    }
}
