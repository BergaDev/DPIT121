//Matthew Bergamini - 8137225

import javax.swing.*;
import java.io.IOException;
import java.security.spec.ECFieldF2m;
import java.util.*;

//import Car.CarType;

//import Car.CarType;




public class Main{

  //public static int flatRateVal =500;
  public static double total = 0;

  //AUTO STRING ENTER
  public static int autoTest = 1;
  public static int logOut = 0;
  public static int userID = 0;
  public static int policyID = 0;

  //public static Scanner input = new Scanner(System.in);
  //static InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", new User("Admin", 01, new Address(58, "Admins Address", "Admin Suburb", "Admin City")), "Admin", "Admin", 600);

  public ArrayList<InsurancePolicy> policies = new ArrayList<>();
  public static void main(String[] args) throws IOException, CloneNotSupportedException, PolicyException, NameException{
   Scanner input = new Scanner(System.in);

   ArrayList<InsurancePolicy> policies = new ArrayList<>();
   InsuranceCompany insuranceCompany = new InsuranceCompany("Matthew", new User("Admin", 600, new Address(6, "CompanyStreet", "CompanySuburb", "CompanyCity"), "Admin", "Admin"), "Admin", "Admin", 100);
   //InsuranceCompany insuranceCompany = new InsuranceCompany();
    Boolean notDead = true;

      insuranceCompany.load("Company.ser", "Admin", "Admin");
      //This just creates and loads the frame, needed to display the .form
      SwingUtilities.invokeLater(() -> {
          //This creates the frame, the title is sown in the window title bar, custimiseable
          JFrame frame = new JFrame("MainForm");
          try {
              //This is actually the bit that puts the UI to the frame, .MainPanel being the name of the JFrame declared in the class of MainForm.java
              frame.setContentPane(new MainForm(insuranceCompany).MainPanel);
          } catch (IOException e) {
              throw new RuntimeException(e);
          } catch (CloneNotSupportedException e) {
              throw new RuntimeException(e);
          }
          //These do stuff
          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
      });


  }


 

}
