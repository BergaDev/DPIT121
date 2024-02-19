//Matthew Bergamini - 8137225
import java.util.*;

public class InsuranceCompany {
  
  String name;
  private ArrayList<User>users;
  private String adminUsername;
  private String adminPassword;
  private int flatRate;


  public InsuranceCompany(String name, User users, String adminUsername, String adminPassword, int flatRate){
    this.name = name;
    this.adminUsername = adminUsername;
    this.adminPassword = adminPassword;
    this.flatRate = flatRate;
    this.users = new ArrayList<>();
  }

  Boolean validateAdmin(String username, String password){
    if (username == adminUsername && password == adminPassword){
      return true;
    }
    return false;
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
      findUser(userID).printPolicies(flatRate);
    }
  }

  //Print policies for all users
  void print(){
    for (User user: users){
      int userID = user.getUserID();
      user.print();
      printPolicies(userID);
    }
  }

  //Cannot reduce visability of a method in the super?
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
    System.out.println("!! UserID does not exist !!");
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

  

  


}
