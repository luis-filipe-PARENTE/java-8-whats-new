package section2.java_8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestMapReduceFilter {


  public static void main(String[] args) {
    testMapFilterReduce();
  }
  
  private static void testMapFilterReduce() {
    
    List<Integer> myList = Arrays.asList(1, 5, 8);
    Stream<Integer> myStream = myList.stream();

    Integer[] myArray = {1, 5, 8};
   Stream<Integer> myStream1 = Arrays.stream(myArray);
   
   String[] myArray2 = new String[]{"bob", "alice", "paul", "ellie"};
   Stream<String> myStream2= Arrays.stream(myArray2);
   
   Supplier<Stream<String>> streamSupplier = () ->  Stream.of("bob", "alice", "paul", "ellie");
   
   Stream<String> myNewStream = 
       myStream2.map(String::toUpperCase);
   
   String str = streamSupplier.get().reduce("", (a,b) -> a + b);
   System.out.println(str);
   
   String[] myNewArray =
       streamSupplier.get().filter(s -> s.length() > 4).toArray(String[]::new);
   
   
  }

}
