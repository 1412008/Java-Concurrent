package concurentMapDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class CMExample {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        ConcurrentNavigableMap<Integer, String> map = new ConcurrentSkipListMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        ConcurrentNavigableMap<Integer, String> headMap = map.headMap(3);

        System.out.println(headMap);

        map.replace(2, "hehe");

        System.out.println(headMap);

        System.out.println(map.subMap(1, 3));
    }
}
