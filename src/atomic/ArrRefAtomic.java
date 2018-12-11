package atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class ArrRefAtomic {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        AtomicReferenceArray<String> arr = new AtomicReferenceArray<>(5);
        arr.set(0, "hello");
        arr.set(1, "test");
        arr.set(2, a);
        arr.set(3, "concurrent");
        arr.set(4, "azla");
        System.out.println(arr);
        System.out.println(arr.compareAndSet(2, a, b));
        System.out.println(arr.compareAndSet(2, a, b));
    }
}
