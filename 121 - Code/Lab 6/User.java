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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class User implements Cloneable, Comparable<Address>, Serializable{
  private static final long serialVersionUID = 6529685098267757691L;
  
  private String name;
  private int userID;
  private Address address;
  //private ArrayList<InsurancePolicy>policies;
  private HashMap<Integer, InsurancePolicy> policies;

  public User (String name, int userID, Address address){
    this.name = name;
    this.userID = userID;
    this.address = address;
    //Maybe ArrayList thing?
    //this.policies = new ArrayList<>();
    this.policies = new HashMap<>();
  }

  User (User userCopy){
    this.name = userCopy.name;
    this.userID = userCopy.userID;
    this.address = userCopy.address;
    //Hows this work?
    this.policies = new HashMap<>();
  }

  public User() {
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
  public Integer getUserID(){
    return userID;
  }
  public Address getAddress(){
    return address;
  }



  /*
  public ArrayList<InsurancePolicy> getPolicies(){
    return policies;
  }
  public InsurancePolicy getPolicy(int i){
    return policies.get(i);
  }
  */

  public ArrayList<InsurancePolicy> getPolicies(){
    Set<Integer> keys = policies.keySet();
    TreeSet<Integer> sortedKeys = new TreeSet<>(keys);
    ArrayList<InsurancePolicy> policyArray = new ArrayList<>();

    for (Integer key: sortedKeys){
      //System.out.println(policies.get(key));
      policyArray.add(policies.get(key));
    }

    return policyArray;
  }

  public Set<Integer> getKeys(){
    Set<Integer> keys = policies.keySet();
    return keys;
  }

  public HashMap<Integer, InsurancePolicy> getHashPolices(){
    return policies;
  }

  public InsurancePolicy getPolicy(int policyID){
    return policies.get(policyID);
  }

  //Added for comparison
  public String getCity(){
    return address.getCity();
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


  /*
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
  */

  boolean addPolicy(InsurancePolicy policy){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    policies.put(policy.getId(), policy);
    return true;
  }

    boolean removePolicy(InsurancePolicy policy){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    policies.remove(policy.getId());
    return true;
  }

      boolean removeUser(InsurancePolicy policy){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    policies.remove(policy.getId());
    return true;
  }

  InsurancePolicy findPolicy (int policyID){
    return policies.get(policyID);
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

  /*
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

*/

/*
  void printPolicies(int flatRate){
    //See it passes both policies and flatRate to use the correct method
    ArrayList<InsurancePolicy> policies = getPolicies();
    InsurancePolicy.printPolicies(policies,flatRate);
  }
  */

  //Hash - Working
  void printPolicies(int flatRate){
    //See it passes both policies and flatRate to use the correct method
    HashMap<Integer, InsurancePolicy> policies = getHashPolices();
    InsurancePolicy.printPolicies(policies,flatRate);
  }


  //Hash works tho
  //Might have a calc issue - Seems that it exists in lab 4 as well, likely an old issue i didnt notice 
  double calcTotalPremiums (int flatRate){
    HashMap<Integer, InsurancePolicy> usersPolicies = getHashPolices();
   return InsurancePolicy.calcTotalPayments(usersPolicies, flatRate);
  }

  /* 
  double calcTotalPremiums (int flatRate){
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();
   return InsurancePolicy.calcTotalPayments(usersPolicies, flatRate);
  }
  */

  /*
  void carPriceRiseAll(double risePercent){
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();
    InsurancePolicy.carPriceRiseAll(usersPolicies, risePercent);
  }
  */

  //Hash - WOrks 
  void carPriceRiseAll(double risePercent){
    HashMap<Integer, InsurancePolicy> usersPolicies = getHashPolices();
    InsurancePolicy.carPriceRiseAll(usersPolicies, risePercent);
  }

  /*
  ArrayList<InsurancePolicy>filterByCarModel (String carModel){
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();
    return InsurancePolicy.filterByCarModel(usersPolicies, carModel);
  }
  */

  //Hash - works 
  HashMap<Integer, InsurancePolicy>filterByCarModel (String carModel){
    HashMap<Integer, InsurancePolicy> usersPolicies = getHashPolices();
    return InsurancePolicy.filterByCarModel(usersPolicies, carModel);
  }


  //Whoops

//Todo, add expiry date to the constructors 
  Boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException{
    System.out.println("Is this before death?");
    try{
      return addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate));
    } catch (PolicyException e){
      return addPolicy(new ThirdPartyPolicy(policyHolderName, e.genID(), car, numberOfClaims, comments, expiryDate));
    }
    
  }

  Boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException{
    System.out.println("Is this before death?");
    try {
      return addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate));
    } catch (PolicyException e){
      return addPolicy(new ComprehensivePolicy(policyHolderName, e.genID(), car, numberOfClaims, driverAge, level, expiryDate));
    }
    
  }

  
  //Not working with hash
  ArrayList<InsurancePolicy> filterByExpiryDate (MyDate date){
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();
    return InsurancePolicy.filterByExpiryDate(usersPolicies, date);
  }

  //Working hash
  ArrayList<String> allCars(){
    ArrayList<String> allCars = new ArrayList<>();
    ArrayList<InsurancePolicy> userPolicies = getPolicies();
      //userPolicies.add(getPolicies());


      for (int i = 0; i < userPolicies.size(); i++){
        allCars.add(userPolicies.get(i).getCar().getModel());
      }


      return allCars;
  }

  //Wokring - HashWork
  ArrayList<String> distinctCarModels(){
    ArrayList<String> allCars = new ArrayList<>();
    
      ArrayList<InsurancePolicy> userPolicies = getPolicies();
      //userPolicies.add(policies);

      for (int i = 0; i < userPolicies.size(); i++){
        allCars.add(userPolicies.get(i).getCar().getModel());
      }
    
    //Behold, my mess
    //HashSet takes an array and makes it unique
    Set<String> uniqueModels = new HashSet<>(allCars);
    //Below just makes it match the required return type 
    ArrayList<String> uniqueCars = new ArrayList<>(uniqueModels);
    return uniqueCars;
  }

  //Working - HashMap
  ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels){
    ArrayList<Integer>  modelCount = new ArrayList<>();
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();

   //usersPolicies.addAll(policies);

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

//Working - HashMap
  double getTotalPaymentForCarModel(String carModel){
    double carTotal = 0;
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();
    Set<Integer> keys = getKeys();
      //usersPolicies.addAll(policies);

      for (Integer key: keys){
        
        if (policies.get(key).getCar().getModel() == carModel){
          carTotal = (carTotal + policies.get(key).calcPayment(InsuranceCompany.flatRateStatic));
        }
      }
      return carTotal;
  }
//Working - HashMap
  ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels){
    ArrayList<Double>  modelPayment = new ArrayList<>();
    ArrayList<InsurancePolicy> usersPolicies = getPolicies();
    Set<Integer> keys = getKeys();

    //usersPolicies.addAll(getPolicies());

    for (String model: carModels){
      Double carPayment = 0.0;
      for (Integer key: keys){
       if (policies.get(key).getCar().getModel().equals(model)){
        carPayment = carPayment + policies.get(key).calcPayment(InsuranceCompany.flatRateStatic);
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

//Does not work - or im just a nugget
ArrayList<User> shallowCopy(ArrayList<User> users) throws CloneNotSupportedException{
  ArrayList<User> shallowCopy = new ArrayList<>();
  shallowCopy.add((User) users.clone());
  return shallowCopy;
}
//Works, I pass a users list from insuranceCompany but I don't know if that's the intedended method
 ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException{
 ArrayList<User> deepCopy = new ArrayList<>();

 for (User user: users){
  deepCopy.add((User) user.clone());
 }

 return deepCopy;
}

//Works
ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException{
   ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
   Collection<InsurancePolicy> usersPolicies = policies.values();
   
  //deepCopy.clear();
  
  for (InsurancePolicy policy: usersPolicies){
    deepCopy.add((InsurancePolicy)policy.clone());
  }
  return deepCopy;
}

//
ArrayList<InsurancePolicy> shallowCopyPolicies() throws CloneNotSupportedException{
return new ArrayList<>(policies.values());
}

//Need to make work with hashmap
@Override
public User clone() throws CloneNotSupportedException{
  ArrayList<InsurancePolicy> usersPolicies = getPolicies();

  User output = (User) super.clone();
  output.address = address.clone();
  output.policies = new HashMap<Integer, InsurancePolicy>();
  //output.policies = policies.clone();
  for (InsurancePolicy policy: usersPolicies){
    output.policies.put(policy.getId(), (InsurancePolicy) policy.clone());
    //output.policies
  }

    return output;
}

//Works 
@Override
public int compareTo(Address Address) {
  return address.compareTo(Address);
}

//Working
public ArrayList<InsurancePolicy> compareTo1(){
  ArrayList<InsurancePolicy> usersPolicies = getPolicies();
  ArrayList<Double> paymentList = new ArrayList<>();
  ArrayList<InsurancePolicy> sortedArray = new ArrayList<>();
  int flatRate = 500;
  for (InsurancePolicy policy: usersPolicies){
    paymentList.add(policy.calcPayment(flatRate));
  }

  Collections.sort(paymentList);

  for (int i = 0; i < paymentList.size(); i++){
    for (InsurancePolicy aPolicy: usersPolicies){
      double policyPayment = aPolicy.calcPayment(flatRate);
      if (policyPayment == paymentList.get(i)){
        sortedArray.add(aPolicy);
      }
    }
  }
  

  return sortedArray;
}

//Working
ArrayList<InsurancePolicy> sortPoliciesByDate(){
  ArrayList<InsurancePolicy> usersPolicies = getPolicies();
  ArrayList<InsurancePolicy> policyArray = new ArrayList<>();
  Set<Integer> keys = getKeys();
  //ArrayList<MyDate> dates = new ArrayList<>();

  for (Integer key: keys){
    policyArray.add(policies.get(key));
    //dates.add(policy.calcDate());
  }

  //Have to add this part for the Collections to work
  Comparator<InsurancePolicy> policyComparator = Comparator.comparing((InsurancePolicy policy) -> policy.calcDate());

  Collections.sort(policyArray, policyComparator);
  return policyArray;
}

/* 
ArrayList<User> sortUsers(){
  return insuranceCompany.sortUsers();
}
*/

//Hash
HashMap<String, Integer> getTotalCountPerCarModel(){
  HashMap<String, Integer> countPerCar = new HashMap<>();
  ArrayList<String> carModels = distinctCarModels();
  ArrayList<Integer> carCount = getTotalCountPerCarModel(carModels);

  for (int i = 0; i < distinctCarModels().size(); i++){
    countPerCar.put(carModels.get(i), carCount.get(i));
  }
  return countPerCar;
}
//Hash
HashMap<String, Double> getTotalPremiumPerCarModel(){
  HashMap<String, Double> premiumPer = new HashMap<>();
  ArrayList<String> carModels = distinctCarModels();
  ArrayList<Double> carPayments =  getTotalPaymentPerCarModel(distinctCarModels());

  for (int i = 0; i < distinctCarModels().size(); i++){
    premiumPer.put(carModels.get(i), carPayments.get(i));
  }
  return premiumPer;
}

public void reportPaymentPerModel(){
  String format = "%1$-20s%2$-30s%3$-20s\n";
  System.out.format(format, "Car Model", "Total Premium Payment", "Average Premium Payment");

  HashMap<String, Double> carPayments = getTotalPremiumPerCarModel();
  HashMap<String, Integer> countModels = getTotalCountPerCarModel();
  Set<String> keys = countModels.keySet();

  for (String model: keys){
    double calcAvg = carPayments.get(model) / countModels.get(model);
    System.out.format(format, model, carPayments.get(model), calcAvg);
  }
}

static Boolean save (HashMap<Integer, User> users, String fileName) throws IOException{
   ObjectOutputStream output = null;
   Set<Integer> keys = users.keySet();
   
   try {
    output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
   } catch (IOException ex) {
    System.out.println("Error in creating the file");
   }

   try {
    for (Integer key: keys){
      output.writeObject(users.get(key));
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

  static HashMap<Integer, User> load (String fileName) throws IOException{
    ObjectInputStream input = null;
    HashMap<Integer, User> users = new HashMap<>();
    

    try {
      input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
    } catch (IOException e) {
      System.out.println("Error in open: " + e);
    }

    try {
      while(true){
        User loadedUser = (User) input.readObject();
        users.put(loadedUser.getUserID(), loadedUser);
      }
    } catch (EOFException eof){
      System.out.println("Reached end of save file");
    } catch (IOException e) {
      System.out.println("Error in adding user to HashMap: " + e);
    } catch (ClassNotFoundException ce){
      System.out.println("Class not found error: " + ce);
    } 

    try {
      if (input != null){
        input.close();
        return users;
      }
    } catch (IOException e) {
      System.out.println("Error in close of file: " + e);
    }
    return users;
  }

  String toDelimitedString(){
    return name + "," + userID + "," + address.toDelimitedString();
  }

  //Working, maybe tweak how policies are saved
   static Boolean saveTextFile (HashMap<Integer, User> users, String fileName) throws IOException{
    BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
    Set<Integer> userKeys = users.keySet();

    for (Integer key: userKeys){
      HashMap<Integer, InsurancePolicy> userPolicies = users.get(key).policies;
      output.write("User," + users.get(key).toDelimitedString());
      int size = userPolicies.size();
      output.write("," + size);
      Set<Integer> keys = userPolicies.keySet();
      for (Integer policy: keys){
        output.write("\n");
        output.write(userPolicies.get(policy).toDelimitedString());
      }
  }

    output.close();
    return true;
  }

  //Works in test case, not in load menu
   static HashMap<Integer, User> loadTextFile(String fileName) throws IOException, PolicyException{
    //System.out.println("Do we even hit here?");
    BufferedReader input = new BufferedReader(new FileReader(fileName));
    String line = input.readLine();
    Boolean inPolicies = false;
    User newUser = new User();
    HashMap<Integer, User> loadedUsers = new HashMap<>();

    while (line != null){
      line = line.trim();
      String[] field = line.split(",");

      String fieldZ = field[0];

      //System.out.println("We made it here?");

      if (fieldZ.equals("User")){
        String name = field[1];
        int userID = Integer.parseInt(field[2]);
        int streetNum = Integer.parseInt(field[3]);
        String street = field[4];
        String suburb = field[5];
        String city = field[6];
        int numOfPolicies = Integer.parseInt(field[7]);
        //System.out.println("Amount of policies that user: " + name + " holds is: " + numOfPolicies);
        newUser = new User(name, userID, new Address(streetNum, street, suburb, city));
      
      } else if (fieldZ.equals("CP")){
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
        int lvel = Integer.parseInt(field[11]);
        //System.out.println("Field 6 is: " + field[6]);

        if (type.equals("HATCH")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)));
        } else if (type.equals("SUV")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)));
        } else if (type.equals("SED")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SED, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)));
        } else if (type.equals("LUX")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)));
        }
        
        //InsurancePolicy policy = new ComprehensivePolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day));
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
        
        //newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
        //InsurancePolicy policy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day));
        //addPolicy(policy);

        if (type.equals("HATCH")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
        } else if (type.equals("SUV")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
        } else if (type.equals("SED")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SED, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
        } else if (type.equals("LUX")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
        }
      }

      loadedUsers.put(newUser.getUserID(), newUser);
			line = input.readLine();
    }


    input.close();
    //System.out.println("Contents of loadedUsers: " + loadedUsers);
    //loadedUsers.put(34543, new User("Text", 1, null));
    return loadedUsers;
  }


}
  

