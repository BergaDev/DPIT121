
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main 
{
    public static void main(String[] args) throws CloneNotSupportedException, DriverException 
    {
        Car car1 = new Car (1, "Mercedes-Benz GLE 450", 15000, CarType.SUV);
        Car car2 = new Car (2, "Mercedes-Benz GLE 450", 12000, CarType.SUV);
        Car car3 = new Car (3, "Ford Explorer", 10000, CarType.SUV);
        Car car4 = new Car (4, "Porsche 911", 20000, CarType.Sport);
        Car car5 = new Car (5, "Nissan GT-R", 19000, CarType.Sport);
        Car car6 = new Car (6, "Nissan GT-R", 21000, CarType.Sport);
        Car car7 = new Car (7, "Toyota Corolla", 10000, CarType.Sedan);
        Car car8 = new Car (8, "BMW M340", 13000, CarType.Sedan);
        Car car9 = new Car (9, "Toyota Corolla", 14000, CarType.Sedan);
        Car car10 = new Car (10, "Jaguar XJ", 10000, CarType.Luxury);
        Car car11 = new Car (11, "BMW X7", 13000, CarType.Luxury);
        Car car12 = new Car (12, "BMW X7", 14000, CarType.Luxury);
        
        PostCode pc1 = new PostCode ("Keiraville", "Wollongong", "NSW");
        PostCode pc2 = new PostCode ("Darlington", "Sydney", "NSW");
        PostCode pc3 = new PostCode ("Ashfield", "Perth", "NSW");
        
        Address ad1 = new Address (1, "Irvine Rd", pc1, "Australia");
        Address ad2 = new Address (3, "Vine St", pc2, "Australia");
        Address ad3 = new Address (5, "Milton St", pc3, "Australia");
        
        MyDate date1 = new MyDate (2019, Month.June, 23);
        MyDate date2 = new MyDate (2019, Month.July, 22);
        MyDate date3 = new MyDate (2019, Month.May, 24);
        
        
        Driver dr1 = new Driver ("Park", "ChanYeol", 3000001, ad1, date1);
        Driver dr2 = new Driver ("Kim", "Dan", 3000002, ad2, date2);
        Driver dr3 = new Driver ("Lee", "YeonSo", 3000003, ad3, date3);
        
        
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
        
        /*
        //ARRAYLIST OF DRIVERS
        ArrayList<Driver> drivers = new ArrayList<> ();
        drivers.add(dr1);
        drivers.add(dr2);
        drivers.add(dr3);
        
        Driver.printDrivers(drivers);
        
        //Test code for sorting the list of drivers based on his/her lisence date (GOOD)
        Collections.sort(drivers);
        Driver.printDrivers(drivers);
        */
        
        //INSTEAD OF HAVING ARRAYLIST OF DRIVER, CREATE HASHMAP OF DRIVER
        HashMap<Integer, Driver> drivers = new HashMap<> ();
        drivers.put(dr1.getID(), dr1);
        drivers.put(dr2.getID(), dr2);
        drivers.put(dr3.getID(), dr3);
        
        /*
        System.out.println("\nREPORT 1: TOTAL DRIVER PER CITY");
        HashMap<String, Integer> reportTotalDriverPerCity = getTotalDriverCountPerCity(drivers);
        printReportTotalDriverCountPerCity(reportTotalDriverPerCity);
        
        
        System.out.println("\nREPORT 2: TOTAL CAR PRICE PER CITY");
        HashMap<String, Double> reportTotalCarPricePerCity = getTotalCarPricePerCity(drivers);
        printReportTotalCarPricePerCity(reportTotalCarPricePerCity);
        
        
        System.out.println("\nREPORT 3: TOTAL CAR PRICE PER CAR MODEL (DRIVER 1)");
        HashMap<String, Double> report3 = dr1.getTotalPricePerCarModel();
        dr1.printReportTotalPricePerCarModel(report3);
        
        
        System.out.println("\nREPORT 4: TOTAL CAR PRICE PER CAR MODEL (ALL CARS IN THE SYSTEM)");
        HashMap<String, Double> report4 = getTotalCarPricePerCarModel(drivers);
        printReportTotalCarPricePerCarModel(report4);
        */
        
        /*
        ArrayList<Driver> shallowCopy = Driver.shallowCopy(drivers);
        Collections.sort(shallowCopy);
        for(Driver drv : shallowCopy)
        {
            System.out.println(drv);
        }
        */
        
        //UI
        options(drivers);
        
    }
    
    public static HashMap<String, Integer> getTotalDriverCountPerCity (HashMap<Integer, Driver> drivers)//REPORT 1: GET TOTAL DRIVER PER CITY
    {
        HashMap<String, Integer> cities = new HashMap<> ();
        for (Driver drv : drivers.values())
        {
            Integer count = cities.get(drv.getCity());
            if(count!=null)
            {
                cities.put(drv.getCity(), count+1);
            }
            else
            {
                cities.put(drv.getCity(), 1);
            }
        }
        return cities;
    }
    
    public static void printReportTotalDriverCountPerCity (HashMap<String, Integer> report) //REPORT 1: PRINT THE REPORT
    {
        for (String city: report.keySet())
        {
            System.out.println(city+" "+report.get(city));
        }
        System.out.println();
    }
    
    public static HashMap<String, Double> getTotalCarPricePerCity (HashMap<Integer, Driver> drivers) //REPORT 2: GET TOTAL CAR PRICE PER CITY
    {
        HashMap<String, Double> cities = new HashMap<> ();
        for (Driver drv : drivers.values())
        {
            Double bal = cities.get(drv.getCity());
            if(bal!=null)
            {
                cities.put(drv.getCity(), bal.doubleValue()+drv.calcValueOfCars());
            }
            else
            {
                cities.put(drv.getCity(), drv.calcValueOfCars());
            }
        }
        return cities;
    }
    
    public static void printReportTotalCarPricePerCity (HashMap<String, Double> report) //REPORT 2: PRINT THE REPORT
    {
        for (String city: report.keySet())
        {
            System.out.println(city+" "+report.get(city));
        }
        System.out.println();
    }
    
    public static HashMap<String, Double> getTotalCarPricePerCarModel (HashMap<Integer,Driver> drivers) //REPORT 3: GET TOTAL CAR PRICE PER CAR MODEL
    {
        HashMap<String, Double> models = new HashMap<> ();
        for(Driver drv : drivers.values())
        {
            HashMap<String, Double> modelsDriver = drv.getTotalPricePerCarModel();
            for (String mdl : modelsDriver.keySet())
            {
                Double price = models.get(mdl);
                if(price!=null)
                {
                    models.put(mdl, price+modelsDriver.get(mdl));
                }
                else
                {
                    models.put(mdl, modelsDriver.get(mdl));
                }
            }
        }
        return models;
    }
    
    public static void printReportTotalCarPricePerCarModel (HashMap<String, Double> models) //REPORT 3: PRINT THE REPORT
    {
        for (String model: models.keySet())
        {
            System.out.println(model+" "+models.get(model));
        }
        System.out.println();
    }
    
    
    public static Driver findDriver (HashMap<Integer,Driver> drivers, int driverID) // FIND DRIVER
    {
        return drivers.get(driverID);
    }
    
    public static void displayOptions ()
    {
        System.out.println ("<3 <3 My console application <3 <3");
        System.out.println ("1. Create New Driver");
        System.out.println ("2. Shallow, Deep Copies and Sort The Shallow Copy");
        System.out.println ("3. Print the original list, its shallow copy, and its deep copies (2)");
        System.out.println ("4. Find driver and change his/her city");
        System.out.println ("5. Add new car for a driver");
        System.out.println ("6. Report: Number of drivers per city");
        System.out.println ("7. Report: Total car price per city");
        System.out.println ("8. Report: Total car price per car model");
        System.out.println ("9. Exit");
        System.out.println ("Enter 1-8");
    }
    
    public static Driver getNewDriverInformation () throws DriverException // GET NEW DRIVER INFORMATION
    {
        Scanner sc = new Scanner (System.in); 
        System.out.println("CREATE NEW DRIVER");
        System.out.println("Enter ID: ");
        //int id=sc.nextInt();
        int id=0;
        while (id==0)
        {
            try
            {
                id=sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = sc.next();
                System.out.println("Enter integer please :)\nEnter ID: ");
            }
        }
        System.out.println("Enter first name: ");
        String fname = sc.nextLine();
        System.out.println("Enter last name: ");
        String lname = sc.nextLine();
        System.out.println("Enter street number: ");
        //int streetNum = sc.nextInt();
        int streetNum=0;
        while (streetNum==0)
        {
            try
            {
                streetNum=sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = sc.next();
                System.out.println("Enter integer please :)\nEnter street number: ");
            }
        }
        System.out.println("Enter street name: ");
        String streetName = sc.nextLine();
        System.out.println("Enter suburb");
        String suburb = sc.nextLine();
        System.out.println("Enter city");
        String city = sc.nextLine();
        System.out.println("Enter state: ");
        String state = sc.nextLine();
        System.out.println("Enter country: ");
        String country = sc.nextLine();
        System.out.println("Enter Lisence day: (int)");
        //int day = sc.nextInt();
        int day=0;
        while (day==0)
        {
            try
            {
                day=sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = sc.next();
                System.out.println("Enter integer please :)\nEnter Lisence day: (int)");
            }
        }
        System.out.println("Enter Lisence month: (January, February, March, April, May, June, July, August, September, October, November, December)");
        String mth = sc.next();
        Month month = Month.valueOf(mth);
        
        System.out.println("Enter Lisence year: (int)");
        //int year = sc.nextInt();
        int year=0;
        while (year==0)
        {
            try
            {
                year=sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                String badInput = sc.next();
                System.out.println("Enter integer please :)\nEnter Lisence year: (int)");
            }
        }
        MyDate md = new MyDate (year, month, day);
        PostCode pc = new PostCode (suburb, city, state);
        Address addr = new Address (streetNum, streetName, pc, country);

        Driver drv=null;
        try
        {
            drv = new Driver (fname, lname, id, addr, md);
        }
        catch (DriverException e)
        {
            drv = new Driver (fname, lname, e.getNewID(), addr, md);
            System.out.println(e);
        }

        System.out.println("Adding cars to your list of cars");
        System.out.println("Enter 'Y' or 'y' to add more car OR enter 'S' or's' to stop");
        String ans = sc.next();
        while(ans.equals("Y")||ans.equals("y"))
        {
            System.out.println("Enter car id: ");
            //int carID=sc.nextInt();
            int carID=0;
            while (carID==0)
            {
                try
                {
                    carID=sc.nextInt();
                    sc.nextLine();
                }
                catch (InputMismatchException e)
                {
                    String badInput = sc.next();
                    System.out.println("Enter integer please :)\nEnter car id: ");
                }
            }
            System.out.println("Enter car model: ");
            String carModel=sc.next();
            System.out.println("Enter car price: ");
            //double carPrice=sc.nextDouble();
            double carPrice=0;
            while (carPrice==0)
            {
                try
                {
                    carPrice=sc.nextDouble();
                    sc.nextLine();
                }
                catch (InputMismatchException e)
                {
                    String badInput = sc.next();
                    System.out.println("Enter integer please :)\nEnter car price: ");
                }
            }
            System.out.println("Enter Car Type : (SUV, Sport, Sedan, Luxury)");
            String ct = sc.next();
            CarType carType = CarType.valueOf(ct);
            Car car = new Car (carID, carModel, carPrice, carType);
            drv.addCar(car);
            System.out.println("Enter 'Y' or 'y' to add more car OR enter 'S' or's' to stop");
            ans = sc.next();
        }
        return drv;
    }
    
    public static void findDriverAndDeepCopyAndChangeCity (HashMap<Integer,Driver> drivers, int drvID) throws CloneNotSupportedException //FIND DRIVER AND DEEP COPY AND CHANGE CITY
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
            System.out.println("***Original***\n"+drver);
            System.out.println("***Deep Copy (Clone)***\n"+drvClone);
        }
        else
            System.out.println("Sorry, driver with the given ID is not available");
    }
    
    public static void findDriverAndDeepCopyAndAddCar (HashMap<Integer,Driver> drivers, int drvID)
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

            System.out.println("***Original***\n"+drver2);
            System.out.println("***Deep Copy (Copy Constructor)***\n"+drverCopyCons);
        }
        else
            System.out.println("Sorry, driver with the given ID is not available");
    }
    
    public static void addDriver (HashMap<Integer,Driver> drivers, Driver drv)
    {
        if(findDriver(drivers,drv.getID())==null)
        {
            drivers.put(drv.getID(), drv);
            System.out.println("Add driver was successful");
        }
        else
            System.out.println("Add driver was NOT successful");   
    }
    
    public static void options (HashMap<Integer,Driver> drivers) throws CloneNotSupportedException, DriverException
    {
        Scanner sc = new Scanner (System.in);
        int option=0;
        ArrayList<Driver> shallowCopy = new ArrayList<> ();
        ArrayList<Driver> deepCopyCC = new ArrayList<> ();
        ArrayList<Driver> deepCopyC = new ArrayList<> ();
        while(true)
        {
            try
            {
                do
                {
                    displayOptions ();
                    option=sc.nextInt();
                    switch (option)
                    {
                        case 1:
                            Driver drv = getNewDriverInformation();
                            addDriver(drivers, drv);
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                            break;
                        case 2:
                            System.out.println("MAKE SHALLOW COPY, DEEP COPY BY USING COPY CONSTRUCTOR, DEEP COPY BY USING CLONE() AND SORT THE SHALLOW COPY");
                            shallowCopy = Driver.shallowCopy(drivers);
                            deepCopyCC = Driver.deepCopyByUsingCopyConstructor(drivers);
                            deepCopyC = Driver.deepCopyByUsingClone(drivers);
                            Collections.sort(shallowCopy);
                            System.out.println("SUCCESS :)");
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                            break;
                        case 3:
                            System.out.println("PRINT ORIGINAL LIST, SHALLOW COPY LIST, DEEP COPY COPY CONSTRUCTOR LIST, AND DEEP COPY CLONE() LIST");
                            System.out.println("*****************************Original List*********************************");
                            Driver.printDrivers(drivers);
                            System.out.println("\n*****************************Shallow copy List*****************************");
                            Driver.printDrivers(shallowCopy);
                            System.out.println("\n******************************Deep copy (copy constructor) List*****************************");
                            Driver.printDrivers(deepCopyCC);
                            System.out.println("\n*****************************Deep copy (clone()) List*****************************");
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
                            System.out.println("REPORT: LIST OF ALL CITIES AND TOTAL NUMBER OF DRIVERS PER CITY");
                            HashMap<String, Integer> report1=getTotalDriverCountPerCity(drivers);
                            printReportTotalDriverCountPerCity(report1);
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                            break;
                        case 7:
                            System.out.println("REPORT: LIST OF ALL CITIES AND TOTAL CAR PRICE PER CITY");
                            HashMap<String, Double> report2 = getTotalCarPricePerCity(drivers);
                            printReportTotalCarPricePerCity(report2);
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                            break;
                        case 8:
                            System.out.println("REPORT: LIST OF ALL CAR MODELS AND TOTAL CAR PRICE PER CAR MODEL");
                            HashMap<String, Double> report3 = getTotalCarPricePerCarModel(drivers);
                            printReportTotalCarPricePerCarModel(report3);
                            System.out.println("Press enter to continue");
                            sc.nextLine();
                            sc.nextLine();
                            break;
                        case 9:
                            return;
                        default:
                            System.out.println("Sorry, Wrong Option");
                    }
                }while(option!=9);
            }
            catch (InputMismatchException e)
            {
                System.out.println("Wrong input, press anything to continue");
                sc.next();
            }
        }
    }
}
