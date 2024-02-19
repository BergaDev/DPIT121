package SupBookExamples;

// Fig. 17.24: RandomIntStream3.java
// Rolling a die 60,000,000 times with streams
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomIntStream3 {
   public static void main(String[] args) {
      SecureRandom random1 = new SecureRandom();

      // roll a die 60,000,000 times and summarize the results
      System.out.printf("%-6s%s%n", "Face", "Frequency");
      Instant start1 = Instant.now();                         
      random1.ints(60_000_000, 1, 7)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(),
               Collectors.counting()))
            .forEach((face, frequency) -> 
               System.out.printf("%-6d%d%n", face, frequency));
      Instant end1 = Instant.now();                           
      System.out.printf("Total time in milliseconds: %d%n%n", 
         Duration.between(start1, end1).toMillis());
      System.out.printf("%-6s%s%n", "Face", "Frequency");

      Random random2 = new Random();
      Instant start2 = Instant.now();                         
      random2.ints(60_000_000, 1, 7)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(),
               Collectors.counting()))
            .forEach((face, frequency) -> 
               System.out.printf("%-6d%d%n", face, frequency));
      Instant end2 = Instant.now();                           
      System.out.printf("Total time in milliseconds: %d%n%n", 
         Duration.between(start2, end2).toMillis());


   } 
} 


/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
