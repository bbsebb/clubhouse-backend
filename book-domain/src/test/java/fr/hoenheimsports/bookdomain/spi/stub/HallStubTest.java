package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HallStubTest {
    private HallStub hallStub;
    private Hall testHall;

    @BeforeEach
    void setUp() {
        hallStub = new HallStub();

        Address testAddress = new Address("rue des Vosges", 67800, "Hoenheim");
        testHall = new Hall(UUID.randomUUID(), "club house", testAddress, 0);

        hallStub.getHalls().put(testHall.id(), testHall);
    }

    @Test
    void testFindAll() {
        List<Hall> allHalls = hallStub.findAll();
        assertNotNull(allHalls);
        assertTrue(allHalls.contains(testHall));
    }

    @Test
    void testFindById() {
        Optional<Hall> foundHallOpt = hallStub.findById(testHall.id());

        assertTrue(foundHallOpt.isPresent());
        assertEquals(testHall, foundHallOpt.get());
    }
}