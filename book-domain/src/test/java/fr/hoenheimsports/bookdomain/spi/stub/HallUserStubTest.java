package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HallUserStubTest {
    private HallUserStub hallUserStub;
    private final Address testAddress = new Address("rue des Vosges", 67800, "Hoenheim");

    @BeforeEach
    void setUp() {
        hallUserStub = new HallUserStub();
    }

    @Test
    void testSaveAndFindRegisteredUser() {
        Tenant tenant = new Tenant(UUID.randomUUID(), "TestUser", "test@example.com", testAddress, true);
        hallUserStub.save(tenant);

        Optional<Tenant> foundUser = hallUserStub.findRegisteredUserById(tenant.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(tenant, foundUser.get());
    }

    @Test
    void testSaveAndFindUnregisteredUser() {
        AssociationHallUser user = new AssociationHallUser(UUID.randomUUID(), "AssocUser", "assoc@example.com");
        hallUserStub.save(user);

        Optional<AssociationHallUser> foundUser = hallUserStub.findUnregisteredUserById(user.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testFindById() {
        Tenant tenant = new Tenant(UUID.randomUUID(), "TestUser", "test@example.com", testAddress, true);
        hallUserStub.save(tenant);

        Optional<HallUser> foundUser = hallUserStub.findById(tenant.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(tenant, foundUser.get());
    }
}