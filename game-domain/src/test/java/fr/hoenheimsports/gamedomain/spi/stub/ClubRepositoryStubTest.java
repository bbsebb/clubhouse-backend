package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClubRepositoryStubTest {

    private ClubRepositoryStub clubRepositoryStub;

    @BeforeEach
    void setUp() {
        clubRepositoryStub = new ClubRepositoryStub();
    }

    @Test
    void testSave() {
        Set<Hall> hallsSet = Set.of(Hall.UNKNOWN);
        Club sportsClub = new Club("code123", "Sports Club", hallsSet);
        Club savedClub = clubRepositoryStub.save(sportsClub);

        assertEquals(sportsClub, savedClub, "The saved club should match the original club");
    }

    @Test
    void testFindAllClub() {
        Set<Hall> hallsSet = Set.of(Hall.UNKNOWN);
        Club sportsClub = new Club("code123", "Sports Club", hallsSet);
        Club musicClub = new Club("code456", "Music Club", hallsSet);

        clubRepositoryStub.save(sportsClub);
        clubRepositoryStub.save(musicClub);

        var allClubs = clubRepositoryStub.findAll();

        assertTrue(allClubs.contains(sportsClub), "The list should contain the sports club");
        assertTrue(allClubs.contains(musicClub), "The list should contain the music club");
        assertEquals(2, allClubs.size(), "There should be two clubs in the list");
    }

    @Test
    void testFindByCodeExisting() {
        Set<Hall> hallsSet = Set.of(Hall.UNKNOWN);
        Club sportsClub = new Club("code123", "Sports Club", hallsSet);
        clubRepositoryStub.save(sportsClub);

        Optional<Club> foundClub = clubRepositoryStub.findById("code123");

        assertTrue(foundClub.isPresent(), "The club should be found");
        assertEquals(sportsClub, foundClub.get(), "The found club should match the saved club");
    }

    @Test
    void testFindByCodeNonExisting() {
        Optional<Club> foundClub = clubRepositoryStub.findById("NonExistingCode");
        assertFalse(foundClub.isPresent(), "No club should be found for a non-existing code");
    }
}
