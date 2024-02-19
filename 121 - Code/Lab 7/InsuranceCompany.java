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


public class InsuranceCompany implements Cloneable, Serializable{

  private static final long serialVersionUID = 6529685098267757690L;
  
  String name;
  //private ArrayList<User>users;
  private HashMap<Integer, User> users;
  private String adminUsername;
  private String adminPassword;
  public int flatRate;
  public static int flatRateStatic;
  public int userIDGen = 0000;


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
    this.users = new HashMap<Integer, User>();
  }

  void setAdminPassword(String password){
    this.adminPassword = password;
    System.out.println("");
  }

  void setName(String name){
    this.name = name;
  }

  String getAdminUserName(){
    return adminUsername;
  }
  String getAdminPassword(){
    return adminPassword;
  }

  ArrayList<Integer> getUserIDs(){
    ArrayList<Integer> intKeys = new ArrayList<>();
    try {
      Set<Integer> keys = users.keySet();
    
      intKeys.addAll(keys);
  
    } catch (NullPointerException e) {
      System.out.println("No users could be found: " + e);
    }
    
    return intKeys;
  }


 boolean validateAdmin(String username, String password){
    if (username.equals(adminUsername) && password.equals(adminPassword)){
      return true;
    } else {
      return false;
    }
  }

  boolean validateUser(String username, String password){
      Set<Integer> userKeys = users.keySet();
      for (Integer user: userKeys){
          if (username.equals(users.get(user).userName) && password.equals(users.get(user).userPassword)){
              return true;
          }
      }
      return false;
  }

  public String findPolicyToString(int policyID){
      Set<Integer> userKeys = users.keySet();
      String policy = "";
      for (Integer user: userKeys){
          HashMap<Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices(users.get(user).userName, users.get(user).userPassword);
          Set<Integer> policyKeys = userPolicies.keySet();
          for (Integer thisPolicy: policyKeys){
              if (thisPolicy == policyID){
                  policy = userPolicies.get(thisPolicy).toDelimitedString();
              }
          }
      }
      return policy;
  }

  public HashMap<Integer, InsurancePolicy> filterByPolicyHolderName(String policyHolderName, int userID, String userName, String userPassword){
      String gotName = findUser(userID).userName;
      String gotPassword = findUser(userID).userPassword;
      HashMap<Integer, InsurancePolicy> foundPolicies = new HashMap<>();
      if (userName.equals(gotName) && userPassword.equals(gotPassword)){
          HashMap<Integer, InsurancePolicy> userPolicies = findUser(userID).getHashPolices(userName, userPassword);
          Set<Integer> policyKey = userPolicies.keySet();

          for (Integer policy: policyKey){
              if (userPolicies.get(policy).policyHolderName.equals(policyHolderName)){
                  foundPolicies.put(policy, userPolicies.get(policy));
              }
          }
          return foundPolicies;
      } else {
          return null;
      }
  }

  public HashMap<Integer, InsurancePolicy> filterByExpiryDate(int userID, String userName, String userPassword, MyDate expiryDate){
      String gotName = findUser(userID).userName;
      String gotPassword = findUser(userID).userPassword;
      HashMap<Integer, InsurancePolicy> foundPolicies = new HashMap<>();
      if (userName.equals(gotName) && userPassword.equals(gotPassword)) {
          ArrayList<InsurancePolicy> allPolicies = findUser(userID).getPolicies();
          //int userID, MyDate date, String userName, String userPassword
          ArrayList<InsurancePolicy> gotPolicies = filterByExpiryDate(userID, expiryDate, gotName, gotPassword);
          for (int i = 0; i < gotPolicies.size(); i++){
              foundPolicies.put(gotPolicies.get(i).getId(), gotPolicies.get(i));
          }
          return foundPolicies;
      } else {
          return null;
      }
  }



  //Hash
  public String printUsers(){
    ArrayList<Integer> keys = getUserIDs();
    for (Integer user: keys){
      users.get(user).print();
    }
      return null;
  }

  public String printUsersToString(){
      String output = "";
      Set<Integer> userKeys = users.keySet();
      for (Integer user: userKeys){
         output = output + (users.get(user).toString() + "\n");
      }
      return output;
  }

    public int getUserID(String uName, String uPassword){
      Set<Integer> userKeys = users.keySet();
      for (Integer user: userKeys){
          if (uName.equals(users.get(user).userName) && uPassword.equals(users.get(user).userPassword)){
              return user;
          }
      }
      return 404;
    }

    public User getUser(int userID){
        Set<Integer> userKeys = users.keySet();
        for (Integer user: userKeys){
            if (user == userID){
                return findUser(userID);
            }
        }
        return null;
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
    if (users == null){
      
      users.put(user.getUserID(), user);
    } else if (users != null){
      if (users.containsKey(user.getUserID()) == false){
      users.put(user.getUserID(), user);
    }
    } 
    
    
    return false;
    
     
  }


  Boolean addUser(String name, int userID, Address address, String userName, String userPassword){
    User user = new User(name, userID, address, userName, userPassword);
    return addUser(user);
  }

  User findUser (int userID){
    return users.get((Integer) userID);
  }

  public String getName(){
    return name;
  }

  //This is where i need to add a check for duplicates 
  Boolean addPolicy(int userID, InsurancePolicy policy, String userName, String userPassword){
    if (findUser(userID) != null){ 
      if (findPolicy(userID, policy.getId(), userName, userPassword) == null){
        InsurancePolicy.addPolicy(findUser(userID), policy, userName, userPassword);
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

    Boolean removePolicy(int userID, InsurancePolicy policy, String userName, String userPassword){
    if (findUser(userID) != null){    
      if (findPolicy(userID, policy.getId(), userName, userPassword) != null){
        InsurancePolicy.removePolicy(findUser(userID), policy, userName, userPassword);
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
  InsurancePolicy findPolicy (int userID, int policyID, String userName, String userPassword){
    return users.get(userID).findPolicy(policyID, userName, userPassword);
  }

  //Print policies for one user - Hash
  void printPolicies(int userID, String userName, String userPassword){
    users.get(userID).printPolicies(flatRate, userName, userPassword);
  }

  //Print policies for all users - Hash 
  void print(){
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      String uName = users.get(key).userName;
      String uPassword = users.get(key).userPassword;
      users.get(key).printPolicies(flatRate, uName, uPassword);
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
  Boolean createThirdPartyPolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate, String userName, String userPassword) throws PolicyException, NameException{
        InsurancePolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate);
        InsurancePolicy.addPolicy(users.get(userID), policy, userName, userPassword);
    
    return false;
  }

  //Hash
  Boolean createComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level, MyDate expiryDate, String userName, String userPassword) throws PolicyException, NameException{
      InsurancePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate);
      InsurancePolicy.addPolicy(users.get(userID), policy, userName, userPassword);
      
    System.out.println("");
    
    return false;
  }

  //Hash
  double calcTotalPayments(int userID, String userName, String userPassword){
    if (users.get(userID) != null){
      //Just return the value when calling from a print
      //System.out.println("Total Premium Payments for this user is $" + findUser(userID).calcTotalPremiums(flatRate));
      return users.get(userID).calcTotalPremiums(flatRate, userName, userPassword);
    } 
    return 0.0;
    
  }

  //hash
  double calcTotalPayments(String userName, String userPassword){
    double runningTotal = 0;
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      runningTotal = runningTotal + users.get(key).calcTotalPremiums(flatRate, userName, userPassword);
    }
    return runningTotal;
  }

  //Hash
  Boolean carPriceRise(int userID, double risePercent, String userName, String userPassword){
    if (users.get(userID) != null){
      users.get(userID).carPriceRiseAll(risePercent, userName, userPassword);
      return true;
    }
    return false;
  }

  //Hash
  void carPriceRise(double risePercent, String userName, String userPassword){
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      users.get(key).carPriceRiseAll(risePercent, userName, userPassword);
    }
  }

  //Hash
   HashMap<Integer, InsurancePolicy> allPolicies(){
    HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
     for (InsurancePolicy policy: users.get(key).getPolicies()){
      allPolicies.put(policy.getId(), policy);
     }
    }
    return allPolicies;
  }

  //hash
  HashMap<Integer, InsurancePolicy> filterByCarModel (int userID, String carModel, String userName, String userPassword){
    HashMap<Integer, InsurancePolicy> modelPolicies = new HashMap<Integer, InsurancePolicy>();
    if (users.get(userID) != null){
     return users.get(userID).filterByCarModel(carModel, userName, userPassword);
    }
    
    return modelPolicies;
  }

  //hash
  HashMap<Integer, InsurancePolicy> filterByCarModel(String carModel){
    HashMap<Integer, InsurancePolicy> modelPolicies = new HashMap<Integer, InsurancePolicy>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      String userName = users.get(key).userName;
      String userPassword = users.get(key).userPassword;
      return users.get(key).filterByCarModel(carModel, userName, userPassword);
    }
    return modelPolicies;
  }

  //Hash
  ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date, String userName, String userPassword){
    ArrayList<InsurancePolicy> policyDates = new ArrayList<>();
    if (users.get(userID) != null){
      return users.get(userID).filterByExpiryDate(date, userName, userPassword);
    }
    return policyDates;
  }
  //Hash
  ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date){
    ArrayList<InsurancePolicy> policyDates = new ArrayList<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      String userName = users.get(key).userName;
      String userPassword = users.get(key).userPassword;
      return users.get(key).filterByExpiryDate(date, userName, userPassword);
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
  double paymentPerCity (int userID, String userName, String userPassword){
    double userPayment = 0;
    if (users.get(userID) != null){
     userPayment = calcTotalPayments(userID, userName, userPassword);
    }
    
    return userPayment;
  }

   //Hahs
  double getTotalPaymentsForCity(String city){
    double cityTotal = 0;
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      String userName = users.get(key).userName;
      String userPassword = users.get(key).userPassword;
      if (users.get(key).getAddress().getCity() == city){
        double userCityTotal = paymentPerCity(users.get(key).getUserID(), userName, userPassword);
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
    HashMap<Integer, InsurancePolicy> policies = allPolicies();
    ArrayList<Double> modelPayment = new ArrayList<>();
    Set<Integer> policyKeys = policies.keySet();

    for (String model: uniqueCars){
      Double modelTotal = 0.0;
     for (Integer policy: policyKeys){
      
      if (policies.get(policy).getCar().getModel().equals(model)){
        modelTotal = (modelTotal + policies.get(policy).calcPayment(flatRate));
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

   HashMap<Integer, User> deepCopyUsersHashMap(HashMap<Integer, User> users) throws CloneNotSupportedException{
    HashMap<Integer, User> deepCopy = new HashMap<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      deepCopy.put(key, users.get(key).clone());
    }
    return deepCopy;
  }

  HashMap<Integer, User> shallowCopyUsersHashMap(HashMap<Integer, User> users) throws CloneNotSupportedException{
    HashMap<Integer, User> shallowCopy = new HashMap<>();
    ArrayList<Integer> keys = getUserIDs();
    for (Integer key: keys){
      shallowCopy.put(key, users.get(key));
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
  public Boolean save (String fileName, String adminUsername, String adminPassword) throws IOException{
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    InsuranceCompany saveCompany = new InsuranceCompany(this);
    
    

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
        String userName = users.get(key).userName;
        String userPassword = users.get(key).userPassword;
        HashMap<Integer, InsurancePolicy> userPolicies = users.get(key).getHashPolices(userName, userPassword);
        Set<Integer> policyKeys = userPolicies.keySet();
        String name = users.get(key).getName();
        int userID = users.get(key).getUserID();
        int streetNum = users.get(key).getAddress().streetNum;
        String street = users.get(key).getAddress().street;
        String suburb = users.get(key).getAddress().suburb;
        String city = users.get(key).getAddress().city;

        saveCompany.addUser(name, userID, new Address(streetNum, street, suburb, city), userName, userPassword);
        //saveCompany.addUser(users.get(key));
        
        for (Integer policy: policyKeys){
          saveCompany.addPolicy(key, userPolicies.get(policy), userName, userPassword);
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

    
    
    return true;
  } else {
    return false;
  }
  }

  //Working - pass
  public Boolean load (String fileName, String adminUsername, String adminPassword) throws IOException, CloneNotSupportedException{
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    ObjectInputStream input = null;
    System.out.println("Myabe we made it here?");
    InsuranceCompany loadedCompany = new InsuranceCompany();

    try {
      input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
    } catch (FileNotFoundException e){
      System.out.println("This file could not be found: " + e);
    } 
    catch (IOException e) {
      System.out.println("Error in open (InsuranceCompany): " + e);
        try {
            Options.testCase(this);
            load(fileName, adminUsername, adminPassword);
        } catch (PolicyException ex) {
            throw new RuntimeException(ex);
        } catch (NameException ex) {
            throw new RuntimeException(ex);
        }
    }

    try {
      while(true){
       loadedCompany = (InsuranceCompany) input.readObject();
        
        //new InsuranceCompany(loadedCompany.name, loadedCompany.users, loadedCompany.adminUsername, loadedCompany.adminPassword, loadedCompany.flatRate);
        
        
        HashMap<Integer, User> loadedUsers = loadedCompany.users;
        Set<Integer> userKeys = loadedCompany.users.keySet();
        HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
        System.err.println("Before for");
        for (Integer user: userKeys){

          //addUser(loadedCompany.users.get(user));
          String userName = loadedUsers.get(user).getName();
          int userID = loadedUsers.get(user).getUserID();
          int streetNum = loadedUsers.get(user).getAddress().streetNum;
          String street = loadedUsers.get(user).getAddress().street;
          String suburb = loadedUsers.get(user).getAddress().suburb;
          String city = loadedUsers.get(user).getAddress().city;
          String uName = loadedUsers.get(user).getUserName();
          String uPassword = loadedUsers.get(user).getUserPassword();
          addUser(userName, userID, new Address(streetNum, street, suburb, city), uName, uPassword);
          

          HashMap<Integer, InsurancePolicy> userPolicies = loadedUsers.get(user).getHashPolices(uName, uPassword);
          Set<Integer> policyKeys = userPolicies.keySet();
          for (Integer policy: policyKeys){
            addPolicy(userID, userPolicies.get(policy), uName, uPassword);
          }

          System.out.println("Testing get users in load: " + users.size());
        }
        
        
        


        //System.out.println("Prining the name from the loaded object: " + insuranceCompany.getName());
        //insuranceCompany.printUsers();
      }
    } catch (EOFException eof){
      System.out.println("Reached end of save file");
    } catch (IOException e) {
        System.err.println("TEST");
      System.out.println("Error in adding policy: " + e);
    } catch (ClassNotFoundException ce){
      System.out.println("Class not found error: " + ce);
    } 

    try {
      if (input != null){
        input.close();
        this.adminPassword = loadedCompany.adminPassword;
        this.adminUsername = loadedCompany.adminUsername;
        this.name = loadedCompany.name;
        this.flatRate = loadedCompany.flatRate;
        this.users = deepCopyUsersHashMap(loadedCompany.getUsersHash(adminUsername, adminPassword));
        System.out.println("File has been loaded");
        return true;
      }
    } catch (IOException e) {
      System.out.println("Error in close of file: " + e);
    }
    return true;
  } else {
    return false;
  }
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
      String uName = users.get(key).userName;
      String uPassword = users.get(key).userPassword;
      output.write("\n");
      output.write("User," + users.get(key).toDelimitedString());
      
      Set<Integer> policyKeys = users.get(key).getHashPolices(uName, uPassword).keySet();
      HashMap<Integer, InsurancePolicy> userPolicies = users.get(key).getHashPolices(uName, uPassword);

      for (Integer policyKey: policyKeys){
        output.write("\n");
        output.write("Policy," + userPolicies.get(policyKey).toDelimitedString() + "," + key);
      }
    }

    output.close();
    return true;
  }

  //Work on it 
  Boolean loadTextFile(String fileName) throws IOException, PolicyException, NameException{
    BufferedReader input = new BufferedReader(new FileReader(fileName));
    String line = input.readLine();
    Boolean inPolicies = false;
    InsuranceCompany newCompany = new InsuranceCompany();
    HashMap<Integer, User> loadedUsers = new HashMap<>();

    while (line != null){
      line = line.trim();
      String[] field = line.split(",");

      String fieldZ = field[0];
      String uName = "ChangeMe";
      String uPassword = "ChangeMe";

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
        uName = field[7];
        uPassword = field[8];
        //System.out.println("Amount of policies that user: " + name + " holds is: " + numOfPolicies);
        addUser(name, userID, new Address(streetNum, street, suburb, city), uName, uPassword);
      
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
          int userID = Integer.parseInt(field[14]);
          //Adding these here because changing the one higher up doesnt change
          String userName = findUser(userID).getUserName();
          String userPassword = findUser(userID).getUserPassword();
          
          addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, 19, level, new MyDate(year, month, day)), userName, userPassword);
          if (type.equals("HATCH")){
            addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
          } else if (type.equals("SUV")){
            addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
          } else if (type.equals("SED")){
            addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SED, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
          } else if (type.equals("LUX")){
            addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, model, modelYear, price), claims, 19, 1, new MyDate(year, month, day)), userName, userPassword);
          }
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
          String userName = findUser(userID).getUserName();
          String userPassword = findUser(userID).getUserPassword();
          //InsurancePolicy thisPolicy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day));

          if (type.equals("HATCH")){
            InsurancePolicy thisPolicy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, model, modelYear, price), claims, comments, new MyDate(year, month, day));
            addPolicy(userID, thisPolicy, userName, userPassword);
          } else if (type.equals("SUV")){
            InsurancePolicy thisPolicy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, model, modelYear, price), claims, comments, new MyDate(year, month, day));
            addPolicy(userID, thisPolicy, userName, userPassword);
          } else if (type.equals("SED")){
            InsurancePolicy thisPolicy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SED, model, modelYear, price), claims, comments, new MyDate(year, month, day));
            addPolicy(userID, thisPolicy, userName, userPassword);
          } else if (type.equals("LUX")){
            InsurancePolicy thisPolicy = new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, model, modelYear, price), claims, comments, new MyDate(year, month, day));
            addPolicy(userID, thisPolicy, userName, userPassword);
          }
          //newCompany.addPolicy(userID, new ThirdPartyPolicy(policyHolderName, policyID, new Car(null, model, modelYear, price), claims, comments, new MyDate(year, month, day)));
          //addPolicy(userID, thisPolicy, userName, userPassword);
        }
      }


			line = input.readLine();
    }
    

    input.close();
    return true;
  }

  //Pass
  HashMap<Integer, InsurancePolicy> getPoliciesHash(){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
    for (Integer user: userKeys){
      String uName = users.get(user).userName;
      String uPassword = users.get(user).userPassword;
      HashMap<Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices(uName, uPassword);
      Set<Integer> policy = userPolicies.keySet();

      for (Integer key: policy){
        allPolicies.put(key, userPolicies.get(key));
      }
    }

    return allPolicies;
  } else {
    return null;
  }
  }
//Password
  HashMap<Integer, User> getUsersHash(String adminUsername, String adminPassword){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, User> allUsers = new HashMap<>();
    for (Integer key: userKeys){
      allUsers.put(key, users.get(key));
    }
    return allUsers;
  } else {
    return null;
  }
  }

  int userIDGen(){
    userIDGen = userIDGen + 1;
    if (getUserID(userIDGen) != userIDGen){
      return userIDGen;
    } else {
      userIDGen = userIDGen + 1;
    }
    return userIDGen;
  }

  //Works - pass
  HashMap<String, ArrayList<User>> getUsersPerCity(String adminUserName, String adminPassword){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    HashMap <String, ArrayList<User>> usersCities = new HashMap<>();
    Set<Integer> userKeys = users.keySet();
    //ArrayList<User> userArray = new ArrayList<>();
    ArrayList<String> cities = populateDistinctCityNames();

    //Rethink - needs an arraylist of users, gotta get distinct and then add a user array
    for (String city: cities){
      ArrayList<User> userArray = new ArrayList<>();
      for (Integer userID: userKeys){
        if (users.get(userID).getCity() == city){
          userArray.add(users.get(userID));
        }
      }
      usersCities.put(city, userArray);
    }
    return usersCities;
  } else {
    return null;
  }
    
  }

  //Works -pass
  HashMap<String, ArrayList<InsurancePolicy>> filterPoliciesByExpiryDate (MyDate expiryDate, String adminUserName, String adminPassword){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    HashMap<String, ArrayList<InsurancePolicy>> expiryPolicies = new HashMap<>();
    Set<Integer> userKeys = users.keySet();

    for (Integer userID: userKeys){
      String uName = users.get(userID).userName;
      String uPassword = users.get(userID).userPassword;
      expiryPolicies.put(users.get(userID).getName(), users.get(userID).filterByExpiryDate(expiryDate, uName, uPassword));
    }
    return expiryPolicies;
  } else {
    return null;
  }
  }

  //Works
  int [] policyCount (String adminUsername, String adminPassword, int [] ranges){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();

    //Getting all policies into hash
    for (Integer user: userKeys){
      Set<Integer> userPoliciesKey = users.get(user).getHashPolices(users.get(user).userName, users.get(user).userPassword).keySet();
      HashMap<Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices(users.get(user).userName, users.get(user).userPassword);
      for (Integer policy: userPoliciesKey){
        allPolicies.put(userPolicies.get(policy).id, userPolicies.get(policy));
      }
    }

    Set<Integer> allPolicyKeys = allPolicies.keySet();

    int [] results = new int[ranges.length];
    for (int i = 0; i < ranges.length-1; i++){
  
      int count = 0;
      int lower = ranges[i];
      int higher = 0;
          if ((i) == ranges.length-1){
            higher = ranges[i-1];
          } else {
            higher = ranges[i+1];
          }
      for (Integer policy: allPolicyKeys){
        double calcPayment = allPolicies.get(policy).calcPayment(InsuranceCompany.flatRateStatic);
        if (calcPayment >= lower && calcPayment < higher){
          count = count + 1;
        }
      }
      results[i] = count;
    }
    System.out.println("Items in results: " + results.length);
    return results;
  } else {
    return null;
  }
  }


  HashMap <String, int []> policyCityCount (String adminUsername, String adminPassword, int [] ranges){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    HashMap<String, int []> hashResults = new HashMap<>();
    ArrayList<String> cities = populateDistinctCityNames();

    for (String city: cities){
      int [] cityResults = new int[ranges.length];
      for (int i = 0; i < ranges.length-1; i++){
        int count = 0;
        int lower = ranges[i];
        int higher = 0;
              if ((i) == ranges.length-1){
                higher = ranges[i-1];
              } else {
                higher = ranges[i+1];
              }

        Set<Integer> userKeys = users.keySet();
        
        for (Integer user: userKeys){
          if (city.equals(users.get(user).getCity())){
            HashMap<Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices(users.get(user).userName, users.get(user).userPassword);
            Set<Integer> policyKeys = userPolicies.keySet();

            for (Integer policy: policyKeys){
              double calcPayment = userPolicies.get(policy).calcPayment(InsuranceCompany.flatRateStatic);
              if (calcPayment >= lower && calcPayment < higher){
                count = count + 1;
                }
              }
          }
        }


          //End ranges for 
          cityResults[i] = count;
        }
        hashResults.put(city, cityResults);
    }
    return hashResults;
  } else {
    return null;
  }
  }

  int[] userCount (String adminUsername, String adminPassword, int [] ranges){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
    int [] results = new int[ranges.length];

  for (int i = 0; i < ranges.length-1; i++){
    int count = 0;
    int lower = ranges[i];
    int higher = 0;
          if ((i) == ranges.length-1){
            higher = ranges[i-1];
          } else {
            higher = ranges[i+1];
          }
      for (Integer user: userKeys){
        HashMap<Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices(users.get(user).userName, users.get(user).userPassword);
        Set<Integer> thesePolicies = userPolicies.keySet();
        for (Integer policy: thesePolicies){
          double calcPayment = userPolicies.get(policy).calcPayment(InsuranceCompany.flatRateStatic);
          if (calcPayment >= lower && calcPayment < higher){
            count = count + 1;
          }
        }
        }
        results[i] = count;
    }
    return results;
  } else {
    return null;
  }
  }

  //Working
  HashMap <String, Integer []> userCarModelCount (String adminUsername, String adminPassword, Integer [] ranges){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    HashMap<String, Integer []> allHashes = new HashMap<>();
    //Set
    //How many users have a policy between the ranges (not how many policies between ranges)
    Set<Integer> userKeys = users.keySet();
    Integer[] output =  new Integer[ranges.length];
    ArrayList<String> distinctCarModels = distinctCarModels();

    for (String model: distinctCarModels){
      int pos = 0;
      Integer[] modelIntArray = new Integer[ranges.length];
      //For each item in array
      for (int i = 0; i < output.length; i++){
        pos = i;
        int count = 0;
        for (Integer user: userKeys){
          HashMap<String, Integer []> userResults = findUser(user).policyCarModelCount(findUser(user).userName, findUser(user).userPassword, ranges);
          Set<String> resultsKey = userResults.keySet();
          //This is for each result (model) needs to change 
          for (String result: resultsKey){
            if (result.equals(model)){
              Integer[] resultsArray = userResults.get(result);
              count = count + resultsArray[i];
            }
            
          }
        }
          //Add count to array, after add to hash
          modelIntArray[i] = count;
        }
        allHashes.put(model, modelIntArray);
      }
    


    return allHashes;
    } else {
      return null;
    }
  }

  
  HashMap<String, Integer []> policyCarModelCount (String adminUsername, String adminPassword, Integer [] ranges){
    if (adminUsername.equals(getAdminUserName()) && adminPassword.equals(getAdminPassword())){
    HashMap<String, Integer []> outputHash = new HashMap<>();
    Set<Integer> userKeys = users.keySet();
    HashMap<Integer, InsurancePolicy> allPolicies = new HashMap<>();
    for (Integer user: userKeys){
      HashMap <Integer, InsurancePolicy> userPolicies = users.get(user).getHashPolices(users.get(user).userName, users.get(user).userPassword);
      Set<Integer> theseKeys = userPolicies.keySet();
      for (Integer policy: theseKeys){
        allPolicies.put(policy, userPolicies.get(policy));
      }
    }
    
    Set<Integer> policyKeys = allPolicies.keySet();
    ArrayList<String> carModels = distinctCarModels();

    for (String model: carModels){
      Integer [] results = new Integer [ranges.length];
        for (int i = 0; i <= ranges.length-1; i++){
          int count = 0;
          int lower = ranges[i];
          int higher = 0;
          if ((i) == ranges.length-1){
            higher = ranges[i-1];
          } else {
            higher = ranges[i+1];
          }
          
          for (Integer policy: policyKeys){
            if (model.equals(allPolicies.get(policy).getCar().getModel())){
              double calcPayment = allPolicies.get(policy).calcPayment(InsuranceCompany.flatRateStatic);
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

  public ArrayList<User> sortUsersByPremium() {
  ArrayList<User> userList = new ArrayList<>();
  HashMap<String, Double> userListTest = new HashMap<>();
  Set<Integer> userKeys = users.keySet();
  for (Integer user: userKeys){
    userList.add(users.get(user));
    Double userPremium = users.get(user).calcTotalPremiums(flatRate, users.get(user).userName, users.get(user).userPassword);
    userListTest.put(users.get(user).getName(), userPremium);
  }

    Collections.sort(userList, new PremiumComparator(flatRate));

    //Testing results
    System.out.println("Rember that this test HashMap is not sorted");
    Set<String> resultKeys = userListTest.keySet();
    for (String user: resultKeys){
      System.out.println("User: " + user + ", $" + userListTest.get(user));
    }

    System.out.println("Sorted Result");
    return userList;
}

}

class PremiumComparator implements Comparator<User> {
  public static int test = 69;
        private int flatRate;

        public PremiumComparator(int flatRate) {
            this.flatRate = flatRate;
        }

        @Override
        public int compare(User user1, User user2) {
            double totalPayment1 = user1.calcTotalPremiums(flatRate, user1.userName, user1.userPassword);
            double totalPayment2 = user2.calcTotalPremiums(flatRate, user2.userName, user2.userPassword);
            return Double.compare(totalPayment1, totalPayment2);
        }
    }


