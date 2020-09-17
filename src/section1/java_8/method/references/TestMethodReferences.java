package section1.java_8.method.references;

import java.util.Comparator;
import java.util.function.Consumer;

public class TestMethodReferences {

  public static void main(String[] args) {
    complexAndEasyAwayConsumer();
  }


  static void complexAndEasyAwayConsumer() {
    Consumer<String> c = s -> System.out.println(s);
    c.accept("Hello");

    Consumer<String> c1 = System.out::println;
    c1.accept("Hello");

  }


  static void complexAndEasyAwayComparator() {
    Comparator<Integer> c = (i1, i2) -> Integer.compare(i1, i2);
    System.out.println(c.compare(1, 3));


    Comparator<Integer> c1 = Integer::compare;
    System.out.println(c1.compare(3, 3));

  }

}
