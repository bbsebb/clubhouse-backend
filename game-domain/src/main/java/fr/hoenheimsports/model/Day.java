package fr.hoenheimsports.model;

public record Day(int number) {
    public static final Day SINGLE_DAY_GAME = new Day(0);
    public Day {
        if(number<0) {
            throw new IllegalArgumentException("The day must be equal or greater than 0");
        }
    }
}
