
package MiningGame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Gamer thread
 *
 * @author Kaitlyn Zavala-Salazar
 */
public class Gamer implements Runnable {
    private String name;
    private List<Coin> coins;
    private BlockingQueue<Coin> queue;
    private static int counter = 0;

    public Gamer(BlockingQueue<Coin> queue) {
        this.queue = queue;
        this.coins = new ArrayList<>();
        this.counter++;
        this.name = "Gamer" + this.counter;
    }

    @Override
    public void run() {
        int waitTries = 0;
        while (coins.size() <= 20) {
            if (!this.queue.isEmpty() && waitTries < 3) {
                ArrayList<Coin> drops = new ArrayList<>();
                this.queue.drainTo(drops, 10);
                System.out.println(this.name + " took " + drops.size() + " coin(s) from the room.");
                Thread.yield();
                while (drops.size() > 0) {
                    this.coins.add(drops.remove(0));
                }
                System.out.println(this.name + " now has " + this.coins.size() + " coin(s).");
            } else if (waitTries >= 3) {
                System.out.println(this.name + " waited 3 times already. Quiting now...");
                return;
            } else {
                try {
                    Thread.sleep(7000);
                    waitTries += 1;
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
