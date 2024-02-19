
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Employee implements Serializable
{
    protected String id;
    protected String name;
    protected Address address;
    
    public Employee (String id, String name, Address addr)
    {
        this.id = id;
        this.name = name;
        this.address = addr;
    }
    
    public String toString ()
    {
        return "ID: "+id+"\nName: "+name+"\nAddress: "+address;
    }
    
    public String toDelimitedString ()
    {
        return id+","+name+","+address.toDelimitedString();
    }
    
    public abstract double calcPay ();
}
