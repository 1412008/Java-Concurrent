package myJava;

class MyRunnable implements Runnable {
	private int counter = 0;

	@Override
	public void run() {
		counter++;
		System.out.println(
				new StringBuilder().append(Thread.currentThread().getName()).append(": Counter: ").append(counter));
	}
	
}

public class RunnableDemo {	
	public static void main(String[] args) throws InterruptedException {
		MyRunnable mc = new MyRunnable();
		Thread a = new Thread(mc, "A");
		//mc = new MyRunnable();
		Thread b = new Thread(mc, "B");
		a.start();
		Thread.sleep(300);
		b.start();
	}
}
