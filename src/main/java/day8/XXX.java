package day8;

public class XXX {
}

interface Y<T> {
    void abc(T x);
}
interface Z<T>{
    T temp2(T a);
}
class A {
    A() {
        System.out.println("XXX");
    }
}

class YYY{
    public static void main(String[] args) {
        Z<String> z = String::new;
        System.out.println(z.temp2("abc"));
    }
}
