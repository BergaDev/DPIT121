package SupBookExamples;

// RandomIntStream2.java
import java.security.SecureRandom;
import java.util.function.Function;

public class RandomIntStream2 {
   public static void main(String[] args) {
      SecureRandom random = new SecureRandom();

      random.ints(50, 1, 1000)
            .filter(x -> x % 2 == 1)
            .sorted()
            .forEach(x -> System.out.printf("%d ", x));
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
