//Matthew Bergamini - 8137225
import java.util.ArrayList;
import java.util.Scanner;

public class TestCase {
  
public static double total = 0;
public static int autoTest = 1;
  public static void testCode(){
    Scanner input = new Scanner(System.in);

    System.out.println("Creating first policies");
    //Corrected the constructors for the policies which i've had wrong since lab 1, whoops
    Car car1 = new Car(Car.CarType.SUV, "Tesla", 2020, 69000);
    //This one is not added to array yet, it's used to check duplicate later in the tests 
    ThirdPartyPolicy policy4 = new ThirdPartyPolicy("Philip", 7, new Car(Car.CarType.SED, "MG", 2021, 15000), 0, "Tests the filter array", new MyDate(2024, 10, 14));
    ThirdPartyPolicy policy6 = new ThirdPartyPolicy("Matt", 8, new Car(Car.CarType.SED, "Kia", 2021, 43000), 0, "Tests the filter array", new MyDate(2023, 12, 31));
    
    ArrayList<InsurancePolicy> policies = new ArrayList<>();
    
    Address address1 = new Address(1,"First Street", "Cool suburb", "Cooler city");
    User user1 = new User("User 1", 1, address1);
    User user2 = new User("User 2", 2, new Address(2, "Second Street", "Second Suburb", "Second City"));
   

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
      insuranceCompany.addUser("User 3", 3, new Address(3, "Third Street", "Third Suburb", "Third City"));
      System.out.println("");

      System.out.println("Creating new policies and adding to user");
      insuranceCompany.createComprehensivePolicy(1, "James", 1, car1, 0, 19, 1, new MyDate(2024, 5, 21));
      insuranceCompany.createThirdPartyPolicy(1, "Jack", 2, new Car(Car.CarType.HATCH, "Toyota", 2018, 4569), 1, "This is policy2", new MyDate(2025, 9, 24));
      insuranceCompany.createComprehensivePolicy(1, "Jill", 3, new Car(Car.CarType.LUX, "Polestar", 2023, 110000), 1, 40, 5,  new MyDate(2023, 6, 31));
      insuranceCompany.createThirdPartyPolicy(1, "Sam", 4, new Car(Car.CarType.SED, "Polestar", 2021, 59000), 0, "This is just a duplicate thing", new MyDate(2022, 12, 31));
      insuranceCompany.createThirdPartyPolicy(2, "Matt", 4, new Car(Car.CarType.SED, "Kia", 2021, 59000), 0, "Tests the filter array", new MyDate(2023, 12, 31));
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

      
      System.out.println("");

      
      
     

      System.out.println("Filter policies a user holds by ExpiryDate");
      InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(2, new MyDate(2023, 12, 31)));
      System.out.println("");

      System.out.println("Filter all policies by car model");
      InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel("Tesla"));
      System.out.println("");

      System.out.println("Filter all policies by expiryDate");
      int year, month, day;
            if (autoTest == 1){
            year = 2024;
            month = 5;
            day = 21;
          } else {
            System.out.println("Enter year: ");
            year = input.nextInt();
            System.out.println("Enter month: ");
            month = input.nextInt();
            System.out.println("Enter day: ");
            day = input.nextInt();
          }
        InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(new MyDate(year, month, day)));
        System.out.println("");
      

     System.out.println("Find user by userID and change address information");
     int streetNum, userID;
     String street, suburb, city;
          if (autoTest == 1){
            userID = 2;
            streetNum = 100;
            street = "Changed Street";
            suburb = "Changed Suburb";
            city = "Changed City";
          } else {
            System.out.println("Enter userID");
            userID = input.nextInt();
            input.next();
            System.out.println("Enter new Street Number");
            streetNum = input.nextInt();
            input.next();
            System.out.println("Enter new Street Name");
            street = input.nextLine();
            input.next();
            System.out.println("Enter new Suburb Name");
            suburb = input.nextLine();
            input.next();
            System.out.println("Enter new City Name");
            city = input.nextLine();
            input.next();
          }

          if (insuranceCompany.findUser(userID) != null){
            insuranceCompany.findUser(userID).setAddress(new Address(streetNum, street, suburb, city));
            insuranceCompany.findUser(userID).print();
          }

        System.out.println("Now running Distinct city names");
        //InsurancePolicy.printPolicies(insuranceCompany.populateDistinctCityNames());
        ArrayList<String> distinctCities = insuranceCompany.populateDistinctCityNames();
       // System.out.println(insuranceCompany.populateDistinctCityNames());

       for (String item: distinctCities){
        System.out.print(item + " , ");
       }
       System.out.println("");


       //This works 100%
       System.out.println("This is the total for Cooler city: $" + insuranceCompany.getTotalPaymentsForCity("Cooler city"));
       System.out.println("");


       //This is likely the correct output 
       System.out.println("Here is the results per city: " + insuranceCompany.getTotalPaymentsPerCity(insuranceCompany.populateDistinctCityNames()));
       System.out.println("");


       insuranceCompany.reportPaymentPerCity(insuranceCompany.populateDistinctCityNames(), insuranceCompany.getTotalPaymentsPerCity(insuranceCompany.populateDistinctCityNames()));
       System.out.println("");
       
       
     
       //Working 100%
       System.out.println("Distinct car models " + insuranceCompany.distinctCarModels());

       System.out.println("Distinct models for user 1 " + user1.distinctCarModels());

      // System.out.println("All cars, user 1 " + user1.allCars());

      // System.out.println("Model count " + user1.getTotalCountPerCarModel(user1.allCars()));

       //System.out.println("All cars, all users " + insuranceCompany.allCars());

       //System.out.println("Count all cars, all users: " + insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()));

      // System.out.println("Get total payment for car for user 1: " + user1.getTotalPaymentForCarModel("Tesla"));

      // System.out.println("Payments for all models, all users" + insuranceCompany.getTotalPaymentPerCarModelTest(insuranceCompany.distinctCarModels()));

       //System.out.println("Payment per car models for user 1" + user1.getTotalPaymentPerCarModel(user1.distinctCarModels()));

       //insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.distinctCarModels(), insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()), insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));

       if (insuranceCompany.removeUser(1, policies) == true){
        System.out.println("The user with ID: 1" + userID + ", Has been removed");
       } else {
        System.out.println("We have had an issue removing the user");
       }
       System.out.println(insuranceCompany.findUser(1));

       //System.out.println(insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()));

       //System.out.println(insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));
    
       insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.distinctCarModels(), insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()), insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));

          //Else ends here
    }
  }
}
