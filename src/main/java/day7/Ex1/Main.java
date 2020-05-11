package day7.Ex1;

public class Main {
    static int MIN_V = 0;
    static int MAX_V = 11;
    public static void main(String[] args) {
        day7.Ex1.Storage storage = new day7.Ex1.Storage();
        day7.Ex1.Printer printer = new day7.Ex1.Printer(storage);
        day7.Ex1.Counter counter = new day7.Ex1.Counter(storage);
    }
}
