package section2.java_8.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReductionExample {

  public static void main(String[] args) {

    doingTheSum();
    wrongResultWhenFindingTheMax();

  }


  private static void doingTheSum() {
    List<Integer> list = List.of(10, 10, 10);

    // @formatter:off
    System.out.println(
       list.stream().reduce(0, Integer::sum)
    );
    
    System.out.println(
        list.stream().mapToInt(Integer::intValue).sum()
    );
    
    System.out.println(
        list.stream().collect(Collectors.summingInt(Integer::intValue))
    );
    

// @formatter:on
  }


  /**
   * When we try to find the max with reduce, sometimes the value identity is doesn't be the good
   */
  private static void wrongResultWhenFindingTheMax() {
    List<Integer> list = List.of(-10, -20, -1);
    System.out.println(list.stream().reduce(Integer.MIN_VALUE, Integer::max)); // Not valid for the
                                                                               // empty list
    // or even better
    Optional<Integer> optMax = list.stream().reduce(Integer::max);
    optMax.ifPresentOrElse(System.out::println, () -> System.out.println("Optional is mmpty!"));


  }

}
