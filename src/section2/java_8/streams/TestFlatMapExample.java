package section2.java_8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class TestFlatMapExample {

  public static void main(String[] args) {

    List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    List<Integer> list2 = Arrays.asList(2, 4, 6);
    List<Integer> list3 = Arrays.asList(3, 5, 7);

    List<List<Integer>> list = Arrays.asList(list1, list2, list3);

    System.out.println(list);

    Function<List<?>, Integer> size = List::size;
    Function<List<Integer>, Stream<?>> flatmapper = List::stream;

    list.stream().map(size).forEach(System.out::println);
    list.stream().flatMap(flatmapper).forEach(System.out::println);
    list.stream().forEach(System.out::println);

    Integer sum = list.stream().reduce(new ArrayList<>(), (l, curr) -> {
      List<Integer> newList = new ArrayList<>();
      newList.addAll(curr);
      newList.addAll(l);
      return newList;
    }).size();

    System.out.println(sum);

  }

}
