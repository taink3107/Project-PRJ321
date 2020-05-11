package day7.Ex3_remake2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mythread extends Thread {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    private int max_size;
    private int min_size;
    private long time_start;
    volatile CountDivisor divisor;
    Object b;

    public Mythread(CountDivisor divisor, int max_size, int min_size, long time_start) {
        this.max_size = max_size;
        this.min_size = min_size;
        this.divisor = divisor;
        this.time_start = time_start;
    }

    @Override
    public void run() {
        int count = 0;
        b = new Object();
        synchronized (b) {
            for (int i = min_size; i <= max_size; i++) {
                count = divisor.getUC(i);
                if (count >= divisor.getCount_before()) {
                    divisor.setCount_before(count);
                    divisor.setMax_value(i);
                }
            }
            display();
        }

    }

    public void display() {
        long time_end = System.currentTimeMillis();
        logger.info("Max value is: {} --- Larger divisor is: {} --- Elapse time is {}s", divisor.getMax_value(), divisor.getCount_before(), (time_end - time_start));

    }
}
