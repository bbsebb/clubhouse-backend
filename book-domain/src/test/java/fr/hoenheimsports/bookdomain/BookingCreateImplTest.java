package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.api.BookingCreate;
import fr.hoenheimsports.bookdomain.exception.TimeslotAlreadyBooked;
import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.rule.CreateBookingStateRule;
import fr.hoenheimsports.bookdomain.spi.stub.BookingStub;
import fr.hoenheimsports.bookdomain.spi.stub.EmailProviderStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingCreateImplTest {

    private final BookingStub bookingStub;
    private final EmailProviderStub emailProviderStub;
    private final HallUser tenant1;
    private final HallUser tenant2;
    private final HallUser associationHallUser;
   // private final HallUser t4;
    private final Hall hall;
    private BookingCreate bookingCreate;
    private final CreateBookingStateRule createBookingStateRule;


    public BookingCreateImplTest() {
        this.bookingStub = new BookingStub();
        this.emailProviderStub = new EmailProviderStub();
        this.createBookingStateRule = new CreateBookingStateRule();
        Address address = new Address("street",0,"city");
        tenant1 = new Tenant(UUID.randomUUID(),"username1","user1@email.fr",address);
        tenant2 = new Tenant(UUID.randomUUID(),"username2","user2@email.fr",address);
        associationHallUser = new AssociationHallUser(UUID.randomUUID(),"username3","user3@email.fr");
        //t4 = new AssociationHallUser(UUID.randomUUID(),"username4","user4@email.fr");
        hall = new Hall(UUID.randomUUID(),"club house",new Address("rue des vosges",67800,"Hoeneim"),0);

    }

    @BeforeEach
    void setUp() {
        this.bookingCreate = new BookingCreateImpl(this.bookingStub,this.emailProviderStub, this.createBookingStateRule);

    }

    @AfterEach
    void tearDown() {
        this.bookingStub.clear();
    }

    @Test
    void create() {

        var timeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,1,0));
        Booking actual = this.bookingCreate.create(hall, associationHallUser,timeslot);

        Booking excepted = new Booking(actual.id(), hall, associationHallUser,timeslot,actual.state());

        assertEquals(excepted,actual);

    }

    @Test
    void createWithExistingBookedTimeslotTest() {
        this.populateForCreateWithExistingBookedTimeslotTest();

        var overlapseTimeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,1,0));

        //With other user and same timeslot already recorded
        Exception exception = assertThrows(TimeslotAlreadyBooked.class, () -> this.bookingCreate.create(hall, tenant2,overlapseTimeslot));
        assertEquals("timeslot already booked",exception.getMessage());
        //With same user and same timeslot already recorded
        assertDoesNotThrow(() -> this.bookingCreate.create(hall, tenant1,overlapseTimeslot));

    }

    @Test
    void createWithMergedBooking() {
        this.populateForCreateWithExistingBookedTimeslotTest();
        var overlapseTimeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,2,0));
        Booking actual = this.bookingCreate.create(hall, tenant1,overlapseTimeslot);
        var mergedTimeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,0),
                LocalDateTime.of(2020,1,1,2,0));

        Booking excepted = new Booking(actual.id(), hall, tenant1,mergedTimeslot,actual.state());

        assertEquals(mergedTimeslot,actual.timeslot());
        assertEquals(excepted,actual);
    }



    private void populateForCreateWithExistingBookedTimeslotTest() {
        var timeslot1 = new Timeslot(
                LocalDateTime.of(2020,1,1,0,0),
                LocalDateTime.of(2020,1,1,1,0));
        var booking1 = new Booking(UUID.randomUUID(), hall, tenant1,timeslot1,this.createBookingStateRule.apply(tenant1));
        this.bookingStub.save(booking1);
    }

}