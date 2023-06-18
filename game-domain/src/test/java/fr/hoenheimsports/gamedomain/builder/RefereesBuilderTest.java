package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Referee;
import fr.hoenheimsports.gamedomain.model.Referees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RefereesBuilderTest {
    private RefereesBuilder refereesBuilder;
    private String expectedReferee1Name;
    private String expectedReferee2Name;
    private String expectedReferee3Name;
    private String expectedReferee4Name;


    @BeforeEach
    public void setup() {
        refereesBuilder = new RefereesBuilder();
        expectedReferee1Name = "John Doe";
        expectedReferee2Name = "Jane Smith";
        expectedReferee3Name = "Mike Johnson";
        expectedReferee4Name = "Sarah Brown";

    }

    @Test
    public void testBuilderMethod() {
        RefereesBuilder refereesBuilder1 = RefereesBuilder.builder();
        assertNotNull(refereesBuilder1);
    }

    @Test
    public void testRefereesBuilder() {
        Referees referees = refereesBuilder
                .withDesignatedReferee1(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee1Name))
                .withDesignatedReferee2(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee2Name))
                .withOfficiatingReferee1(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee3Name))
                .withOfficiatingReferee2(refereeBuilder -> refereeBuilder
                        .withName(expectedReferee4Name))
                .build();

        assertNotNull(referees.designatedReferee1());
        assertNotNull(referees.designatedReferee1().id());
        assertEquals(expectedReferee1Name, referees.designatedReferee1().name());

        assertNotNull(referees.designatedReferee2());
        assertNotNull(referees.designatedReferee2().id());
        assertEquals(expectedReferee2Name, referees.designatedReferee2().name());

        assertNotNull(referees.officiatingReferee1());
        assertNotNull(referees.officiatingReferee1().id());
        assertEquals(expectedReferee3Name, referees.officiatingReferee1().name());

        assertNotNull(referees.officiatingReferee2());
        assertNotNull(referees.officiatingReferee2().id());
        assertEquals(expectedReferee4Name, referees.officiatingReferee2().name());
    }

    @Test
    public void testRefereesBuilderWithRefereeObjects() {
        Referee expectedReferee1 = new Referee(
                UUID.randomUUID(),
                "Referee1"
        );
        Referee expectedReferee2 = new Referee(
                UUID.randomUUID(),
                "Referee2"
        );
        Referee expectedReferee3 = new Referee(
                UUID.randomUUID(),
                "Referee3"
        );
        Referee expectedReferee4 = new Referee(
                UUID.randomUUID(),
                "Referee4"
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