//Matthew Bergamini - 8137225
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;

public class Options {
  
  public static int policyID = 3000000;
  public static int autoTest = 0;
  public static int userID;
  public static int logOut = 0;
  public static Boolean isAdmin = false;

  public static void menus(int choice, InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{

    Scanner input = new Scanner(System.in);
   //ArrayList<InsurancePolicy> policies = new ArrayList<>();
  //InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", new User("Admin", 01, new Address(58, "Admins Address", "Admin Suburb", "Admin City")), "Admin", "Admin", 600);

    if (choice == 1){
      if (isAdmin == true){
        AdminMenu(insuranceCompany, policies);
      } else {

      

        try {
      System.out.println("Admin Login");
      System.out.println("");
      System.out.print("Enter Admin UserName: ");
      String aUname = input.nextLine();
      System.out.println("");
      System.out.print("Enter Admin Password: ");
      String aPword = input.nextLine();

       if (insuranceCompany.validateAdmin(aUname, aPword) == true){
        isAdmin = true;
        AdminMenu(insuranceCompany, policies);
      } else {
        System.out.println("Uh Uh Uh, You didn't say the magic word");
        menus(choice, insuranceCompany, policies);
      }
        } catch (InputMismatchException e)
            {
                System.err.println("error in Input : "+e);
                System.out.println("Please enter the value again");
            }
     
    }

    //End of the choice bit
  } else if (choice == 2){
    System.out.println("");
    System.out.print("Enter a userID: ");
    userID = input.nextInt();
    if (insuranceCompany.findUser(userID) != null){
      System.out.println("We made it here");
      System.out.println("");
      System.out.print("Enter userName for this user: ");
      String uName = input.nextLine();
      System.out.println("");
      System.out.print("Enter userPassword for this user: ");
      String uPassword = input.nextLine();
      if (insuranceCompany.findUser(userID).userName.equals(uName) && insuranceCompany.findUser(userID).userPassword.equals(uPassword)){
        UserMenu(insuranceCompany, policies, userID);
      } else {
        System.out.println("");

        System.out.println("These are the wrong credentials");
      }
      
    } else {
      System.out.println("");
      System.out.println("This user does not exist, makes sure that you have created a user first");
    }
    
  }

}

public static void AdminMenu(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
  Scanner input = new Scanner(System.in);
  isAdmin = true;
  String uName = "Admin";
  String uPassword = "Admin";

    Boolean firstTime = true;
    int logOut = 0;
    while (logOut == 0){

      if (firstTime == false){
        System.out.println("");
        System.out.println("Enter anything to continue....");
        System.in.read();
      }

      try{
      System.out.println("Assignment Program Main Menu");
      System.out.println("");

      System.out.println("Program Options");
      System.out.println("1) Test Code");
      System.out.println("2) Create User");
      System.out.println("3) Create Third Party Policy");
      System.out.println("4) Create Comprehensive Policy");
      System.out.println("5) Print User Information");
      System.out.println("6) Filter By Car Model");
      System.out.println("7) Filter By Expiry Date");
      System.out.println("8) Update Address");
      System.out.println("9) Swap to user menu");
      System.out.println("10) Report Payment per city");
      System.out.println("11) Report payments per car model");
      System.out.println("12) Remove policy from a user");
      System.out.println("13) Change admin password");
      System.out.println("14) Remove user");
      System.out.println("15) Report payments per city - Hash");
      System.out.println("16) Report payments per car model - Hash");
      System.out.println("17) Save and load data");
      System.out.println("18) ASS2 Range tests");
      System.out.println("19) Exit Program");
      System.out.println("");

      int option;
      System.out.print("Enter an option: ");
      option = input.nextInt();
      input.nextLine();

      if (option == 1){
        testCase(insuranceCompany);
        firstTime = false;
      } else if (option == 2){
        createUser(insuranceCompany, policies);
        firstTime = false;
      } else if (option == 3){
        createTPP(insuranceCompany, policies);
        firstTime = false;
      } else if (option == 4){
        createCP(insuranceCompany, policies);
        firstTime = false;
      } else if (option == 5){
        PrintUsers(insuranceCompany, policies);
        PrintPolicies(insuranceCompany, policies);
        firstTime = false;
      } else if (option == 6){
        filterCarModel(insuranceCompany, policies);
        firstTime = false;
      } else if (option == 7){
        filterExpiryDate(insuranceCompany, policies);
        firstTime = false;
      } else if (option == 8){
        updateAddress(insuranceCompany, policies);
        firstTime = false;
      } 
      //inputMis
      else if (option == 9){
        try {
        firstTime = false;
        System.out.println("");
        System.out.print("Choose a user to manage: ");
        int userID = input.nextInt();
        if (insuranceCompany.findUser(userID) != null){
          UserMenu(insuranceCompany, policies, userID);
        } else {
          System.out.println("Check that this userID exists");
        }
      } catch (InputMismatchException e)
      {
          System.err.println("error in Input : "+e);
          System.out.println("Please enter the value again"); 
          AdminMenu(insuranceCompany, policies);
      }
        
      } else if (option == 10){
        firstTime = false;
        //Done, print format working good
        insuranceCompany.reportPaymentPerCity(insuranceCompany.populateDistinctCityNames(), insuranceCompany.getTotalPaymentsPerCity(insuranceCompany.populateDistinctCityNames()));
      }  else if (option == 11){
        firstTime = false;
        System.out.println("");

        insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.distinctCarModels(), insuranceCompany.getTotalCountPerCarModel(insuranceCompany.distinctCarModels()), insuranceCompany.getTotalPaymentPerCarModel(insuranceCompany.distinctCarModels()));
        System.out.println("");

      }
      
      //Working 100% - inputMis
      else if (option == 12){
        firstTime = false;
        try {
        System.out.println("");
        System.out.println("");
        System.out.print("Enter a userID: ");
        int userID = input.nextInt();
        input.nextLine();
        System.out.print("Enter a policyID: ");
        int removeID = input.nextInt();
        input.nextLine();
        insuranceCompany.removePolicy(userID, insuranceCompany.findPolicy(userID, removeID, uName, uPassword), uName, uPassword);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
      } 
      //inputMis
      else if (option == 13){
        System.out.println();
        System.out.print("Enter a new admin password: ");
        String password = input.nextLine();
        insuranceCompany.setAdminPassword(password);
        firstTime = false;
      } 
      //inputMis
      else if (option == 14){
        firstTime = false;
        try {
        System.out.println();
        System.out.print("Enter the userID to remove: ");
        int userID = input.nextInt();

        if (insuranceCompany.removeUser(1, policies) == true){
          System.out.println("The user with ID: " + userID + ", Has been removed");
         } else {
          System.out.println("The removal has failed for an unknown reason");
         }
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again");
            AdminMenu(insuranceCompany, policies);
        }
      } else if (option == 15){
        firstTime = false;
        insuranceCompany.reportPaymentPerCity();
      } else if (option == 16){
        firstTime = false;
        insuranceCompany.reportPaymentPerModel();
      } else if (option == 17){
        saveLoad(insuranceCompany, policies, uName, uPassword);
        firstTime = false;
      } else if (option == 18){
        firstTime = false;
        intArrayRanges(insuranceCompany, policies);
      }
       else if (option == 19){
        mainMenu(insuranceCompany, policies);
        break;
      }
    } catch (InputMismatchException e)
    {
        System.err.println("error in Input : "+e);
        System.out.println("Please enter the value again");
        AdminMenu(insuranceCompany, policies); 
    }
    }




}


    

    public static void UserMenu(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException, NameException{
      String uName = insuranceCompany.findUser(userID).userName;
      String uPassword = insuranceCompany.findUser(userID).userPassword;
      Boolean firstTime = true;
      System.out.println("");
      int option;
      Scanner input = new Scanner(System.in);
      while (logOut == 0){

        
        if (firstTime == false){
        System.out.println("");
        System.out.println("Enter anything to continue....");
        System.in.read();
      }
      try{
      
        

          System.out.println("User Menu");
          System.out.println("1) Create a Policy");
          System.out.println("2) Print user information");
          System.out.println("3) Print policies");
          System.out.println("4) Change users address");
          System.out.println("5) Filter by expiry date ");
          System.out.println("6) Filter by car model");
          System.out.println("7) Report payments per car model");
          System.out.println("8) Remove Policy from user");
          System.out.println("9) Report total car model payments for user");
          System.out.println("10) Log out");
          System.out.println("");
          System.out.print("Choose an option: ");
          option = input.nextInt();
      
    
      if (option == 1) {
        firstTime = false;
        System.out.println("");
        System.out.println("1) Create Third Party Policy");
        System.out.println("2) Create Comprehensive Policy");
        System.out.println("");
        System.out.print("Enter an option: ");
    
        int policyType = input.nextInt();
        if (policyType == 1){
          createTPP(insuranceCompany, policies, userID);
        } else if (policyType == 2){
          createCP(insuranceCompany, policies, userID);
        }
      } else if (option == 3){
        //Make it for one policy
        insuranceCompany.printPolicies(userID, uName, uPassword);
        firstTime = false;
      } else if (option == 4){
        updateAddress(insuranceCompany, policies, userID);
        firstTime = false;     
      } else if (option == 2){
        PrintUsers(insuranceCompany, policies, userID);
        PrintPolicies(insuranceCompany, policies, userID, uName, uPassword);
        firstTime = false;
      } else if (option == 6){
        filterExpiryDate(insuranceCompany, policies, userID);
        firstTime = false;
      } else if (option == 5){
        filterCarModel(insuranceCompany, policies, userID);
        firstTime = false;
      } else if (option == 7){
        firstTime = false;
        //insuranceCompany.findUser(userID);
        insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.findUser(userID).distinctCarModels(), insuranceCompany.findUser(userID).getTotalCountPerCarModel(insuranceCompany.findUser(userID).distinctCarModels()), insuranceCompany.findUser(userID).getTotalPaymentPerCarModel(insuranceCompany.findUser(userID).distinctCarModels(), uName, uPassword));
      } else if (option == 8){
        firstTime = false;
        try {
        System.out.println("");
        input.nextLine();
        System.out.print("Enter a policyID: ");
        int removeID = input.nextInt();
        input.nextLine();
        insuranceCompany.removePolicy(userID, insuranceCompany.findPolicy(userID, removeID, uName, uPassword), uName, uPassword);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            UserMenu(insuranceCompany, policies, userID);
        }
      } else if (option == 9){
        insuranceCompany.findUser(userID).reportPaymentPerModel(uName, uPassword);
        firstTime = false;
      }
      
      else if (option == 10){
        mainMenu(insuranceCompany, policies);
      }

    } catch (InputMismatchException e)
    {
        System.err.println("error in Input : "+e);
        System.out.println("Please enter the value again");
        UserMenu(insuranceCompany, policies, userID);
    }
    }
    
    }
    


   //100% - inputMis
   public static void createUser(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    //Working
    Scanner input = new Scanner(System.in);
    String uName = "";
    String uPassword = "";

    int streetNum;
    String userName, streetName, suburbName, cityName;
    try {
    
    System.out.print("Enter the name of the user: ");
    userName = input.nextLine();
    userID = (insuranceCompany.userIDGen());

    System.out.println("User ID is: " + userID);
    System.out.println();
    System.out.print("Enter a userName: ");
    uName = input.nextLine();
    System.out.println();
    System.out.print("Enter a userPassword: ");
     uPassword = input.nextLine();
    System.out.println("Enter the address of the user");
    System.out.println("");
        System.out.print("Enter street number: ");
        streetNum = input.nextInt();
        input.nextLine();
        System.out.println("");
        System.out.print("Enter street name: ");
        streetName = input.nextLine();
        
        System.out.println("");
        System.out.print("Enter suburb name: ");
        suburbName = input.nextLine();
        
        System.out.println("");
        System.out.print("Enter city name: ");
        cityName = input.nextLine();
    
    insuranceCompany.addUser(userName, userID, new Address(streetNum, streetName, suburbName, cityName), uName, uPassword);
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again");
      AdminMenu(insuranceCompany, policies);
  }

  }

  public static void mainMenu(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> insurancePolicies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    System.out.println("");
    System.out.println("1) Admin Login");
    System.out.println("2) User Login");
    System.out.println("3) Log out");
    System.out.println("");
    System.out.print("Choose an Option: ");
    int choice = input.nextInt();

    if (choice == 1){
      AdminMenu(insuranceCompany, insurancePolicies);
    } else if (choice == 2){
      System.out.println();
      System.out.print("Enter a userID: ");
      int thisUser = input.nextInt();
      input.nextLine();
      if (insuranceCompany.findUser(thisUser) != null){
        System.out.println("");
        System.out.print("Enter the username for this user: ");
        String uName = input.nextLine();
        System.out.println("");
        System.out.print("Enter the password for this user: ");
        String uPassword = input.nextLine();

        if (uPassword.equals(insuranceCompany.findUser(thisUser).userPassword) && uName.equals(insuranceCompany.findUser(thisUser).userName)){
          UserMenu(insuranceCompany, insurancePolicies, thisUser);
        } else {
          System.out.println("This is the wrong login for this user");
          mainMenu(insuranceCompany, insurancePolicies);
        }
      }
      
    } else if (choice == 3){
      exit();
      logOut = 1;
    } else {
      System.out.println("Please check your input");
    }


  }

  //Cofrimed working
  public static void PrintUsers(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies){
    insuranceCompany.printUsers();
  }
  public static void PrintUsers(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID){
    insuranceCompany.printUsers(userID);
  }
  public static void PrintPolicies(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies){
    insuranceCompany.print();
  }
  public static void PrintPolicies(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID, String uName, String uPassword){
    insuranceCompany.printPolicies(userID, uName, uPassword);
  }


  public static void exit(){
    System.out.println("You have been logged out of the program, have a good day");
    logOut = 1;
    return;
  }

  //Works 100% - Includes inputMis
  public static void createTPP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);

    double carPrice;
    int userID, typeIN, manufacturingYear, year, month, day, claims;
    String policyHolderName, carModel, comments;
    if (autoTest == 0){
      try {
    System.out.print("Enter the userID to associate this policy with: ");
    userID = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the name of the policy holder: ");
    policyHolderName = input.nextLine();
    System.out.println("");
    policyID = policyID + 1;
    System.out.print("PolicyID assigned is " + policyID);
    System.out.println("");
    System.out.print("Enter the number of claims that this holder has made: ");
    claims = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter a comment for this policy: ");
    comments = input.nextLine();
    System.out.println("Enter some expiry date details");
    System.out.println("");
    System.out.print("Enter the year of expiry: ");
    year = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the month of expiry: ");
    month = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the day of expiry: ");
    day = input.nextInt();
    input.nextLine();
    System.out.println("");

    if (month > 12 || month < 1 || day < 1 || day > 31){
      outOfBounds(insuranceCompany, policies);
    } 

    System.out.print("Now enter the car details");   
    System.out.println("");

    System.out.print("Enter the model of the car: ");
    carModel = input.nextLine();

    System.out.println("");
    System.out.print("Enter the year of manufacturing of the car: ");
    manufacturingYear = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the price of the car: ");
    carPrice = input.nextDouble();
    input.nextLine();
    System.out.println("");

    //Might save this as the last thing to do with creating the car 
    System.out.println("Choose the type of car");
    System.out.println("1) Hatch");
    System.out.println("2) SUV");
    System.out.println("3) SED");
    typeIN = input.nextInt();
    String uName = insuranceCompany.findUser(userID).userName;
    String uPassword = insuranceCompany.findUser(userID).userPassword;
    if (typeIN == 1){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 2){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 3){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day), uName, uPassword);
    }
  } catch (InputMismatchException e){
    System.err.println("error in Input : "+e);
    System.out.println("Please enter the value again"); 
    AdminMenu(insuranceCompany, policies);
  }
  } 
  }

  //Includes inputMis
  public static void createTPP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    int thisUser = userID;
    String uName = insuranceCompany.findUser(userID).userName;
    String uPassword = insuranceCompany.findUser(userID).userPassword;
    double carPrice;
    int typeIN, manufacturingYear, year, month, day, claims;
    String policyHolderName, carModel, comments;
    if (autoTest == 0){
      try {
    System.out.println("");
    System.out.print("Enter the name of the policy holder: ");
    policyHolderName = input.nextLine();
    System.out.println("");
    policyID = policyID + 1;
    System.out.print("PolicyID assigned is " + policyID);
    System.out.println("");
    System.out.print("Enter the number of claims that this holder has made: ");
    claims = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter a comment for this policy: ");
    comments = input.nextLine();
    System.out.println("Enter some expiry date details");
    System.out.println("");
    System.out.print("Enter the year of expiry: ");
    year = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the month of expiry: ");
    month = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the day of expiry: ");
    day = input.nextInt();
    input.nextLine();
    System.out.println("");

    if (month > 12 || month < 1 || day < 1 || day > 31){
      outOfBounds(insuranceCompany, policies);
    }

    System.out.print("Now enter the car details");
    System.out.println("");    
    System.out.print("Enter the model of the car: ");
    carModel = input.nextLine();

    System.out.println("");
    System.out.print("Enter the year of manufacturing of the car: ");
    manufacturingYear = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the price of the car: ");
    carPrice = input.nextDouble();
    input.nextLine();
    System.out.println("");

    //Might save this as the last thing to do with creating the car 
    System.out.println("Choose the type of car");
    System.out.println("1) Hatch");
    System.out.println("2) SUV");
    System.out.println("3) SED");
    typeIN = input.nextInt();
    if (typeIN == 1){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 2){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 3){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day), uName, uPassword);
    }
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again"); 
      UserMenu(insuranceCompany, policies, userID);
  }
  }
  }

  //100% - inputMis - Working on the policyID
  public static void createCP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);

    double carPrice;
    int userID, typeIN, manufacturingYear, year, month, day, claims, age, level;
    String policyHolderName, carModel, comments;
    if (autoTest == 0){
      try {
    System.out.print("Enter the userID to associate this policy with: ");
    userID = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the name of the policy holder: ");
    policyHolderName = input.nextLine();
    System.out.println("");
    policyID = policyID + 1;
    //Working on this 
    /*
    try{
      policyID = policyID + 1;
    } catch ()
    */
    System.out.print("PolicyID assigned is " + policyID);
    System.out.println("");
    System.out.print("Enter the number of claims that this holder has made: ");
    claims = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the age of the policy holder: ");
    age = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the policy level of this policy: ");
    level = input.nextInt();
    input.nextLine();
    System.out.println("");

    System.out.println("Enter some expiry date details");
    System.out.println("");
    System.out.print("Enter the year of expiry: ");
    year = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the month of expiry: ");
    month = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the day of expiry: ");
    day = input.nextInt();
    input.nextLine();
    System.out.println("");

    if (month > 12 || month < 1 || day < 1 || day > 31){
      outOfBounds(insuranceCompany, policies);
    }

    System.out.print("Now enter the car details");
    System.out.println("");

    System.out.print("Enter the model of the car: ");
    carModel = input.nextLine();

    System.out.println("");
    System.out.print("Enter the year of manufacturing of the car: ");
    manufacturingYear = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the price of the car: ");
    carPrice = input.nextDouble();
    input.nextLine();
    System.out.println("");

    //Might save this as the last thing to do with creating the car 
    System.out.println("Choose the type of car");
    System.out.println("1) Hatch");
    System.out.println("2) SUV");
    System.out.println("3) SED");
    typeIN = input.nextInt();

    String uName = insuranceCompany.findUser(userID).userName;
    String uPassword = insuranceCompany.findUser(userID).userPassword;

    if (typeIN == 1){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 2){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 3){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day), uName, uPassword);
    }
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again"); 
      AdminMenu(insuranceCompany, policies);
  }
  } 
  }
  //100% - inputMis
  public static void createCP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);

    double carPrice;
    int typeIN, manufacturingYear, year, month, day, claims, age, level;
    String policyHolderName, carModel, comments;
    if (autoTest == 0){
      try {
    System.out.println("");
    System.out.print("Enter the name of the policy holder: ");
    policyHolderName = input.nextLine();
    System.out.println("");
    policyID = policyID + 1;
    System.out.print("PolicyID assigned is " + policyID);
    System.out.println("");
    System.out.print("Enter the number of claims that this holder has made: ");
    claims = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the age of the policy holder: ");
    age = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the policy level of this policy: ");
    level = input.nextInt();
    input.nextLine();
    System.out.println("");

    System.out.println("Enter some expiry date details");
    System.out.println("");
    System.out.print("Enter the year of expiry: ");
    year = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the month of expiry: ");
    month = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the day of expiry: ");
    day = input.nextInt();
    input.nextLine();
    System.out.println("");

    if (month > 12 || month < 1 || day < 1 || day > 31){
      outOfBounds(insuranceCompany, policies);
    }

    System.out.print("Now enter the car details");
    System.out.println("");
        
    System.out.print("Enter the model of the car: ");
    carModel = input.nextLine();

    System.out.println("");
    System.out.print("Enter the year of manufacturing of the car: ");
    manufacturingYear = input.nextInt();
    input.nextLine();
    System.out.println("");
    System.out.print("Enter the price of the car: ");
    carPrice = input.nextDouble();
    input.nextLine();
    System.out.println("");

    //Might save this as the last thing to do with creating the car 
    System.out.println("Choose the type of car");
    System.out.println("1) Hatch");
    System.out.println("2) SUV");
    System.out.println("3) SED");
    typeIN = input.nextInt();

    String uName = insuranceCompany.findUser(userID).userName;
    String uPassword = insuranceCompany.findUser(userID).userPassword;

    if (typeIN == 1){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 2){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day), uName, uPassword);
    } else if (typeIN == 3){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day), uName, uPassword);
    }
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again"); 
      UserMenu(insuranceCompany, policies, userID);
  }
  }
  }

   
  //Wokring - inputMis
  public static void filterCarModel(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);


    String carModel;
    int userID;

    System.out.println("1) Search through all policies");
    System.out.println("2) Search for a specific user");
    System.out.println("");
    System.out.print("Choose an option: ");
    int option = input.nextInt();
    input.nextLine();
    System.out.println("");


    if (option == 1){
        if(autoTest == 0){
          try {
        System.out.print("Enter a model of car to search for: ");
        carModel = input.nextLine();
        InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(carModel), insuranceCompany.flatRate);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
        
      } else {
          carModel = "Polestar 2";
          InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(carModel), insuranceCompany.flatRate);
          }
    } else if (option == 2){
      if(autoTest == 0){
        try {
        System.out.print("Enter the userID of the target user: ");
        userID = input.nextInt();
        input.nextLine();
        System.out.print("Enter a model of car to search for: ");
        carModel = input.nextLine();
        String uName = insuranceCompany.findUser(userID).userName;
        String uPassword = insuranceCompany.findUser(userID).userPassword;
        InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(userID, carModel, uName, uPassword), insuranceCompany.flatRate);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
        }
    }


  }

   public static void filterCarModel(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);


    String carModel;

      if(autoTest == 0){
        try {
        System.out.print("Enter a model of car to search for: ");
        carModel = input.nextLine();
        String uName = insuranceCompany.findUser(userID).userName;
        String uPassword = insuranceCompany.findUser(userID).userPassword;
        InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(userID, carModel, uName, uPassword), insuranceCompany.flatRate);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
        }
    }
    
  //Working - inputMis
  public static void filterExpiryDate(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    int option;
    int year, month, day;

    if (autoTest == 0){
      System.out.println("1) Search all users");
      System.out.println("2) Search through a specific user");
      System.out.println("");
      System.out.print("Choose an option: ");
      option = input.nextInt();

      if (option == 1){
        try {
        System.out.println("");
        System.out.println("Enter the expiry date");
        System.out.println("");
        System.out.print("Year: ");
        year = input.nextInt();
        System.out.println("");
        System.out.print("Month: ");
        month = input.nextInt();
        System.out.println("");
        System.out.print("Day: ");
        day = input.nextInt();

        InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(new MyDate(year, month, day)));
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
      } else if (option == 2){
        try {
        System.out.println("");
        System.out.print("Enter the userID to search: ");
        int userID = input.nextInt();
        System.out.println("");
        System.out.println("Enter the expiry date");
        System.out.println("");
        System.out.print("Year: ");
        year = input.nextInt();
        System.out.println("");
        System.out.print("Month: ");
        month = input.nextInt();
        System.out.println("");
        System.out.print("Day: ");
        day = input.nextInt();
        String uName = insuranceCompany.findUser(userID).userName;
        String uPassword = insuranceCompany.findUser(userID).userPassword;

        InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(userID, new MyDate(year, month, day), uName, uPassword));
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
      }

    }
  }
//Working
   public static void filterExpiryDate(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    int option;
    int year, month, day;

    if (autoTest == 0){

      option = input.nextInt();

        try {
        System.out.println("");
        System.out.println("Enter the expiry date");
        System.out.println("");
        System.out.print("Year: ");
        year = input.nextInt();
        System.out.println("");
        System.out.print("Month: ");
        month = input.nextInt();
        System.out.println("");
        System.out.print("Day: ");
        day = input.nextInt();
        String uName = insuranceCompany.findUser(userID).userName;
        String uPassword = insuranceCompany.findUser(userID).userPassword;

        InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(userID, new MyDate(year, month, day), uName, uPassword));
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
      }

    } 
  

  //Working - inputMis
  public static void updateAddress(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    int streetNum, userID;
    String street, suburb, city;
    try {
         if (autoTest == 2){
           userID = 2;
           streetNum = 100;
           street = "Changed Street";
           suburb = "Changed Suburb";
           city = "Changed City";
         } else {
          System.out.println("");

           System.out.print("Enter userID: ");
           userID = input.nextInt();
           input.nextLine();
           System.out.println("");

           System.out.println("Enter new Street Number: ");
           streetNum = input.nextInt();
           input.nextLine();
           System.out.println("");

           System.out.print("Enter new Street Name: ");
           street = input.nextLine();
           System.out.println("");

           System.out.print("Enter new Suburb Name: ");
           suburb = input.nextLine();
           System.out.println("");

           System.out.print("Enter new City Name: ");
           city = input.nextLine();
          
         }

         if (insuranceCompany.findUser(userID) != null){
           insuranceCompany.findUser(userID).setAddress(new Address(streetNum, street, suburb, city));
           insuranceCompany.findUser(userID).print();
         }
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
  }
//inputMis
  public static void updateAddress(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    int streetNum;
    String street, suburb, city;
    try {
         if (autoTest == 2){
           userID = 2;
           streetNum = 100;
           street = "Changed Street";
           suburb = "Changed Suburb";
           city = "Changed City";
         } else {
           System.out.println("Enter new Street Number: ");
           streetNum = input.nextInt();
           input.nextLine();
           System.out.println("");

           System.out.print("Enter new Street Name: ");
           street = input.nextLine();
           System.out.println("");

           System.out.print("Enter new Suburb Name: ");
           suburb = input.nextLine();
           System.out.println("");

           System.out.print("Enter new City Name: ");
           city = input.nextLine();
         }

         if (insuranceCompany.findUser(userID) != null){
           insuranceCompany.findUser(userID).setAddress(new Address(streetNum, street, suburb, city));
           insuranceCompany.findUser(userID).print();
         }
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            UserMenu(insuranceCompany, policies, userID);
        }
  }

  public static void consolidatedReports(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies){
    Scanner input = new Scanner(System.in);
    System.out.println("Got there");
    int option;
    System.out.println();
    System.out.println("1) Report ");
    System.out.println("Choose an option");

  }


  //Add this to things like dates when using an if to break the process
  public static void outOfBounds(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    Scanner input = new Scanner(System.in);
    System.out.println("The value you entered does not exit for this input");
    if (isAdmin == true){
      AdminMenu(insuranceCompany, policies);
    } else {
      try {
      System.out.println();
      System.out.print("Enter the userID to manage: ");
      int userID = input.nextInt();
      if (insuranceCompany.findUser(userID) != null){
        UserMenu(insuranceCompany, policies, userID);
      }
    } catch (InputMismatchException e)
    {
        System.err.println("error in Input : "+e);
        System.out.println("Please enter the value again");
        outOfBounds(insuranceCompany, policies);
    }
      
    }
  }

  public static void testCase(InsuranceCompany insuranceCompany) throws IOException, CloneNotSupportedException, PolicyException, NameException{
    System.out.println("UGHHGHGHHGHG");



    Scanner input = new Scanner(System.in);

    int saveChoice = 0;
    System.out.println("Choose 1 for testCase with save or 2 to load a save file");
    saveChoice = input.nextInt();

    if (saveChoice == 1){

    System.out.println("Creating first policies");
    Address address1 = new Address(1,"First Street", "Cool suburb", "Wollongong");
    User user1 = new User("Sara", 1, address1, "SarasName", "SarasPassword");
    User user2 = new User("John", 2, new Address(2, "Second Street", "Second Suburb", "Wollongong"), "JohnsName", "JohnsPassword");
   

    //Admin stuff - Flatrate 600 to be different
    //InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", user1, "Admin", "Admin", 600);
    if (!insuranceCompany.validateAdmin("Admin", "Admin")){
      System.out.println("Wrong login credentials");
    } else {
      System.out.println("Login Successful");
      System.out.println("");

      insuranceCompany.addUser(user1);
      insuranceCompany.addUser(user2);
      //Make this one have an error, wait on email reply
      insuranceCompany.addUser("Robert", 3, new Address(3, "Third Street", "Third Suburb", "Sydney"), "RobertsName", "RobertsPassword");
      insuranceCompany.addUser("Alex", 4, new Address(4, "Fourth Street", "Fourth Suburb", "Melbourne"), "AlexsName", "AlexsPassword");
      System.out.println("");

      //The new policies to march test case
      Car car1 = new Car(Car.CarType.SUV, "Toyota Camry", 2020, 5000);
      insuranceCompany.createComprehensivePolicy(1, "James Dean", 1, car1, 0, 19, 1, new MyDate(2024, 5, 21), "SarasName", "SarasPassword");
      insuranceCompany.createThirdPartyPolicy(1, "Jack And", 2, new Car(Car.CarType.HATCH, "Toyota Camry", 2018, 40000), 0, "This is policy2", new MyDate(2025, 9, 24), "SarasName", "SarasPassword");
      insuranceCompany.createThirdPartyPolicy(1, "Jill And", 3, new Car(Car.CarType.HATCH, "Toyota Camry", 2018, 20000), 0, "This is policy2", new MyDate(2025, 9, 24), "SarasName", "SarasPassword");
      insuranceCompany.createComprehensivePolicy(1, "Santa Month", 4, new Car(Car.CarType.LUX, "Nissan Dualis", 2020, 45000), 0, 35, 1, new MyDate(2024, 5, 21), "SarasName", "SarasPassword");

      //John
      insuranceCompany.createThirdPartyPolicy(2, "Policy One", 5, new Car(Car.CarType.LUX, "Nissan Dualis", 2022, 40000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "JohnsName", "JohnsPassword");
      insuranceCompany.createThirdPartyPolicy(2, "Policy Two", 6, new Car(Car.CarType.LUX, "Toyota Camry", 2022, 100), 0, "This is a new Policy", new MyDate(2023, 1, 21), "JohnsName", "JohnsPassword");
      insuranceCompany.createThirdPartyPolicy(2, "Policy Three", 7, new Car(Car.CarType.LUX, "Nissan Dualis", 2022, 90000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "JohnsName", "JohnsPassword");
      insuranceCompany.createThirdPartyPolicy(2, "Policy Four", 8, new Car(Car.CarType.LUX, "Ford Ranger", 2022, 190000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "JohnsName", "JohnsPassword");
      
      //Robert
      insuranceCompany.createThirdPartyPolicy(3, "Policy One", 9, new Car(Car.CarType.LUX, "Ford Ranger", 2022, 5000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "RobertsName", "RobertsPassword");
      insuranceCompany.createThirdPartyPolicy(3, "Policy Two", 10, new Car(Car.CarType.LUX, "Nissan Dualis", 2022, 5000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "RobertsName", "RobertsPassword");
      insuranceCompany.createThirdPartyPolicy(3, "Policy Three", 11, new Car(Car.CarType.LUX, "Toyota Camry", 2022, 490000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "RobertsName", "RobertsPassword");
      insuranceCompany.createThirdPartyPolicy(3, "Policy Four", 12, new Car(Car.CarType.LUX, "Porsche Cayenne", 2022, 30000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "RobertsName", "RobertsPassword");

      //Alex
      insuranceCompany.createThirdPartyPolicy(4, "Policy One", 13, new Car(Car.CarType.LUX, "Ferrari 488", 2022, 40000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "AlexsName", "AlexsPassword");
      insuranceCompany.createThirdPartyPolicy(4, "Policy Two", 14, new Car(Car.CarType.LUX, "Nissan Dualis", 2022, 5000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "AlexsName", "AlexsPassword");

      
      InsurancePolicy.save(user1.getHashPolices("SarasName", "SarasPassword"), "InsurancePolicyBinary.ser");
      System.out.println("");

      //System.out.println("Loading policies hash");
      //System.out.println(InsurancePolicy.load("10DecDataLab5.ser"));
      System.out.println("");
      System.out.println("");

      System.out.println("Save insurance Company");
      insuranceCompany.save("InsuranceCompanyBinary.ser", "Admin", "Admin");

      System.out.println("");
      System.out.println("Saving users with policies");
      User.saveTextFile(insuranceCompany.getUsersHash("Admin", "Admin"), "UserText");
      //user1.save(user1.getHashPolices(), null)

      System.out.println("");
      System.out.println("Saving insurance company");
      insuranceCompany.saveTextFile("insuranceCompanyText");

      User.loadTextFile("UserText");
      

      //-----------------------------------------------------------------------------------
    //testing the save and load policies in/from BINARY FILE

    
    try 
    {
      InsurancePolicy.save(insuranceCompany.allPolicies(),"policies.ser");
      HashMap<Integer,InsurancePolicy> policies1 =InsurancePolicy.load("policies.ser");
      System.out.println("Printing a list of policies loaded from binary file");
      InsurancePolicy.printPolicies(policies1);
    }
    catch (IOException e)
    {
      System.err.println(e);
    }
    

    //-----------------------------------------------------------------------------------
    //testing the save and load policies in/from TEXT FILE

    
    try 
    {
      InsurancePolicy.saveTextFile(insuranceCompany.allPolicies(),"policies.txt");
      HashMap<Integer,InsurancePolicy> policies1=InsurancePolicy.loadTextFile("policies.txt");
      System.out.println("Printing a list of policies loaded from Text file");
      InsurancePolicy.printPolicies(policies1);
    }
    catch (IOException e)
    {
      System.err.println(e);
    }
    

    //-----------------------------------------------------------------------------------
    //testing the save and load users in/from BINARY FILE

    
    try 
    {
      User.save(insuranceCompany.getUsersHash("Admin", "Admin"),"users.ser");
      HashMap<Integer,User> users=User.load("users.ser");
      System.out.println("Printing a list of users loaded from binary file");
      System.out.println(users.values());
    }
    catch (IOException e)
    {
      System.err.println(e);
    }
    

    //-----------------------------------------------------------------------------------
    //testing the save and load users in/from TEXT FILE
    
    try 
    {
      User.saveTextFile(insuranceCompany.getUsersHash("Admin", "Admin"),"users.txt");
      HashMap<Integer,User> users=User.loadTextFile("users.txt");
      System.out.println("Printing a list of users loaded from Text file");
      System.out.println(users.values());
    }
    catch (IOException e)
    {
      System.err.println(e);
    }

    
    //-----------------------------------------------------------------------------------
    //testing the save and load InsuranceCompany in/from BINARY FILE
    
    try 
    {
      insuranceCompany.save("company.ser", "Admin", "Admin");
      InsuranceCompany insuranceCompany2=new InsuranceCompany(); // use default constructor
      System.out.println("Starting to add user");
      insuranceCompany2.load("company.ser", "Admin", "Admin");
      System.out.println(insuranceCompany2);
    }
    catch (IOException e)
    {
      System.err.println(e);
    }
  
    //-----------------------------------------------------------------------------------
    //testing the save and load InsuranceCompany in/from Text FILE
    
    
    try 
    {
      insuranceCompany.saveTextFile("company.txt");
      InsuranceCompany insuranceCompany2=new InsuranceCompany();
      insuranceCompany2.loadTextFile("company.txt");
      System.out.println("Printing the isnurance company loaded from text file");
      System.out.println(insuranceCompany2);
      insuranceCompany2.printPolicies(1, "SarasName", "SarasPassword");
    }
    catch (IOException e)
    {
      System.err.println(e);
    }
    

    System.out.println("Testing core features: ");
    System.out.println(insuranceCompany.getUsersPerCity("Admin", "Admin"));

    System.out.println("ExpiredPoliciesHash: ");
    //System.out.println(insuranceCompany.filterByExpiryDate(new MyDate(2024, 1, 21)));

    System.out.println("POlicies for 1");
    insuranceCompany.printPolicies(1, "SarasName", "SarasPassword");
    System.out.println("");
    
    System.out.println("User1 total payments: " +insuranceCompany.findUser(1).calcTotalPremiums(600, "SarasName", "SarasPassword"));

    //I know this works now 
     
    int [] ranges = {200,500,1000,10000};
    int [] returnedArrayUser = insuranceCompany.findUser(1).policyCount("SarasName", "SarasPassword", ranges);
    System.out.println(Arrays.toString(returnedArrayUser));
    

    //Also works, just with the same out of bounds issue on 4
    
    //int [] jim = {200,500,1000,10000};
    int [] returnedArrayCompany = insuranceCompany.policyCount("Admin", "Admin", ranges);
    System.out.println(Arrays.toString(returnedArrayCompany));
    

    //System.out.println(insuranceCompany.policyCityCount("Admin", "Admin"));

      //Also somehwat working
    
    int [] returnedArrayUserCount = insuranceCompany.userCount("Admin", "Admin", ranges);
    System.out.println(Arrays.toString(returnedArrayUserCount));
    


    System.out.println("User PolicyCarModelCount");
    Integer [] modelPricingInteger = {200,500,1000,10000};
    HashMap<String, Integer []> returnedHashModelCount = insuranceCompany.findUser(1).policyCarModelCount("SarasName", "SarasPassword", modelPricingInteger);
    Set<String> carModels = returnedHashModelCount.keySet();
    for (String model: carModels){
      System.out.println("element " + model + ", is: " + Arrays.toString(returnedHashModelCount.get(model)));
    }
    System.out.println();

    
    System.out.println("InsuranceCompany PolicyCarModelCount");
    //Integer [] modelPricing = {200,500,1000,10000};
    HashMap<String, Integer []> returnedHashIC = insuranceCompany.policyCarModelCount("Admin", "Admin", modelPricingInteger);
    Set<String> carModelsIC = returnedHashIC.keySet();
    for (String model: carModelsIC){
      System.out.println("element " + model + ", is: " + Arrays.toString(returnedHashIC.get(model)));
    }
    System.out.println();

    System.out.println("InsuranceCompany cityPolicyCount");
        HashMap<String, int []> PCC = insuranceCompany.policyCityCount("Admin", "Admin", ranges);
        Set<String> PCCKeys = PCC.keySet();
        for (String result: PCCKeys){
          System.out.println(result + ", " + Arrays.toString(PCC.get(result)));
        }
      System.out.println();
    
    
    System.out.println("InsuranceCompany UserCarModelCount");
    Integer [] modelPricing = {200,500,1000,10000};
    HashMap<String, Integer []> returnedHashUCMC = insuranceCompany.userCarModelCount("Admin", "Admin", modelPricing);
    Set<String> carModelsICPR = returnedHashUCMC.keySet();
    for (String model: carModelsICPR){
      System.out.println("element " + model + ", is: " + Arrays.toString(returnedHashUCMC.get(model)));
    }
    System.out.println();

    System.out.println("InsuranceCompany sort users by premium");
    System.out.println(insuranceCompany.sortUsersByPremium());


      } 
    } else if (saveChoice == 2){

      //Working - Returns correct hashMap
      //InsurancePolicy.loadTextFile("Name");
      //System.out.println(InsurancePolicy.loadTextFile("Name"));

      //Working
      insuranceCompany.loadTextFile("InsuranceCompanySave");

      


      
      /* Works
        insuranceCompany.load("InsuranceCompany.ser");
        insuranceCompany.printUsers();

      */
        

        //User stuff - Working
        //Create the user by using the insuranceCompany method, that was easy
        //insuranceCompany.addUser(User.loadTextFile("User1Save"));


        
      }
  }

  public static void saveLoad(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, String aUserName, String aPassword) throws IOException, PolicyException, CloneNotSupportedException, NameException {
    try {
    Scanner input = new Scanner(System.in);
    System.out.println();
    System.out.println("1) Binary methods");
    System.out.println("2) TextFile methods");
    System.out.println();
    System.out.print("Choose an option: ");
    int option = input.nextInt();
    System.out.println();

    if (option == 1){
    System.out.println("1) Save InsuranceCompany as binary");
    System.out.println("2) Load InsuranceCompany as binary");
    System.out.println("3) Save Policies as binary");
    System.out.println("4) Load Policies as binary");
    System.out.println("5) Save Users as binary");
    System.out.println("6) Load Users as binary - Not working");
    System.out.println();
    System.out.print("Choose an option: ");
    int choice = input.nextInt();
    System.out.println();
    System.out.print("Enter a file name .ser: ");
    input.nextLine();
    String fileName = "";
    fileName = input.nextLine();
      if (choice == 1){
        try {
          insuranceCompany.save(fileName, aUserName, aPassword);
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        
      } else if (choice == 2){
        try {
          insuranceCompany.load(fileName, aUserName, aPassword);
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        insuranceCompany.load(fileName, aUserName, aPassword);
      } else if (choice == 3){
        try {
          InsurancePolicy.save(insuranceCompany.getPoliciesHash(), fileName);
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        
      } else if (choice == 4){
        try {
          InsurancePolicy.load(fileName);
        } catch (FileNotFoundException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
      } else if (choice == 5){
        try {
          User.save(insuranceCompany.getUsersHash(aUserName, aPassword), fileName);
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        
      } else if (option == 6){
        try {
          System.out.println(User.load(fileName));
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
      }
    
    } else if (option == 2){
    System.out.println("1) Save InsuranceCompany as text");
    System.out.println("2) Load InsuranceCompany as text");
    System.out.println("3) Save Policies as text");
    System.out.println("4) Load Policies as text");
    System.out.println("5) Save Users as text - Works");
    System.out.println("6) Load Users as text - Does not work");
    System.out.println();
    System.out.print("Choose an option: ");
    int choice = input.nextInt();
    input.nextLine();
    System.out.println();
    System.out.print("Enter a file name .ser");
    String fileName = input.nextLine();

      if (choice == 1){
        insuranceCompany.saveTextFile(fileName);
      } else if (choice == 2){
        try {
          insuranceCompany.loadTextFile(fileName);
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        
      } else if (choice == 3){
        InsurancePolicy.saveTextFile(insuranceCompany.getPoliciesHash(), fileName);
      } else if (choice == 4){
        try {
          System.out.println(InsurancePolicy.loadTextFile(fileName));
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        
      } else if (choice == 5){
        User.saveTextFile(insuranceCompany.getUsersHash(aUserName, aPassword), fileName);
      } else if (option == 6){
          System.out.println();
          try {
          System.out.println(User.loadTextFile(fileName));
        } catch (NoSuchFileException e) {
          System.out.println("A file by that name could not be found: " + e);
        }
        
      }
    }
  } catch (InputMismatchException e){
    System.out.println("This is not a valid input: " + e);
  } catch (NullPointerException e){
    System.out.println("Input error: " + e);
  }
    
  }

  public static void intArrayRanges(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies){
    Scanner input = new Scanner(System.in);

    System.out.println("1) PolicyCount ");
    System.out.println("2) PolicyCityCount ");
    System.out.println("3) UserCount ");
    System.out.println("4) UserCarModelCount ");
    System.out.println("5) PolicyCarModelCount ");
    System.out.println();
    System.out.println("Choose an option: ");
    int choice = input.nextInt();

    if (choice == 1){
      int [] ranges = {200,500,1000,10000};
      int [] returnedArrayCompany = insuranceCompany.policyCount("Admin", "Admin", ranges);
      System.out.println(Arrays.toString(returnedArrayCompany));
    } else if (choice == 2){
        //System.out.println(insuranceCompany.policyCityCount("Admin", "Admin"));
        int [] ranges = {200,500,1000,10000};
        HashMap<String, int []> PCC = insuranceCompany.policyCityCount("Admin", "Admin", ranges);
        Set<String> PCCKeys = PCC.keySet();
        for (String result: PCCKeys){
          System.out.println(result + ", " + Arrays.toString(PCC.get(result)));
        }
    } else if (choice == 3){
      int [] ranges = {200,500,1000,10000};
      int [] returnedArrayUserCount = insuranceCompany.userCount("Admin", "Admin", ranges);
      System.out.println(Arrays.toString(returnedArrayUserCount));
    } else if (choice == 4){
      Integer [] modelPricingInteger = {200,500,1000,10000};
      HashMap<String, Integer []> returnedHashIC = insuranceCompany.policyCarModelCount("Admin", "Admin", modelPricingInteger);
      Set<String> carModelsIC = returnedHashIC.keySet();
      for (String model: carModelsIC){
        System.out.println("element " + model + ", is: " + Arrays.toString(returnedHashIC.get(model)));
      }
      System.out.println();
    } else if (choice == 5){
      Integer [] modelPricing = {200,500,1000,10000};
      HashMap<String, Integer []> returnedHashUCMC = insuranceCompany.userCarModelCount("Admin", "Admin", modelPricing);
      Set<String> carModelsICPR = returnedHashUCMC.keySet();
      for (String model: carModelsICPR){
        System.out.println("element " + model + ", is: " + Arrays.toString(returnedHashUCMC.get(model)));
    }
    }
  }


  
  
}

   

