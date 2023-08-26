package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.BookingState;
import fr.hoenheimsports.bookdomain.model.Tenant;

public class TenantBookingStateRule extends RuleChain<Booking> {
    @Override
    public boolean matches(Booking booking) {
        return booking.getUser() instanceof Tenant;
    }

    @Override
    public Booking apply(Booking booking) {
        booking.setState(BookingState.PENDING);
        return booking;
    }
}
