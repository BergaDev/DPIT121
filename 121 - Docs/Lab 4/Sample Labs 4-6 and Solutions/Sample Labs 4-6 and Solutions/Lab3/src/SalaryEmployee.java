
import java.io.Serializable;


public class SalaryEmployee extends Employee implements Serializable
{
    protected double salary;
    
    public SalaryEmployee (String id, String name, Address addr,double salary)
    {
        super (id, name, addr);
        this.salary = salary;
    }
    
    public String toString ()
    {
        return super.toString()+"\nSalary: "+salary+"\n";
    }
    
    public String toDelimitedString ()
    {
        return "SE"+","+super.toDelimitedString()+","+salary;
    }
    
    public double calcPay ()
    {
        return salary / 52.3;
    }
}
