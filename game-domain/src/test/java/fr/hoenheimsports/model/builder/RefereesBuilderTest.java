package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.PhoneNumber;
import fr.hoenheimsports.model.Referee;
import fr.hoenheimsports.model.Referees;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereesBuilderTest {

    @Test
    public void testRefereesBuilder() {
        RefereesBuilder refereesBuilder = new RefereesBuilder();
        String expectedReferee1Name = "John Doe";
        String expectedReferee2Name = "Jane Smith";
        String expectedReferee3Name = "Mike Johnson";
        String expectedReferee4Name = "Sarah Brown";
        String expectedReferee1PhoneNumber = "1234567890";
        String expectedReferee2PhoneNumber = "0987654321";
        String expectedReferee3PhoneNumber = "0987654323";
        String expectedReferee4PhoneNumber = "0987654324";

        Referees referees = refereesBuilder
                .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee1Name)
                        .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(expectedReferee1PhoneNumber)))
                .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee2Name)
                        .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(expectedReferee2PhoneNumber)))
                .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee3Name)
                        .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(expectedReferee3PhoneNumber)))
                .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee4Name)
                        .withPhoneNumber(phoneNumberBuilder -> phoneNumberBuilder.withPhoneNumber(expectedReferee4PhoneNumber)))
                .build();

        assertNotNull(referees.designatedReferee1());
        assertNotNull(referees.designatedReferee1().id());
        assertEquals(expectedReferee1Name, referees.designatedReferee1().name());
        assertEquals(expectedReferee1PhoneNumber, referees.designatedReferee1().phoneNumber().phoneNumber());

        assertNotNull(referees.designatedReferee2());
        assertNotNull(referees.designatedReferee2().id());
        assertEquals(expectedReferee2Name, referees.designatedReferee2().name());
        assertEquals(expectedReferee2PhoneNumber, referees.designatedReferee2().phoneNumber().phoneNumber());

        assertNotNull(referees.officiatingReferee1());
        assertNotNull(referees.officiatingReferee1().id());
        assertEquals(expectedReferee3Name, referees.officiatingReferee1().name());
        assertEquals(expectedReferee3PhoneNumber, referees.officiatingReferee1().phoneNumber().phoneNumber());

        assertNotNull(referees.officiatingReferee2());
        assertNotNull(referees.officiatingReferee2().id());
        assertEquals(expectedReferee4Name, referees.officiatingReferee2().name());
        assertEquals(expectedReferee4PhoneNumber, referees.officiatingReferee2().phoneNumber().phoneNumber());
    }

    @Test
    public void testRefereesBuilderWithRefereeObjects() {
        RefereesBuilder refereesBuilder = new RefereesBuilder();
        Referee expectedReferee1 = new Referee(
                UUID.randomUUID(),
                "Referee1",
                new PhoneNumber("1111111111")
        );
        Referee expectedReferee2 = new Referee(
                UUID.randomUUID(),
                "Referee2",
                new PhoneNumber("2222222222")
        );
        Referee expectedReferee3 = new Referee(
                UUID.randomUUID(),
                "Referee3",
                new PhoneNumber("3333333333")
        );
        Referee expectedReferee4 = new Referee(
                UUID.randomUUID(),
                "Referee4",
                new PhoneNumber("4444444444")
        );

        Referees referees = refereesBuilder
                .withDesignatedReferee1(expectedReferee1)
                .withDesignatedReferee2(expectedReferee2)
                .withOfficiatingReferee1(expectedReferee3)
                .withOfficiatingReferee2(expectedReferee4)
                .build();

        assertEquals(expectedReferee1, referees.designatedReferee1());
        assertEquals(expectedReferee2, referees.designatedReferee2());
        assertEquals(expectedReferee3, referees.officiatingReferee1());
        assertEquals(expectedReferee4, referees.officiatingReferee2());
    }
}