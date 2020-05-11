package day8;

import java.util.function.Consumer;

public class YXX<T> implements Consumer<T> {

    @Override
    public void accept(T t) {

    }
    public static void a(String s) {
        System.out.println(s);
    }
    public static void main(String[] args) {
        Consumer<String> consumer = YXX::a;
    }
}