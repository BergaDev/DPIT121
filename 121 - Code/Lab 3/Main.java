//Matthew Bergamini - 8137225

import java.util.*;

//import Car.CarType;

//import Car.CarType;




public class Main {

  //public static int flatRateVal =500;
  public static double total = 0;

  //AUTO STRING ENTER
  public static int autoTest = 1;
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);

    System.out.println("Creating first policies");
    //Corrected the constructors for the policies which i've had wrong since lab 1, whoops
    Car car1 = new Car(Car.CarType.SUV, "Tesla", 2020, 69000);
    //This one is not added to array yet, it's used to check duplicate later in the tests 
    ThirdPartyPolicy policy4 = new ThirdPartyPolicy("Philip", 12, new Car(Car.CarType.SED, "MG", 2021, 15000), 0, "Tests the filter array", new MyDate(2024, 10, 14));
    ThirdPartyPolicy policy6 = new ThirdPartyPolicy("Matt", 4, new Car(Car.CarType.SED, "Kia", 2021, 59000), 0, "Tests the filter array", new MyDate(2023, 12, 31));
    
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
      insuranceCompany.createThirdPartyPolicy(1, "Jack", 2, new Car(Car.CarType.HATCH, "Toyota", 2018, 20000), 1, "This is policy2", new MyDate(2025, 9, 24));
      insuranceCompany.createComprehensivePolicy(1, "Jill", 3, new Car(Car.CarType.LUX, "BMW", 2023, 110000), 1, 40, 5,  new MyDate(2023, 6, 31));
      insuranceCompany.createThirdPartyPolicy(1, "Sam", 4, new Car(Car.CarType.SED, "Polestar", 2021, 59000), 0, "This is just a duplicate thing", new MyDate(2022, 12, 31));
      insuranceCompany.createThirdPartyPolicy(2, "Matt", 4, new Car(Car.CarType.SED, "Kia", 2021, 59000), 0, "Tests the filter array", new MyDate(2023, 12, 31));
      //Checks for duplicate id's now
      insuranceCompany.createThirdPartyPolicy(3, "New Policy", 4, new Car(Car.CarType.LUX, "Polestar", 2022, 70000), 0, "This is a new Policy", new MyDate(2023, 1, 21));
      insuranceCompany.createComprehensivePolicy(1, "Another New Policy", 68, new Car(Car.CarType.HATCH, "Honda", 2009, 7000), 3, 19, 1, new MyDate(2024, 2, 11));
      insuranceCompany.createThirdPartyPolicy(3, "Newest Policy", 78, new Car(Car.CarType.LUX, "Cupra", 2022, 100000), 1, "This is another new policy", new MyDate(2024, 5, 15));
      

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

      System.out.println("Enter a userID to print user policies");
      int userIN;
      if (autoTest == 1){
        userIN = 2;
      } else{
        userIN = input.nextInt();
      }
      insuranceCompany.printPolicies(userIN);
      
      System.out.println("Enter userID and policyID to find the matching policy");
      userIN = 0;
      int userPolicyID = 0;
      if (autoTest == 1){
        userIN = 1;
        userPolicyID = 2;
      } else {
        System.out.println("Enter userID");
        userIN = input.nextInt();
        System.out.println("Enter policyID");
        userPolicyID = input.nextInt();
      }
      insuranceCompany.findPolicy(userIN, userPolicyID);
      System.out.println("");

      System.out.println("Printing all users inside the insuranceCompany");
      insuranceCompany.print();

      System.out.println("Price rise all users");
      insuranceCompany.carPriceRise(0.1);
      insuranceCompany.print();
      System.out.println("");

      System.out.println("Print payments for one user, $"  + insuranceCompany.calcTotalPayments(2));
      System.out.println("");

      System.out.println("Total premiums for all users in company, $" + insuranceCompany.calcTotalPayments());
      System.out.println("");

      System.out.println("Print all policies in insuranceCompany");
      InsurancePolicy.printPolicies(insuranceCompany.allPolicies());
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
     
    


          //Else ends here
    }

    
    }

    

    
  public void Lab1(){


    /* 
    //Create a car
    Car car1 = new Car(Car.CarType.SUV, "Tesla", 2020, 69000);
    
    Car car2 = new Car(Car.CarType.HATCH, "Toyota", 2018, 20000);
    Car car3 = new Car(Car.CarType.LUX, "BMW", 2023, 10000000);
    Car car4 = new Car(Car.CarType.SED, "Kia", 2021, 30000);
    

    //Creating the first policy
    ComprehensivePolicy policy1 = new ComprehensivePolicy("James", 1, car1, 0, 19, 1, "This is the first account");
    ThirdPartyPolicy policy2 = new ThirdPartyPolicy("Jack", 2, car2, 2, 31, 2, "This is the second policy");
    ComprehensivePolicy policy3 = new ComprehensivePolicy("Jill", 3, car3, 0, 30, 5, "Is rich");
    ThirdPartyPolicy policy4 = new ThirdPartyPolicy("John", 4, car4, 1, 50, 2, "Has some worth");
    System.out.println("Policies created");

    //THE CAR PART IS NOT WORKING, don't know why it isn't, seems to just store the information from the last policy

    */
    
    /* 
    ComprehensivePolicy policy1 = new ComprehensivePolicy("James", 1, new Car(Car.CarType.SUV, "Tesla", 2020, 69000), 0, 19, 1, "This is the first account");
    ThirdPartyPolicy policy2 = new ThirdPartyPolicy("Jack", 2, new Car(Car.CarType.HATCH, "Toyota", 2018, 20000), 3, 31, 2, "This is the second policy");
    ComprehensivePolicy policy3 = new ComprehensivePolicy("Jill", 3, new Car(Car.CarType.LUX, "BMW", 2023, 110000), 1, 40, 5, "Is rich");
    ThirdPartyPolicy policy4 = new ThirdPartyPolicy("John", 4, new Car(Car.CarType.SED, "Kia", 2021, 30000), 1, 50, 2, "Has some worth");
    

    ArrayList<InsurancePolicy> policies = new ArrayList<>();
    policies.add (policy1);
    policies.add (policy2);
    policies.add (policy3);
    policies.add (policy4);
    System.out.println("Added to array");

    System.out.println("Printing using print method");
    for (InsurancePolicy policy : policies){
      policy.print(); //Print using the print method
    }
    System.out.println("Printing using toString method");

    for (InsurancePolicy policy : policies){
      System.out.println(policy); //Print using toString 
    }

   System.out.println("Doing calcs");
    for (InsurancePolicy policy : policies){
      
      System.out.println("The price of " + policy.policyHolderName + " is " + policy.calcPayment(flatRateVal));
      //total + policy.calcPayment(flatRateVal);
      //I need to redo this
      total += policy.calcPayment(flatRateVal);

      System.out.println("Total Payment is " + total);
    }
    //System.out.println("Total Payment is " + total);
    System.out.println("Payment calcs done");
    */

  }

  public void Lab2(){

    /*
    Scanner input = new Scanner(System.in);
    ComprehensivePolicy policy1 = new ComprehensivePolicy("James", 1, new Car(Car.CarType.SUV, "Tesla", 2020, 69000), 0, 19, 1, "This is the first account");
    ThirdPartyPolicy policy2 = new ThirdPartyPolicy("Jack", 2, new Car(Car.CarType.HATCH, "Toyota", 2018, 20000), 3, 31, 2, "This is the second policy");
    ComprehensivePolicy policy3 = new ComprehensivePolicy("Jill", 3, new Car(Car.CarType.LUX, "BMW", 2023, 110000), 1, 40, 5, "Is rich");
    ThirdPartyPolicy policy4 = new ThirdPartyPolicy("John", 4, new Car(Car.CarType.SED, "Kia", 2021, 30000), 1, 50, 2, "Has some worth");
    ThirdPartyPolicy policy5 = new ThirdPartyPolicy("Matt", 4, new Car(Car.CarType.SED, "Tesla", 2021, 59000), 0, 20, 2, "Tests the filter array");
    
    ArrayList<InsurancePolicy> policies = new ArrayList<>();
    policies.add (policy1);
    policies.add (policy2);
    policies.add (policy3);
    policies.add (policy4);
    policies.add (policy5);
    System.out.println("Added to array");


    

    

    //This calls the method from the InsurancePolicy Class and passes the policies array
    System.out.println("About to start the method");
    InsurancePolicy.printPolicies(policies);
    System.out.println("And have run the print method, if not working, L");

    //Calc method
    //This must be done by using (Val = InsurancePolicy.clacTotalPayments(Policies, Val);)
    double totalVal = InsurancePolicy.calcTotalPayments(policies, flatRateVal);
    System.out.println("The total value of premiums owed is: " + totalVal);


    //Run price rise
    System.out.println("Running price rise");
    //Call a single policy and run the price rise
    policy1.carPriceRise(0.1);
    InsurancePolicy.printPolicies(policies);
    System.out.println("Should have seen a price rise on first policy");
    //Done the first price rise

    //Price rise all
    System.out.println("Starting priceRiseAll");
    InsurancePolicy.carPriceRiseAll(policies, 0.1);
    InsurancePolicy.printPolicies(policies);
    System.out.println("Should have seen a price rise across all cars");

    //Filter by model
    System.out.println("Finding car model: " + InsurancePolicy.filterByCarModel(policies, "Tesla"));
    //InsurancePolicy.filterByCarModel(policies, "Tesla");
    System.out.println("Should have returned policy");

    /* Adding the create a user bit here because items below this need to have this exist beforehand  */

    //Create a user
    /*
    Address address1 = new Address(1,"First Street", "Cool suburb", "Cooler city");
    User user1 = new User("James", 01, address1);

    //Adding policy to user 
    //The test case for lab 2 wants more than 1 policies to be added to a user 
    System.out.println("This is before add to user addPolicy()");
    InsurancePolicy.addPolicy(user1, policy1);
    InsurancePolicy.addPolicy(user1, policy2);
    InsurancePolicy.addPolicy(user1, policy5);
    //InsurancePolicy.addPolicy(user1, policy3);
    //InsurancePolicy.addPolicy(user1, policy4);
    System.out.println("This is after that");

    System.out.println("Find a users policy by the policy ID");
    //Find policy by ID, check for correct return
    //I have the check in two places, this can check to not break the whole program then
    InsurancePolicy filteredPolicies = user1.findPolicy(1);
    if(filteredPolicies == null){
      System.out.println("A policy ID for this user can not be found");
    } else {
      filteredPolicies.print();
    }

    System.out.println("Finding a policy by ID and chnaging infromation about a policy");
    //Storing and changing the data of a policy by PolicyID 
    filteredPolicies = user1.findPolicy(1);
    if (filteredPolicies == null){
      System.out.println("Policy ID does not exist for this user");
    } else {
      filteredPolicies.carPriceRise(0.1);
      filteredPolicies.setPolicyHolderName("Robert");
      filteredPolicies.setCarModel("Toyota Camry");
      filteredPolicies.setManufacturingYear(2018);
      filteredPolicies.print();
    }


    

    System.out.println("Call user1.print()");
    user1.print();

    //This calls a new print method in InsurancePolicy that takes in flatRateVal, finally an organic chance to use method overloading
    System.out.println("Testing print policies in user class, check InsurancePolicy when an issue arrises");
    user1.printPolicies(flatRateVal);
   
    System.out.println("Finding total premiums for a user");
    //Calc total preium
    System.out.println("Total premium for this user is: $" + user1.calcTotalPremiums(flatRateVal));

    System.out.println("Price rise all policies a user holds");
    //Price rise all policies attached to user
    System.out.println("Starting price rise on all users");
    user1.carPriceRiseAll(0.1);
    user1.printPolicies(flatRateVal);
    System.out.println("Total premium for this user is: $" + user1.calcTotalPremiums(flatRateVal));
    System.out.println("Done price rise all");

    System.out.println("Filtering a users policies by car model");
    //Filter a users policies by car model
    System.out.println("Filter by car model Tesla: " + user1.filterByCarModel("Tesla"));
    System.out.println("");

    System.out.println("Changing address stuff");
    //Change the users city to wollongong
    address1.setCity("Wollongong");
    System.out.println("City for user1 is: " + address1.getCity());
    System.out.println("");

    System.out.println("Enter the new address of the user ");
    
    int inputStreetNum;
    String inputStreetName;
    String inputSuburbName;
    String inputCityName;
    

    //Process for changing address
    //Automating lab task tests, makes like quicker
    if (autoTest == 1){
      inputStreetNum = 59;
      inputStreetName = "New Street Name";
      inputSuburbName = "New Suburb Name";
      inputCityName = "New City Name";
    } else {
      System.out.println("Enter Street Number: ");
     inputStreetNum = input.nextInt();
     

    System.out.println("Enter Street Name: ");
    input.next();
     inputStreetName = input.nextLine();
     

    System.out.println("Enter Suburb Name: ");
      input.next();
     inputSuburbName = input.nextLine();

    System.out.println("Enter City Name: ");
     input.next();
     inputCityName = input.nextLine();
    }

    //This is all updated now to go through the user, uses the address instance that belongs to a user, instead of indivudually calling address1 
    user1.setStreetNum(inputStreetNum);
    user1.setStreet(inputStreetName);
    user1.setSuburb(inputSuburbName);
    user1.setCity(inputCityName);

    System.out.println("Street num is: " + address1.getStreetNum() + " Street Name is: " + address1.getStreet() + " Suburb name is: " + address1.getSuburb() + " City name is: " + address1.getCity());


    System.out.println("Filter by car model");
    //FIlter by car model
    System.out.println("Enter a car model to filter for (Tesla or Toyota)");
    String inputCarModel;
    if (autoTest == 1){
     inputCarModel = "Tesla";
    } else {
     inputCarModel = input.nextLine();
      //System.out.println("Filter by car model (Tesla or Toyota): " + user1.filterByCarModel(inputCarModel));
    }
    //Create array, store result
    ArrayList<InsurancePolicy> filteredCars = user1.filterByCarModel(inputCarModel);
    //Print any resulting policies
    InsurancePolicy.printPolicies(filteredCars);
    */
    
  }

  void lab3Additions(){

    // User user3 = new User("User 3", 03, new Address(3, "Third Street", "Third Suburb", "Third City"));
    //User user3 = new User("Jack", 03, address1);

    //Adding policy to user 
    //The test case for lab 2 wants more than 1 policies to be added to a user 
    /*
    System.out.println("This is before add to user addPolicy()");
    InsurancePolicy.addPolicy(user1, policy1);
    InsurancePolicy.addPolicy(user1, policy2);
    InsurancePolicy.addPolicy(user2, policy5);
    //InsurancePolicy.addPolicy(user2, policy3);
    //InsurancePolicy.addPolicy(user1, policy4);
    System.out.println("This is after that");
     */

     //This one needs work in the InsurancePolicy class, need to work on isExpired part 
    /* 
    ArrayList<InsurancePolicy> ExpiryArray = user1.filterByExpiryDate(new MyDate(2023, 11, 31));
    InsurancePolicy.printPolicies(ExpiryArray);
    System.out.println();
    */

    /* 
    System.out.println("Testing adding users, user1 & 2 have the same userID");
    insuranceCompany.addUser(user1);
    insuranceCompany.addUser(user2);

    System.out.println("Add user using the seocond addUser method");
    insuranceCompany.addUser("John", 3, new Address(6, "Test Street", "Test Suburb", "Test City"));

    System.out.println("Testing adding a policy to a user, and confirming that the policy was added");
    insuranceCompany.addPolicy(3, policy3);
    insuranceCompany.addPolicy(3, policy2);
    //findUser should not be used to print the users policies

    insuranceCompany.findPolicy(3, 3);
    System.out.println();

    System.out.println("Starting to print all policies for a user");
    insuranceCompany.printPolicies(3);
    
    System.out.println("Starting to print all policies for all users");
    insuranceCompany.print();

    System.out.println("Creating new policies and adding to user");
    insuranceCompany.createThirdPartyPolicy(3, "New Policy", 58, new Car(Car.CarType.LUX, "Polestar", 2022, 70000), 0, expiryDate1, 39, 1);
    insuranceCompany.createComprehensivePolicy(3, "Another New Policy", 68, new Car(Car.CarType.HATCH, "Honda", 2009, 7000), 3, expiryDate1, 19, 1);
    insuranceCompany.printPolicies(3);

    System.out.println("Calc a users total premiums $" + insuranceCompany.calcTotalPayments(3));

    System.out.println("Total Premium of all users $" + insuranceCompany.calcTotalPayments());
    System.out.println("");

    System.out.println("Price Rise on all of a users policies " + insuranceCompany.carPriceRise(3, 0.1));
    insuranceCompany.printPolicies(3);

    System.out.println("Price rise across all users");
    insuranceCompany.carPriceRise(0.1);

    System.out.println("Add all policies and print");
    InsurancePolicy.printPolicies(insuranceCompany.allPolicies());

    System.out.println("Filter a users policies by car model");
    InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(1, "Tesla"));

    System.out.println("Filter a users policies by expiry date - Still not working");
    /* 
    user1.filterByExpiryDate(expiryDate1);
    
    ArrayList<InsurancePolicy> filterByExpiry1 = insuranceCompany.filterByExpiryDate(1, expiryDate1);
    //InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(1, new MyDate(2023, 11, 31)));
    */

    /* 
    System.out.println("Filter by car model for all users");
    InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel("Tesla"));

    */

  }

  //This is actually past the old labs
}
