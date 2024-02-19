package SupBookExamples;

// LetterGrades class uses the switch statement to count letter grades.
import java.util.ArrayList; // program uses class Scanner
import java.util.List; // program uses class Scanner
import java.util.Scanner; // program uses class Scanner
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class LetterGrades {
   public static void main(String[] args) {
      // create Scanner to obtain input from command window
      Scanner input = new Scanner(System.in);
      ArrayList<Integer> grades = new ArrayList<>();

      // processing phase
      // prompt for input and read grade from user
      System.out.print("Enter grade or -1 to quit: "); 
      int grade = input.nextInt(); 

      // loop until sentinel value read from user
      while (grade != -1) {
         grades.add(grade);
         System.out.print("Enter grade or -1 to quit: "); 
         grade = input.nextInt(); 
      }

      List<String> letterGrades = grades.stream()
         .map(x -> {
                if (x >= 90) {return "A";}
                else if (x >= 80) {return "B";}
                else if (x >= 70) {return "C";}
                else if (x >= 60) {return "D";}
                else {return "F";}
             })
         .collect(Collectors.toList());

      System.out.printf("Letter grades are: %s%n", letterGrades);
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
