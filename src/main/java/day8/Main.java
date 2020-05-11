package day8;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.ProviderNotFoundException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("1", "2", "4", "3");
        Supplier<Stream<String>> supplier = () -> Stream.of("1", "2", "2", "2", "1");
        //     Stream<Date> stream1 = Stream.generate(() -> {return new Date();});
//        stream1.limit(10).forEach(System.out::println);
//        Stream.Builder<String> builder = Stream.builder();
//        Stream<String> stream2 = builder
//                .add("ABC").add("BCX").add("XYZ").add("GHK").build();
        // stream2.forEach(System.out::print);
        ///  supplier.get().skip(1).limit(10).forEach(System.out::println);
        // stream.filter(s -> s.contains("Script")).forEach(System.out::println);
        //stream.map(s -> s.startsWith("J")).sorted().forEach(System.out::println);
        // List<String> strings = Arrays.asList("1","2","3","4","5");
        //   Stream<List<String>> listStream = Stream.of(strings);
        // listStream.filter(strings1 -> strings1.equals("1")).forEach(System.out::println);
        // listStream.flatMap(strings1 -> strings1.stream()).filter(s -> s.equals("1")).forEach(s -> System.out.println("Numbmer is : "+ s));
        // ABC aaa = new ABC();
        // List<Integer> values = Arrays.asList(1, 3, 2);
        // values.stream().sorted(aaa::compare).forEach(System.out::println);
        //       String a = supplier.get().max(String::compareTo).get();
        //    logger.info("Count result is: {}, Max value is: {}", supplier.get().filter(s -> s.equals(a)).count(), a);
        //      supplier.get().filter(s -> s.contains("a")).peek(s -> System.out.println("value is" + s)).findFirst();
//
//        List<String> list = Arrays.asList("D", "A", "C", "B");
//        Optional<String> result = list.stream().findAny();
//        Optional<String> result1 = list.stream().findAny();
//        Optional<String> result2 = list.stream().findAny();
//        Optional<String> result3 = list.stream().findAny();
//        System.out.println(result.get() +" "+ result1.get() +" "+ result2.get()+" "+result3.get());
//    }
//        List<Integer> integers = Arrays.asList(-5, 5, 23, 4, 0, -1, 3);
//
//        Integer value = integers.stream()
//                .filter(i -> i >= 0 && i <= 5)
//                .findAny().get();
//        System.out.println(value);
//        //value.ifPresent(v -> System.out.println(v));
//
//    long time_start = System.currentTimeMillis();
//        Stream<Integer> stream1 = IntStream.range(0,1000000).map(i -> i).boxed();
//        System.out.println(stream1.count());
//        long time_end = System.currentTimeMillis();
//        System.out.println("time is: " +(time_end - time_start));
//        Optional<String> optional = Optional.empty();
//       // Optional<String> optional1 = Optional.of("Nguyễn Khánh Tài");
//       // optional.get();
//        System.out.println(optional.isPresent());
//
//        optional1.ifPresent(s -> s.replace("a","Y"));
//        System.out.println(optional1.get());
//        ;
        // optional.orElseThrow(() -> new NumberFormatException("This should not happen!!!"));
        //   Optional<Optional<String>> multiOptional = Optional.of(Optional.of("Hello"));
        /*
         * day 9
         * */

        Province province = new Province("Hung Yen");
        Adress adress = new Adress(province);

        Person person = new Person();
        Person person1 = null;
        Person person2 = new Person("Tai NK", adress);
        Person person3 = new Person("Tai NK2", new Adress());

        display(person3);
//        Optional<Optional<String>> multiOptional = Optional.of(Optional.of("gpcodre"));
//
//        System.out.println("Value of Optional object: " + multiOptional);
//        System.out.println("Optional.map: " + multiOptional.map(gender -> gender.map(String::toUpperCase)));
//        System.out.println("Optional.flatMap: " + multiOptional.flatMap(gender -> gender.map(String::toUpperCase)));

        List<Person1> list = Arrays.asList(
                new Person1("Tai", 30),
                new Person1("T", 12),
                new Person1("Ai", 11),
                new Person1("Ti", 02));
        Predicate<Person1> predicate = s -> s.getAge() < 30;
        Predicate<Person1> predicate1 = s -> s.getName().equals("Tai");

        Optional<Person1> personX = list.stream().filter(predicate.and(predicate1)).findFirst();

        personX.ifPresent(p -> System.out.println(p.getName()));

        personX.orElseThrow(() -> new IllegalArgumentException("Cannot found"));

    }

    public static void display(Person person) {
        Optional<String> o = Optional.ofNullable(Optional.ofNullable(person)
                .flatMap(person2 -> Optional.ofNullable(person2.adress)
                        .flatMap(address -> Optional.ofNullable(address.province))
                        .flatMap(province -> Optional.ofNullable(province.Provinct)))
                .orElseThrow(() -> new RuntimeException("Hello word")));
        System.out.println(o.get());
    }
}

