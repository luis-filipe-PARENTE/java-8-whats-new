package section2.java_8.streams.composing.functions;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

// https://www.deadcoderising.com/2015-09-07-java-8-functional-composition-using-compose-and-andthen/

public class TestComposeAndThen {

  // default time zone
  private final static ZoneId defaultZoneId = ZoneId.systemDefault();

  private static BiFunction<String, List<Article>, List<Article>> byAuthor =
      (name, articles) -> articles.stream().filter(a -> a.getAuthor().equals(name))
          .collect(Collectors.toList());

  private static BiFunction<String, List<Article>, List<Article>> byTag =
      (tag, articles) -> articles.stream().filter(a -> a.getTags().contains(tag))
          .collect(Collectors.toList());

  private static Function<List<Article>, List<Article>> sortByDate = articles -> articles.stream()
      .sorted((x, y) -> y.getDate().compareTo(x.getDate())).collect(Collectors.toList());


  private static Function<List<Article>, Optional<Article>> first = a -> a.stream().findFirst();



  public static void main(String[] args) {
    differenceBetweeComposeAndAndThen();

    List<Article> listArticles = createData();

    getArticlesByAuthor(listArticles, "Jonh Shauber");
    getArticlesByTag(listArticles, "tag6");
    sortByDate(listArticles);
    byAuthorAndSortedByDate(listArticles);

  }

  private static void getArticlesByAuthor(List<Article> listArticles, String author) {
    List<Article> listArticlesByName = byAuthor.apply(author, listArticles);
    listArticlesByName.forEach(System.out::println);
  }

  private static void getArticlesByTag(List<Article> listArticles, String tagName) {
    List<Article> listArticlesByTag = byTag.apply(tagName, listArticles);
    listArticlesByTag.forEach(System.out::println);
  }

  private static void sortByDate(List<Article> listArticles) {


    List<Article> sortedListByDate = sortByDate.apply(listArticles);
    Optional<Article> optArticle = first.apply(sortedListByDate);

    Function<List<Article>, Optional<Article>> newest = first.compose(sortByDate);
    Optional<Article> optAricle = newest.apply(listArticles);
    System.out.println("with compose function " + optAricle.orElseGet(Article::new));

    System.out.println("This is the first article on soreted list \n " + optArticle.get());

    sortedListByDate.forEach(System.out::println);
  }

  private static void byAuthorAndSortedByDate(List<Article> listArticles) {
    BiFunction<String, List<Article>, List<Article>> byAuthorSorted = byAuthor.andThen(sortByDate);

    byAuthor.andThen(sortByDate);

    byAuthorSorted.apply("Jonh Shauber", listArticles).forEach(System.out::println);;
  }

  private static List<Article> createData() {
    List<Article> list = new ArrayList<>();
    LocalDate.parse("2015-02-20");


    Article a1 = new Article("Jonh Shauber", convertStringToDate(LocalDate.of(2016, 8, 19)),
        new HashSet<>(Arrays.asList("tag1")));
    Article a1_1 = new Article("Jonh Shauber", convertStringToDate(LocalDate.of(2023, 12, 13)),
        new HashSet<>(Arrays.asList("tag1_1")));
    Article a2 = new Article("Ayrton Senna", convertStringToDate(LocalDate.of(2020, 3, 14)),
        new HashSet<>(Arrays.asList("tag3")));
    Article a3 = new Article("Alain Prost", convertStringToDate(LocalDate.of(2018, 1, 28)),
        new HashSet<>(Arrays.asList("tag6")));
    Article a4 = new Article("Cris Marquez", convertStringToDate(LocalDate.of(2022, 8, 8)),
        new HashSet<>(Arrays.asList("tag7")));


    list.addAll(Arrays.asList(a1, a2, a3, a4, a1_1));
    return list;

  }

  private static Date convertStringToDate(LocalDate localdate) {
    return Date.from(localdate.atStartOfDay(defaultZoneId).toInstant());
  }

  private static void differenceBetweeComposeAndAndThen() {
    // Function<Integer, Integer> times2 = e -> e * 2;
    UnaryOperator<Integer> times2 = e -> e * 2;
    UnaryOperator<Integer> squared = e -> e * e;
    // Function<Integer, Integer> squared = e -> e * e;

    System.out.println(times2.compose(squared).apply(4)); // Returns 32 fTimes2(fSquare(4))
    System.out.println(times2.andThen(squared).apply(4)); // Returns 64 fSquare(fTimes2(4))
  }

}
