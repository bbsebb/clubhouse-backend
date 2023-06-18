package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.PhoneNumber;
import fr.hoenheimsports.gamedomain.model.Referee;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereeBuilderTest {
    @Test
    public void testRefereeBuilder() {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        UUID expectedId = UUID.randomUUID();
        String expectedName = "John Doe";


        Referee referee = refereeBuilder
                .withId(expectedId)
                .withName(expectedName)
                .build();

        assertEquals(expectedId, referee.id());
        assertEquals(expectedName, referee.name());
    }

    @Test
    public void testRefereeBuilderWithAutoGeneratedId() {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        String expectedName = "John Doe";


        Referee referee = refereeBuilder
                .withName(expectedName)
                .build();

        assertNotNull(referee.id());
        assertEquals(expectedName, referee.name());
    }

    @Test
    public void testRefereeBuilderWithPhoneNumberObject() {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        UUID expectedId = UUID.randomUUID();
        String expectedName = "John Doe";

        Referee referee = refereeBuilder
                .withId(expectedId)
                .withName(expectedName)
                .build();

        assertEquals(expectedId, referee.id());
        assertEquals(expectedName, referee.name());
    }

    @Test
    public void testRefereeBuilderWithPhoneNumberObjectAndAutoGeneratedId() {
        RefereeBuilder refereeBuilder = new RefereeBuilder();
        String expectedName = "John Doe";

        Referee referee = refereeBuilder
                .withName(expectedName)
                .build();

        assertNotNull(referee.id());
        assertEquals(expectedName, referee.name());
    }
}