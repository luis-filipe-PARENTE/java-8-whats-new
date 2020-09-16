package java_8.functional.interfaces.toobox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TestFunctionalInterfacesToolbox {


  // for some help :)
  // https://mkyong.com/java8/java-8-unaryoperator-examples/

  public static void main(String[] args) {

    getRandom();
    consumeMyObject();
    testMyPredicate();
    testFunctionInterface();


    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> result = math(list, x -> x * 2);
    System.out.println(result); // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

  }


  // Functional interfaces are divided into 4 categories part of the java.util.function

  // Cat 1 - Supplier
  static Double getRandom() {
    Supplier<Double> randomValue = () -> Math.random();
    return randomValue.get();
  }


  // Cat 4 - Consumer/BiConsumer/UnaryOperator (UnaryOperator is a special case )
  static void consumeMyObject() {
    Consumer<MyObject> consumer = (MyObject obj) -> {

      obj.setName(obj.getName() + " after process!");
      System.out.println(obj.getName());
    };

    MyObject obj1 = new MyObject("Hello");
    MyObject obj2 = new MyObject(" wrold");

    System.out.println(consumer);
    System.out.println(consumer.toString());
    System.out.println(consumer);

    consumer.accept(obj1);


    BiConsumer<MyObject, MyObject> bi_consumer = (MyObject o1, MyObject o2) -> {
      System.out.println(o1.getName() + o2.getName());
    };

    bi_consumer.accept(obj1, obj2);

  }


  // Cat - 3 Predicate/ BiPredicate

  static void testMyPredicate() {
    Predicate<Integer> btf = n -> n > 5;
    Predicate<Integer> btf1 = n -> n < 9;
    List<Integer> nums = List.of(2, 3, 1, 5, 6, 7, 8, 9, 12);
    nums.stream().filter(btf.and(btf1)).forEach(System.out::println); // compose
    nums.stream().filter(btf).forEach(System.out::println);
  }


  static void testIntPredicateCompose() {

    int[] nums = {2, 3, 1, 5, 6, 7, 8, 9, 12};

    IntPredicate p1 = n -> n > 3;
    IntPredicate p2 = n -> n < 9;

    Arrays.stream(nums).filter(p1.and(p2)).forEach(System.out::println);
  }

  // Cat - 4 Function/BiFunction
  static void testFunctionInterface() {
    Function<String, Integer> func = (String num) -> {
      return Integer.valueOf(num);
    };

    System.out.println(func.apply("2"));
  }

  public static <T> List<T> math(List<T> list, UnaryOperator<T> uo) {
    List<T> result = new ArrayList<>();
    for (T t : list) {
      result.add(uo.apply(t));
    }
    return result;
  }



}


class MyObject {

  private String name;

  public MyObject() {

  }

  MyObject(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
