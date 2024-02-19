//Matthew Bergamini - 8137225
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributesException;

//import Car.CarType;

public class TestCase {

  public static void testCode() throws CloneNotSupportedException, IOException, PolicyException{
    System.out.println("UGHHGHGHHGHG");



    Scanner input = new Scanner(System.in);

    int saveChoice = 0;
    System.out.println("Choose 1 for testCase with save or 2 to load a save file");
    saveChoice = input.nextInt();

    if (saveChoice == 1){

    System.out.println("Creating first policies");
    //Corrected the constructors for the policies which i've had wrong since lab 1, whoops
    Car car1 = new Car(Car.CarType.SUV, "Tesla", 2020, 69000);
    //This one is not added to array yet, it's used to check duplicate later in the tests 
    ThirdPartyPolicy policy4 = new ThirdPartyPolicy("Philip", 7, new Car(Car.CarType.SED, "MG", 2021, 15000), 0, "Tests the filter array", new MyDate(2024, 10, 14));
    ThirdPartyPolicy policy6 = new ThirdPartyPolicy("Matt", 8, new Car(Car.CarType.SED, "Kia", 2021, 43000), 0, "Tests the filter array", new MyDate(2023, 12, 31));
    
    ArrayList<InsurancePolicy> policies = new ArrayList<>();
    
    Address address1 = new Address(1,"First Street", "Cool suburb", "Cooler city");
    User user1 = new User("Ben", 1, address1);
    User user2 = new User("Adam", 2, new Address(2, "Second Street", "Second Suburb", "Second City"));
   

    //Admin stuff - Flatrate 600 to be different
    InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", user1, "Admin", "Admin", 600);
    if (!insuranceCompany.validateAdmin("Admin", "Admin")){
      System.out.println("Wrong login credentials");
    } else {
      System.out.println("Login Successful");
      System.out.println("");

      insuranceCompany.addUser(user1);
      insuranceCompany.addUser(user2);
      //Make this one have an error, wait on email reply
      insuranceCompany.addUser("Jack", 3, new Address(3, "Third Street", "Third Suburb", "Third City"));
      System.out.println("");

      System.out.println("Creating new policies and adding to user");
      insuranceCompany.createComprehensivePolicy(1, "James", 1, car1, 0, 19, 1, new MyDate(2024, 5, 21));
      insuranceCompany.createThirdPartyPolicy(1, "Jack", 2, new Car(Car.CarType.HATCH, "Toyota", 2018, 4569), 1, "This is policy2", new MyDate(2025, 9, 24));
      insuranceCompany.createComprehensivePolicy(1, "Jill", 3, new Car(Car.CarType.LUX, "Polestar", 2023, 110000), 1, 40, 5,  new MyDate(2023, 6, 31));
      insuranceCompany.createThirdPartyPolicy(1, "Sam", 4, new Car(Car.CarType.SED, "Polestar", 2021, 59000), 0, "This is just a duplicate thing", new MyDate(2022, 12, 31));
      insuranceCompany.createThirdPartyPolicy(1, "Matt", 3000001, new Car(Car.CarType.SED, "Kia", 2021, 59000), 0, "Tests the filter array", new MyDate(2023, 12, 31));
      //Checks for duplicate id's now
      insuranceCompany.createThirdPartyPolicy(3, "New Policy", 4, new Car(Car.CarType.LUX, "Polestar", 2022, 70000), 0, "This is a new Policy", new MyDate(2023, 1, 21));
      insuranceCompany.createComprehensivePolicy(1, "Another New Policy", 5, new Car(Car.CarType.HATCH, "Honda", 2009, 7000), 3, 19, 1, new MyDate(2024, 2, 11));
      insuranceCompany.createThirdPartyPolicy(3, "Newest Policy", 6, new Car(Car.CarType.LUX, "Cupra", 2022, 100000), 1, "This is another new policy", new MyDate(2024, 5, 15));
      

      insuranceCompany.addPolicy(2, policy4);
      insuranceCompany.addPolicy(3, policy6);
      //insuranceCompany.addPolicy(3, policy5);
      System.out.println("");
      System.out.println("Adding to user that doesn't exist");
      insuranceCompany.addPolicy(4, policy6);
      System.out.println("");
      System.out.println("Adding duplicate policy");
      insuranceCompany.addPolicy(3, policy6);
      //I have reached peak levels of idiot - I did not put a check in here yet - Done

      /*
      System.out.println("Below I print the arraylist");

      //Turning HashMap to Arraylist
     ArrayList<InsurancePolicy> stored = user1.getPolicies();
     for (InsurancePolicy item: stored){
      item.print();
      stored.get(1).getCar().getModel();
      System.out.println();
     }

     


     System.out.println("User1 Policies -  testing arraylist method ");
     user1.printPolicies(500);

     System.out.println("Car models - testing if it works still");
     System.out.println(user1.allCars());
     System.out.println(user1.distinctCarModels());

     System.out.println("");
     System.out.println("cout models");
     System.out.println(user1.getTotalCountPerCarModel(user1.distinctCarModels()));

     System.out.println("");
     System.out.println("Payments for model");
     System.out.println(user1.getTotalPaymentForCarModel("Polestar"));
      
     ArrayList<InsurancePolicy> user1HashPolicies = user1.getPolicies();

      System.out.println("");
     System.out.println("Payments by model");
     System.out.println(user1.getTotalPaymentPerCarModel(user1.distinctCarModels()));
     */


     System.out.println("");
     System.out.println("Testing cloning below");

     //Deep clone works with hash 
     ArrayList<InsurancePolicy> deepHash = user1.deepCopyPolicies();
     for (InsurancePolicy anPolicy: deepHash){
      anPolicy.setId(600);
      anPolicy.print();
      System.out.println("");

     }

     System.out.println("And orignal policies");
     user1.printPolicies(500);
     System.out.println("");

     System.out.println("Calc total for user");
     System.out.println(user1.calcTotalPremiums(500));
      

      System.out.println("");


      System.out.println("Sorting users");
      System.out.println();

      ArrayList<User> alph = insuranceCompany.sortUsers();
      System.out.println(alph.size());
      for (User name: alph){
        System.out.println(name.getName());
        System.out.println();
      }

      System.out.println();
      System.out.println("Printing Users");
      insuranceCompany.printUsers();

      System.out.println(insuranceCompany.distinctCarModels());

      System.out.println("Total Payments per model is :" + insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));

      user1.reportPaymentPerModel();

      System.out.println("Count per model" + insuranceCompany.getTotalCountPerCarModel());

      System.out.println("total premium model" + insuranceCompany.getTotalPremiumPerCarModel());

      insuranceCompany.reportPaymentPerCity();


      //insuranceCompany.createComprehensivePolicy(1, "New One", 12, new Car(Car.CarType.HATCH, "New Car", 2023, 21020), 0, 19, 1, new MyDate(2024, 5, 21));

      //Lab4.testCode();

      InsurancePolicy.save(user1.getHashPolices(), "10DecDataLab5.ser");
      System.out.println("");

      System.out.println("Loading policies hash");
      System.out.println(InsurancePolicy.load("10DecDataLab5.ser"));
      System.out.println("");
      System.out.println("");


      insuranceCompany.save("AnFile.ser");

      System.out.println("Testing delIm Save");
      user1.getPolicy(3000001).saveTextFile(user1.getHashPolices(), "Name");
      user1.getPolicy(3000001).loadTextFile("Name");
      } 
    } else if (saveChoice == 2){
        InsuranceCompany insuranceCompany = new InsuranceCompany("Damn");
        insuranceCompany.load("AnFile.ser");
        System.out.println("Testing name from loaded constructor: " + insuranceCompany.getName());
      }
  }
  
  

}
