//Matthew Bergamini - 8137225

import java.io.IOException;
import java.security.spec.ECFieldF2m;
import java.util.*;

//import Car.CarType;

//import Car.CarType;




public class Main{

  //public static int flatRateVal =500;
  public static double total = 0;

  //AUTO STRING ENTER
  public static int autoTest = 1;
  public static int logOut = 0;
  public static int userID = 0;
  public static int policyID = 0;

  //public static Scanner input = new Scanner(System.in);
  //static InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", new User("Admin", 01, new Address(58, "Admins Address", "Admin Suburb", "Admin City")), "Admin", "Admin", 600);

  public ArrayList<InsurancePolicy> policies = new ArrayList<>();
  public static void main(String[] args) throws IOException, CloneNotSupportedException, PolicyException{
   Scanner input = new Scanner(System.in);

   ArrayList<InsurancePolicy> policies = new ArrayList<>();
   InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", new User("Admin", 01, new Address(58, "Admins Address", "Admin Suburb", "Admin City")), "Admin", "Admin", 600);
   //InsuranceCompany insuranceCompany = new InsuranceCompany();
    Boolean notDead = true;

  try{
    System.out.println("");
    System.out.println("1) Admin Login");
    System.out.println("2) User Login");
    System.out.println("3) Load InsuranceCompany");
    System.out.println("4) Log Out");
    System.out.println("");
    System.out.print("Choose an Option: ");
    int choice = input.nextInt();
    if (choice == 4){
      Options.exit();
    } else if (choice == 3){
      //InsuranceCompany insuranceCompany2 = new InsuranceCompany(InsuranceCompany.loadTextFile("insuranceCompnayLoad"));
      //InsuranceCompany.loadTextFile("insuranceCompanySave");
      //Options.mainMenu(insuranceCompany, policies);
      System.out.println("I don't know how this is meant to be used yet remember");
    }
    else {
      Options.menus(choice, insuranceCompany, policies);
    }
    
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again");
      Main.main(args);
  }


  }


 

}
