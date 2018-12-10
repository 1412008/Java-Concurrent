package cyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CyclicBarrierExp {
    public static void main(String[] args) {
        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier 1 executed.");
            }
        };

        CyclicBarrier cb = new CyclicBarrier(3, barrierAction);
        Semaphore semaphore = new Semaphore(3);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    
                    semaphore.acquire();
                    System.out.println("Thread " + Thread.currentThread().getName() + " acquired permission.");
                    Thread.sleep(500);
                    cb.await();
                    semaphore.release();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable, "A").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "C").start();
        System.out.println("abc");
    }
}
