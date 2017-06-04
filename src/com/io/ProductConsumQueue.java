package com.io;

import java.io.IOException;
import java.util.Random;

public class ProductConsumQueue {

	public static void main(String[] args) throws IOException {
		//BasicQueue queue=new BasicQueue("file","queue.txt");
	}

}

class Consumer {
    public static void main(String[] args) throws InterruptedException {
        try {
            BasicQueue queue = new BasicQueue("file", "task");
            Random rnd = new Random();
            while (true) {
                byte[] bytes = queue.dequeue();
                if (bytes == null) {
                    Thread.sleep(rnd.nextInt(1000)+1000);
                    continue;
                }
                System.out.println("consume: " + new String(bytes, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Producer {
	
    public static void main(String[] args) throws InterruptedException {
        try {
            BasicQueue queue = new BasicQueue("file", "task");
            int i = 0;
            Random rnd = new Random();
            while (true) {
                String msg = new String("task " + (i++));
                queue.enqueue(msg.getBytes("UTF-8"));
                System.out.println("produce: " + msg);
                Thread.sleep(rnd.nextInt(1000)+1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}