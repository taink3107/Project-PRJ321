package day7.Ex3_remake;

public class CountDivisor {
    private int max_value;
    private int count_before;

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public int getCount_before() {
        return count_before;
    }

    public void setCount_before(int count_before) {
        this.count_before = count_before;
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
}
