package blackjack.views;

/**
 * State enum, state for all the different panels (CardLayout)
 * @author Lukas Wigren
 */
public enum State {
    MENU("MENU"),
    SETTINGS("SETTINGS"),
    CHAT("CHAT"),
    RULES("RULES"),
    GAME("GAME"),
    SCOREBOARD("SCOREBOARD"),
    EXIT("EXIT");

    private final String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
