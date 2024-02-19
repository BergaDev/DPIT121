//Matthew Bergamini - 8137225

import java.util.ArrayList;

abstract class InsurancePolicy implements Cloneable, Comparable<MyDate>{
  protected String policyHolderName;
  protected int id;
  protected Car car;
  protected int numberOfClaims;
  protected MyDate expiryDate;

 //The constuctor for the abstract class, it must contain anything things passed in the main process, clears errors there and in the children
  public InsurancePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate){
    this.policyHolderName = policyHolderName;
    this.id = id;
    this.car = car;
    this.numberOfClaims = numberOfClaims;
    //I forgot this
    this.expiryDate = expiryDate;
  }

  //Copy consturctors for Lab 4
  InsurancePolicy (InsurancePolicy ipCopy){
    this.policyHolderName = ipCopy.policyHolderName;
    this.id = ipCopy.id;
    this.car = ipCopy.car;
    this.numberOfClaims = ipCopy.numberOfClaims;
    this.expiryDate = ipCopy.expiryDate;
  }


  abstract public double calcPayment(double flatRate);

  public void print(){
    System.out.println("Policy Holder Name: " + policyHolderName + " ID number: " + id + " Car " + car + " Number of Claims: " + numberOfClaims + " Expiry Date: " + expiryDate);
  }

  public String toString(){
    //return super.toString(); //Maybe just don't have anything else trying to print on this one hey?
    return "Policy Holder Name: " + policyHolderName + " ID number: " + id + " Car " + car + " Number of Claims: " + numberOfClaims + " Expiry Date: " + expiryDate;
    //return null; Litrally just returns null
  }

  //Starting setters
  public void setPolicyHolderName(String policyHolderName){
    this.policyHolderName = policyHolderName;
  }

  public void setId(int id){
    this.id = id;
  }

  public void setCar(Car car){
    this.car = car;
  }

  public void setNumberOfClaims(int numberOfClaims){
    this.numberOfClaims = numberOfClaims;
  } 

  public void setExpiryDate(MyDate expiryDate){
    this.expiryDate = expiryDate;
  }

  //Starting getters
  public String getPolicyHolderName(){
    return policyHolderName;
  }

  public int getId(){
    return id;
  }

  public Car getCar(){
    return car;
  }

  public int getNumberOfClaims(){
    return numberOfClaims;
  }

  public MyDate getExpiryDate(){
    return expiryDate;
  }


  public double getPolicyPremium(double flatRate){
    double policyPremium = calcPayment(flatRate);
     policyPremium = Math.round(policyPremium*100);
     policyPremium = policyPremium /100;

     return policyPremium;
  }

  public static void printPolicies(ArrayList<InsurancePolicy>policies){
    for (InsurancePolicy policy : policies){
      policy.print(); //Print using the print method
      System.out.println();
    }
  }

  //This one answes Lab 2 requirment of printing policy with premium printed
  public static void printPolicies(ArrayList<InsurancePolicy>policies, int flatRate){
    for (InsurancePolicy policy : policies){
     double policyPremium = policy.calcPayment(flatRate);
     policyPremium = Math.round(policyPremium*100);
     policyPremium = policyPremium /100;
      policy.print(); //Print using the print method
      System.out.println("Premium for this policy is: $" + policyPremium);
      System.out.println();
    }
  }

  public static double calcTotalPayments(ArrayList<InsurancePolicy>policies, int flatRate){
    double totalIP = 0;

    //Bassicly just copy and paste the main code right in here
    for (InsurancePolicy policy : policies){
      totalIP += policy.calcPayment(flatRate);
      return totalIP;
    }
    //Make sure to have last, outisde of an {}
    return totalIP;

    
  }

  //Note to self, don't put static where it isnt needed 
  public void carPriceRise(double risePercent){
    car.priceRise(risePercent);
  }
  
  //carPriceRiseAll
  public static void carPriceRiseAll(ArrayList<InsurancePolicy>policies, double risePercent){
    for (InsurancePolicy policy : policies){
      policy.carPriceRise(risePercent); 
    }
  }

  //filtedByCarModel
  static ArrayList<InsurancePolicy> filterByCarModel (ArrayList<InsurancePolicy>policies, String carModel){

    //In Car class because it find model that way, and per example
    return Car.filterByCarModel(policies, carModel);

  }

  //Add policy to user 
  public static void addPolicy(User user, InsurancePolicy policy){
    if(user.addPolicy(policy)){
      System.out.println("Added policy to user");
      System.out.println();
    }else {
      System.out.println("!! Failed to add policy to user !!");
      System.out.println();
    }
  }

  public static void removePolicy(User user, InsurancePolicy policy){
    if(user.removePolicy(policy)){
      System.out.println("Removed policy from user");
      System.out.println();
    }else {
      System.out.println("!! Failed to remove policy from user !!");
      System.out.println();
    }
  }

    public static void removeUser(User user, InsurancePolicy policy){
    if(user.removePolicy(policy)){
      System.out.println("Removed policy from user");
      System.out.println();
    }else {
      System.out.println("!! Failed to remove policy from user !!");
      System.out.println();
    }
  }

  void setCarModel(String model){
    car.setModel(model);
  }
  void setManufacturingYear(int year){
    car.setManufacturingYear(year);
  }

  static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy>policies, MyDate date){
    ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();
    for (InsurancePolicy policy: policies){
      if(policy.getExpiryDate().isExpired(date)){
        expiredPolicies.add(policy);
      }
    }
    return expiredPolicies;
  }

 static void removePolicy (ArrayList<InsurancePolicy> policies, int policyID, int userID){
int item = 0;
  for (int i = 0; i < policies.size(); i++){
    item = i + 1;
    System.out.println("This is item: " + item);
  }
  //policies.remove(0);
  System.out.println("Not even here?");
    
  }

  static ArrayList<InsurancePolicy> getCarModel (ArrayList<InsurancePolicy> policies){
    ArrayList<InsurancePolicy> cars = new ArrayList<>();
    Boolean isTrue = false;
    for (InsurancePolicy policy: policies){
      policy.getCar().getType();
    }

    
    return cars;
  }


  static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException{
    ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();

    for (InsurancePolicy policy: policies){
      shallowCopy.add(policy);
    }
    return shallowCopy;
  }

  //Works
  static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException{
    ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
    deepCopy.clear();
    
    for (InsurancePolicy policy: policies){
      deepCopy.add((InsurancePolicy) policy.clone());
    }
    return deepCopy;
  }

  //Here for testing, works 
  public void chnageUserID(int id){
    this.id = id;
  }


  //This is important to have in every class
  @Override
  public Object clone() throws CloneNotSupportedException{
    InsurancePolicy output = (InsurancePolicy) super.clone();
    output.expiryDate = (MyDate) expiryDate.clone();
    output.car = (Car) car.clone();
    return output;
  }

  @Override
  public int compareTo(MyDate MyDate) {
    return expiryDate.compareTo(MyDate);
  }

  public MyDate calcDate(){
   MyDate dates = new MyDate(new MyDate(expiryDate.year, expiryDate.month, expiryDate.day));
    return dates;
  }
  


  

  

  
  


  
}
