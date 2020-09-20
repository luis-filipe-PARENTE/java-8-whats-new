package section2.java_8.streams.filtering.consuming;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstPredicates {

  private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
  private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
  private static final String NUMBER = "0123456789";

  private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
  private static SecureRandom random = new SecureRandom();


  public static void main(String[] args) {

    Predicate<String> p1 = s -> s.length() > 4;
    Predicate<String> p2 = s -> s.length() < 4;
    Predicate<String> p3 = s -> s.length() == 5;

    Stream<String> stream = Stream.of("one", "two", "three", "four", "five");

    // @formatter:off
      stream
        .filter(p1.or(p2).and(p3))
        .forEach(System.out::println);
      
      List<String> listStr = new ArrayList<>();
      
      Predicate<String> p = s -> s.startsWith("a");
      Predicate<String> pV1 = s -> s.endsWith("a");
      
      for (int i = 0; i < 100_000; i++) {
        listStr.add(generateRandomString(8));
      }
      
      List<String> result = new ArrayList<>();
      
      /**
       * peek and filter lazy operators
       * Streams are lazy because intermediate operations are not evaluated unless terminal operation is invoked.
       */
      Stream<String> lazyStream = listStr
                                  .stream()
                                  .peek(System.out::println)
                                  .filter(p.and(pV1))
                                  .peek(result::add);
      
      System.out.println(lazyStream.collect(Collectors.toList()).size());
      
 
      System.out.println(result.size());
     
      System.out.println(listStr.size());
      Stream<String> streamStrWithFilters = listStr.stream().filter(p.and(pV1)); // the call to the filter method is lazy
      System.out.println(listStr.size());
      
      List<String> filtredlist = streamStrWithFilters.collect(Collectors.toList());
      System.out.println(String.format("The size of filtred list: %d", filtredlist.size()));
      filtredlist.forEach(System.out::println);
    // @formatter:on


  }

  public static String generateRandomString(int length) {
    if (length < 1)
      throw new IllegalArgumentException();

    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {

      // 0-62 (exclusive), random returns 0-61
      int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
      char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

      // debug
      // System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

      sb.append(rndChar);

    }

    return sb.toString();

  }

}
