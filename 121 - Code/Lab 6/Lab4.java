import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab4 {
  public static double total = 0;
public static int autoTest = 1;
  public static void testCode() throws IOException, CloneNotSupportedException, PolicyException{
    Scanner input = new Scanner(System.in);

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
      InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel("Tesla"), 500);
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
      // System.out.println("This is the total for Cooler city: $" + insuranceCompany.getTotalPaymentsForCity("Cooler city"));
       System.out.println("");


       //This is likely the correct output 
      // System.out.println("Here is the results per city: " + insuranceCompany.getTotalPaymentsPerCity(insuranceCompany.populateDistinctCityNames()));
       System.out.println("");


       //insuranceCompany.reportPaymentPerCity(insuranceCompany.populateDistinctCityNames(), insuranceCompany.getTotalPaymentsPerCity(insuranceCompany.populateDistinctCityNames()));
       System.out.println("");
       
       
     
       //Working 100%
      // System.out.println("Distinct car models " + insuranceCompany.distinctCarModels());

       //System.out.println("Distinct models for user 1 " + user1.distinctCarModels());

      // System.out.println("All cars, user 1 " + user1.allCars());

      // System.out.println("Model count " + user1.getTotalCountPerCarModel(user1.allCars()));

       //System.out.println("All cars, all users " + insuranceCompany.allCars());

       //System.out.println("Count all cars, all users: " + insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()));

      // System.out.println("Get total payment for car for user 1: " + user1.getTotalPaymentForCarModel("Tesla"));

      // System.out.println("Payments for all models, all users" + insuranceCompany.getTotalPaymentPerCarModelTest(insuranceCompany.distinctCarModels()));

       //System.out.println("Payment per car models for user 1" + user1.getTotalPaymentPerCarModel(user1.distinctCarModels()));

       //insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.distinctCarModels(), insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()), insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));

       /*
       if (insuranceCompany.removeUser(1, policies) == true){
        System.out.println("The user with ID: 1" + userID + ", Has been removed");
       } else {
        System.out.println("We have had an issue removing the user");
       }
       */
      // System.out.println(insuranceCompany.findUser(2));

       //System.out.println(insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()));

       //System.out.println(insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));
    
       insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.distinctCarModels(), insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()), insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));

          //Else ends here

        //System.out.println(user2.deepCopy(policies));
       //user1.printPolicies(1);


       //Poop, this changes the original as well
       /*
        ArrayList<InsurancePolicy> deepCopy = user1.deepCopyPolicies();
        for (InsurancePolicy copy: deepCopy){
          System.out.println();
          copy.setCarModel("Changed car model, deepCopy");
          copy.print();

        }


        System.out.println();
        System.out.println("Anything below here should not be changed");
        System.out.println();
        System.out.println();
        user1.printPolicies(1);

      Options.AdminMenu(insuranceCompany, policies);
      */

      /*
      ArrayList<InsurancePolicy> shallowPolicies = InsurancePolicy.shallowCopy(insuranceCompany.allPolicies());
      for (InsurancePolicy copy: shallowPolicies){
        copy.setCarModel("Shallow, should change all");
        copy.print();
        System.out.println();
         }

         */

         //Ok so this works, it's just in the Car where it is stuffing up, add a clone there maybe?
         /* 
    ArrayList<InsurancePolicy> deepCopy = InsurancePolicy.deepCopy(insuranceCompany.allPolicies());
      for (InsurancePolicy copy: deepCopy){
        copy.chnageUserID(56);
        copy.setCarModel("Deep, a different car model");
        copy.print();
        System.out.println();
         }
         System.out.println("DeepCopy end");


         System.out.println();
         System.out.println("User clone starts below");
         System.out.println();

         */


    /* 
    ArrayList<User> shallowCopy = user1.deepCopy(insuranceCompany.returnUserList());
    for (User copy: shallowCopy){
      copy.setName("The deepest copy");
      copy.print();
    }
    */

    /*
    ArrayList<InsurancePolicy> deepCopyPolicies = user1.deepCopyPolicies();
    for (InsurancePolicy copy: deepCopyPolicies){
      copy.setCarModel("The DeepCopyMethod car");
      copy.print();
    }
    */

    /*
    ArrayList<User> userCopy = insuranceCompany.deepCopyUsers();
    for (User user: userCopy){
      user.setName("Deep cloned");
      user.print();
    }
    */




    System.out.println("And now below, the orginal list");
    insuranceCompany.printUsers();

         //insuranceCompany.print();

  System.out.println(user1.getPolicy(1));

  //Works 
  // System.out.println(user1.getPolicy(1).compareTo(new MyDate(2020, 12, 21)));

  // System.out.println(user1.compareTo(user1.getAddress()));

   //Not sure
   //System.out.println(user1.compareTo1());

   ArrayList<InsurancePolicy> policesSorted = user1.sortPoliciesByDate();
   System.out.println("");
   System.out.println("Size is");
   System.out.println(policesSorted.size());

    }

    ArrayList<InsurancePolicy> deepCopyPolicies = user1.deepCopyPolicies();
    ArrayList<InsurancePolicy> shallowCopyUsers = user1.shallowCopyPolicies();

    user1.setCity("New York");
    insuranceCompany.createComprehensivePolicy(1, "New One", 12, new Car(Car.CarType.HATCH, "New Car", 2023, 21020), 0, 19, 1, new MyDate(2024, 5, 21));

    ArrayList<User> deepCopyUsers = insuranceCompany.deepCopyUsers();
    ArrayList<User> shallowCopyUser = insuranceCompany.shallowCopyUsers();

    insuranceCompany.addUser(new User("New User", 5, new Address(54, "Super New", "Street", "City")));


    //Print
    System.out.println("Policy Copies below");
    System.out.println("");

    for (InsurancePolicy policy: deepCopyPolicies){
      policy.setPolicyHolderName("DeepCopyPolicies");
      policy.print();
      System.out.println("");
    }

    for (InsurancePolicy policy: shallowCopyUsers){
      policy.print();
      System.out.println("");
    }
    System.out.println("");
    System.out.println("Uncloned policies");
    System.out.println("");
    
    user1.printPolicies(500);

    System.out.println("");
    System.out.println("Cloned users");

    for (User user: deepCopyUsers){
      user.setName("deepCopy users");
      user.print();
      System.out.println("");
    }

    for (User user: shallowCopyUser){
      //user.setName("ChangeAcrossShallowAndOrignal");
      user.print();
      System.out.println("");
    }

    System.out.println("");
    System.out.println("Uncloned user list");
    insuranceCompany.printUsers();

    System.out.println("Compare1 Test");
   // System.out.println(user1.compareTo1());
    ArrayList<InsurancePolicy> sortedPolicies = user1.compareTo1();
    for (InsurancePolicy sorted: sortedPolicies){
      System.out.println(sorted.calcPayment(500));
      System.out.println("");

    }

    System.out.println("Sorting by date");
    System.out.println();

    ArrayList<InsurancePolicy> dateSorted = user1.sortPoliciesByDate();
    for (InsurancePolicy sortedDate: dateSorted){
      sortedDate.print();
      System.out.println("");

    }

    System.out.println("Sort Users by ascending alethabet city names");
    ArrayList<User> userList = insuranceCompany.sortUsers();
    for (User aUser: userList){
      System.out.println(aUser.getCity());
      //aUser.printPolicies(500);
      System.out.println();
    }

    System.out.println("Printing Cloned company name");
    System.out.println("");

    InsuranceCompany clonedCompany = insuranceCompany.clone();
    clonedCompany.setName("Cloned Company");
    System.out.println(clonedCompany.getName());
    System.out.println("");

    System.out.println("Printing oringal company name");
    System.out.println(insuranceCompany.getName());



  }
}
