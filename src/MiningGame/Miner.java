
package MiningGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author sscortez16
 */
public class Miner implements Runnable {
    private String name;
    private ArrayList<Coin> coins = new ArrayList<>(
            List.of(
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin(),
                    new Coin()
            )
    );
    private BlockingQueue<Coin> queue;

    public Miner(BlockingQueue<Coin> queue) {
        this.name = "Miner";
        this.queue = queue;
    }

    @Override
    public void run() {
        while (coins.size() > 0) {
            System.out.println("Miner has " + coins.size() + " coin(s).");
            int capacity = this.queue.remainingCapacity();
            System.out.println("Remaining available coin slots: " + capacity);
            
            if (capacity > 0) {
                try {
                    this.queue.put(coins.remove(0));
                    System.out.println("Added coin to room.");
                    System.out.println("Coins in room: " + this.queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(500);
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
