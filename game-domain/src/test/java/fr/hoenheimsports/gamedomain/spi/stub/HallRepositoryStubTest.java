package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HallRepositoryStubTest {

    private HallRepositoryStub hallRepositoryStub;

    @BeforeEach
    void setUp() {
        ClubRepositoryStub clubRepositoryStub = new ClubRepositoryStub();
        hallRepositoryStub = new HallRepositoryStub(clubRepositoryStub);
    }

    @Test
    void testSave() {
        Address address = new Address("123 Main St", 12345, "City");
        Hall hall = new Hall(UUID.randomUUID(), "Concert Hall", address, GlueAuthorization.UNKNOWN);

        Hall savedHall = hallRepositoryStub.save(hall);

        assertEquals(hall, savedHall, "The saved hall should match the original hall");
    }

    @Test
    void testFindHallByKeys() {
        Address address = new Address("123 Main St", 12345, "City");
        Hall hall = new Hall(UUID.randomUUID(), "Concert Hall", address, GlueAuthorization.UNKNOWN);

        hallRepositoryStub.save(hall);
        Optional<Hall> foundHall = hallRepositoryStub.findByKeys("Concert Hall", "123 Main St", 12345, "City");

        assertTrue(foundHall.isPresent(), "Hall should be found by keys");
        assertEquals(hall, foundHall.get(), "Found hall should match the saved hall");
    }

    @Test
    void testFindHallById() {
        Address address = new Address("123 Main St", 12345, "City");
        Hall hall = new Hall(UUID.randomUUID(), "Concert Hall", address, GlueAuthorization.UNKNOWN);

        hallRepositoryStub.save(hall);
        Optional<Hall> foundHall = hallRepositoryStub.findById(hall.id().toString());

        assertTrue(foundHall.isPresent(), "Hall should be found by ID");
        assertEquals(hall, foundHall.get(), "Found hall should match the saved hall");
    }

    @Test
    void testFindAllHalls() {
        Address address1 = new Address("123 Main St", 12345, "City");
        Address address2 = new Address("456 Elm St", 67890, "Town");

        Hall hall1 = new Hall(UUID.randomUUID(), "Concert Hall", address1, GlueAuthorization.UNKNOWN);
        Hall hall2 = new Hall(UUID.randomUUID(), "Music Hall", address2, GlueAuthorization.UNKNOWN);

        hallRepositoryStub.save(hall1);
        hallRepositoryStub.save(hall2);

        Set<Hall> allHalls = hallRepositoryStub.findAll();

        assertTrue(allHalls.contains(hall1), "Hall1 should be in the retrieved set");
        assertTrue(allHalls.contains(hall2), "Hall2 should be in the retrieved set");
        assertEquals(2, allHalls.size(), "There should be two halls in the set");
    }

    @Test
    void testFindByClubCode() {

        Set<Hall> hallsForClub = hallRepositoryStub.findByClubId("nonExistentClubCode");

        assertTrue(hallsForClub.isEmpty(), "There should be no halls for a non-existent club");
    }

}
