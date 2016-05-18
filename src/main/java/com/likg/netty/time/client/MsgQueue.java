package com.likg.netty.time.client;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by likg on 2016-05-18.
 */
public class MsgQueue {

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    public static BlockingQueue<String> getQueue(){
        return queue;
    }

    public void createMsg(){
        try {
            while (true) {
                Random random = new Random();
                String msg = "msg" + random.nextInt(10000);
                queue.put(msg);

                System.out.println("createMsg ===" + msg);

                Thread.sleep(random.nextInt(1000));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
