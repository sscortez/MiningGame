
package MiningGame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.Objects;

/**
 * Gamer thread
 *
 * @author kzavala
 */
public class Gamer implements Runnable {
    private String name;
    private List<Coin> coins;
    private BlockingQueue<Coin> queue;

    public Gamer(BlockingQueue<Coin> queue) {
        this.name = "Gamer";
        this.queue = queue;
        this.coins = new ArrayList<>();
    }

    @Override
    public void run() {
        while (coins.size() <= 20) {
            
            Coin element = null;
            element = this.queue.peek();
            System.out.println("Peeked element: " + element);
            
            if (!Objects.isNull(element)) {
                try {
                    Coin coin = this.queue.take();
                    System.out.println("Took a coin from the room.");
                    this.coins.add(coin);
                    System.out.println("Gamer now has " + this.coins.size() + " coin(s).");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coin> getCoins() {
        return coins;
    }

}
