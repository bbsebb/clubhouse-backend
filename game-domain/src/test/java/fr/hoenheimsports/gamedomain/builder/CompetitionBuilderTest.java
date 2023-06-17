package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Competition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CompetitionBuilderTest {


    @Test
    public void testBuildWithPoolConsumer() {
        UUID id = UUID.randomUUID();
        String name = "Competition";
        String poolNumber = "A";
        String poolName = "Pool A";

        Competition competition = new CompetitionBuilder()
                .withName(name)
                .withPool(poolBuilder -> poolBuilder
                        .withNumber(poolNumber)
                        .withName(poolName))
                .build();

        Assertions.assertEquals(name, competition.name());
        Assertions.assertEquals(poolNumber, competition.pools().get(0).code());
        Assertions.assertEquals(poolName, competition.pools().get(0).name());
    }
}