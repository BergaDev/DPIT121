//Matthew Bergamini - 8137225
import java.util.ArrayList;


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

  
  
  
}
