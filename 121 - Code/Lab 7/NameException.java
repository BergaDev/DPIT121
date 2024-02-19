import java.util.Scanner;
public class NameException extends Exception {
  public void nameException(){
    System.out.println("The current name does not conform to the requiremnets, enter a new one");
  }

  String newName(){
    Scanner input = new Scanner(System.in);
    System.out.println("The current name does not conform to the requiremnets, enter a new one");
    System.out.println("");
    System.out.print("First and Last name: ");
    String newName = input.nextLine();
    return newName;
  }
}
