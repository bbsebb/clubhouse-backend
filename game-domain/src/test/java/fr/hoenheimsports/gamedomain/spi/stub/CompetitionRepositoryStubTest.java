package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.model.Competition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionRepositoryStubTest {

    private CompetitionRepositoryStub competitionRepositoryStub;

    @BeforeEach
    void setUp() {
        competitionRepositoryStub = new CompetitionRepositoryStub();
    }

    @Test
    void testSave() {
        Competition soccerCompetition = new Competition("Soccer World Cup");
        Competition savedCompetition = competitionRepositoryStub.save(soccerCompetition);

        assertEquals(soccerCompetition, savedCompetition, "The saved competition should match the original competition");
    }

    @Test
    void testFindByIdExisting() {
        Competition soccerCompetition = new Competition("Soccer World Cup");
        competitionRepositoryStub.save(soccerCompetition);

        Optional<Competition> foundCompetition = competitionRepositoryStub.findById("Soccer World Cup");

        assertTrue(foundCompetition.isPresent(), "The competition should be found");
        assertEquals(soccerCompetition, foundCompetition.get(), "The found competition should match the saved competition");
    }

    @Test
    void testFindByIdNonExisting() {
        Optional<Competition> foundCompetition = competitionRepositoryStub.findById("NonExistingCompetition");
        assertFalse(foundCompetition.isPresent(), "No competition should be found for a non-existing name");
    }
}
