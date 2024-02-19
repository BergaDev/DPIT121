//Matthew Bergamini - 8137225

import java.util.*;

//import Car.CarType;




public class Main {

  public static double flatRateVal =500;
  public static double total = 0;
  public static void main(String[] args){


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
