
import java.util.ArrayList;


public class Driver implements Cloneable, Comparable<Driver>
{
    private String firstName;
    private String lastName;
    private int driverID;
    private Address address;
    private ArrayList<Car> cars;
    
    public Driver (String fname, String lname, int id, Address addr)
    {
        this.firstName=fname;
        this.lastName=lname;
        this.driverID=id;
        this.address=addr;
        this.cars= new ArrayList<> ();
    }
    
    public Driver (Driver driver)
    {
        this.firstName=driver.firstName;
        this.lastName=driver.lastName;
        this.driverID=driver.driverID;
        this.address=new Address (driver.address);
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
    
    public String toString ()
    {
        String output= driverID+", "+firstName+" "+lastName+"\nAddress:"+address+"\n";
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
    
    @Override
    public Driver clone() throws CloneNotSupportedException
    {
        Driver output = (Driver) super.clone();
        output.address=address.clone();
        output.cars=new ArrayList<Car>();
        for (Car car : cars)
        {
            output.cars.add(car.clone());
        }
        return output;
    }
    
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
    
    public static void printDrivers (ArrayList<Driver> drivers)
    {
        for (Driver driver : drivers)
        {
            System.out.println (driver);
        }
    }

    @Override
    public int compareTo(Driver otherDriver) //based on city name
    {
        return address.compareTo(otherDriver.address);
    }
    
    public int compareTo1 (Driver driver) //based on total value of the car // descending (largest to smallesr)
    {
        if (calcValueOfCars()>driver.calcValueOfCars())
            return 1;
        else if (calcValueOfCars()<driver.calcValueOfCars())
            return -1;
        else
            return 0;
    }
    
    
}
