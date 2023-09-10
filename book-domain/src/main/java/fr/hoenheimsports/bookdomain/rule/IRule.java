package fr.hoenheimsports.bookdomain.rule;

/**
 * A rule is a predicate and a function
 * @param <T> the type of the rule
 */
public interface IRule<T> {
    /**
     * Check if the rule matches
     * @param t the object to check
     * @return true if the rule matches
     */
    boolean matches(T t);

    /**
     * Apply the rule
     * @param t the object to apply the rule to
     * @return the object after the rule has been applied
     */
    T apply(T t);

}
