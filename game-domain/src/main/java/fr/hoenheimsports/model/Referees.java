package fr.hoenheimsports.model;

import java.util.Objects;

public record Referees(Referee designatedReferee1, Referee designatedReferee2, Referee officiatingReferee1, Referee officiatingReferee2) {
    public static final Referees UNKNOWN = new Referees(Referee.UNKNOWN,Referee.UNKNOWN,Referee.UNKNOWN,Referee.UNKNOWN);
    public Referees {
        Objects.requireNonNull(designatedReferee1, "designatedReferee1 should not be null");
        Objects.requireNonNull(designatedReferee2, "designatedReferee2 should not be null");
        Objects.requireNonNull(officiatingReferee1, "officiatingReferee1 should not be null");
        Objects.requireNonNull(officiatingReferee2, "officiatingReferee2 should not be null");
    }
}
