
import java.util.ArrayList;
import java.util.HashMap;


public class Driver implements Cloneable, Comparable<Driver>
{
    private String firstName;
    private String lastName;
    private int driverID;
    private Address address;
    private MyDate lisenceDate;
    private ArrayList<Car> cars;
    
    public Driver (String fname, String lname, int id, Address addr, MyDate lisenceDate) throws DriverException
    {
        this.firstName=fname;
        this.lastName=lname;
        if(id<3000000 || id>3999999)
        {
            throw new DriverException(id);
        } 
        this.driverID=id;
        this.address=addr;
        this.lisenceDate=lisenceDate;
        this.cars= new ArrayList<> ();
    }
    
    public Driver (Driver driver)
    {
        this.firstName=driver.firstName;
        this.lastName=driver.lastName;
        this.driverID=driver.driverID;
        this.address=new Address (driver.address);
        this.lisenceDate=new MyDate (driver.lisenceDate);
        this.cars=new ArrayList<> ();
        for(Car car : driver.cars)
        {
            cars.add(new Car (car));
        }
    }
    
    public int getID ()
    {
        return driverID;
    }
    
    public void setID (int newID)
    {
        driverID=newID;
    }
    
    public String getCity ()
    {
        return address.getCity();
    }
    
    public void setCity (String newCity)
    {
        address.setCity(newCity);
    }
    
    public double calcValueOfCars ()
    {
        double value=0;
        for (Car car : cars)
        {
            value+=car.getPrice();
        }
        return value;
    }
    
    @Override
    public String toString ()
    {
        String output= driverID+", "+firstName+" "+lastName+"\nAddress:"+address+"\nLisence date: "+lisenceDate+"\n\n";
        output+="List of Cars:\n";
        for(Car car : cars)
        {
            output+=car+"\n";
        }
        return output;
    }
    
    public void addCar (Car car)
    {
        cars.add(car);
    }
    
    public HashMap<String, Double> getTotalPricePerCarModel () //REPORT 3: GET TOTAL PRICE PER CAR MODEL
    {
        HashMap<String, Double> models = new HashMap<> ();
        for (Car car : cars)
        {
            Double price = models.get(car.getCarModel());
            if(price!=null)
            {
                models.put(car.getCarModel(), price.doubleValue()+car.getPrice());
            }
            else
            {
                models.put(car.getCarModel(), car.getPrice());
            }
        }
        return models;
    }
    
    public void printReportTotalPricePerCarModel (HashMap<String, Double> models) //REPORT 3: PRINT THE REPORT
    {
        for(String model : models.keySet())
        {
            System.out.println(model+": "+models.get(model));
        }
        System.out.println();
    }
    
    /*
    public static void shallowCopy(ArrayList<Driver> drivers, ArrayList<Driver> shallowCopy)
    {
        shallowCopy.clear();
        for (Driver drv : drivers)
        {
            shallowCopy.add(drv);
        }
    }
    
    public static ArrayList<Driver> shallowCopy (ArrayList<Driver> drivers)
    {
        ArrayList<Driver> shallowCopy = new ArrayList<> ();
        for(Driver drv : drivers)
        {
            shallowCopy.add(drv);
        }
        return shallowCopy;
    }
    */
    
    public static ArrayList<Driver> shallowCopy (HashMap<Integer, Driver> drivers)
    {
        ArrayList<Driver> shallowCopy = new ArrayList<> ();
        for(Driver drv : drivers.values())
        {
            shallowCopy.add(drv);
        }
        return shallowCopy;
    }
    
    /*
    public static void deepCopyByUsingCopyConstructor (ArrayList<Driver> drivers, ArrayList<Driver> deepCopy)
    {
        deepCopy.clear();
        for(Driver drv : drivers)
        {
            deepCopy.add(new Driver (drv));
        }
    }
    
    public static ArrayList<Driver> deepCopyByUsingCopyConstructor (ArrayList<Driver> drivers)
    {
        ArrayList<Driver> deepCopyCC = new ArrayList<> ();
        for(Driver drv : drivers)
        {
            deepCopyCC.add(new Driver (drv));
        }
        return deepCopyCC;
    }
    */
    
    public static ArrayList<Driver> deepCopyByUsingCopyConstructor (HashMap<Integer, Driver> drivers)
    {
        ArrayList<Driver> deepCopyCC = new ArrayList<> ();
        for(Driver drv : drivers.values())
        {
            deepCopyCC.add(new Driver (drv));
        }
        return deepCopyCC;
    }
    
    @Override
    public Driver clone() throws CloneNotSupportedException
    {
        Driver output = (Driver) super.clone();
        output.address=address.clone();
        output.lisenceDate=lisenceDate.clone();
        output.cars=new ArrayList<Car>();
        for (Car car : cars)
        {
            output.cars.add(car.clone());
        }
        return output;
    }
    
    /*
    public static void deepCopyByUsingClone (ArrayList<Driver> drivers, ArrayList<Driver> deepCopy) throws CloneNotSupportedException
    {
        deepCopy.clear();
        for (Driver driver : drivers)
        {
            deepCopy.add(driver.clone());
        }
    }
    
    public static ArrayList<Driver> deepCopyByUsingClone (ArrayList<Driver> drivers) throws CloneNotSupportedException
    {
        ArrayList<Driver> deepCopyC = new ArrayList<> ();
        for (Driver drv : drivers)
        {
            deepCopyC.add(drv.clone());
        }
        return deepCopyC;
    }
    */
    
    public static ArrayList<Driver> deepCopyByUsingClone (HashMap<Integer,Driver> drivers) throws CloneNotSupportedException
    {
        ArrayList<Driver> deepCopyC = new ArrayList<> ();
        for (Driver drv : drivers.values())
        {
            deepCopyC.add(drv.clone());
        }
        return deepCopyC;
    }
    
    public static void printDrivers (ArrayList<Driver> drivers)
    {
        for (Driver driver : drivers)
        {
            System.out.println("=============================================");
            System.out.println (driver);
            System.out.println("=============================================");
        }
    }
    
    public static void printDrivers (HashMap<Integer,Driver> drivers)
    {
        for (Driver driver : drivers.values())
        {
            System.out.println("=============================================");
            System.out.println (driver);
            System.out.println("=============================================");
        }
    }
    
    @Override
    public int compareTo (Driver otherDriver)
    {
        return lisenceDate.compareTo(otherDriver.lisenceDate);
    }

    /*
    public int compareTo3 (Driver otherDriver)
    {
        if(driverID<otherDriver.driverID)
        {
            return -1;
        }
        else if (driverID>otherDriver.driverID)
        {
            return 1;
        }
        else
            return 0;
    }
    
    public int compareTo1(Driver otherDriver) //based on city name
    {
        return address.compareTo(otherDriver.address);
    }
    
    public int compareTo2 (Driver driver) //based on total value of the car // descending (largest to smallesr)
    {
        if (calcValueOfCars()>driver.calcValueOfCars())
            return 1;
        else if (calcValueOfCars()<driver.calcValueOfCars())
            return -1;
        else
            return 0;
    }
    */
    
}
