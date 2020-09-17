package section1.java_8.lambda.functionalinterface;

public class TestMyCustomFunctionalInterface {

  public static void main(String[] args) {
    MyCustomFunctionalInterface functionalInterface = () -> System.out.println("Hello wrold!");
    functionalInterface.doSmething();
  }

}
