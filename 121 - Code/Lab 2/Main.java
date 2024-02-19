//Matthew Bergamini - 8137225

import java.util.*;
import java.util.Scanner;

//import Car.CarType;




public class Main {

  public static int flatRateVal =500;
  public static double total = 0;

  //AUTO STRING ENTER
  public static int autoTest = 1;
  public static void main(String[] args){

    //Declaring scanner at the top of this
    Scanner input = new Scanner(System.in);

    //Some test code to play with getter and setters
    //Though setters I don't know the purpose of using when I've already put in the string
    //Oh maybe its for changing it later 

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

  }
}
