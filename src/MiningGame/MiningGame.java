/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiningGame;

/**
 *
 * @author sscortez16
 */

import java.util.*;
import java.io.*;
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
        BlockingQueue<Coin> blockingQueue = new ArrayBlockingQueue<>(40);
    
        Miner prod = new Miner(blockingQueue);
        Gamer cons = new Gamer(blockingQueue);

        Thread prodThread = new Thread(prod);
        Thread consThread = new Thread(cons);

        prodThread.start();
        consThread.start();
    }
} 
