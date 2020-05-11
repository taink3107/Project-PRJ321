package day7.Ex3_remake2;

import java.util.ArrayList;
import java.util.List;

public class CountDivisor {
    private  int max_value;
    private  int count_before;
    private boolean isProcess = true;
    public  int getMax_value() {
        return max_value;
    }

    public  void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public  int getCount_before() {
        return count_before;
    }

    public  void setCount_before(int count_before) {
        this.count_before = count_before;
    }
    public void setProcess(boolean check){
        this.isProcess = check;
    }
    public int getUC(int number2) {
        int count = 0;
        for (int i = 1; i <= number2; i++) {
            if (number2 % i == 0) {
                count++;
            }
        }
        return count;
    }

    public List<Task> taskList(int begin, int end) {
        List<Task> tasks = new ArrayList<Task>();
        int raw_end = 0;
        int raw_begin = 0;
        while (true) {
            raw_begin = raw_end + 1;
            raw_end += end / begin;
            if (raw_end > end) {
                break;
            }
            Task task = new Task(raw_begin, raw_end);
            tasks.add(task);
        }
        return tasks;
    }
}
