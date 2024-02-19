/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hooman
 */
 
 import java.util.*;
 
 

class Bank 
{
    public static HashMap<String,Account>accounts=new HashMap<String,Account>();
    //public static final String [] city= {"Sydney","Wollongong","Melbourne"};
    public static final int [] ranges={0,1000,10000,100000000};
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        // TODO code application logic here
        Account acc1 = new Account ("Kim",  100,"Sydney");
        Account acc2 = new Account ("Jack", 1800,"Sydney");
        Account acc3 = new Account ("Jill", 20000,"Wollongong");
        Account acc4 = new Account ("Robert", 8000,"Melbourne");
        accounts.put(acc1.getID(),acc1);
        accounts.put(acc2.getID(),acc2);
        accounts.put(acc3.getID(),acc3);
        accounts.put(acc4.getID(),acc4);
        
        
        // printing the list
//        for (Account acc:accounts)
//        {
//            acc.print();
//        }
        printAccounts();
        
        //search accounts
        String id="Kim";
        /*while (!id.equals("q"))
        {
            Scanner sc=new Scanner (System.in);
            System.out.print(" Enter an account ID: ");
            id=sc.nextLine();*/
            Account acc=findAccount(id);
            if (acc!=null)
                acc.print();
            else
                System.out.println(" Account has not been found");
        //}
        
        // report total balances for all accounts for all given cities
        HashMap<String,Double> cities=totalBalForCities();
        reportCity(cities);
        
        //report balances in three ranges the fixed ones and using the rang array
        
        //System.out.println("bal<1000 "+  accountsCounInRange(0,1000));
        //System.out.println("1000<bal<10000 "+  accountsCounInRange(1000,10000));
        //System.out.println("10000<bal<1000000000 "+  accountsCounInRange(10000,1000000000));
        //reportRanges();
     
    }

    public static void printAccounts()
    {
        for (Account acc:accounts.values())
        {
            acc.print();
        }
    }
    
    public  static Account findAccount(String id)
    {
        /*for( Account acc:accounts)
        {
            if (acc.getID().equals(id))
                return acc;  
        }
        return null;*/
        return accounts.get(id);
    }
    public static HashMap<String,Double> totalBalForCities()
    
    {
        /*double total=0;
        for( Account acc:accounts.values())
        {
            if(acc.getCity().equals(city))
                total+=acc.getBalance();
        }
        return total;*/
        HashMap<String,Double> cities=new HashMap<String,Double>();
        for(Account acc:accounts.values())
        {
            Double bal=cities.get(acc.getCity());
            //System.out.println(bal);
            if (bal!=null)
            {
                cities.put(acc.getCity(),bal+acc.getBalance());
            }
            else
            {
                cities.put(acc.getCity(),acc.getBalance());
            }
        }
        return cities;
    }
    public  static void  reportCity(HashMap<String,Double> cities)
    {
        System.out.println();
        for (String city: cities.keySet())
        {
            System.out.println(city+" "+cities.get(city));
        }
        
        /*System.out.println();
        for (int i=0;i<city.length;i++)
        {
            System.out.println(city[i]+" "+totalBalForCity(city[i]));
        }
        System.out.println();*/
             
        
    }
    /*public static int accountsCounInRange(double lower,double higher)
    {
        int count=0;
        for( Account acc:accounts)
        {
            if((acc.getBalance()>=lower)&&(acc.getBalance()<higher))
                count++;
        }
        return count;    
    }
    
    public static void reportRanges()
    {
        System.out.println();
        for (int i=0;i<ranges.length-1;i++)
        {
            System.out.println("Number of accounts between "+ranges[i]+" and "+ranges[i+1]+"= "+accountsCounInRange(ranges[i],ranges[i+1]) );
        }
        System.out.println();   
    }*/

    
}
 
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
      
    boolean deposite(double amount)
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
        System.out.println (id + " " + balance+ " "+city);
    }
}




