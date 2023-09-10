package fr.hoenheimsports.bookdomain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
/**
 * Booking class is the main class of the domain. It represents a booking of a hall by a user. It contains the following attributes:
 * <ul>
 *     <li>id: the unique identifier of the booking</li>
 *     <li>hall: the hall booked</li>
 *     <li>user: the user who booked the hall</li>
 *     <li>timeslot: the timeslot of the booking</li>
 *     <li>state: the state of the booking</li>
 *     <li>payment: the payment of the booking</li>
 *     <li>allowsOverlap: if the booking allows overlap with other bookings</li>
 *     <li>use: the use of the booking</li>
 *     <li>BookingState: the state of the booking</li>
 * </ul>
 *
 */
public final class Booking {
    private final UUID id;
    private final Hall hall;
    private final HallUser user;
    private Timeslot timeslot;
    private BookingState state;
    private Payment payment;
    private boolean allowsOverlap;
    private String use;

    /**
     * Constructor of the Booking class
     * @param id the unique identifier of the booking
     * @param hall the hall booked
     * @param user the user who booked the hall
     * @param timeslot the timeslot of the booking
     * @param state the state of the booking
     * @param payment the payment of the booking
     * @param allowsOverlap if the booking allows overlap with other bookings
     * @param use the use of the booking
     */
    public Booking(UUID id, Hall hall, HallUser user, Timeslot timeslot, BookingState state, Payment payment, boolean allowsOverlap, String use) {
        Objects.requireNonNull(id, "start should not be null");
        Objects.requireNonNull(hall, "hall should not be null");
        Objects.requireNonNull(user, "user should not be null");
        Objects.requireNonNull(timeslot, "timeslot should not be null");
        Objects.requireNonNull(state, "state should not be null");
        Objects.requireNonNull(use, "use should not be null");
        Objects.requireNonNull(payment, "payment should not be null");
        this.payment = payment;
        this.id = id;
        this.hall = hall;
        this.user = user;
        this.timeslot = timeslot;
        this.state = state;
        this.allowsOverlap = allowsOverlap;
        this.use = use;
    }

    /**
     *
     * @return the unique identifier of the booking
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @return the hall booked
     */
    public Hall getHall() {
        return hall;
    }

    /**
     *
     * @return the user who booked the hall
     */
    public HallUser getUser() {
        return user;
    }

    /**
     *
     * @return the timeslot of the booking
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     *
     * @param timeslot the timeslot of the booking
     * @return the timeslot of the booking
     */
    public Timeslot mergeOverlappingTimeslot(Timeslot timeslot) {
        this.timeslot = this.timeslot.mergeOverlappingTimeslot(timeslot);
        return this.getTimeslot();
    }

    /**
     *
     * @return the state of the booking
     */
    public BookingState getState() {
        return state;
    }

    /**
     *
     * @param state the state of the booking
     */
    public void setState(BookingState state) {
        this.state = state;
    }

    /**
     *
     * @return the payment of the booking
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     *
     * @return if the booking allows overlap with other bookings
     */
    public boolean isAllowsOverlap() {
        return allowsOverlap;
    }

    /**
     *
     * @param allowsOverlap if the booking allows overlap with other bookings
     */
    public void setAllowsOverlap(boolean allowsOverlap) {
        this.allowsOverlap = allowsOverlap;
    }

    /**
     * @return the use of the booking
     */
    public String getUse() {
        return use;
    }

    /**
     *
     * @param use the use of the booking
     */
    public void setUse(String use) {
        this.use = use;
    }

    /**
     *
     * @param otherBookings the list of other bookings
     * @return if the booking has a timeslot free
     */
    public boolean hasTimeslotFree(List<Booking> otherBookings) {
        return otherBookings.stream()
                .filter(booking -> booking.getTimeslot().isOverlaps(this.getTimeslot()))
                .filter(booking -> !booking.isAllowsOverlap())
                .filter(booking -> this.getHall().equals(booking.getHall()))
                .filter(this.filterNotSameUserBooking())
                .filter(this.filterActiveBooking())
                .findAny()
                .isEmpty();
    }

    private Predicate<Booking> filterNotSameUserBooking() {
        return booking -> !booking.getUser().getId().equals(this.user.getId());
    }

    private Predicate<Booking> filterActiveBooking() {
        return booking -> booking.getState().equals(BookingState.ACCEPTED)
                || booking.getState().equals(BookingState.VALIDATED)
                || booking.getState().equals(BookingState.PENDING)
                || booking.getState().equals(BookingState.FINISHED)
                ;
    }

    public void pay(PaymentType paymentType, UUID collectorId) {
        this.payment = this.payment.pay(paymentType, collectorId);
    }

    public void toPrice(BigDecimal amount) {
        if (this.payment.amount().compareTo(BigDecimal.ZERO) != 0 && this.payment.isPaid()) {
            throw new IllegalStateException("The payment already paid");
        }
        this.payment = new Payment(amount, false, this.payment.paymentType(), this.payment.collectorId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking booking)) return false;
        return allowsOverlap == booking.allowsOverlap && Objects.equals(id, booking.id) && Objects.equals(hall, booking.hall) && Objects.equals(user, booking.user) && Objects.equals(timeslot, booking.timeslot) && state == booking.state && Objects.equals(payment, booking.payment) && Objects.equals(use, booking.use);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hall, user, timeslot, state, payment, allowsOverlap, use);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", hall=" + hall +
                ", user=" + user +
                ", timeslot=" + timeslot +
                ", state=" + state +
                ", payment=" + payment +
                ", allowsOverlap=" + allowsOverlap +
                ", use='" + use + '\'' +
                '}';
    }
}
