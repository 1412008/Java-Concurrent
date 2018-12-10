package blockingQueueDemo;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Take: " + queue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + queue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + queue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + queue.take());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
