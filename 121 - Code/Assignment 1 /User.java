//Matthew Bergamini - 8137225
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class User {
  
  private String name;
  private int userID;
  private Address address;
  private ArrayList<InsurancePolicy>policies;

  public User (String name, int userID, Address address){
    this.name = name;
    this.userID = userID;
    this.address = address;
    //Maybe ArrayList thing?
    this.policies = new ArrayList<>();
  }

  public void setName(String name){
    this.name = name;
  }
  public void setUserID(int userID){
    this.userID = userID;
  }
  public void setAddress(Address address){
    this.address = address;
  }
  public String getName(){
    return name;
  }
  public int getUserID(){
    return userID;
  }
  public Address getAddress(){
    return address;
  }
  public ArrayList<InsurancePolicy> getPolicies(){
    return policies;
  }
  public InsurancePolicy getPolicy(int i){
    return policies.get(i);
  }

  
  void setCity(String city){
    address.setCity(city);
  }
  void setStreetNum(int streetNum){
    address.setStreetNum(streetNum);
  }
  void setStreet(String street){
    address.setStreet(street);
  }
  void setSuburb(String suburb){
    address.setSuburb(suburb);
  }


  boolean addPolicy(InsurancePolicy policy){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    policies.add(policy);
    return true;
  }

    boolean removePolicy(InsurancePolicy policy){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    policies.remove(policy);
    return true;
  }

      boolean removeUser(InsurancePolicy policy){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    policies.remove(policy);
    return true;
  }

  InsurancePolicy findPolicy (int policyID){
    for (InsurancePolicy policy:policies){
      //getId exisits in InsurancePolicy, the Id has an uncap d
      if (policy.getId()==policyID){
        return policy;
      }
    }
    System.out.println("!!  An existing policy has not been found  !!");
    return null;
  }


  public void print(){
    System.out.println("Name: " + name + " ID of user: " + userID);
    System.out.print("Users address: ");
    address.print();
    //Don't need the polices printed when just getting user information
    //InsurancePolicy.printPolicies(policies);
    System.out.println();

  }
  
  //This needs a bit of work
  public String toString(){
    return super.toString()+"Name: " + name + " ID of user: " + userID; 
  }

  //Calls a new overloadded method in InsurancePolicy, don't confuse the two
  void printPolicies(int flatRate){
    //See it passes both policies and flatRate to use the correct method
    InsurancePolicy.printPolicies(policies,flatRate);
  }


  double calcTotalPremiums (int flatRate){
   return InsurancePolicy.calcTotalPayments(policies, flatRate);
  }

  void carPriceRiseAll(double risePercent){
    InsurancePolicy.carPriceRiseAll(policies, risePercent);
  }

  ArrayList<InsurancePolicy>filterByCarModel (String carModel){
    return InsurancePolicy.filterByCarModel(policies, carModel);
  }

  //Whoops

//Todo, add expiry date to the constructors 
  Boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments){
    return addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate));
  }

  Boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level){
    return addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate));
  }

  
  ArrayList<InsurancePolicy> filterByExpiryDate (MyDate date){
    return InsurancePolicy.filterByExpiryDate(policies, date);
  }

  ArrayList<String> allCars(){
    ArrayList<String> allCars = new ArrayList<>();
    ArrayList<ArrayList <InsurancePolicy>> userPolicies = new ArrayList<>();
      int numOfPolicies = getPolicies().size();
      userPolicies.add(getPolicies());

      for (int i = 0; i < getPolicies().size(); i++){
        allCars.add(getPolicy(i).getCar().getModel());
      }

      return allCars;
  }

  //Wokring
  ArrayList<String> distinctCarModels(){
    ArrayList<String> allCars = new ArrayList<>();
    
      ArrayList<ArrayList <InsurancePolicy>> userPolicies = new ArrayList<>();
      int numOfPolicies = getPolicies().size();
      userPolicies.add(getPolicies());

      for (int i = 0; i < getPolicies().size(); i++){
        allCars.add(getPolicy(i).getCar().getModel());
      }
    
    //Behold, my mess
    //HashSet takes an array and makes it unique
    Set<String> uniqueModels = new HashSet<>(allCars);
    //Below just makes it match the required return type 
    ArrayList<String> uniqueCars = new ArrayList<>(uniqueModels);
    return uniqueCars;
  }

  //Working
  ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels){
    ArrayList<Integer>  modelCount = new ArrayList<>();
    ArrayList<InsurancePolicy> usersPolicies = new ArrayList<>();

      for (int i = 0; i < getPolicies().size(); i++){
        usersPolicies.add(getPolicy(i));
      }

    for (String model: carModels){
      int count = 0;
      for (int i = 0; i < usersPolicies.size(); i++){
       if (usersPolicies.get(i).getCar().getModel().equals(model)){
        count = count + 1;
       }
      }
      modelCount.add(count);
    }    
    return modelCount;
  }

//Working
  double getTotalPaymentForCarModel(String carModel){
    double carTotal = 0;
    ArrayList<ArrayList <InsurancePolicy>> userPolicies = new ArrayList<>();
      userPolicies.add(getPolicies());

      for (int i = 0; i < getPolicies().size(); i++){
        if (getPolicy(i).getCar().getModel() == carModel){
          carTotal = (carTotal + getPolicy(i).calcPayment(InsuranceCompany.flatRateStatic));
        }
      }
      return carTotal;
  }
//Working
  ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels){
    ArrayList<Double>  modelPayment = new ArrayList<>();
    ArrayList<InsurancePolicy> usersPolicies = new ArrayList<>();

      for (int i = 0; i < getPolicies().size(); i++){
        usersPolicies.add(getPolicy(i));
      }

    for (String model: carModels){
      Double carPayment = 0.0;
      for (int i = 0; i < usersPolicies.size(); i++){
       if (usersPolicies.get(i).getCar().getModel().equals(model)){
        carPayment = carPayment + usersPolicies.get(i).calcPayment(InsuranceCompany.flatRateStatic);
       }
      }
      modelPayment.add(carPayment);
    }    
    return modelPayment;
  }

void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments){
    String format = "%1$-20s%2$-30s%3$-20s\n";
    System.out.format(format, "Car Model", "Total Premium Payment", "Average Premium Payment");

    for (int i = 0; i < carModels.size(); i++){
      double calcAvg = (premiumPayments.get(i) / counts.get(i));

      System.out.format(format, carModels.get(i), premiumPayments.get(i), calcAvg);
    }

}
  
  
}
