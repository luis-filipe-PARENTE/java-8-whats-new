package section2.java_8.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import section2.java_8.streams.model.Person;

public class LiveCodingCollectorsExample {

  public static void main(String... args) {

    List<Person> persons = new ArrayList<>();

    try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(
            LiveCodingCollectorsExample.class.getResourceAsStream("people.txt")));

        Stream<String> stream = reader.lines();) {

      stream.map(line -> {
        String[] s = line.split(" ");
        Person p = new Person(s[0].trim(), Integer.parseInt(s[1]));
        persons.add(p);
        return p;
      }).forEach(System.out::println);


    } catch (IOException ioe) {
      System.out.println(ioe);
    }

    Optional<Person> opt =
        persons.stream().filter(p -> p.getAge() >= 22).min(Comparator.comparing(Person::getAge));
    System.out.println(opt);

    Optional<Person> opt2 = persons.stream().max(Comparator.comparing(Person::getAge));
    System.out.println(opt2);

    Map<Integer, List<Person>> map1 =
        persons.stream().collect(Collectors.groupingBy(Person::getAge));
    System.out.println(map1);

    Map<Integer, String> map = persons.stream().collect(Collectors.groupingBy(Person::getAge,
        Collectors.mapping(Person::getName, Collectors.joining(", "))));
    System.out.println("map => " + map);

    Map<Integer, Long> map2 =
        persons.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
    System.out.println("map2 => " + map2);

    Map<Integer, List<String>> map3 = persons.stream().collect(Collectors.groupingBy(Person::getAge,
        Collectors.mapping(Person::getName, Collectors.toList())));
    System.out.println("map3 => " + map3);

    Map<Integer, Set<String>> map4 = persons.stream().collect(Collectors.groupingBy(Person::getAge,
        Collectors.mapping(Person::getName, Collectors.toCollection(TreeSet::new))));
    System.out.println("map4 => " + map4);


    List<Integer> list = Stream.of(1, 33, 3, 5, 33, 3, 2, 3, 5, 33)
        .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.toList()))
        .lastEntry().getValue();

    System.out.println(Stream.of(1, 33, 3, 5, 33, 3, 2, 3, 5, 33)
        .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.toList()))
        .lastEntry());

    Stream.of(1, 33, 3, 5, 33, 3, 2, 3, 5, 33)
        .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.toList()))
        .lastEntry();

    System.out.println(list);

    testTreeMap();
  }


  private static void testTreeMap() {

    TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
    map.put(3, "val");
    map.put(2, "val");
    map.put(1, "val");
    map.put(5, "val");
    map.put(5, "valBis");
    map.put(4, "val");

    System.out.println(map);
  }

}
