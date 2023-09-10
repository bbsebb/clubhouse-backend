package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.Booking;

import java.math.BigDecimal;

/**
 * A rule that applies a price to a booking
 */
public class AssociationHallUserBookingPaymentRule extends RuleChain<Booking> {
    private static final BigDecimal associationHallPrice = BigDecimal.ZERO;

    @Override
    public boolean matches(Booking booking) {
        return booking.getUser() instanceof AssociationHallUser;
    }

    @Override
    public Booking apply(Booking booking) {
        booking.toPrice(associationHallPrice);
        return booking;
    }
}
