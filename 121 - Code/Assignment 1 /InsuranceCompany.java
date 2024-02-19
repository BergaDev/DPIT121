//Matthew Bergamini - 8137225
import java.util.*;

public class InsuranceCompany {
  
  String name;
  private ArrayList<User>users;
  private String adminUsername;
  private String adminPassword;
  public int flatRate;
  public static int flatRateStatic;


  public InsuranceCompany(String name, User users, String adminUsername, String adminPassword, int flatRate){
    this.name = name;
    this.adminUsername = adminUsername;
    this.adminPassword = adminPassword;
    this.flatRate = flatRate;
    this.users = new ArrayList<>();
    this.flatRateStatic = flatRate;
  }

  void setAdminPassword(String password){
    this.adminPassword = password;
    System.out.println("");
  }

 boolean validateAdmin(String username, String password){
    if (username.equals(adminUsername) && password.equals(adminPassword)){
      return true;
    } else {
      return false;
    }
  }

  public void printUsers(){
    for (User user: users){
      user.print();
    }
  }

    public void printUsers(int userID){
    for (User user: users){
     if (user.getUserID() == userID){
      user.print();
     } else {
      System.out.println("Could not find a user to print for");
     }
    }
  }

  Boolean addUser(User user){
    
    int addUserID = user.getUserID();
    for (User currentUser: users){
      int eachID = currentUser.getUserID();
      //System.out.println("EachID is " + eachID);
      if (eachID == addUserID){
        System.out.println("!!User already exists!!");
        System.out.println("");
        return false;
      }
    }    
    System.out.println("!!User doesn't exist, adding to array!!");
    System.out.println("");
    users.add(user);
    return true;
     
  }

  Boolean addUser(String name, int userID, Address address){
    User user = new User(name, userID, address);
    return addUser(user);
  }

  User findUser (int userID){
    for (User user: users){
      if (user.getUserID() == userID){
        //user.print();
        return user;
      }
    }
    return null;
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

  //Testing shit
  Boolean removeUser(int userID, ArrayList<InsurancePolicy> policy){
    if (findUser(userID) != null){    

        //InsurancePolicy.(findUser(userID), policy);
      //findUser(userID);
      users.remove(findUser(userID));
        return true;
      } else {
        System.out.println("!! A User has not be found to remove !!");
        System.out.println("");
      }
    
    System.out.println("");
    return null;
  }

  InsurancePolicy findPolicy (int userID, int policyID){
    if (findUser(userID) != null){
     if (findUser(userID).findPolicy(policyID) != null){
      findUser(userID).findPolicy(policyID).print();
      return findUser(userID).findPolicy(policyID);
     }

    }
    return null;
  }

  //Print policies for one user
  void printPolicies(int userID){
    if (findUser(userID) != null){
      //findUser(userID).print();
      findUser(userID).printPolicies(flatRate);
    }
  }

  //Print policies for all users
  void print(){
    for (User user: users){
      int userID = user.getUserID();
      //user.print();
      printPolicies(userID);
    }
  }

  
  public String toString(){
    String printString = "";
    for (User user: users){
      printString += user.toString()+"\n";
    }
    return printString;
  }

  Boolean createThirdPartyPolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate){
    if (findUser(userID) != null){
      //If no policy exists with ID returns null - Not working 
      if(findPolicy(userID, id) == null){
        InsurancePolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, comments, expiryDate);
        InsurancePolicy.addPolicy(findUser(userID), policy);
      } else {
        System.out.println("!! The user already has a policy with the same ID !!");
        System.out.println("");
      }
      
    }
    
    return false;
  }

  Boolean createComprehensivePolicy (int userID, String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level, MyDate expiryDate){
    if (findUser(userID) != null){
      if (findPolicy(userID, id) == null){
      InsurancePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, driverAge, level, expiryDate);
      InsurancePolicy.addPolicy(findUser(userID), policy);
      }
    } 
    System.out.println("");
    
    return false;
  }

  double calcTotalPayments(int userID){
    if (findUser(userID) != null){
      //Just return the value when calling from a print
      //System.out.println("Total Premium Payments for this user is $" + findUser(userID).calcTotalPremiums(flatRate));
      return findUser(userID).calcTotalPremiums(flatRate);
    } 
    return 0.0;
    
  }

  double calcTotalPayments(){
    double runningTotal = 0;
    for (User user: users){
      runningTotal = runningTotal + user.calcTotalPremiums(flatRate);
    }
    return runningTotal;
  }

  Boolean carPriceRise(int userID, double risePercent){
    if (findUser(userID) != null){
      findUser(userID).carPriceRiseAll(risePercent);
      return true;
    }
    return false;
  }

  void carPriceRise(double risePercent){
    for (User user: users){
      user.carPriceRiseAll(risePercent);
    }
  }

   ArrayList<InsurancePolicy> allPolicies(){
    ArrayList<InsurancePolicy> allPolicies = new ArrayList<>();
    for (User user: users){
     for (InsurancePolicy policy: user.getPolicies()){
      allPolicies.add(policy);
     }
    }
    return allPolicies;
  }

  ArrayList<InsurancePolicy> filterByCarModel (int userID, String carModel){
    ArrayList<InsurancePolicy> modelPolicies = new ArrayList<>();
    if (findUser(userID) != null){
     return findUser(userID).filterByCarModel(carModel);
    }
    
    return modelPolicies;
  }
  ArrayList<InsurancePolicy> filterByCarModel(String carModel){
    ArrayList<InsurancePolicy> modelPolicies = new ArrayList<>();
    for (User user: users){
      return user.filterByCarModel(carModel);
    }
    return modelPolicies;
  }

  //This method confirm working
  ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date){
    ArrayList<InsurancePolicy> policyDates = new ArrayList<>();
    if (findUser(userID) != null){
      return findUser(userID).filterByExpiryDate(date);
    }
    return policyDates;
  }
  //Working now
  ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date){
    ArrayList<InsurancePolicy> policyDates = new ArrayList<>();
    for (User user: users){
      return user.filterByExpiryDate(date);
    }
    return policyDates;
  }


  //Works 100%
  ArrayList<String> populateDistinctCityNames(){
    ArrayList<String> cities = new ArrayList<String>();
    Boolean cityDoesNotExist = false;
    int UsersID = 0;

    for (User user: users){
     if (cities.isEmpty()){
      //If no cities add one, makes it so the for actually runs
      cities.add(user.getAddress().getCity());
      }

     for (String city: cities){
      if (user.getAddress().getCity() == city){
        break;
      } else{
        cityDoesNotExist = true;
        UsersID = user.getUserID();
      }
     }

     if (cityDoesNotExist == true){
      cities.add(findUser(UsersID).getAddress().getCity());
      cityDoesNotExist = false;
     }

     
    }
    return cities;
  }

  //Works
  double paymentPerCity (int userID){
    double userPayment = 0;
    if (findUser(userID) != null){
     userPayment = calcTotalPayments(userID);
    }
    
    return userPayment;
  }

   //Works
  double getTotalPaymentsForCity(String city){
    double cityTotal = 0;
    for (User user: users){
      if (user.getAddress().getCity() == city){
        double userCityTotal = paymentPerCity(user.getUserID());
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
  
//Working
  ArrayList<String> distinctCarModels(){
    ArrayList<String> allCars = new ArrayList<>();
    for (User user: users){
      ArrayList<ArrayList <InsurancePolicy>> userPolicies = new ArrayList<>();
      int numOfPolicies = user.getPolicies().size();
      userPolicies.add(user.getPolicies());

      for (int i = 0; i < user.getPolicies().size(); i++){
        allCars.add(user.getPolicy(i).getCar().getModel());
        
      }
    }
    //Behold, my mess
    //HashSet takes an array and makes it unique
    Set<String> uniqueModels = new HashSet<>(allCars);
    //Below just makes it match the required return type 
    ArrayList<String> uniqueCars = new ArrayList<>(uniqueModels);
    return uniqueCars;
  }

  ArrayList<String> allCars(){
    ArrayList<String> allCars = new ArrayList<>();


    for (User user: users){
    ArrayList<ArrayList <InsurancePolicy>> userPolicies = new ArrayList<>();
      int numOfPolicies = user.getPolicies().size();
      userPolicies.add(user.getPolicies());

      for (int i = 0; i < user.getPolicies().size(); i++){
        allCars.add(user.getPolicy(i).getCar().getModel());
      }
    }

      return allCars;
  }

  //Working
  ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels){
    ArrayList<Integer>  modelCount = new ArrayList<>();
    ArrayList<InsurancePolicy> usersPolicies = new ArrayList<>();

    for (User user: users){
      for (int i = 0; i < user.getPolicies().size(); i++){
        usersPolicies.add(user.getPolicy(i));
      }
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
  ArrayList<Double> getTotalPaymentPerCarModel (ArrayList<String> carModels){
    ArrayList<Double>  modelPayment = new ArrayList<>();
    ArrayList<InsurancePolicy> usersPolicies = new ArrayList<>();

    for (User user: users){
      for (int i = 0; i < user.getPolicies().size(); i++){
        usersPolicies.add(user.getPolicy(i));
      }
    }

    for (String model: carModels){
      Double carPayment = 0.0;
      for (int i = 0; i < usersPolicies.size(); i++){
       if (usersPolicies.get(i).getCar().getModel().equals(model)){
        carPayment = carPayment + usersPolicies.get(i).calcPayment(flatRate);
       }
      }
      modelPayment.add(carPayment);
    }    
    return modelPayment;
  }

  
  //Working
  void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiumPayments){
    String format = "%1$-20s%2$-30s%3$-20s\n";
    System.out.format(format, "Car Model", "Total Premium Payment", "Average Premium Payment");

    for (int i = 0; i < carModels.size(); i++){
      double calcAvg = (premiumPayments.get(i) / counts.get(i));
  
      System.out.format(format, carModels.get(i), premiumPayments.get(i), calcAvg);
    }

  }

  public int getUserID(int userID){
    int foundUserID = 0;
    for (User user: users){
     if (user.getUserID() == userID){
      foundUserID = user.getUserID();
      
     }
    }
    return foundUserID;
  }


  

  


}
