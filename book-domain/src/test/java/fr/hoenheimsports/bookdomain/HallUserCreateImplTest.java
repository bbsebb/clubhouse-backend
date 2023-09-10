package fr.hoenheimsports.bookdomain;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.bookdomain.spi.stub.HallUserStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HallUserCreateImplTest {
    private HallUserStub hallUserStub;
    private HallUserCreateImpl hallUserCreate;

    @BeforeEach
    void setUp() {
        hallUserStub = new HallUserStub();
        hallUserCreate = new HallUserCreateImpl(hallUserStub);
    }

    @Test
    void testCreateUnregisteredUser() {
        String username = "testUser";
        String email = "test@test.com";
        Address address = new Address("1 Test Street", 67888, "Test City"); // Supposant que vous avez un constructeur approprié pour Address
        boolean isMembre = true;

        HallUser createdUser = hallUserCreate.createUnregisteredUser(username, email, address, isMembre);

        assertTrue(createdUser instanceof Tenant);
        assertEquals(username, createdUser.getUsername());
        assertEquals(email, createdUser.getEmail());
    }

    @Test
    void testCreateRegisteredUser() {
        UUID id = UUID.randomUUID();
        String username = "registeredUser";
        String email = "registered@test.com";

        HallUser createdUser = hallUserCreate.createRegisteredUser(id, username, email);

        assertTrue(createdUser instanceof AssociationHallUser);
        assertEquals(id, createdUser.getId());
        assertEquals(username, createdUser.getUsername());
        assertEquals(email, createdUser.getEmail());
    }

    @Test
    void testFindByUd() {
        UUID id = UUID.randomUUID();
        String username = "findUser";
        String email = "find@test.com";

        hallUserStub.save(new AssociationHallUser(id, username, email));
        Optional<HallUser> foundUserOpt = hallUserCreate.findByUd(id);

        assertTrue(foundUserOpt.isPresent());
        assertEquals(id, foundUserOpt.get().getId());
        assertEquals(username, foundUserOpt.get().getUsername());
        assertEquals(email, foundUserOpt.get().getEmail());
    }
}