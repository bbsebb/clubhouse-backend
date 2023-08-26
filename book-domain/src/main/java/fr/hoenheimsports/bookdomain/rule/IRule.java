package fr.hoenheimsports.bookdomain.rule;

public interface IRule<T> {
    boolean matches(T t);
    T apply(T t);

}
