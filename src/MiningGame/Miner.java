
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
    private BlockingQueue<Coin> room;
    private ArrayList<Coin> coins = new ArrayList<Coin>(
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
                    new Coin()
                    )
    );

    public Miner(BlockingQueue room) {
        this.name = "Miner";
        this.room = room;
    }

    @Override
    public void run() {
        while (coins.size() > 0) {
            System.out.println("Current coins in Miner: " + coins.size());
            int capacity = this.room.remainingCapacity();
            System.out.println("There are " + capacity + " available coin slot(s) in the room.");
            if (capacity > 0) {
                try {
                    Coin element = coins.remove(0);
                    this.room.put(element);
                    System.out.println("Added a coin");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println("Sleep for 0.5 seconds.");
                    Thread.sleep(500);  // Sleep for 0.5 seconds
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
