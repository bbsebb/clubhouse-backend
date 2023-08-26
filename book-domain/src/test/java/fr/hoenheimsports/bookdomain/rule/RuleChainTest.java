package fr.hoenheimsports.bookdomain.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleChainTest {
    private RuleChain<Integer> doubleIfEven;
    private RuleChain<Integer> increment;

    @BeforeEach
    public void setup() {
        doubleIfEven = new RuleChain<>() {
            @Override
            public boolean matches(Integer integer) {
                return integer % 2 == 0;
            }

            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };

        increment = new RuleChain<>() {
            @Override
            public boolean matches(Integer integer) {
                return true;
            }

            @Override
            public Integer apply(Integer integer) {
                return integer + 1;
            }
        };
    }

    @Test
    public void testSingleRuleMatching() {
        assertEquals(4, doubleIfEven.handle(2));
    }

    @Test
    public void testSingleRuleNotMatching() {
        assertEquals(3, doubleIfEven.handle(3)); // The rule doesn't match, so the number is unchanged.
    }

    @Test
    public void testChainedRulesBothMatching() {
        doubleIfEven.setNext(increment);
        assertEquals(5, doubleIfEven.handle(2));  // 2 is doubled to 4, then incremented to 5.
    }

    @Test
    public void testChainedRulesFirstNotMatching() {
        doubleIfEven.setNext(increment);
        assertEquals(4, doubleIfEven.handle(3));  // 3 is not doubled, but is incremented to 4.
    }

}