package section1.java_8.lambda.runnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExamplesWithLambdaExpressions {


  public static void main(String[] args) throws InterruptedException {

    runWithAnonymousClass();
    runWithLambdaExpression();
    runComparatorWithAnonylousClass();
    runComparatorWithLambda();

  }

  public static void runWithAnonymousClass() throws InterruptedException {

    Runnable runnable = new Runnable() {

      @Override
      public void run() {

        int waitingTime = 0;

        try {
          waitingTime = 1000 * (1 + (int) (Math.random() * 5));

          Thread.sleep(waitingTime);
        } catch (InterruptedException ex) {
          System.out.println("Exception has been caught" + ex);
        }

        for (int i = 0; i < 3; i++) {
          System.out.println("Hello world from thread [" + Thread.currentThread().getName()
              + "], waiting time: " + waitingTime);
        } ;

      }
    };


    Thread t = new Thread(runnable);

    t.start();
    t.join();

    Thread t1 = new Thread(runnable);
    t1.start();
    t1.join();

  }

  public static void runWithLambdaExpression() throws InterruptedException {
    Runnable runnable = () -> {

      int waitingTime = 0;

      try {
        waitingTime = 1000 * (1 + (int) (Math.random() * 5));

        Thread.sleep(waitingTime);
      } catch (InterruptedException ex) {
        System.out.println("Exception has been caught" + ex);
      }

      for (int i = 0; i < 3; i++) {
        System.out.println("Hello world from thread with lambda ["
            + Thread.currentThread().getName() + "], waiting time: " + waitingTime);
      } ;
    };

    Thread t = new Thread(runnable);

    t.start();
    t.join();

  }

  public static void runComparatorWithAnonylousClass() {

    Comparator<String> comp = new Comparator<>() {

      @Override
      public int compare(String s1, String s2) {
        return s1.compareTo(s2);
      }
    };


    List<String> list = Arrays.asList("***", "**", "****", "*");

    Collections.sort(list, comp);
    list.forEach(System.out::println);

  }

  public static void runComparatorWithLambda() {

    Comparator<String> comp = (String s1, String s2) -> s1.compareTo(s2);

    List<String> list = Arrays.asList("***", "**", "****", "*");

    Collections.sort(list, comp);
    list.forEach(System.out::println);

  }



}
