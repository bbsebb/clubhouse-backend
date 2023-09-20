package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class RefereesEntity {
    public static final RefereesEntity UNKNOWN = new RefereesEntity(RefereeEntity.UNKNOWN, RefereeEntity.UNKNOWN, RefereeEntity.UNKNOWN, RefereeEntity.UNKNOWN);
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RefereeEntity designatedReferee1;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RefereeEntity designatedReferee2;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RefereeEntity officiatingReferee1;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RefereeEntity officiatingReferee2;

    public RefereesEntity() {
    }

    public RefereesEntity(RefereeEntity designatedReferee1, RefereeEntity designatedReferee2, RefereeEntity officiatingReferee1, RefereeEntity officiatingReferee2) {
        this.designatedReferee1 = designatedReferee1;
        this.designatedReferee2 = designatedReferee2;
        this.officiatingReferee1 = officiatingReferee1;
        this.officiatingReferee2 = officiatingReferee2;
    }

    public RefereeEntity getDesignatedReferee1() {
        return designatedReferee1;
    }

    public void setDesignatedReferee1(RefereeEntity designatedReferee1) {
        this.designatedReferee1 = designatedReferee1;
    }

    public RefereeEntity getDesignatedReferee2() {
        return designatedReferee2;
    }

    public void setDesignatedReferee2(RefereeEntity designatedReferee2) {
        this.designatedReferee2 = designatedReferee2;
    }

    public RefereeEntity getOfficiatingReferee1() {
        return officiatingReferee1;
    }

    public void setOfficiatingReferee1(RefereeEntity officiatingReferee1) {
        this.officiatingReferee1 = officiatingReferee1;
    }

    public RefereeEntity getOfficiatingReferee2() {
        return officiatingReferee2;
    }

    public void setOfficiatingReferee2(RefereeEntity officiatingReferee2) {
        this.officiatingReferee2 = officiatingReferee2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefereesEntity that)) return false;
        return Objects.equals(designatedReferee1, that.designatedReferee1) && Objects.equals(designatedReferee2, that.designatedReferee2) && Objects.equals(officiatingReferee1, that.officiatingReferee1) && Objects.equals(officiatingReferee2, that.officiatingReferee2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designatedReferee1, designatedReferee2, officiatingReferee1, officiatingReferee2);
    }
}
