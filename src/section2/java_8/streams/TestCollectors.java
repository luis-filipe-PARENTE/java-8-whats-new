package section2.java_8.streams;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import section2.java_8.streams.model.Article;

public class TestCollectors {

  private final static Predicate<Article> p = a -> a.getId() < 3;

  public static void main(String[] args) {
    collectingInString();
    collectingInAMapGrouping();
    collectingInAMapGroupingAndCounting();
  }


  private static void collectingInString() {

    String result = getListOfData().stream().filter(p).map(Article::getAuthor)
        .collect(Collectors.joining(" ,"));

    System.out.println(result);
  }


  private static void collectingInAMapGrouping() {
    Map<Integer, List<Article>> result =
        getListOfData().stream().filter(p).collect(Collectors.groupingBy(Article::getId));

    System.out.println(result);
  }

  private static void collectingInAMapGroupingAndCounting() {
    Map<Integer, Long> result = getListOfData().stream().filter(p)
        .collect(Collectors.groupingBy(Article::getId, Collectors.counting()));

    System.out.println(result);
  }


  private static List<Article> getListOfData() {


    Article a1 = new Article("Jonh Doe a1", new Date(), new HashSet<>(), 1);
    Article a1_1 = new Article("Jonh Doe a1_1", new Date(), new HashSet<>(), 1);
    Article a1_2 = new Article("Jonh Doe a1_2", new Date(), new HashSet<>(), 1);
    Article a2 = new Article("Jonh Doe a2", new Date(), new HashSet<>(), 2);
    Article a2_1 = new Article("Jonh Doe a2_1", new Date(), new HashSet<>(), 2);
    Article a2_2 = new Article("Jonh Doe a2_2", new Date(), new HashSet<>(), 2);
    Article a3 = new Article("Jonh Doe a3", new Date(), new HashSet<>(), 3);

    return List.of(a1, a1_1, a1_2, a2, a2_1, a2_2, a3);
  }

}
