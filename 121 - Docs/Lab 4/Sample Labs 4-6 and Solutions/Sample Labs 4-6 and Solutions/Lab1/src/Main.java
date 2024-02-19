
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main 
{

    public static void main(String[] args) throws CloneNotSupportedException 
    {
        Car car1 = new Car (1, "Mercedes-Benz GLE 450", 15000, CarType.SUV);
        Car car2 = new Car (2, "Cadillac XT6", 12000, CarType.SUV);
        Car car3 = new Car (3, "Ford Explorer", 10000, CarType.SUV);
        Car car4 = new Car (4, "Porsche 911", 20000, CarType.Sport);
        Car car5 = new Car (5, "Nissan GT-R", 19000, CarType.Sport);
        Car car6 = new Car (6, "McLaren 570S", 21000, CarType.Sport);
        Car car7 = new Car (7, "Toyota Corolla", 10000, CarType.Sedan);
        Car car8 = new Car (8, "BMW M340", 13000, CarType.Sedan);
        Car car9 = new Car (9, "Hyundai Elantra", 14000, CarType.Sedan);
        Car car10 = new Car (10, "Jaguar XJ", 10000, CarType.Luxury);
        Car car11 = new Car (11, "BMW X7", 13000, CarType.Luxury);
        Car car12 = new Car (12, "Audi A8", 14000, CarType.Luxury);
        
        PostCode pc1 = new PostCode ("Keiraville", "Wollongong", "NSW");
        PostCode pc2 = new PostCode ("Darlington", "Sydney", "NSW");
        PostCode pc3 = new PostCode ("Ashfield", "Perth", "NSW");
        
        Address ad1 = new Address (1, "Irvine Rd", pc1, "Australia");
        Address ad2 = new Address (3, "Vine St", pc2, "Australia");
        Address ad3 = new Address (5, "Milton St", pc3, "Australia");
        
        Driver dr1 = new Driver ("Park", "ChanYeol", 1, ad1);
        Driver dr2 = new Driver ("Kim", "Dan", 2, ad2);
        Driver dr3 = new Driver ("Lee", "YeonSo", 3, ad3);
        
        //add car to driver's list of cars
        dr1.addCar(car1);
        dr1.addCar(car4);
        dr1.addCar(car7);
        dr1.addCar(car10);
        
        dr2.addCar(car2);
        dr2.addCar(car5);
        dr2.addCar(car8);
        dr2.addCar(car11);
        
        dr3.addCar(car3);
        dr3.addCar(car6);
        dr3.addCar(car9);
        dr3.addCar(car12);
        
        //ARRAYLIST OF DRIVERS
        ArrayList<Driver> drivers = new ArrayList<> ();
        drivers.add(dr1);
        drivers.add(dr2);
        drivers.add(dr3);
        
//        //1. EMPTY ARRAYLIST FOR SHALLOW COPY
//        ArrayList<Driver> shallowCopy = new ArrayList<> ();
//        Driver.shallowCopy(drivers, shallowCopy);
//        
//        //2. EMPTY ARRAYLIST FOR DEEP COPY WITH COPY CONSTRUCTOR
//        ArrayList<Driver> deepCopy1 = new ArrayList<> ();
//        Driver.deepCopyByUsingCopyConstructor(drivers, deepCopy1);
//        
//        //3. EMPTY ARRAYLIST FOR DEEP COPY WITH CLONE()
//        ArrayList<Driver> deepCopy2 = new ArrayList<> ();
//        Driver.deepCopyByUsingClone(drivers, deepCopy2);
//        
//        System.out.println("Original List");
//        Driver.printDrivers(drivers);
//        System.out.println();
//        System.out.println();
//        System.out.println("Shallow Copy List");
//        Driver.printDrivers(shallowCopy);
//        System.out.println();
//        System.out.println();
//        System.out.println("Deep Copy List by copy constructor");
//        Driver.printDrivers(deepCopy1);
//        System.out.println();
//        System.out.println();
//        System.out.println("Deep Copy List by clone()");
//        Driver.printDrivers(deepCopy2);
//        System.out.println();
//        System.out.println();
//        
//        //CHANGE CITY OF 1 DRIVER
//        drivers.get(0).setCity("Badung");
//        System.out.println("***************After changing***************");
//        System.out.println("Original List");
//        Driver.printDrivers(drivers);
//        System.out.println();
//        System.out.println();
//        System.out.println("Shallow Copy List");
//        Driver.printDrivers(shallowCopy);
//        System.out.println();
//        System.out.println();
//        System.out.println("Deep Copy List by copy constructor");
//        Driver.printDrivers(deepCopy1);
//        System.out.println();
//        System.out.println();
//        System.out.println("Deep Copy List by clone()");
//        Driver.printDrivers(deepCopy2);
//        System.out.println();
//        System.out.println();
//        
//        
//        Collections.sort(drivers);
//        System.out.println("***************After sorting***************");
//        System.out.println("Original List");
//        Driver.printDrivers(drivers);
//        System.out.println();
//        System.out.println();
//        System.out.println("Shallow Copy List");
//        Driver.printDrivers(shallowCopy);
//        System.out.println();
//        System.out.println();
//        System.out.println("Deep Copy List by copy constructor");
//        Driver.printDrivers(deepCopy1);
//        System.out.println();
//        System.out.println();
//        System.out.println("Deep Copy List by clone()");
//        Driver.printDrivers(deepCopy2);
//        System.out.println();
//        System.out.println();
        
        options(drivers);
    }
    
    public static Driver findDriver (ArrayList<Driver> drivers, int driverID)
    {
        for (Driver driver : drivers)
        {
            if (driver.getID()==driverID)
            {
                return driver;
            }
        }
        return null;
    }
    
    public static void displayOptions ()
    {
        System.out.println ("<3 <3 My console application <3 <3");
        System.out.println ("1. Create New Driver");
        System.out.println ("2. Do these things:");
        System.out.println ("\t\tCreate a shallow copy \n\t\tdeep copy (copy constructor) \n\t\tdeep copy (clone()) \n\t\tsorts the original list based on city in descending order");
        System.out.println ("3. Print the original list, its shallow copy, and its deep copies (2)");
        System.out.println ("4. Find driver and change his/her city");
        System.out.println("5. Add new car for a driver");
        System.out.println("6. Exit");
        System.out.println("Enter 1-5");
    }
    
    public static Driver getNewDriverInformation ()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("CREATE NEW DRIVER");
        System.out.println("Enter ID: ");
        int id = sc.nextInt();
        System.out.println("Enter first name: ");
        String fname = sc.next();
        System.out.println("Enter last name: ");
        String lname = sc.next();
        System.out.println("Enter street number: ");
        int streetNum = sc.nextInt();
        System.out.println("Enter street name: ");
        String streetName = sc.next();
        System.out.println("Enter suburb");
        String suburb = sc.next();
        System.out.println("Enter city");
        String city = sc.next();
        System.out.println("Enter state: ");
        String state = sc.next();
        System.out.println("Enter country: ");
        String country = sc.next();
        PostCode pc = new PostCode (suburb, city, state);
        Address addr = new Address (streetNum, streetName, pc, country);
        Driver drv = new Driver (fname, lname, id, addr);
        
        System.out.println("Adding cars to your list of cars");
        System.out.println("How many cars do you have?");
        int num = sc.nextInt();
        for (int i=1; i<=num; i++)
        {
            System.out.println("Enter car id: ");
            int carID=sc.nextInt();
            System.out.println("Enter car model: ");
            String carModel=sc.next();
            System.out.println("Enter car price: ");
            double carPrice=sc.nextDouble();
            System.out.println("Enter Car Type : (suv, sport, sedan, luxury)");
            String ct = sc.next();
            CarType carType = CarType.valueOf(ct);
            Car car = new Car (carID, carModel, carPrice, carType);
            drv.addCar(car);
        }
        return drv;
    }
    
    public static void findDriverAndDeepCopyAndChangeCity (ArrayList<Driver> drivers, int drvID) throws CloneNotSupportedException
    {
        Scanner sc = new Scanner (System.in);
        Driver drver = findDriver (drivers, drvID);
        Driver drvClone=null;
        if (drver!=null)
        {
            System.out.println("Make a deep copy of this driver (by using clone())");
            drvClone = drver.clone();
            System.out.println("Change the city of this driver");
            System.out.println("Enter new city: ");
            String newCity = sc.next();
            drver.setCity(newCity);
            System.out.println(drver);
            System.out.println(drvClone);
        }
        else
            System.out.println("Sorry, driver with the given ID is not available");
    }
    
    public static void findDriverAndDeepCopyAndAddCar (ArrayList<Driver> drivers, int drvID)
    {
        Scanner sc = new Scanner (System.in);
        Driver drver2 = findDriver (drivers, drvID);
        Driver drverCopyCons = null;
        if(drver2!=null)
        {
            System.out.println("Make a deep copy of this driver (by using copy constructor");
            drverCopyCons = new Driver (drver2);

            System.out.println("Add new car to this driver");
            System.out.println("Enter car id: ");
            int carID=sc.nextInt();
            System.out.println("Enter car model: ");
            String carModel=sc.next();
            System.out.println("Enter car price: ");
            double carPrice=sc.nextDouble();
            System.out.println("Enter Car Type : (SUV, Sport, Sedan, Luxury)");
            String ct = sc.next();
            CarType carType = CarType.valueOf(ct);

            Car car = new Car (carID, carModel, carPrice, carType);
            drver2.addCar(car);

            System.out.println(drver2);
            System.out.println(drverCopyCons);
        }
        else
            System.out.println("Sorry, driver with the given ID is not available");
    }
    
    public static void options (ArrayList<Driver> drivers) throws CloneNotSupportedException
    {
        Scanner sc = new Scanner (System.in);
        int option=0;
        ArrayList<Driver> shallowCopy = new ArrayList<> ();
        ArrayList<Driver> deepCopyCC = new ArrayList<> ();
        ArrayList<Driver> deepCopyC = new ArrayList<> ();
        do
        {
            displayOptions ();
            option=sc.nextInt();
            switch (option)
            {
                case 1: 
                    Driver drv = getNewDriverInformation();
                    drivers.add(drv);
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("MAKE SHALLOW COPY, DEEP COPY BY USING COPY CONSTRUCTOR, DEEP COPY BY USING CLONE()");
                    shallowCopy = Driver.shallowCopy(drivers);
                    deepCopyCC = Driver.deepCopyByUsingCopyConstructor(drivers);
                    deepCopyC = Driver.deepCopyByUsingClone(drivers);
                    Collections.sort(drivers);
                    System.out.println("SUCCESS :)");
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 3:
                    System.out.println("PRINT ORIGINAL LIST, SHALLOW COPY LIST, DEEP COPY COPY CONSTRUCTOR LIST, AND DEEP COPY CLONE() LIST");
                    System.out.println("***Original List***");
                    Driver.printDrivers(drivers);
                    System.out.println("\n***Shallow copy List***");
                    Driver.printDrivers(shallowCopy);
                    System.out.println("\n***Deep copy (copy constructor) List***");
                    Driver.printDrivers(deepCopyCC);
                    System.out.println("\n***Deep copy (clone()) List***");
                    Driver.printDrivers(deepCopyC);
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("FIND DRIVER AND CHANGE HIS/HER CITY");
                    System.out.println("Enter Driver ID: ");
                    int drvID = sc.nextInt();
                    findDriverAndDeepCopyAndChangeCity(drivers, drvID);
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 5:
                    System.out.println("FIND DRIVER AND ADD NEW CAR");
                    System.out.println("Enter Driver ID: ");
                    int drvID2 = sc.nextInt();
                    findDriverAndDeepCopyAndAddCar (drivers, drvID2);
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Sorry, Wrong Option");
            }
        }while(option!=6);
    }
}
