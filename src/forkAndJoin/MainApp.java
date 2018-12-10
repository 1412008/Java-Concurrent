package forkAndJoin;

import java.util.concurrent.ForkJoinPool;

public class MainApp {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        
        //MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        //forkJoinPool.invoke(myRecursiveAction);
        
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);
        System.out.println(forkJoinPool.invoke(myRecursiveTask));
        
    }
}
