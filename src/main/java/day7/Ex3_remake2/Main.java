package day7.Ex3_remake2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    static final int THREAD_SIZE = 10;
    static final int MAX_VALUE = 100;
    public static volatile int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Mythread[] mythreads = new Mythread[THREAD_SIZE];
        CountDivisor divisor = new CountDivisor();

        List<Task> taskList = divisor.taskList(THREAD_SIZE, MAX_VALUE);
        long time_start = System.currentTimeMillis();
        int i = 0;
        for (Mythread thread : mythreads) {
            thread = new Mythread(divisor, taskList.get(i).getTask_end(), taskList.get(i).getTask_begin(), time_start);
            thread.start();
            i++;
        }
//        ExecutorService service = Executors.newCachedThreadPool();
//        for(int temp = 0 ;temp<THREAD_SIZE;temp++){
//            service.execute(new Mythread(divisor, taskList.get(i).getTask_end(), taskList.get(i).getTask_begin(), time_start));
//            i++;
//        }
//        service.shutdown();
//        while (!service.isTerminated()){
//           // System.out.println("h222");
//        }

//        Supplier<Stream<String>> supplier = new Supplier<Stream<String>>() {
//            @Override
//            public Stream<String> get() {
//
//            }
//        };
        Stream.of("A","B","C","D","E","F");

    }

}
