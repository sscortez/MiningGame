/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        while (coins.size() <= 20) {
            System.out.println("Miner has " + coins.size() + " coin(s).");
            int capacity = this.queue.remainingCapacity();
            System.out.println("Remaining available coin slots: " + capacity);
            
            if (capacity > 0) {
                try {
                    this.queue.put(coins.remove(0));
                    System.out.println("Added coin to room.");
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
