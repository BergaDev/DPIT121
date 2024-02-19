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
import java.util.Comparator;

public class User implements Cloneable, Comparable<Address>, Serializable{
  private static final long serialVersionUID = 6529685098267757691L;
  
  private String name;
  private int userID;
  private Address address;
  //private ArrayList<InsurancePolicy>policies;
  private HashMap<Integer, InsurancePolicy> policies;
  String userName;
  String userPassword;

  public User (String name, int userID, Address address, String userName, String userPassword){
    this.name = name;
    this.userID = userID;
    this.address = address;
    //Maybe ArrayList thing?
    //this.policies = new ArrayList<>();
    this.policies = new HashMap<>();
    this.userName = userName;
    this.userPassword = userPassword;
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
  public String getUserName(){
    return userName;
  }
  public void setUserName(String userName){
    this.userName = userName;
  }
  public String getUserPassword(){
    return userPassword;
  }

  public void setUserPassword(String uPassword){
    this.userPassword = uPassword;
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

  public HashMap<Integer, InsurancePolicy> getHashPolices(String uName, String uPassword){
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
      return policies;
    } else {
      return null;
    }
  }

  public String allPoliciesToString(){
    Set<Integer> policyKeys = policies.keySet();
    String output = "";
    for (Integer policy: policyKeys){
      output = output + policies.get(policy).toString() + "\n";
    }
    return output;
  }

  public InsurancePolicy getPolicy(int policyID, String uName, String uPassword){
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
      return policies.get(policyID);
    } else {
      return null;
    }
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


  boolean addPolicy(InsurancePolicy policy, String uName, String uPassword){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
      policies.put(policy.getId(), policy);
      return true;
    } else {
      return false;
    }
  }

    boolean removePolicy(InsurancePolicy policy, String uName, String uPassword){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
      policies.remove(policy.getId());
      return true;
    } else {
      return false;
    }
  }

      boolean removeUser(InsurancePolicy policy, String uName, String uPassword){
    //Unlike the book example I am not checking for an exisitng policy, I should add that though
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
      policies.remove(policy.getId());
    return true;
    } else {
      return false;
    }
  }

  InsurancePolicy findPolicy (int policyID, String uName, String uPassword){
    if (uName.equals(userName) && userPassword.equals(userPassword) || uName.equals("Admin")){
      return policies.get(policyID);
    } else{
      return null;
    }
    
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
    return "Name: " + name + ", ID of user: " + userID;
  }


  //Hash - Working
//  void printPolicies(int flatRate, String uName, String uPassword){
//    //See it passes both policies and flatRate to use the correct method
//    HashMap<Integer, InsurancePolicy> policies = getHashPolices(uName, uPassword);
//    InsurancePolicy.printPolicies(policies,flatRate);
//  }

  void printPolicies(int flatRate, String uName, String uPassword){
    //See it passes both policies and flatRate to use the correct method
    HashMap<Integer, InsurancePolicy> policies = getHashPolices(uName, uPassword);
    //When using hash the for each has to have (ID, Policy)
    policies.forEach((policyID, policy) -> InsurancePolicy.printPolicies(policy, flatRate));
  }


  //Hash works tho
  //Might have a calc issue - Seems that it exists in lab 4 as well, likely an old issue i didnt notice 
  double calcTotalPremiums (int flatRate, String uName, String uPassword){
    HashMap<Integer, InsurancePolicy> usersPolicies = getHashPolices(uName, uPassword);
   return InsurancePolicy.calcTotalPayments(usersPolicies, flatRate);
  }


  //Hash - WOrks 
  void carPriceRiseAll(double risePercent, String uName, String uPassword){
    HashMap<Integer, InsurancePolicy> usersPolicies = getHashPolices(uName, uPassword);
    InsurancePolicy.carPriceRiseAll(usersPolicies, risePercent);
  }

  //Hash - works 
  HashMap<Integer, InsurancePolicy>filterByCarModel (String carModel, String uName, String uPassword){
    HashMap<Integer, InsurancePolicy> usersPolicies = getHashPolices(uName, uPassword);
    return InsurancePolicy.filterByCarModel(usersPolicies, carModel);
  }


//Todo, add expiry date to the constructors 
  Boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments, String uName, String uPassword) throws PolicyException, NameException{
    System.out.println("Is this before death?");
    try{
      return addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate), uName, uPassword);
    } catch (PolicyException e){
      return addPolicy(new ThirdPartyPolicy(policyHolderName, e.genID(), car, numberOfClaims, comments, expiryDate), uName, uPassword);
    }
    
  }

  Boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level, String uName, String uPassword) throws PolicyException, NameException{
    System.out.println("Is this before death?");
    try {
      return addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate), uName, uPassword);
    } catch (PolicyException e){
      return addPolicy(new ComprehensivePolicy(policyHolderName, e.genID(), car, numberOfClaims, driverAge, level, expiryDate), uName, uPassword);
    }
    
  }

  
  //Not working with hash
  ArrayList<InsurancePolicy> filterByExpiryDate (MyDate date, String uName, String uPassword){
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
          ArrayList<InsurancePolicy> usersPolicies = getPolicies();
    return InsurancePolicy.filterByExpiryDate(usersPolicies, date);
    } else {
      return null;
    }

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
  double getTotalPaymentForCarModel(String carModel, String uName, String uPassword){
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
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
    } else {
      return 0.0;
    }
  }
//Working - HashMap
  ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels, String uName, String uPassword){
    if (uName.equals(userName) && uPassword.equals(userPassword) || uName.equals("Admin")){
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
  } else {
    return null;
  }
  }

void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments){
    String format = "%1$-20s%2$-30s%3$-20s\n";
    System.out.format(format, "Car Model", "Total Premium Payment", "Average Premium Payment");

    for (int i = 0; i < carModels.size(); i++){
      double calcAvg = (premiumPayments.get(i) / counts.get(i));

      System.out.format(format, carModels.get(i), premiumPayments.get(i), calcAvg);
    }

}


//ArrayList<User> shallowCopy(ArrayList<User> users) throws CloneNotSupportedException{
//  ArrayList<User> shallowCopy = new ArrayList<>();
//  shallowCopy.add((User) users.clone());
//  return shallowCopy;
//}

  ArrayList<User> shallowCopy(ArrayList<User> users) throws CloneNotSupportedException{
    ArrayList<User> shallowCopy = new ArrayList<>();
    users.forEach(user -> {
        try {
            shallowCopy.add(user.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    });
    return shallowCopy;
  }


//Works, I pass a users list from insuranceCompany but I don't know if that's the intedended method
// ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException{
// ArrayList<User> deepCopy = new ArrayList<>();
//
// for (User user: users){
//  deepCopy.add((User) user.clone());
// }
//
// return deepCopy;
//}

  ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException{
    ArrayList<User> deepCopy = new ArrayList<>();

    users.forEach(user -> {
        try {
            deepCopy.add((User) user.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    });

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

static HashMap<Integer, User> shallowCopyHashMap(HashMap<Integer, User> users){
  HashMap<Integer, User> shallowCopy = new HashMap<>();
  Set<Integer> userKeys = users.keySet();
  for (Integer user: userKeys){
    shallowCopy.put(user, users.get(user));
  }
  return shallowCopy;
}

static HashMap<Integer, User> deepCopyHashMap(HashMap<Integer, User> users) throws CloneNotSupportedException{
  HashMap<Integer, User> deepCopy = new HashMap<>();
  Set<Integer> userKeys = users.keySet();
  for (Integer user: userKeys){
    deepCopy.put(user, users.get(user).clone());
  }
  return deepCopy;
}

HashMap<Integer, InsurancePolicy> deepCopyPoliciesHashMap() throws CloneNotSupportedException{
  HashMap<Integer, InsurancePolicy> deepCopy = new HashMap<>();
  Set<Integer> policyKeys = policies.keySet();
  for (Integer policy: policyKeys){
    deepCopy.put(policy, policies.get(policy).clone());
  }
  return deepCopy;
}

HashMap<Integer, InsurancePolicy> shallowCopyPoliciesHashMap() throws CloneNotSupportedException{
  HashMap<Integer, InsurancePolicy> shallowCopy = new HashMap<>();
  Set<Integer> policyKeys = policies.keySet();
  for (Integer policy: policyKeys){
    shallowCopy.put(policy, policies.get(policy));
  }
  return shallowCopy;
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

public HashMap<Integer, InsurancePolicy> sortPoliciesAlph(){
  Set<Integer> userKeys = policies.keySet();
  ArrayList<InsurancePolicy> policiesArray = getPolicies();
  HashMap<Integer, InsurancePolicy> output = new HashMap<>();

  // The Compartor
  Comparator<InsurancePolicy> policyComparator = Comparator
          .comparing(InsurancePolicy::getPolicyHolderName, String.CASE_INSENSITIVE_ORDER);


  Collections.sort(policiesArray, policyComparator);
  for (InsurancePolicy policy : policiesArray) {
    output.put(policy.getId(), policy);
  }

  return output;
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
HashMap<String, Double> getTotalPremiumPerCarModel(String uName, String uPassword){
  HashMap<String, Double> premiumPer = new HashMap<>();
  ArrayList<String> carModels = distinctCarModels();
  ArrayList<Double> carPayments =  getTotalPaymentPerCarModel(distinctCarModels(), uName, uPassword);

  for (int i = 0; i < distinctCarModels().size(); i++){
    premiumPer.put(carModels.get(i), carPayments.get(i));
  }
  return premiumPer;
}

public void reportPaymentPerModel(String uName, String uPassword){
  String format = "%1$-20s%2$-30s%3$-20s\n";
  System.out.format(format, "Car Model", "Total Premium Payment", "Average Premium Payment");

  HashMap<String, Double> carPayments = getTotalPremiumPerCarModel(uName, uPassword);
  HashMap<String, Integer> countModels = getTotalCountPerCarModel();
  Set<String> keys = countModels.keySet();

  for (String model: keys){
    double calcAvg = carPayments.get(model) / countModels.get(model);
    System.out.format(format, model, carPayments.get(model), calcAvg);
  }
}

  public String reportPaymentPerModelToString(String uName, String uPassword){
    String format = "%1$-20s%2$-30s%3$-20s\n";
    String body = "";
    body += (" Car Model " + " Total Premium Payment  " + " Average Premium Payment " + "\n");

    HashMap<String, Double> carPayments = getTotalPremiumPerCarModel(uName, uPassword);
    HashMap<String, Integer> countModels = getTotalCountPerCarModel();
    Set<String> keys = countModels.keySet();

    for (String model: keys){
      double calcAvg = carPayments.get(model) / countModels.get(model);
      body += (model + ", " + carPayments.get(model) + ", " + calcAvg + "\n");
    }
    return body;
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
      System.out.println("Error in open (UserClass): " + e);
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
    return name + "," + userID + "," + address.toDelimitedString() + "," + userName + "," + userPassword;
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
   static HashMap<Integer, User> loadTextFile(String fileName) throws IOException, PolicyException, NameException{
    //System.out.println("Do we even hit here?");
    BufferedReader input = new BufferedReader(new FileReader(fileName));
    String line = input.readLine();
    Boolean inPolicies = false;
    User newUser = new User();
    HashMap<Integer, User> loadedUsers = new HashMap<>();
    String userPassword = "";
    String userName = "";

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
        userName = field[7];
        userPassword = field[8];
        int numOfPolicies = Integer.parseInt(field[9]);
        //System.out.println("Amount of policies that user: " + name + " holds is: " + numOfPolicies);
        newUser = new User(name, userID, new Address(streetNum, street, suburb, city), userName, userPassword);
      
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
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
        } else if (type.equals("SUV")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
        } else if (type.equals("SED")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SED, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
        } else if (type.equals("LUX")){
          newUser.addPolicy(new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
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
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, model, modelYear, price), claims, comments, new MyDate(year, month, day)), userName, userPassword);
        } else if (type.equals("SUV")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, model, modelYear, price), claims, comments, new MyDate(year, month, day)), userName, userPassword);
        } else if (type.equals("SED")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SED, model, modelYear, price), claims, comments, new MyDate(year, month, day)), userName, userPassword);
        } else if (type.equals("LUX")){
          newUser.addPolicy(new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, model, modelYear, price), claims, comments, new MyDate(year, month, day)), userName, userPassword);
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

  int [] policyCount (String uName, String uPassword, int [] ranges){
    if (uName.equals(getUserName()) && uPassword.equals(getUserPassword())){
    Set<Integer> policyKeys = policies.keySet();
    
    int [] results = new int[ranges.length];
    for (int i = 0; i < ranges.length; i++){
      int count = 0;
      int lower = ranges[i];
      int higher = 0;
          if ((i) == ranges.length-1){
            higher = ranges[i-1];
          } else {
            higher = ranges[i+1];
          }
      for (Integer policy: policyKeys){
        double calcPayment = policies.get(policy).calcPayment(InsuranceCompany.flatRateStatic);
        if (calcPayment >= lower && calcPayment < higher){
          count++;
        }
      }
      results[i] = count;
    }
    return results;
  } else {
    return null;
  }
  }

  HashMap<String, Integer []> policyCarModelCount (String username, String password, Integer [] ranges){
    if (username.equals(getUserName()) && password.equals(getUserPassword())){
    HashMap<String, Integer []> outputHash = new HashMap<>();
    Set<Integer> policyKeys = policies.keySet();
    ArrayList<String> carModels = distinctCarModels();

    for (String model: carModels){
      Integer [] results = new Integer [ranges.length];
        for (int i = 0; i < ranges.length; i++){
          int count = 0;
          int lower = ranges[i];
          int higher = 0;
          if ((i) == ranges.length-1){
            higher = ranges[i-1];
          } else {
            higher = ranges[i+1];
          }
          for (Integer policy: policyKeys){
            if (model.equals(policies.get(policy).getCar().getModel())){
              double calcPayment = policies.get(policy).calcPayment(InsuranceCompany.flatRateStatic);
                if (calcPayment >= lower && calcPayment < higher){
                  count++;
                }
            }
            
          }
          results[i] = count;
        }
        outputHash.put(model, results);
    }
    return outputHash;
  } else {
    return null;
  }
  }

  


}
  

