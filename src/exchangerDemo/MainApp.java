package exchangerDemo;

import java.util.concurrent.Exchanger;

public class MainApp {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1, "A").start();
        new Thread(exchangerRunnable2, "B").start();
    }
}
