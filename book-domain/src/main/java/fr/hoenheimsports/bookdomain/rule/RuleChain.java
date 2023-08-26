package fr.hoenheimsports.bookdomain.rule;

public abstract class RuleChain<T> implements IRule<T> {
    private RuleChain<T> nextRule;
    @SafeVarargs
    public static <T> RuleChain<T> buildChain(RuleChain<T> firstRule, RuleChain<T>... nextRules) {
        if (firstRule == null) {
            throw new IllegalArgumentException("The first rule cannot be null.");
        }
        RuleChain<T> head = firstRule;
        for(RuleChain<T> next : nextRules) {
            if (next == null) throw new IllegalArgumentException("A rule in the chain cannot be null.");
            head = head.setNext(next);
        }
        return firstRule;
    }

    public RuleChain<T> setNext(RuleChain<T> nextRule) {
        this.nextRule = nextRule;
        return nextRule;
    }

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
