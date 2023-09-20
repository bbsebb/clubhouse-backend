package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CoachRepositoryStubTest {

    private CoachRepositoryStub coachRepositoryStub;

    @BeforeEach
    void setUp() {
        coachRepositoryStub = new CoachRepositoryStub();
    }

    @Test
    void testSave() {
        PhoneNumber coachPhone = new PhoneNumber("1234567890");
        Coach footballCoach = new Coach(UUID.randomUUID(), "John Doe", coachPhone);
        Coach savedCoach = coachRepositoryStub.save(footballCoach);

        assertEquals(footballCoach, savedCoach, "The saved coach should match the original coach");
    }

    @Test
    void testFindCoachByKeysExisting() {
        PhoneNumber coachPhone = new PhoneNumber("1234567890");
        Coach footballCoach = new Coach(UUID.randomUUID(), "John Doe", coachPhone);
        coachRepositoryStub.save(footballCoach);

        Optional<Coach> foundCoach = coachRepositoryStub.findByKeys("John Doe");

        assertTrue(foundCoach.isPresent(), "The coach should be found");
        assertEquals(footballCoach, foundCoach.get(), "The found coach should match the saved coach");
    }

    @Test
    void testFindCoachByKeysNonExisting() {
        Optional<Coach> foundCoach = coachRepositoryStub.findByKeys("NonExistingName");
        assertFalse(foundCoach.isPresent(), "No coach should be found for a non-existing name");
    }

    @Test
    void testFindByIdExisting() {
        PhoneNumber coachPhone = new PhoneNumber("1234567890");
        UUID coachId = UUID.randomUUID();
        Coach footballCoach = new Coach(coachId, "John Doe", coachPhone);
        coachRepositoryStub.save(footballCoach);

        Optional<Coach> foundCoach = coachRepositoryStub.findById(coachId);

        assertTrue(foundCoach.isPresent(), "The coach should be found");
        assertEquals(footballCoach, foundCoach.get(), "The found coach should match the saved coach");
    }

    @Test
    void testFindByIdNonExisting() {
        Optional<Coach> foundCoach = coachRepositoryStub.findById(UUID.randomUUID());
        assertFalse(foundCoach.isPresent(), "No coach should be found for a non-existing ID");
    }
}
