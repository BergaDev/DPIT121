enum CarType
{
    SUV, Sedan, Sport, Luxury
}
public class Car implements Cloneable
{
    private int id;
    private String model;
    private double price;
    private CarType carType;
    
    public Car (int id, String model, double price, CarType carType)
    {
        this.id=id;
        this.model=model;
        this.price=price;
        this.carType= carType;
    }
    
    public Car (Car car)
    {
        this.id=car.id;
        this.model=car.model;
        this.price=car.price;
        this.carType=car.carType;
    }
    
    public double getPrice ()
    {
        return price;
    }
    
    public String toString ()
    {
        return id+" "+model+" "+price+" "+carType;
    }
    
    @Override
    public Car clone() throws CloneNotSupportedException
    {
        return (Car)super.clone();
    }  
}
