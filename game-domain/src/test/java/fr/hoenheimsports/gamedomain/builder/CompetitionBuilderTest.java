package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.model.Pool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CompetitionBuilderTest {

    @Test
    public void testBuilderMethod() {
        CompetitionBuilder competitionBuilder = CompetitionBuilder.builder();
        assertNotNull(competitionBuilder);
    }
    @Test
    public void testBuildWithPoolConsumer() {
        String name = "Competition";
        String poolNumber = "A";
        String poolName = "Pool A";

        Competition competition = new CompetitionBuilder()
                .withName(name)
                .withPool(poolBuilder -> poolBuilder
                        .withCode(poolNumber)
                        .withName(poolName))
                .build();

        Assertions.assertEquals(name, competition.name());
        Assertions.assertEquals(poolNumber, competition.pools().get(0).code());
        Assertions.assertEquals(poolName, competition.pools().get(0).name());
    }
    @Test
    public void testBuildWithPoolConsumerObject() {
        String exceptedName = "Competition";
        String exceptedPoolNumber = "A";
        String exceptedPoolName = "Pool A";
        Pool exceptedPool = PoolBuilder.builder().withCode(exceptedPoolNumber).withName(exceptedPoolName).build();

        Competition competition = new CompetitionBuilder()
                .withName(exceptedName)
                .withPool(exceptedPool)
                .build();

        Assertions.assertEquals(exceptedName, competition.name());
        Assertions.assertEquals(exceptedPoolNumber, competition.pools().get(0).code());
        Assertions.assertEquals(exceptedPoolName, competition.pools().get(0).name());
    }
}