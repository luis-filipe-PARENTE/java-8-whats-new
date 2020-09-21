package section2.java_8.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class TestAggregation {

  public static void main(String[] args) {

    Supplier<Stream<Integer>> factoryStream = () -> List.of(1, 15, 2, -8, 3, 4, 5).stream();

    // Stream<Integer> stream = List.of(1, 2, 3, 4, 5).stream();

    // Note: public interface BinaryOperator<T> extends BiFunction<T,T,T> {

    BinaryOperator<Integer> sum = (elem1, elem2) -> elem1 + elem2;
    Integer id = 0;

    Integer red = factoryStream.get().reduce(id, sum);
    System.out.println(red);

    Optional<Integer> max = factoryStream.get().max(Comparator.naturalOrder());
    Optional<Integer> min = factoryStream.get().min(Comparator.naturalOrder());
    max.ifPresentOrElse(System.out::println, () -> System.out.println(("Max no found!")));
    min.ifPresentOrElse(System.out::println, () -> System.out.println(("Max no found!")));

    Predicate<Integer> supToFour = i -> i > 4;

    // min operator is a terminal operator
    Optional<Integer> minWhenSupTpFour =
        factoryStream.get().filter(supToFour).min(Comparator.naturalOrder());

    System.out.println("Min when sup to four " + minWhenSupTpFour.get());

    Function<String, Integer> length = String::length;
    Function<Integer, Boolean> p1 = v -> v < 24;


    System.out.println(getDataPerson().stream().map(Person::getLastName)
        .allMatch(t -> length.andThen(p1).apply(t)));

  }


  private static List<Person> getDataPerson() {
    Person p1 = new Person("Andreia Sofia");
    Person p2 = new Person("Emilia Rose");
    Person p3 = new Person("Luis-Filipe");
    return List.of(p1, p2, p3);

  }

}


class Person {
  private String lastName;

  public Person() {}

  public Person(String lastName) {
    super();
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


}

