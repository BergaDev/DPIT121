package UserInterface;

import java.util.Scanner;

/**
 *
 * @author hooman
 */
public class UserInterface 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
          mainMenu();
        // TODO code application logic here
    }
    public static void displayMainMenu()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println(" 1) User Login");
        System.out.println(" 2) Admin Login");
        System.out.println(" 3) User Report");
        System.out.println(" 4) exit");
        System.out.println("\n\n Please choose an option from 1 to 4");
        
        
    }
    public static void mainMenu()
    {
        Scanner sc=new Scanner(System.in);
        int option=0;
        //while(true)
        while(option!=4)
            {  
                displayMainMenu();
                option=sc.nextInt();
                switch (option)
                    {
                        case 1:
                           //System.out.println("\n\n I am Main option 1\n\n");
                            userMenu();
                            break;
                        case 2:
                           System.out.println("\n\n I am Main option 2\n\n");
                           System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                           break;
                        case 3:
                           System.out.println("\n\n I am  Main option 3\n\n");
                           System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                           break;
                        case 4:
                           //System.exit(0);
                           break;
                        default:
                            System.out.println("\n\n Wrong option\n\n");   
                    }
            }
        
    }
    
    public static void displayUserMenu()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println(" 1) Add Employee");
        System.out.println(" 2) Remove Employee");
        System.out.println(" 3) Show report");
        System.out.println(" 4) Back");
        System.out.println("\n\n Please choose an option from 1 to 4");
     
    }
    public static void userMenu()
    {
        Scanner sc=new Scanner(System.in);
        int option=0;
        //while(true)
        while(option!=4)
            {  
                displayUserMenu();
                option=sc.nextInt();
                switch (option)
                    {
                        case 1:
                           System.out.println("\n\n  I am User option 1\n\n");
                            System.out.println("Press enter to continue");
                             sc.nextLine();
                             sc.nextLine();
                            break;
                        case 2:
                           System.out.println("\n\n I am User option 2\n\n");
                           System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                           break;
                        case 3:
                           System.out.println("\n\n I am User option 3\n\n");
                           System.out.println("Press enter to continue");
                           sc.nextLine();
                           sc.nextLine();
                           break;
                        case 4:
                           //System.exit(0);
                           return;
                        default:
                            System.out.println("\n\n Wrong option\n\n");   
                    }
            }        
    }
    
}

