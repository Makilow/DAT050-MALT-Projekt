package blackjack;
/**
 * Observer interface
 */
public interface Observer<T> {
    void update(T observable);
}
