package atomic;

import java.util.concurrent.atomic.AtomicReference;

public class RefAtomic {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        AtomicReference<String> atomicReference = new AtomicReference<String>(a);
        
        System.out.println(atomicReference.compareAndSet(a, "ok"));
    }
}
