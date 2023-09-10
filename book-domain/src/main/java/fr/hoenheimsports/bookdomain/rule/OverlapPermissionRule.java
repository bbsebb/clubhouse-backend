package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.Booking;

/**
 * A rule that applies a state to a booking
 */
public class OverlapPermissionRule extends RuleChain<Booking> {
    @Override
    public boolean matches(Booking booking) {
        return booking.getUser() instanceof AssociationHallUser;
    }

    @Override
    public Booking apply(Booking booking) {
        booking.setAllowsOverlap(true);
        return booking;
    }
}
