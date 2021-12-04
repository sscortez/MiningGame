/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiningGame;

/**
 *
 * @author Samuel Cortez
 */

import java.util.concurrent.*;

// We would like to synchronize producer and consumer so that
// producer puts a number in the buffer, then the consumer takes it
// out, then the producer puts another number, and so on.

// This solution provides the right behaviour
// We have changed the class Buffer to include wait() and notify()
// We also have changed the producer and cinsumer classes
// slightly to handle a new exception

public class MiningGame {
    public static void main (String [] args) {
        final int MINERS = 20;
        
        BlockingQueue<Coin> blockingQueue = new ArrayBlockingQueue<>(40);
    
        for (int index = 1; index < MINERS + 1; index++) {
            Thread newprodThread = new Thread(new Miner(blockingQueue));
            newprodThread.start();
            if (index % 2 == 0) {
                Thread newconsThread = new Thread(new Gamer(blockingQueue));
                newconsThread.start();
            }
        }
    }
} 
