package blockingQueueDemo;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("1");
            System.out.println(1);
            Thread.sleep(1000);
            queue.put("2");
            System.out.println(2);
            Thread.sleep(1000);
            queue.put("3");
            System.out.println(3);
            Thread.sleep(1000);
            queue.put("4");
            System.out.println(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
