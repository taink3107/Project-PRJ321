package day8;

import java.util.Comparator;
import java.util.function.Consumer;

public class Xyz {
    Xyz(){
        System.out.println("haha");
    }
    @Override
    public String toString() {
        return "Temp temp";
    }
}
interface B<T>{
    T get();
}
class Temp{
    public static void main(String[] args) {
        B<Xyz> xyzB = Xyz::new;
        xyzB.get();
        System.out.println();
    }
}
