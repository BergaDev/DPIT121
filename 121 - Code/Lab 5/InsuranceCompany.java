//Matthew Bergamini - 8137225
import java.lang.instrument.IllegalClassFormatException;
import java.util.*;

public class InsuranceCompany implements Cloneable{
  
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

    public InsuranceCompany(InsuranceCompany icCopy){
    this.name = icCopy.name;
    this.adminUsername = icCopy.adminUsername;
    this.adminPassword = icCopy.adminPassword;
    this.flatRate = icCopy.flatRate;
    //this.users = new ArrayList<>();
    this.users = new HashMap<>();
    this.flatRateStatic = icCopy.flatRate;
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

}
