package fr.hoenheimsports.bookdomain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public final class Booking {
    private final UUID id;
    private final Hall hall;
    private final HallUser user;
    private Timeslot timeslot;
    private BookingState state;
    private Payment payment;
    private boolean allowsOverlap;
    private String use;

    public Booking(UUID id, Hall hall, HallUser user, Timeslot timeslot, BookingState state, Payment payment, String use) {

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
        this.allowsOverlap = false;
        this.use = use;
    }

    public UUID getId() {
        return id;
    }

    public Hall getHall() {
        return hall;
    }

    public HallUser getUser() {
        return user;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Timeslot mergeOverlappingTimeslot(Timeslot timeslot) {
        this.timeslot = this.timeslot.mergeOverlappingTimeslot(timeslot);
        return this.getTimeslot();
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public Payment getPayment() {
        return payment;
    }

    public boolean isAllowsOverlap() {
        return allowsOverlap;
    }

    public void setAllowsOverlap(boolean allowsOverlap) {
        this.allowsOverlap = allowsOverlap;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public boolean hasTimeslotFree(List<Booking>  otherBookings) {
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
        return booking ->  booking.getState().equals(BookingState.ACCEPTED)
                || booking.getState().equals(BookingState.VALIDATED)
                || booking.getState().equals(BookingState.PENDING)
                || booking.getState().equals(BookingState.FINISHED)
                ;
    }

    public void pay(BigDecimal amountPaid, PaymentType paymentType, UUID collectorId) {
        this.payment = this.payment.pay(amountPaid,paymentType,collectorId);
    }

    public void toPrice(BigDecimal amount) {
        if(this.payment.amount().compareTo(BigDecimal.ZERO) != 0 && this.payment.isPaid()) {
             throw new IllegalStateException("The payment already paid");
        }
        this.payment = new Payment(amount,false,this.payment.paymentType(),this.payment.collectorId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Booking) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.hall, that.hall) &&
                Objects.equals(this.user, that.user) &&
                Objects.equals(this.timeslot, that.timeslot) &&
                Objects.equals(this.state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hall, user, timeslot, state);
    }

    @Override
    public String toString() {
        return "Booking[" +
                "id=" + id + ", " +
                "hall=" + hall + ", " +
                "user=" + user + ", " +
                "timeslot=" + timeslot + ", " +
                "state=" + state + ']';
    }

}
