package section2.java_8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediaryAndFinal {

  public static void main(String[] args) {
    Stream<String> stream = Stream.of("one", "two", "three", "four", "five");

    Predicate<String> p1 = Predicate.isEqual("two");
    Predicate<String> p2 = Predicate.isEqual("three");

    Optional.empty();

    List<String> list = new ArrayList<>();

    // @formatter:off

    CompletableFuture<List<String>> mem = 
            CompletableFuture.completedFuture(
                stream
                .peek(System.out::println) // (lazy)
                .filter(p1.or(p2)) // (lazy)
                .peek(list::add)  // (lazy)
                .collect(Collectors.toList()) // final operation (not lazy)
            );
        
    Supplier<Stream<String>> getStream = () -> mem.join().stream();

    /*
       
     stream
        .peek(System.out::println)
        .filter(p1.or(p2))
        .forEach(list::add); // forEach is a final operator and so isn't a lazy operator
    */

      
      System.out.println("Done!");
      System.out.println("size = " + list.size());
      System.out.println("count stream = " + getStream.get().count());
      System.out.println("count stream = " + getStream.get().count());
      System.out.println("count stream = " + getStream.get().count());
      System.out.println("size = " + list.size());
      
    // @formatter:on


    // Stream operations directly
    Optional<Integer> max = IntStream.of(1, 2, 3, 4, 5).boxed().max(Integer::compareTo);

    System.out.println(max);


  }

}
