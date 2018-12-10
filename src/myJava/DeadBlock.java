package myJava;

public class DeadBlock {
    public static void main(String[] args) throws InterruptedException {
        final Object A = "a";
        final Object B = "b";

        Runnable block1 = new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    System.out.println("Thread 1: locked A");
                    synchronized (B) {
                        System.out.println("Thread 1: locked B");
                        try {
                            A.wait();
                            System.out.println("Thread 1: re-acquired A");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread 1: unlocked B");
                    }
                    System.out.println("Thread 1: unlocked A");
                }
            }
        };

        Runnable block2 = new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    System.out.println("Thread 2: locked A");
                    synchronized (B) {
                        System.out.println("Thread 2: locked B");
                        System.out.println("Thread 2: unlocked B");
                    }
                    System.out.println("Thread 2: unlocked A");
                }
            }
        };

        Runnable block3 = new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    System.out.println("Thread 3: locked A");
                    System.out.println("Thread 3: called A.notify");
                    A.notify();
                    System.out.println("Thread 3: unlocked A");
                }
            }
        };

        Thread a = new Thread(block1, "A");
        a.start();

        // Thread b = new Thread(block2, "B");
        // b.start();

        Thread.sleep(300);
        Thread c = new Thread(block3, "C");
        c.start();
    }
}
