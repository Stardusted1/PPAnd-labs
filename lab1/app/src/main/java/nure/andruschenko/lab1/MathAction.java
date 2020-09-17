package nure.andruschenko.lab1;

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
