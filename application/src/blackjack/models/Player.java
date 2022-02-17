package blackjack.models;

/**
 * Player class representing each player and stats
 * @author Tor Falkenberg
 */
public class Player {

    private String name;
    private int money;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public int getBalance() { return money; }
    public void changeBalance(int change) { this.money = money+change; }
    public String getName() { return name; }

}
