package fr.hoenheimsports.bookdomain.rule;

/**
 * A rule chain is a chain of rules
 * @param <T> the type of the rule
 */
public abstract class RuleChain<T> implements IRule<T> {
    private RuleChain<T> nextRule;

    /**
     * Build a chain of rules
     * @param firstRule the first rule
     * @param nextRules the next rules
     * @return the first rule
     */
    @SafeVarargs
    public static <T> RuleChain<T> buildChain(RuleChain<T> firstRule, RuleChain<T>... nextRules) {
        if (firstRule == null) {
            throw new IllegalArgumentException("The first rule cannot be null.");
        }
        RuleChain<T> head = firstRule;
        for (RuleChain<T> next : nextRules) {
            if (next == null) throw new IllegalArgumentException("A rule in the chain cannot be null.");
            head = head.setNext(next);
        }
        return firstRule;
    }

    /**
     * Set the next rule
     * @param nextRule the next rule
     * @return the next rule
     */
    public RuleChain<T> setNext(RuleChain<T> nextRule) {
        this.nextRule = nextRule;
        return nextRule;
    }

    /**
     * Handle the rule
     * @param t the object to handle
     * @return the object after the rule has been applied
     */
    public T handle(T t) {
        if (matches(t)) {
            t = apply(t);
        }
        if (nextRule != null) {
            return nextRule.handle(t);
        }
        return t;
    }


}
