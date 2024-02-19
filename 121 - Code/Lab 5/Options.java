//Matthew Bergamini - 8137225
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Options {
  
  public static int policyID = 3000000;
  public static int autoTest = 0;
  public static int userID;
  public static int logOut = 0;
  public static Boolean isAdmin = false;

  public static void menus(int choice, InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{

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
       // AdminMenu(insuranceCompany, policies);
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
      UserMenu(insuranceCompany, policies, userID);
    } else {
      System.out.println("");
      System.out.println("This user does not exist, makes sure that you have created a user first");
    }
    
  }

}

public static void AdminMenu(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
  Scanner input = new Scanner(System.in);
  isAdmin = true;

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
      System.out.println("17) Exit Program");
      System.out.println("");

      int option;
      System.out.print("Enter an option: ");
      option = input.nextInt();
      input.nextLine();

      if (option == 1){
        TestCase.testCode();
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
        insuranceCompany.removePolicy(userID, insuranceCompany.findPolicy(userID, removeID));
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
      }
       else if (option == 17){
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


    public static void UserMenu(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException{
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
        insuranceCompany.printPolicies(userID);
        firstTime = false;
      } else if (option == 4){
        updateAddress(insuranceCompany, policies, userID);
        firstTime = false;     
      } else if (option == 2){
        PrintUsers(insuranceCompany, policies, userID);
        PrintPolicies(insuranceCompany, policies, userID);
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
        insuranceCompany.reportPaymentsPerCarModel(insuranceCompany.findUser(userID).distinctCarModels(), insuranceCompany.findUser(userID).getTotalCountPerCarModel(insuranceCompany.findUser(userID).distinctCarModels()), insuranceCompany.findUser(userID).getTotalPaymentPerCarModel(insuranceCompany.findUser(userID).distinctCarModels()));
      } else if (option == 8){
        firstTime = false;
        try {
        System.out.println("");
        input.nextLine();
        System.out.print("Enter a policyID: ");
        int removeID = input.nextInt();
        input.nextLine();
        insuranceCompany.removePolicy(userID, insuranceCompany.findPolicy(userID, removeID));
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            UserMenu(insuranceCompany, policies, userID);
        }
      } else if (option == 9){
        insuranceCompany.findUser(userID).reportPaymentPerModel();
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
   public static void createUser(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
    //Working
    Scanner input = new Scanner(System.in);

    int streetNum;
    String userName, streetName, suburbName, cityName;
    try {
    if (autoTest == 0){
    System.out.print("Enter the name of the user: ");
    userName = input.nextLine();
    userID = (userID + 1);
    System.out.println("User ID is: " + userID);
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
    } else {
      userID = userID + 1;
      userName = "jaramz";
       streetNum = 77;
       streetName = "StreetN";
       suburbName = "SubrubN";
       cityName = "cityN";
    }
    insuranceCompany.addUser(userName, userID, new Address(streetNum, streetName, suburbName, cityName));
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again");
      AdminMenu(insuranceCompany, policies);
  }

  }

  public static void mainMenu(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> insurancePolicies) throws IOException, CloneNotSupportedException, PolicyException{
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
      UserMenu(insuranceCompany, insurancePolicies, thisUser);
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
  public static void PrintPolicies(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID){
    insuranceCompany.printPolicies(userID);
  }


  public static void exit(){
    System.out.println("You have been logged out of the program, have a good day");
    logOut = 1;
    return;
  }

  //Works 100% - Includes inputMis
  public static void createTPP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
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
    if (typeIN == 1){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 2){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    }
  } catch (InputMismatchException e){
    System.err.println("error in Input : "+e);
    System.out.println("Please enter the value again"); 
    AdminMenu(insuranceCompany, policies);
  }
  } else {
    policyID = policyID + 1;
    userID = 1;
    policyHolderName = "JamesPolicy";
    claims = 1;
    comments = "This was done through autotest";
    year = 2022;
    month = 05;
    day = 31;
    carModel = "Tesla Model Y";
    carPrice = 76000;
    manufacturingYear = 2023;
    typeIN = 2;
    if (typeIN == 1){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 2){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createThirdPartyPolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    }
    insuranceCompany.findPolicy(userID, policyID);
  }
  }
  //Includes inputMis
  public static void createTPP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException{
    Scanner input = new Scanner(System.in);
    int thisUser = userID;
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
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 2){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    }
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again"); 
      UserMenu(insuranceCompany, policies, userID);
  }
  } else {
    policyID = policyID + 1;
    policyHolderName = "JamesPolicy";
    claims = 1;
    comments = "This was done through autotest";
    year = 2022;
    month = 05;
    day = 31;
    carModel = "Tesla Model Y";
    carPrice = 76000;
    manufacturingYear = 2023;
    typeIN = 2;
    if (typeIN == 1){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 2){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createThirdPartyPolicy(thisUser, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, comments, new MyDate(year, month, day));
    }
    insuranceCompany.findPolicy(thisUser, policyID);
  }
  }

  //100% - inputMis - Working on the policyID
  public static void createCP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
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
    if (typeIN == 1){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 2){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    }
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again"); 
      AdminMenu(insuranceCompany, policies);
  }
  } else {
    policyID = policyID + 1;
    userID = 2;
    policyHolderName = "JamesComp";
    claims = 0;
    year = 2022;
    month = 05;
    day = 31;
    carModel = "Polestar 2";
    carPrice = 100000;
    manufacturingYear = 2023;
    age = 30;
    level = 1;
    typeIN = 3;
    if (typeIN == 1){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 2){
      System.out.println("We are here");
      System.out.println("Policy ID is " + policyID);
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    }
    System.out.println("Third party creation is done");
    insuranceCompany.findPolicy(userID, policyID);
  }
  }
  //100% - inputMis
  public static void createCP(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException{
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
    if (typeIN == 1){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 2){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    }
  } catch (InputMismatchException e)
  {
      System.err.println("error in Input : "+e);
      System.out.println("Please enter the value again"); 
      UserMenu(insuranceCompany, policies, userID);
  }
  } else {
    policyID = policyID + 1;
    policyHolderName = "JamesComp";
    claims = 0;
    year = 2022;
    month = 05;
    day = 31;
    carModel = "Polestar 2";
    carPrice = 100000;
    manufacturingYear = 2023;
    age = 30;
    level = 1;
    typeIN = 3;
    if (typeIN == 1){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 2){
      System.out.println("We are here");
      System.out.println("Policy ID is " + policyID);
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    } else if (typeIN == 3){
      insuranceCompany.createComprehensivePolicy(userID, policyHolderName, policyID, new Car(Car.CarType.SED, carModel, manufacturingYear, carPrice), claims, age, level, new MyDate(year, month, day));
    }
    System.out.println("Third party creation is done");
    insuranceCompany.findPolicy(userID, policyID);
  }
  }

   
  //Wokring - inputMis
  public static void filterCarModel(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
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
        InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(userID, carModel), insuranceCompany.flatRate);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
        } else {
          userID = 2;
          carModel = "Polestar 2";
          InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(userID, carModel), insuranceCompany.flatRate);
          }
    }


  }

   public static void filterCarModel(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException{
    Scanner input = new Scanner(System.in);


    String carModel;

      if(autoTest == 0){
        try {
        System.out.print("Enter a model of car to search for: ");
        carModel = input.nextLine();
        InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(userID, carModel), insuranceCompany.flatRate);
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
        } else {
          userID = 2;
          carModel = "Polestar 2";
          InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(userID, carModel), insuranceCompany.flatRate);
          }
    }
    
  //Working - inputMis
  public static void filterExpiryDate(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
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

        InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(userID, new MyDate(year, month, day)));
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
      }

    } else if (autoTest == 1){
      year = 2022;
      month = 5;
      day = 31;
      InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(new MyDate(year, month, day)));
    }
  }
//Working
   public static void filterExpiryDate(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException{
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

        InsurancePolicy.printPolicies(insuranceCompany.filterByExpiryDate(userID, new MyDate(year, month, day)));
        } catch (InputMismatchException e)
        {
            System.err.println("error in Input : "+e);
            System.out.println("Please enter the value again"); 
            AdminMenu(insuranceCompany, policies);
        }
      }

    } 
  

  //Working - inputMis
  public static void updateAddress(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
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
  public static void updateAddress(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies, int userID) throws IOException, CloneNotSupportedException, PolicyException{
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
  public static void outOfBounds(InsuranceCompany insuranceCompany, ArrayList<InsurancePolicy> policies) throws IOException, CloneNotSupportedException, PolicyException{
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
  
}

   

