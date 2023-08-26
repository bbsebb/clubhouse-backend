package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;

public class AssociationHallUserBookingStateRule extends RuleChain<Booking>{
    @Override
    public boolean matches(Booking booking) {
        return booking.getUser() instanceof AssociationHallUser;
    }

    @Override
    public Booking apply(Booking booking) {
        booking.setState(BookingState.ACCEPTED);
        return booking;
    }
}
