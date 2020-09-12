package java_8.lambda.filterfileexample.withlambda;

import java.io.File;
import java.io.FileFilter;

public class TestJavaFileFilter {

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
  }


  private static void listFIlesV2() {

    File dir = new File("C:\\tmp");
    File[] javaFiles = dir.listFiles((File file) -> file.getName().endsWith(".java"));

    System.out.println(javaFiles.length);
  }

}

/*
 * > So What is a java 8 Lambda Expression ?
 * 
 * Answer: another way to of writing instances of anonymous classes
 * 
 */
