package day8;

import java.util.Comparator;
import java.util.function.Consumer;

public class ABC<T extends Comparable>{
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
