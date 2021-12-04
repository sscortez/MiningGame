
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
                    new Coin()
            )
    );
    private BlockingQueue<Coin> queue;
    private static int counter = 0;

    public Miner(BlockingQueue<Coin> queue) {
        this.queue = queue;
        this.counter++;
        this.name = "Miner" + this.counter;
    }

    @Override
    public void run() {
        int refills = 0;
        while (true) {
            if (coins.size() > 0 && refills < 3) {
                int capacity = this.queue.remainingCapacity();

                if (capacity > 0) {
                    try {
                        this.queue.put(coins.remove(0));
                        System.out.println(this.name + " added coin to room.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Thread.yield();
                }
            } else if (refills >= 3) {
                System.out.println(this.name + " refilled 3 times already. Done working now...");
                return;
            } else if (coins.size() == 0) {
                this.coins.add(new Coin());
                this.coins.add(new Coin());
                this.coins.add(new Coin());
                this.coins.add(new Coin());
                this.coins.add(new Coin());
                refills+=1;
                System.out.println(this.name + " refilled.");
                Thread.yield();
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
