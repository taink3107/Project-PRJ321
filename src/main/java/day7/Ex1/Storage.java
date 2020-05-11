package day7.Ex1;

public class Storage {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value +"";
    }
}
