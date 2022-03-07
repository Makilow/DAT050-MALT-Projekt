package blackjack.models;

/**
 * Player class, contains name and balance
 * @author Tor Falkenberg
 */
public class Player {
    private final String name;
    private double balance;
    
    /**
     * Constructor for Player
     * @param name  name of the player
     * @param balance balance of the player
     */
    public Player(String name, double balance) {
        if (name == null) { throw new NullPointerException();}
        this.name = name;
        this.balance = balance;
    }
    /**
     * Get function for balance of player
     * @return  the balance 
     */
    public double getBalance() { return balance; }
    /**
     * Change function, changes the balance with the given value
     * @param change    the value to change the balance with
     */
    public void changeBalance(double change) { this.balance += change; }
    /**
     * Get function for the name of the player
     * @return  the name
     */
    public String getName() { return name; }

}
