
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
            if (!this.queue.isEmpty()) {
                try {
                    ArrayList<Coin> drops = new ArrayList<>();
                    this.queue.drainTo(drops);
                    System.out.println("Took " + drops.size() + " coin(s) from the room.");
                    Thread.sleep(1000);
                    while (drops.size() > 0) {
                        this.coins.add(drops.remove(0));
                    }
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
