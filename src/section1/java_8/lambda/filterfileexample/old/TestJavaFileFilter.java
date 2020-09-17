package section1.java_8.lambda.filterfileexample.old;

import java.io.File;
import java.io.FileFilter;

public class TestJavaFileFilter {

  /**
   * anonymous class
   */
  static FileFilter fileFilter = new FileFilter() {

    @Override
    public boolean accept(File file) {
      return file.getName().endsWith(".java");
    }

  };


  public static void main(String[] args) {
    listFIles();
    listFIlesV2();
  }

  private static void listFIles() {

    JavaFileFilter fileFilter = new JavaFileFilter();
    File dir = new File("C:\\tmp");
    File[] javaFiles = dir.listFiles(fileFilter);

    System.out.println(javaFiles.length);
  }


  private static void listFIlesV2() {

    File dir = new File("C:\\tmp");
    File[] javaFiles = dir.listFiles(fileFilter);

    System.out.println(javaFiles.length);
  }


}
