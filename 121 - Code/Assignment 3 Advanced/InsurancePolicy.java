//Matthew Bergamini - 8137225

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;


abstract class InsurancePolicy implements Cloneable, Comparable<MyDate>, Serializable {




  protected String policyHolderName;
  protected int id;
  protected Car car;
  protected int numberOfClaims;
  protected MyDate expiryDate;


 //The constuctor for the abstract class, it must contain anything things passed in the main process, clears errors there and in the children
  public InsurancePolicy (String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate) throws PolicyException, NameException{
    //this.policyHolderName = policyHolderName;
    
    try {
      if (id < 3000000 || id > 3999999){
        throw new PolicyException(id);
      } else {
        this.id = id;
      }
    } catch (PolicyException e) {
      this.id = e.genID();
    }

    try {
      if (Pattern.matches("\\b[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*\\b", policyHolderName)){
        this.policyHolderName = policyHolderName;
      } else {
        throw new NameException();
      }
    } catch (NameException e) {
      this.policyHolderName = e.newName();
    }
    
    //this.id = id;
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

    public abstract int getAge();

    public abstract int getLevel();

    public String getPolicyType(){
      return getPolicyType();
  }




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

//  public static void printPolicies(ArrayList<InsurancePolicy>policies){
//    for (InsurancePolicy policy : policies){
//      policy.print(); //Print using the print method
//      System.out.println();
//    }
//  }

    public static void printPolicies(ArrayList<InsurancePolicy>policies){
        policies.forEach(System.out::println);
    }


  public static void printPolicies(HashMap<Integer, InsurancePolicy>policies){
    Set<Integer> keys = policies.keySet();
    for (Integer policy : keys){
      policies.get(policy).print(); //Print using the print method
      System.out.println();
    }
  }

  //Hash - noPremium
  public static void printUserPolicies(HashMap<Integer, InsurancePolicy> policies, int userID){
    policies.get(userID);
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

  //Hash - with premium 
  public static void printPolicies(HashMap<Integer, InsurancePolicy>policies, int flatRate){
      Set<Integer> keys = policies.keySet();
    for (Integer key : keys){
     double policyPremium = policies.get(key).calcPayment(flatRate);
     policyPremium = Math.round(policyPremium*100);
     policyPremium = policyPremium /100;
      policies.get(key).print(); //Print using the print method
      System.out.println("Premium for this policy is: $" + policyPremium);
      System.out.println();
    }
  }

    public static void printPolicies(InsurancePolicy policy, int flatRate){
            double policyPremium = policy.calcPayment(flatRate);
            policyPremium = Math.round(policyPremium*100);
            policyPremium = policyPremium /100;
            policy.print(); //Print using the print method
            System.out.println("Premium for this policy is: $" + policyPremium);
            System.out.println();
    }

    public static String printPolicy(InsurancePolicy policy, int flatRate){
        double policyPremium = policy.calcPayment(flatRate);
        policyPremium = Math.round(policyPremium*100);
        policyPremium = policyPremium /100;

        String output = (policy.toDelimitedString() + ", Premium for this policy is: $" + policyPremium + "\n");
        return output;
    }

//  public static double calcTotalPayments(ArrayList<InsurancePolicy>policies, int flatRate){
//    double totalIP = 0;
//
//
//    //Bassicly just copy and paste the main code right in here
//    for (InsurancePolicy policy : policies){
//
//      totalIP = totalIP + policy.calcPayment(flatRate);
//
//    }
//    //Make sure to have last, outisde of an {}
//    return totalIP;
//
//
//  }

    public static double calcTotalPayments(ArrayList<InsurancePolicy>policies, int flatRate){
        return policies.stream()
                //Turns each policy into a stream
                .map(x->x.calcPayment(flatRate))
                //This part sums up all streams
                .reduce(0.0,(x,y)->x+y);
    }

  //Hash
  public static double calcTotalPayments(HashMap<Integer, InsurancePolicy>policies, int flatRate){
    double totalIP = 0;
    Set<Integer> keys = policies.keySet();
    int iteration = 0;

    //Bassicly just copy and paste the main code right in here
    for (Integer key : keys){
      totalIP = (totalIP + policies.get(key).calcPayment(flatRate));      
    }

    
    //Make sure to have last, outisde of an {}
    return totalIP;

    
  }


  //Note to self, don't put static where it isnt needed 
  public void carPriceRise(double risePercent){
    car.priceRise(risePercent);
  }
  
  //carPriceRiseAll
//  public static void carPriceRiseAll(ArrayList<InsurancePolicy>policies, double risePercent){
//    for (InsurancePolicy policy : policies){
//      policy.carPriceRise(risePercent);
//    }
//  }

    public static void carPriceRiseAll(ArrayList<InsurancePolicy>policies, double risePercent){
        policies.forEach(policy -> policy.carPriceRise(risePercent));
    }

  //Hash
  public static void carPriceRiseAll(HashMap<Integer, InsurancePolicy>policies, double risePercent){
    Set<Integer> keys = policies.keySet();
    for (Integer key: keys){
      policies.get(key).carPriceRise(risePercent); 
    }
  }

  //filtedByCarModel
  /*
  static ArrayList<InsurancePolicy> filterByCarModel (ArrayList<InsurancePolicy>policies, String carModel){

    //In Car class because it find model that way, and per example
    return Car.filterByCarModel(policies, carModel);

  }
  */


    //Need to fix the Collections import biz

    static ArrayList<InsurancePolicy> filterByCarModel (ArrayList<InsurancePolicy>policies, String carModel){
        return (ArrayList<InsurancePolicy>)(policies.stream()
                .filter(x->x.getCar().getModel().contains(carModel))
                .collect(Collectors.toList()));
    }



  static HashMap<Integer, InsurancePolicy> filterByCarModel (HashMap<Integer, InsurancePolicy>policies, String carModel){

    //In Car class because it find model that way, and per example
    return Car.filterByCarModel(policies, carModel);

  }

  //Add policy to user 
  public static void addPolicy(User user, InsurancePolicy policy, String uName, String uPassword){
    if(user.addPolicy(policy, uName, uPassword)){
      System.out.println("Added policy to user");
      System.out.println();
    }else {
      System.out.println("!! Failed to add policy to user !!");
      System.out.println("");
      System.out.println("UserName and password: " + uName + ", " + uPassword);
      System.out.println();
    }
  }

  public static void removePolicy(User user, InsurancePolicy policy, String uName, String uPassword){
    if(user.removePolicy(policy, uName, uPassword)){
      System.out.println("Removed policy from user");
      System.out.println();
    }else {
      System.out.println("!! Failed to remove policy from user !!");
      System.out.println();
    }
  }

    public static void removeUser(User user, InsurancePolicy policy, String uName, String uPassword){
    if(user.removePolicy(policy, uName, uPassword)){
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

//  static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy>policies, MyDate date){
//    ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();
//    for (InsurancePolicy policy: policies){
//      if(policy.getExpiryDate().isExpired(date)){
//        expiredPolicies.add(policy);
//      }
//    }
//    return expiredPolicies;
//  }


    static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy>policies, MyDate date){
      ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();
        policies.forEach(policy -> {
            if (policy.getExpiryDate().isExpired(date)) {
                expiredPolicies.add(policy);
            }
        });

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


//  static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException{
//    ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
//
//    for (InsurancePolicy policy: policies){
//      shallowCopy.add(policy);
//    }
//    return shallowCopy;
//  }

    static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException{
        ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();

        policies.forEach(policy -> shallowCopy.add(policy));

        return shallowCopy;
    }

  //Hash 
  ArrayList< InsurancePolicy> shallowCopy(HashMap<Integer, InsurancePolicy> policies){
    ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
    Set<Integer> keys = policies.keySet();

    for (Integer key: keys){
      shallowCopy.add(policies.get(key));
    }

    return shallowCopy;
  }

  //Works
//  static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException{
//    ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
//    deepCopy.clear();
//
//    for (InsurancePolicy policy: policies){
//      deepCopy.add((InsurancePolicy) policy.clone());
//    }
//    return deepCopy;
//  }

    static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException{
        ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
        deepCopy.clear();

        policies.forEach(policy -> {
            try {
                deepCopy.add((InsurancePolicy) policy.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        });

        return deepCopy;
    }

  //Hash
  ArrayList< InsurancePolicy> deepCopy (HashMap< Integer, InsurancePolicy> policies) throws CloneNotSupportedException{
    ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
    Set<Integer> keys = policies.keySet();

    for (Integer key: keys){
      deepCopy.add((InsurancePolicy) policies.get(key).clone());
    }
    return deepCopy;
  }

  HashMap< Integer, InsurancePolicy> shallowCopyHashMap (HashMap< Integer, InsurancePolicy> policies){
    HashMap<Integer, InsurancePolicy> shallowCopy = new HashMap<>();
    shallowCopy.putAll(policies);
    return shallowCopy;
  }

  HashMap <Integer, InsurancePolicy> deepCopyHashMap (HashMap< Integer, InsurancePolicy>policies) throws CloneNotSupportedException{
    HashMap<Integer, InsurancePolicy> deepCopy = new HashMap<>();
    Set<Integer> keys = policies.keySet();

    for (Integer key: keys){
      deepCopy.put(key, (InsurancePolicy) policies.get(key).clone());
    }
    return deepCopy;
  }

  //Here for testing, works 
  public void chnageUserID(int id){
    this.id = id;
  }


  //This is important to have in every class
  @Override
  public InsurancePolicy clone() throws CloneNotSupportedException{
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

  //Works
  static Boolean save (HashMap<Integer, InsurancePolicy> policies, String fileName) throws IOException{
   ObjectOutputStream output = null;
   Set<Integer> keys = policies.keySet();
   
   try {
    output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
   } catch (IOException ex) {
    System.out.println("Error in creating the file");
   }

   try {
    for (Integer key: keys){
      output.writeObject(policies.get(key));
    }
   } catch (IOException e) {
    System.out.println("Error in adding");
    System.out.println("Error is " + e);
   }

   try {
    if (output != null){
      output.close();
      return true;
    }
   } catch (IOException e) {
    System.out.println("Error in closing file");
    return false;
   }
   return false;
  }

  //Works
  static HashMap<Integer, InsurancePolicy> load (String fileName) throws IOException{
    ObjectInputStream input = null;
    HashMap<Integer, InsurancePolicy> policies = new HashMap<>();

    try {
      input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
    } catch (IOException e) {
      System.out.println("Error in open: " + e);
    }

    try {
      while(true){
        InsurancePolicy loadedPolicy = (InsurancePolicy) input.readObject();
        policies.put(loadedPolicy.getId(), loadedPolicy);
      }
    } catch (EOFException eof){
      System.out.println("Reached end of save file");
    } catch (IOException e) {
      System.out.println("Error in adding policy to HashMap: " + e);
    } catch (ClassNotFoundException ce){
      System.out.println("Class not found error: " + ce);
    } 

    try {
      if (input != null){
        input.close();
        System.out.println("DOne");
        return policies;
      }
    } catch (IOException e) {
      System.out.println("Error in close of file: " + e);
    }
    return policies;
  }

  //Works
  public String toDelimitedString(){
    return policyHolderName + "," + id + car.toDelimitedString() + "," + numberOfClaims + expiryDate.toDelimitedString();
  }

  //Works
  static Boolean saveTextFile (HashMap<Integer, InsurancePolicy> policies, String fileName) throws IOException{
    BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
    Set<Integer> keys = policies.keySet();
    //This is needed to go through each policy
    for (Integer key: keys){
      //This just seems to run in Comp/TPP
      output.write(policies.get(key).toDelimitedString() + "\n");
    }

    output.close();
    return true;
  }

  //Works
  static HashMap<Integer, InsurancePolicy> loadTextFile (String fileName) throws IOException, PolicyException, NameException{
    HashMap<Integer, InsurancePolicy> loadedPolicies = new HashMap<>();

    BufferedReader input = new BufferedReader(new FileReader(fileName));
    String line = input.readLine();

    
    while (line != null){
      line = line.trim();
      String[] field = line.split(",");

      String fieldZ = field[0];

      if (fieldZ.equals("CP")){
        String policyHolderName = field[1];
        int policyID = Integer.parseInt(field[2]);
        String model = field[3];
        int modelYear = Integer.parseInt(field[4]);
        double price = Double.parseDouble(field[5]);
        String type = field[6];
        int claims = Integer.parseInt(field[7]);
        int year = Integer.parseInt(field[8]);
        int month = Integer.parseInt(field[9]);
        int day = Integer.parseInt(field[10]);
        int level = Integer.parseInt(field[11]);
        InsurancePolicy policy = new ComprehensivePolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, 19, level, new MyDate(year, month, day));
        loadedPolicies.put(policyID, policy);
      } else if (fieldZ.equals("TPP")){
        String policyHolderName = field[1];
        int policyID = Integer.parseInt(field[2]);
        String model = field[3];
        int modelYear = Integer.parseInt(field[4]);
        Double price = Double.parseDouble(field[5]);
        String type = field[6];
        int claims = Integer.parseInt(field[7]);
        int year = Integer.parseInt(field[8]);
        int month = Integer.parseInt(field[9]);
        int day = Integer.parseInt(field[10]);
        String comments = field[11];
        InsurancePolicy policy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day));
        loadedPolicies.put(policyID, policy);
      }


			line = input.readLine();
    }

    
    return loadedPolicies;
  }

    public abstract String getComments();
}
