package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Referee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereeRepositoryStubTest {

    private RefereeRepositoryStub refereeRepositoryStub;

    @BeforeEach
    void setUp() {
        refereeRepositoryStub = new RefereeRepositoryStub();
    }

    @Test
    void testSave() {
        Referee referee = new Referee(UUID.randomUUID(), "John Doe");
        Referee savedReferee = refereeRepositoryStub.save(referee);
        assertEquals(referee, savedReferee, "The saved referee should match the original referee");
    }

    @Test
    void testFindById() {
        Referee referee = new Referee(UUID.randomUUID(), "John Doe");
        refereeRepositoryStub.save(referee);
        Optional<Referee> foundReferee = refereeRepositoryStub.findById(referee.id());

        assertTrue(foundReferee.isPresent(), "Referee should be found by ID");
        assertEquals(referee, foundReferee.get(), "Found referee should match the saved referee");
    }

    @Test
    void testFindByKeys() {
        Referee referee = new Referee(UUID.randomUUID(), "John Doe");
        refereeRepositoryStub.save(referee);
        Optional<Referee> foundReferee = refereeRepositoryStub.findByKeys("John Doe");

        assertTrue(foundReferee.isPresent(), "Referee should be found by name");
        assertEquals(referee, foundReferee.get(), "Found referee should match the saved referee");
    }
}

