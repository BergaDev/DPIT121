
public class Interface2
{
    public static void main(String[] args) 
    {
        Record rec = new Record ("Kim", 150);
        System.out.println (rec);
        rec.setBalance(250);
        System.out.println (rec);
         
        joesCode (rec);
    }
    
    public static void joesCode (ViewRecord rec) //input is the interface
    {
        // do stuff with rec
        
        System.out.println (rec);
//      rec.setBalance(99); //doesn't work
        System.out.println (rec);
    }
}

interface ViewRecord
{
    String getID ();
    double getBalance ();
    
}

class Record implements ViewRecord
{
    private String id;
    private double balance;
    
    public Record (String id, double balance)
    {
        this.id = id;
        this.balance = balance;
    }
    
    public String getID ()
    {
        return id;
    }
    
    public double getBalance ()
    {
        return balance;
    }
    
    public void setID (String id)
    {
        this.id = id;
    }
    
    public void setBalance (double balance)
    {
        this.balance = balance;
    }
    
    public String toString ()
    {
        return id + " " + balance;
    }
}