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

public class InsuranceCompany implements Cloneable, Serializable{

  private static final long serialVersionUID = 6529685098267757690L;
  
  String name;
  //private ArrayList<User>users;
  private HashMap<Integer, User> users;
  private String adminUsername;
  private String adminPassword;
  public int flatRate;
  public static int flatRateStatic;


  public InsuranceCompany(String name, User users, String adminUsername, String adminPassword, int flatRate){
    this.name = name;
    this.adminUsername = adminUsername;
    this.adminPassword = adminPassword;
    this.flatRate = flatRate;
    this.users = new HashMap<>();
    this.flatRateStatic = flatRate;
  }

  public InsuranceCompany(String name, HashMap<Integer, User> users, String adminUsername, String adminPassword, int flatRate){
    this.name = name;
    this.adminUsername = adminUsername;
    this.adminPassword = adminPassword;
    this.flatRate = flatRate;
    this.users = new HashMap<>();
    this.flatRateStatic = flatRate;
  }

  public InsuranceCompany(InsuranceCompany icCopy){
    this.name = icCopy.name;
    this.adminUsername = icCopy.adminUsername;
    this.adminPassword = icCopy.adminPassword;
    this.flatRate = icCopy.flatRate;
    //this.users = new ArrayList<>();
    this.users = new HashMap<>();
    this.flatRateStatic = icCopy.flatRate;
  }

  //Default constructor
  public InsuranceCompany(String damn) {
  }

  public InsuranceCompany() {
  }

  void setAdminPassword(String password){
    this.adminPassword = password;
    System.out.println("");
  }

  void setName(String name){
    this.name = name;
  }

  ArrayList<Integer> getUserIDs(){
    Set<Integer> keys = users.keySet();
    ArrayList<Integer> intKeys = new ArrayList<>();

    intKeys.addAll(keys);

    return intKeys;
  }


 boolean validateAdmin(String username, String password){
    if (username.equals(adminUsername) && password.equals(adminPassword)){
      return true;
    } else {
      return false;
    }
  }

  //Hash
  public void printUsers(){
    ArrayList<Integer> keys = getUserIDs();
    for (Integer user: keys){
      users.get(user).print();
    }
  }

  public void userSize(){
    System.out.println(users.size());
  }

  //Print one user - Hash
  public void printUsers(int userID){
    System.out.println(users.get(userID));
  }

  //Working hash
  Boolean addUser(User user){
    ArrayList<Integer> keys = getUserIDs();
    
    if (users.containsKey(user.getUserID()) == false){
      users.put(user.getUserID(), user);
    }
    return false;
    
     
  }


  Boolean addUser(String name, int userID, Address address){
    User user = new User(name, userID, address);
    return addUser(user);
  }

  User findUser (int userID){
    return users.get((Integer) userID);
  }

  public String getName(){
    return name;
  }

  //This is where i need to add a check for duplicates 
  Boolean addPolicy(int userID, InsurancePolicy policy){
    if (findUser(userID) != null){ 
      if (findPolicy(userID, policy.getId()) == null){
        InsurancePolicy.addPolicy(findUser(userID), policy);
        System.out.println("Has been added");
        return true;
      } else {
        System.out.println("!! A policy by this ID already exists !!");
        System.out.println("");
        return null;
      }
    }
    System.out.println("!! User does not exist !!");
    System.out.println("");
    return null;
  }

    Boolean removePolicy(int userID, InsurancePolicy policy){
    if (findUser(userID) != null){    
      if (findPolicy(userID, policy.getId()) != null){
        InsurancePolicy.removePolicy(findUser(userID), policy);
        return true;
      } else {
        System.out.println("!! A policy has not be found to remove !!");
        System.out.println("");
        return null;
      }
    }
    System.out.println("!! User does not exist !!");
    System.out.println("");
    return null;
  }

  //Hash
  Boolean removeUser(int userID, ArrayList<InsurancePolicy> policy){
    users.remove(userID);
    return null;
  }

  //Hash
  InsurancePolicy findPolicy (int userID, int policyID){
    return users.get(userID).findPolicy(policyID);
  }

  //Print policies for one user - Hash
  void printPolicies(int userID){
    users.get(userID).printPolicies(flatRate);
  }

  //Print policies for all users - Hash 
  void print(){
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      users.get(key).printPolicies(flatRate);
    }
  }

  //Hash
  public String toString(){
    ArrayList<Integer> keys = getUserIDs();
    String printString = "";
    for (Integer key: keys){
      printString += users.get(key).toString()+"\n";
    }
    return printString;
  }

  //Hash?
  Boolean createThirdPartyPolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate) throws PolicyException{
        InsurancePolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate);
        InsurancePolicy.addPolicy(users.get(userID), policy);
    
    return false;
  }

  //Hash
  Boolean createComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level, MyDate expiryDate) throws PolicyException{
      InsurancePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate);
      InsurancePolicy.addPolicy(users.get(userID), policy);
      
    System.out.println("");
    
    return false;
  }

  //Hash
  double calcTotalPayments(int userID){
    if (users.get(userID) != null){
      //Just return the value when calling from a print
      //System.out.println("Total Premium Payments for this user is $" + findUser(userID).calcTotalPremiums(flatRate));
      return users.get(userID).calcTotalPremiums(flatRate);
    } 
    return 0.0;
    
  }

  //hash
  double calcTotalPayments(){
    double runningTotal = 0;
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      runningTotal = runningTotal + users.get(key).calcTotalPremiums(flatRate);
    }
    return runningTotal;
  }

  //Hash
  Boolean carPriceRise(int userID, double risePercent){
    if (users.get(userID) != null){
      users.get(userID).carPriceRiseAll(risePercent);
      return true;
    }
    return false;
  }

  //Hash
  void carPriceRise(double risePercent){
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      users.get(key).carPriceRiseAll(risePercent);
    }
  }

  //Hash
   ArrayList<InsurancePolicy> allPolicies(){
    ArrayList<InsurancePolicy> allPolicies = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
     for (InsurancePolicy policy: users.get(key).getPolicies()){
      allPolicies.add(policy);
     }
    }
    return allPolicies;
  }

  //hash
  HashMap<Integer, InsurancePolicy> filterByCarModel (int userID, String carModel){
    HashMap<Integer, InsurancePolicy> modelPolicies = new HashMap<Integer, InsurancePolicy>();
    if (users.get(userID) != null){
     return users.get(userID).filterByCarModel(carModel);
    }
    
    return modelPolicies;
  }

  //hash
  HashMap<Integer, InsurancePolicy> filterByCarModel(String carModel){
    HashMap<Integer, InsurancePolicy> modelPolicies = new HashMap<Integer, InsurancePolicy>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      return users.get(key).filterByCarModel(carModel);
    }
    return modelPolicies;
  }

  //Hash
  ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date){
    ArrayList<InsurancePolicy> policyDates = new ArrayList<>();
    if (users.get(userID) != null){
      return users.get(userID).filterByExpiryDate(date);
    }
    return policyDates;
  }
  //Hash
  ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date){
    ArrayList<InsurancePolicy> policyDates = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      return users.get(key).filterByExpiryDate(date);
    }
    return policyDates;
  }


  //Hash
  ArrayList<String> populateDistinctCityNames(){
    ArrayList<String> cities = new ArrayList<String>();
    ArrayList<Integer> keys = getUserIDs();
    Boolean cityDoesNotExist = false;
    int UsersID = 0;

    for (Integer key: keys){
     if (cities.isEmpty()){
      //If no cities add one, makes it so the for actually runs
      cities.add(users.get(key).getAddress().getCity());
      }

     for (String city: cities){
      if (users.get(key).getAddress().getCity() == city){
        break;
      } else{
        cityDoesNotExist = true;
        UsersID = users.get(key).getUserID();
      }
     }

     if (cityDoesNotExist == true){
      cities.add(users.get(key).getAddress().getCity());
      cityDoesNotExist = false;
     }

     
    }
    return cities;
  }

  //Hash
  double paymentPerCity (int userID){
    double userPayment = 0;
    if (users.get(userID) != null){
     userPayment = calcTotalPayments(userID);
    }
    
    return userPayment;
  }

   //Hahs
  double getTotalPaymentsForCity(String city){
    double cityTotal = 0;
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      if (users.get(key).getAddress().getCity() == city){
        double userCityTotal = paymentPerCity(users.get(key).getUserID());
        cityTotal = (cityTotal + userCityTotal);
      } else {
      }
    }
    return cityTotal;
  }
//Works
  ArrayList<Double> getTotalPaymentsPerCity(ArrayList<String> cities){
    //Call the getDistinct when calling this method to get the citites
    ArrayList<Double> totalPerCity = new ArrayList<>();
    double oneCity;

    for (String city: cities){
      oneCity = getTotalPaymentsForCity(city);
      totalPerCity.add(oneCity);
    }
    return totalPerCity;
  }

  //Work on the output 
  void reportPaymentPerCity(ArrayList<String> cities, ArrayList<Double> payments){

    String format = "%1$-20s%2$-20s\n";
    System.out.format(format, "City Name", "Total Monthly Payment");


    //System.out.println("CIty Name           Total Premium Payment");
    for (int i=0; i < cities.size(); i++){
      //System.out.print(cities.get(i) + payments.get(i));
      System.out.format(format, cities.get(i), payments.get(i));
    }
  }
  
//Hash - Working 
  ArrayList<String> distinctCarModels(){
    ArrayList<String> allCars = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    ArrayList<InsurancePolicy> policyPolicies = new ArrayList<>();
    ArrayList<ArrayList<InsurancePolicy>> usersPolices = new ArrayList<>();
    

    for (Integer key: keys){
      //usersPolices.add
      usersPolices.add(users.get(key).getPolicies());
      
    }

    for (int i= 0; i < usersPolices.size(); i++){
     policyPolicies.addAll(usersPolices.get(i));
    }

    for (InsurancePolicy policy: policyPolicies){
      allCars.add(policy.getCar().getModel());
    }
    Set<String> uniqueModels = new HashSet<>(allCars);
    ArrayList<String> uniqueList = new ArrayList<>(uniqueModels);
    return uniqueList;
  }

  //Hash
  ArrayList<String> allCars(){
    ArrayList<String> allCars = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    ArrayList<InsurancePolicy> policyPolicies = new ArrayList<>();
    ArrayList<ArrayList<InsurancePolicy>> usersPolices = new ArrayList<>();
    

    for (Integer key: keys){
      //usersPolices.add
      usersPolices.add(users.get(key).getPolicies());
    }

    for (int i= 0; i < usersPolices.size(); i++){
     policyPolicies.addAll(usersPolices.get(i));
    }

    for (InsurancePolicy policy: policyPolicies){
      allCars.add(policy.getCar().getModel());
    }

    return allCars;
  }

  

  //
  ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels){
     
    ArrayList<Integer>  modelCount = new ArrayList<>();
    
    ArrayList<ArrayList<InsurancePolicy>> usersPolices = new ArrayList<>();
    ArrayList<InsurancePolicy> policyPolicies = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();

    for (Integer key: keys){
      usersPolices.add(users.get(key).getPolicies());
    }
    for (int i= 0; i < usersPolices.size(); i++){
      policyPolicies.addAll(usersPolices.get(i));
     }

    for (String model: carModels){
      int count = 0;
      for (InsurancePolicy policy: policyPolicies){
        if (policy.getCar().getModel().equals(model)){
          count = count + 1;
        }
      }
      modelCount.add(count);
    }    
    
    return modelCount;
  }

  //Hash
  ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> distinctModels){
    ArrayList<String> uniqueCars = distinctCarModels();
    ArrayList<Integer> keys = getUserIDs();
    ArrayList<InsurancePolicy> policies = allPolicies();
    ArrayList<Double> modelPayment = new ArrayList<>();

    for (String model: uniqueCars){
      Double modelTotal = 0.0;
     for (InsurancePolicy policy: policies){
      
      if (policy.getCar().getModel().equals(model)){
        modelTotal = (modelTotal + policy.calcPayment(flatRate));
      }
     }
     modelPayment.add(modelTotal);
    }


    return modelPayment;
  }

  
  //Not working, counts is wrong
  void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments){
    String format = "%1$-20s%2$-30s%3$-20s\n";
    System.out.format(format, "Car Model", "Total Premium Payment", "Average Premium Payment");


    /*
    System.out.println("Size of carModels: " + carModels.size());
    System.out.println("Size of counts: " + counts.size());
    System.out.println("Size of payments: " + premiumPayments.size());
    */

    for (int i = 0; i < carModels.size(); i++){
      double calcAvg = (premiumPayments.get(i) / counts.get(i));
  
      System.out.format(format, carModels.get(i), premiumPayments.get(i), calcAvg);
    }

  }

  //Hash
  public int getUserID(int userID){
    int foundUserID = 0;
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
     if (users.get(key).getUserID() == userID){
      foundUserID = users.get(key).getUserID();
      
     }
    }
    return foundUserID;
  }

  //Hash
  public ArrayList<User> returnUserList(){
    ArrayList<User> returnedUsers = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      returnedUsers.add(users.get(key));
    }
    return returnedUsers;
  }

  //hash
  ArrayList<User> deepCopyUsers() throws CloneNotSupportedException{
    ArrayList<User> deepCopy = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      deepCopy.add(users.get(key).clone());
    }
    return deepCopy;
  }

  //Hash
  ArrayList<User> shallowCopyUsers(){
    ArrayList<User> shallowCopy = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      shallowCopy.add(users.get(key));
    }
    return shallowCopy;
  }

  //Hash
  ArrayList<User> sortUsers(){
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();

    for (Integer key: keys){
      userList.add(users.get(key));
    }
    Comparator<User> nameComparator = Comparator.comparing(User::getCity);

    Collections.sort(userList, nameComparator);

    return userList;
  }

  @Override
  public InsuranceCompany clone() throws CloneNotSupportedException{
    InsuranceCompany output = (InsuranceCompany) super.clone();
    //output.users = new ArrayList<User>();
    output.users = new HashMap<Integer, User>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      output.users.put(key, (User) users.get(key).clone());
    }
    return output;
  }

  HashMap<String, Double> getTotalPremiumPerCity(){
    HashMap<String, Double> cityPremium = new HashMap<>();
    ArrayList<String> cities = populateDistinctCityNames();
    ArrayList<Double> payments = getTotalPaymentsPerCity(cities);

    for (int i = 0; i < cities.size(); i++){
      cityPremium.put(cities.get(i), payments.get(i));
    }
    return cityPremium;
  }

  HashMap<String, Integer> getTotalCountPerCarModel(){
    HashMap<String, Integer> countPerCar = new HashMap<>();
    ArrayList<String> carModels = distinctCarModels();
    ArrayList<Integer> carCount = getTotalCountPerCarModel(carModels);
  
    for (int i = 0; i < distinctCarModels().size(); i++){
      countPerCar.put(carModels.get(i), carCount.get(i));
    }
    return countPerCar;
  }

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

  public void reportPaymentPerCity(){
    String format = "%1$-20s%2$-20s\n";
    System.out.format(format, "City Name", "Total Monthly Payment");

    HashMap<String, Double> cityPayments = getTotalPremiumPerCity();
    Set<String> keys = cityPayments.keySet();

    for (String city: keys){
      System.out.format(format, city, cityPayments.get(city));
    }
  }

  //Works
  public Boolean save (String fileName) throws IOException{
    InsuranceCompany saveCompany = new InsuranceCompany(this);
    System.out.println("Before save: " + users);
    

    ObjectOutputStream output = null;

    try{
      output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
    } catch (IOException e){
      System.out.println("Error creating: "+ e);
    }

    try {
      Set<Integer> keys = users.keySet();
      //HashMap<Integer, InsurancePolicy> policies = new HashMap<>();
      
      for (Integer key: keys){
        HashMap<Integer, InsurancePolicy> userPolicies = users.get(key).getHashPolices();
        Set<Integer> policyKeys = userPolicies.keySet();
        String name = users.get(key).getName();
        int userID = users.get(key).getUserID();
        int streetNum = users.get(key).getAddress().streetNum;
        String street = users.get(key).getAddress().street;
        String suburb = users.get(key).getAddress().suburb;
        String city = users.get(key).getAddress().city;

        saveCompany.addUser(name, userID, new Address(streetNum, street, suburb, city));
        //saveCompany.addUser(users.get(key));
        
        for (Integer policy: policyKeys){
          saveCompany.addPolicy(key, userPolicies.get(policy));
        }
      }
      output.writeObject(saveCompany);
    } catch (IOException e) {
      System.out.println("Error in writing saveCompany to file: " + e);
    }

    try {
      if (output != null){
        output.close();
        //System.out.println("After save: " + saveCompany.users);
        //saveCompany.printUsers();
        //saveCompany.printPolicies(1);
        System.out.println("Size of users keys: " + saveCompany.users.keySet().size());
        return true;
      }
    } catch (IOException e) {
      System.out.println("Error closing");
    }

    
    
    return false;
  }

  //Working
  public Boolean load (String fileName) throws IOException{
    ObjectInputStream input = null;

    try {
      input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
    } catch (FileNotFoundException e){
      System.out.println("This file could not be found: " + e);
    } 
    catch (IOException e) {
      System.out.println("Error in open: " + e);
    }

    try {
      while(true){
        InsuranceCompany loadedCompany = (InsuranceCompany) input.readObject();
        
        //new InsuranceCompany(loadedCompany.name, loadedCompany.users, loadedCompany.adminUsername, loadedCompany.adminPassword, loadedCompany.flatRate);
        
        
        HashMap<Integer, User> loadedUsers = loadedCompany.users;
        Set<Integer> userKeys = loadedCompany.users.keySet();
        HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
        for (Integer user: userKeys){

          //addUser(loadedCompany.users.get(user));
          String userName = loadedUsers.get(user).getName();
          int userID = loadedUsers.get(user).getUserID();
          int streetNum = loadedUsers.get(user).getAddress().streetNum;
          String street = loadedUsers.get(user).getAddress().street;
          String suburb = loadedUsers.get(user).getAddress().suburb;
          String city = loadedUsers.get(user).getAddress().city;
          addUser(userName, userID, new Address(streetNum, street, suburb, city));
          

          HashMap<Integer, InsurancePolicy> userPolicies = loadedUsers.get(user).getHashPolices();
          Set<Integer> policyKeys = userPolicies.keySet();
          for (Integer policy: policyKeys){
            addPolicy(userID, userPolicies.get(policy));
          }
        }
        
        
        


        //System.out.println("Prining the name from the loaded object: " + insuranceCompany.getName());
        //insuranceCompany.printUsers();
      }
    } catch (EOFException eof){
      System.out.println("Reached end of save file");
    } catch (IOException e) {
      System.out.println("Error in adding policy: " + e);
    } catch (ClassNotFoundException ce){
      System.out.println("Class not found error: " + ce);
    } 

    try {
      if (input != null){
        input.close();
        System.out.println("File has been loaded");
        return true;
      }
    } catch (IOException e) {
      System.out.println("Error in close of file: " + e);
    }
    return true;
  }


  String toDelimitedString(){
    return name + "," + adminUsername + "," + adminPassword + "," + flatRate;
  }

//Working
  Boolean saveTextFile(String fileName) throws IOException{
    BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
    output.write("InsuranceCompany," + toDelimitedString());
    Set<Integer> keys = users.keySet();
    for (Integer key: keys){
      output.write("\n");
      output.write("User," + users.get(key).toDelimitedString());
      
      Set<Integer> policyKeys = users.get(key).getHashPolices().keySet();
      HashMap<Integer, InsurancePolicy> userPolicies = users.get(key).getHashPolices();

      for (Integer policyKey: policyKeys){
        output.write("\n");
        output.write("Policy," + userPolicies.get(policyKey).toDelimitedString() + "," + key);
      }
    }

    output.close();
    return true;
  }

  //Work on it 
  Boolean loadTextFile(String fileName) throws IOException, PolicyException{
    BufferedReader input = new BufferedReader(new FileReader(fileName));
    String line = input.readLine();
    Boolean inPolicies = false;
    InsuranceCompany newCompany = new InsuranceCompany();
    HashMap<Integer, User> loadedUsers = new HashMap<>();

    while (line != null){
      line = line.trim();
      String[] field = line.split(",");

      String fieldZ = field[0];

      if (fieldZ.equals("InsuranceCompany")){
        name = field[1];
        adminUsername = field[2];
        adminPassword = field[3];
        flatRate = Integer.parseInt(field[4]);
      } else if (fieldZ.equals("User")){
        String name = field[1];
        int userID = Integer.parseInt(field[2]);
        int streetNum = Integer.parseInt(field[3]);
        String street = field[4];
        String suburb = field[5];
        String city = field[6];
        //System.out.println("Amount of policies that user: " + name + " holds is: " + numOfPolicies);
        addUser(name, userID, new Address(streetNum, street, suburb, city));
      
      } else if (fieldZ.equals("Policy")){
          String fieldOne = field[1];
        if (fieldOne.equals("CP")){
          String policyHolderName = field[2];
          int policyID = Integer.parseInt(field[3]);
          String model = field[4];
          int modelYear = Integer.parseInt(field[5]);
          double price = Double.parseDouble(field[6]);
          String type = field[7];
          int claims = Integer.parseInt(field[8]);
          int year = Integer.parseInt(field[9]);
          int month = Integer.parseInt(field[10]);
          int day = Integer.parseInt(field[11]);
          int level = Integer.parseInt(field[12]);
          int userID = Integer.parseInt(field[13]);
          addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, 19, level, new MyDate(year, month, day)));
          //InsurancePolicy policy = new ComprehensivePolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day));
        } else if (fieldOne.equals("TPP")){
          String policyHolderName = field[2];
          int policyID = Integer.parseInt(field[3]);
          String model = field[4];
          int modelYear = Integer.parseInt(field[5]);
          Double price = Double.parseDouble(field[6]);
          String type = field[7];
          int claims = Integer.parseInt(field[8]);
          int year = Integer.parseInt(field[9]);
          int month = Integer.parseInt(field[10]);
          int day = Integer.parseInt(field[11]);
          String comments = field[12];
          int userID = Integer.parseInt(field[13]);
          InsurancePolicy thisPolicy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day));
          //newCompany.addPolicy(userID, new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
          addPolicy(userID, thisPolicy);
        }
      }


			line = input.readLine();
    }
    

    input.close();
    return true;
  }

  HashMap<Integer, InsurancePolicy> getPoliciesHash(){
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
    for (Integer user: userKeys){
      HashMap<Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices();
      Set<Integer> policy = userPolicies.keySet();

      for (Integer key: policy){
        allPolicies.put(key, userPolicies.get(key));
      }
    }

    return allPolicies;
  }

  HashMap<Integer, User> getUsersHash(){
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, User> allUsers = new HashMap<>();
    for (Integer key: userKeys){
      allUsers.put(key, users.get(key));
    }
    return allUsers;
  }

}
