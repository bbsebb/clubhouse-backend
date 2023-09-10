package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.Booking;
import fr.hoenheimsports.bookdomain.model.Tenant;

import java.math.BigDecimal;
/**
 * A rule that applies a price to a booking
 */
public class TenantBookingPaymentRule extends RuleChain<Booking> {
    private static final BigDecimal tenantPrice = new BigDecimal("120.00");

    @Override
    public boolean matches(Booking booking) {
        return booking.getUser() instanceof Tenant;
    }

    @Override
    public Booking apply(Booking booking) {
        booking.toPrice(tenantPrice);
        return booking;
    }
}
