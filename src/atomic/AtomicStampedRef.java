package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

class Test {
    public static final int STATE_UNINITIALIZED = -1;
    public static final int STATE_INITIALIZING_IN_PROGRESS = 2;
    public static final int STATE_INITIALIZED = 1;
    public Supplier<String> dataService = getDataService();
    public AtomicStampedReference<String> ref = new AtomicStampedReference<>(null, STATE_UNINITIALIZED);

    public void init() {
        if (ref.compareAndSet(null, null, STATE_UNINITIALIZED, STATE_INITIALIZING_IN_PROGRESS)) {
            String data = dataService.get();
            ref.compareAndSet(null, data, STATE_INITIALIZING_IN_PROGRESS, STATE_INITIALIZED);
        }
    }

    private static Supplier<String> getDataService() {
        return () -> {
            // sleep a little to simulate the service
            LockSupport.parkNanos(1000);
            return "test string";
        };
    }
}

public class AtomicStampedRef {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {// repeat process multiple times
            Test test = new Test();
            ExecutorService es = Executors.newFixedThreadPool(2);

            // reader thread
            es.execute(() -> {
                while (true) {
                    int[] state = new int[1];
                    String data = test.ref.get(state);
                    boolean dataNotNull = data != null;
                    // print unexpected
                    if (state[0] != Test.STATE_INITIALIZED && dataNotNull) {
                        System.out.printf("state: %s, data: %s\n", state[0], data);
                    }
                    // break on expected initialization
                    if (state[0] == Test.STATE_INITIALIZED && dataNotNull) {
                        System.out.printf("state: %s, data: %s\n", state[0], data);
                        break;
                    }
                    LockSupport.parkNanos(1);
                }
            });

            // writer thread
            es.execute(test::init);

            es.shutdown();
            es.awaitTermination(10, TimeUnit.SECONDS);
        }
        System.out.println("finished");
    }
}
