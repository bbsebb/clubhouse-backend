package fr.hoenheimsports.bookdomain.rule;

public interface IRule<I,O> {
    boolean matches(I t);
    O apply(I t);

}
