package fr.hoenheimsports.bookdomain.rule;

import fr.hoenheimsports.bookdomain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateBookingStateRuleTest {
    private CreateBookingStateRule rule;

    @BeforeEach
    void setUp() {
        rule = new CreateBookingStateRule();
    }

    @Test
    void testMatches_withNonNullUser() {
        HallUser user = new AssociationHallUser(UUID.randomUUID(),"username","email"); // or other HallUser implementation
        assertTrue(rule.matches(user));
    }

    @Test
    void testMatches_withNullUser() {
        assertFalse(rule.matches(null));
    }

    @Test
    void testApply_withAssociationHallUser() {
        HallUser user = new AssociationHallUser(UUID.randomUUID(),"username","email");
        assertEquals(BookingState.VALIDATED, rule.apply(user));
    }

    @Test
    void testApply_withTenant() {
        HallUser user = new Tenant(UUID.randomUUID(),"username","email",new Address("street",0,"city"));
        assertEquals(BookingState.PENDING, rule.apply(user));
    }

    @Test
    void testApply_withNullUser() {
        assertThrows(IllegalArgumentException.class, () -> rule.apply(null));
    }

    @Test
    void testApply_withUnknownUserType() {
        // This assumes that there is another type of HallUser that is not handled
        HallUser user = new HallUser() {
            @Override
            public UUID id() {
                return null;
            }

            @Override
            public String username() {
                return null;
            }

            @Override
            public String email() {
                return null;
            }
        };
        assertThrows(IllegalStateException.class, () -> rule.apply(user));
    }
}