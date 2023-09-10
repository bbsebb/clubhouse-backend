package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.api.BookingCreate;
import fr.hoenheimsports.bookdomain.exception.TimeslotAlreadyBookedException;
import fr.hoenheimsports.bookdomain.model.*;
import fr.hoenheimsports.bookdomain.rule.AssociationHallUserBookingStateRule;
import fr.hoenheimsports.bookdomain.rule.RuleChain;
import fr.hoenheimsports.bookdomain.rule.TenantBookingStateRule;
import fr.hoenheimsports.bookdomain.spi.stub.BookingStub;
import fr.hoenheimsports.bookdomain.spi.stub.EmailProviderStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingCreateImplTest {

    private final BookingStub bookingStub;
    private final HallUser tenant1;
    private final HallUser tenant2;
    private final HallUser associationHallUser;
   // private final HallUser t4;
    private final Hall hall;
    private final String use;
    private BookingCreate bookingCreate;


    public BookingCreateImplTest() {
        this.bookingStub = new BookingStub();
        Address address = new Address("street",0,"city");
        tenant1 = new Tenant(UUID.randomUUID(),"username1","user1@email.fr",address,true);
        tenant2 = new Tenant(UUID.randomUUID(),"username2","user2@email.fr",address,true);
        associationHallUser = new AssociationHallUser(UUID.randomUUID(),"username3","user3@email.fr");
        //t4 = new AssociationHallUser(UUID.randomUUID(),"username4","user4@email.fr");
        hall = new Hall(UUID.randomUUID(),"club house",new Address("rue des vosges",67800,"Hoeneim"),0);
        use = "default use";
    }

    @BeforeEach
    void setUp() {
        this.bookingCreate = new BookingCreateImpl(this.bookingStub, new EmailServiceImpl(new EmailProviderStub()));

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
        Booking actual = this.bookingCreate.create(hall, associationHallUser,timeslot,"use");

        Booking excepted = new Booking(actual.getId(), hall, associationHallUser,timeslot,actual.getState(), Payment.UNKNOWN,true, "use");

        assertEquals(excepted,actual);

    }

    @Test
    void createWithExistingBookedTimeslotTest() {
        this.populateForCreateWithExistingBookedTimeslotTest();

        var overlapseTimeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,1,0));

        //With other user and same timeslot already recorded
        Exception exception = assertThrows(TimeslotAlreadyBookedException.class, () -> this.bookingCreate.create(hall, tenant2,overlapseTimeslot,use));
        assertEquals("timeslot already booked",exception.getMessage());
        //With same user and same timeslot already recorded
        assertDoesNotThrow(() -> this.bookingCreate.create(hall, tenant1,overlapseTimeslot,use));

    }

    @Test
    void createWithMergedBooking() {
        this.populateForCreateWithExistingBookedTimeslotTest();
        var overlapseTimeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,2,0));
        Booking actual = this.bookingCreate.create(hall, tenant1,overlapseTimeslot,"use2");
        var mergedTimeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,0),
                LocalDateTime.of(2020,1,1,2,0));

        Booking excepted = new Booking(actual.getId(), hall, tenant1,mergedTimeslot,actual.getState(), Payment.UNKNOWN,false , "use et use2");

        assertEquals(mergedTimeslot,actual.getTimeslot());
        assertEquals(excepted,actual);
    }

    @Test
    void save() {
        var timeslot = new Timeslot(
                LocalDateTime.of(2020,1,1,0,10),
                LocalDateTime.of(2020,1,1,2,0));
        Booking booking1 = this.bookingCreate.create(hall, tenant1,timeslot,use);
        var timeslot1 = new Timeslot(
                LocalDateTime.of(2021,1,1,0,10),
                LocalDateTime.of(2021,1,1,2,0));
        Booking booking2 = this.bookingCreate.create(hall, tenant2,timeslot1,use);

        assertEquals(booking1,this.bookingCreate.save(booking1));
        assertEquals(booking2,this.bookingCreate.save(booking2));
        assertEquals(2,this.bookingStub.findAll().size());
        assertTrue(this.bookingStub.findAll().contains(booking1));
        assertTrue(this.bookingStub.findAll().contains(booking2));
    }



    private void populateForCreateWithExistingBookedTimeslotTest() {
        var timeslot1 = new Timeslot(
                LocalDateTime.of(2020,1,1,0,0),
                LocalDateTime.of(2020,1,1,1,0));
        var booking1 = new Booking(UUID.randomUUID(), hall, tenant1,timeslot1,BookingState.PENDING, Payment.UNKNOWN,false , "use");
        this.bookingStub.save(RuleChain.buildChain(new TenantBookingStateRule(),new AssociationHallUserBookingStateRule()).handle(booking1));
    }

}