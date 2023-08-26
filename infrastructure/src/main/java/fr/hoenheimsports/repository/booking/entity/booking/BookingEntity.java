package fr.hoenheimsports.repository.booking.entity.booking;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
@Entity
public class BookingEntity {
    @Id
    private UUID id;
    @ManyToOne
    private HallEntity hall;
    @ManyToOne
    private HallUserEntity user;
    @Embedded
    private TimeslotEntity timeslot;
    @Enumerated(EnumType.STRING)
    private BookingStateEntity state;
    @Embedded
    private PaymentEntity payment;
    private boolean allowsOverlap;
    @Column(name = "use-mais-pas-un-putain-de-mot-cle")
    private String use;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HallEntity getHall() {
        return hall;
    }

    public void setHall(HallEntity hall) {
        this.hall = hall;
    }

    public HallUserEntity getUser() {
        return user;
    }

    public void setUser(HallUserEntity user) {
        this.user = user;
    }

    public TimeslotEntity getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeslotEntity timeslot) {
        this.timeslot = timeslot;
    }

    public BookingStateEntity getState() {
        return state;
    }

    public void setState(BookingStateEntity state) {
        this.state = state;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingEntity that)) return false;
        return allowsOverlap == that.allowsOverlap && Objects.equals(id, that.id) && Objects.equals(hall, that.hall) && Objects.equals(user, that.user) && Objects.equals(timeslot, that.timeslot) && state == that.state && Objects.equals(payment, that.payment) && Objects.equals(use, that.use);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hall, user, timeslot, state, payment, allowsOverlap, use);
    }
}
