package bank;

/**
 *
 * @author hooman
 */
class Account
{
    private String id;
    private double balance;
    private String city;
    
    public Account (String id, double balance,String city)
    {
        this.id = id;
        this.city=city;
        if (balance>=0)
        {
          this.balance = balance;
        }
      	else
        {
          System.out.println("The balance is not valid");
      	  balance=0;
        }
        
    }
    
    public String getID ()
    {
        return id;
    }
    public String getCity ()
    {
        return city;
    }
    
    public double getBalance ()
    {
        return balance;
    }
    
    public boolean setBalance (double val)
    {    
		if (val>=0)
		{
			balance = val;
			return true;
		}
		else
		{
			return false;
		}  
    }
  
    boolean withdraw(double amount)
    {
		if (balance>=amount)
		{
			balance-=amount;// balance=balance-amount
			return true;
		}
		else
		{
			return false;
		} 
    }  
    boolean deposit(double amount)
    {
		if (amount>=0)
		{
			balance+=amount;// balance=balance+amount
			return true;
		}
		else
		{
			return false;
		}  
    }
    public void print ()
    {
        System.out.println ("ID: "+id +" Balance: " + balance+ " City: "+ city);
    }
}