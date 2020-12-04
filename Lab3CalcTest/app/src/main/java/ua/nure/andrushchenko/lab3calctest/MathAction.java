package ua.nure.andrushchenko.lab3calctest;

public enum MathAction {
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*");

    String action;

    MathAction(String action) {
        this.action = action;
    }

    public static MathAction get(String abbreviation) {
        for (MathAction action : values()) {
            if (action.action.equals(abbreviation)) {
                return action;
            }
        }
        return null;
    }
}
