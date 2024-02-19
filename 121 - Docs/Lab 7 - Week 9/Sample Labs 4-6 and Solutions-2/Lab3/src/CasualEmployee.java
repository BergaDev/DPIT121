
import java.io.Serializable;


public class CasualEmployee extends Employee implements Serializable
{
    protected double hours;
    protected double rate;
    
    public CasualEmployee (String id, String name, Address addr, double hours, double rate)
    {
        super (id, name, addr);
        this.hours = hours;
        this.rate = rate;
    }
    
    public String toString ()
    {
        return super.toString()+"\nHours: "+hours+"\nRate: "+rate+"\nPay: "+calcPay();
    }
    
    public String toDelimitedString ()
    {
        return "CE"+","+super.toDelimitedString()+","+hours+","+rate;
    }
    
    public double calcPay ()
    {
        return hours * rate;
    }
}
