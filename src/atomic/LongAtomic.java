package atomic;

import java.util.concurrent.atomic.AtomicLong;

public class LongAtomic {
    public static void main(String[] args) {
        AtomicLong al = new AtomicLong();
        al.set(300);
        long tmp = al.getAndAdd(200);
        tmp = al.getAndIncrement();
        System.out.println(al + " - " + tmp);
    }
}
