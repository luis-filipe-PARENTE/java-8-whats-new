package section1.java_8.lambda.filterfileexample.withlambda;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.function.Consumer;

public class TestJavaFileFilter {

  private static Consumer<File[]> printFiles =
      (File[] javaFiles) -> Arrays.stream(javaFiles).forEach(System.out::println);

  /**
   * lambda expression
   */
  static FileFilter fileFilter = (File file) -> file.getName().endsWith(".java");


  public static void main(String[] args) {
    listFIles();
    listFIlesV2();
  }

  private static void listFIles() {

    File dir = new File("C:\\tmp");
    File[] javaFiles = dir.listFiles(fileFilter);

    System.out.println(javaFiles.length);
    printFiles.accept(javaFiles);
  }


  private static void listFIlesV2() {

    File dir = new File("C:\\tmp");
    File[] javaFiles = dir.listFiles((File file) -> file.getName().endsWith(".java"));

    System.out.println(javaFiles.length);
    printFiles.accept(javaFiles);
  }

}

/*
 * > So What is a java 8 Lambda Expression ?
 * 
 * Answer: another way to of writing instances of anonymous classes
 * 
 */
