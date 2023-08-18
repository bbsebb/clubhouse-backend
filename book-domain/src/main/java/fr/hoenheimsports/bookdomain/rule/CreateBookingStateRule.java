package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.*;

public class CreateBookingStateRule implements IRule<HallUser,BookingState>{
    @Override
    public boolean matches(HallUser user) {
        return user!=null;
    }

    @Override
    public BookingState apply(HallUser user) {
        return this.rulesBookingState(user);
    }

    private BookingState rulesBookingState(HallUser user) {
        return switch (user) {
            case AssociationHallUser associationHallUser -> BookingState.VALIDATED;
            case Tenant tenant -> BookingState.PENDING;
            case null -> throw new IllegalArgumentException();
            default -> throw new IllegalStateException();
        };
    }
}
