package day7.Ex3_remake;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int THREAD_SIZE = 5;
    public static void main(String[] args) {
        int count_before = 0;
        int max_value = 0;
        CountDivisor countDivisor = new CountDivisor();
        Doing[] doings = new Doing[THREAD_SIZE];
        long time_Start = System.currentTimeMillis();
        int range = 100000/THREAD_SIZE;
        int begin =0;
        int end = 0;
        for(Doing doing : doings){
            begin = 1;
            end = range;
            doing = new Doing(countDivisor,begin,end);
            doing.run();
            if(countDivisor.getCount_before() >= count_before){
                count_before = countDivisor.getCount_before();
                if(countDivisor.getMax_value() > max_value){
                    max_value = countDivisor.getMax_value();
                }
            }
            begin = end +1;
            end+= range;
        }
        long time_End = System.currentTimeMillis();
        System.out.println("Max value is: "+ max_value +"  and Large divisor of number is: "+ count_before);
        System.out.println("Time elapsed: "+ (time_End - time_Start));
    }
    public List<Integer> integers(int range,int size){
        List<Integer> list = new ArrayList<Integer>();
        return list;
    }

}

