package bank;

/**
 *
 * @author hooman
 */


import java.util.*;

public class Bank 
{
    public static ArrayList<Account>accounts=new ArrayList<Account>();
    public static final String [] cities= {"Sydney","Wollongong","Melbourne"};
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
        Account acc4 = new Account ("Robert", 8000,"Wollongong");
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);
        accounts.add(acc4);
        
		
		
      	if (acc3.setBalance(10)==true)
        {
           System.out.println(" Balance is set succesfully");
        }
      	else
      	{
        	System.out.println(" Balance is not set as the value is not valid");
      	}
          
      	if (acc2.setBalance(-10)==true) //poor design as it is duplicate. See library exmaple
        {
           System.out.println(" Balance is set succesfully");
        }
      	else
      	{
        	System.out.println(" Balance is not set as the value is not valid");
      	}
      
      	if (acc1.deposit(-60))
        {
           System.out.println(" deposite was succesful and the new balance is "+acc1.getBalance());
        }
      	else
      	{
        	System.out.println(" deposite was not succesful");
      	}
      
        if (acc2.withdraw(600))
        {
           System.out.println(" withdraw was succesful and the new balance is "+acc2.getBalance());
        }
      	else
      	{
        	System.out.println(" withdraw was not succesful");
      	}
 
        // printing the list
//        for (Account acc:accounts)
//        {
//            acc.print();
//        }
        printAccounts(); // better then above code
        
		
		// calculate the total balance for all accounts
		//double total = 0;
        //for (Account acc : accounts) {
        //    acc.print ();
        //    total += acc.getBalance();
        //}
        
        //System.out.println ("Total balance = " + total);
		
		double total = calcTotalBalance(); // better than above code
		System.out.println ("Total balance = " + total);	
		
        //search accounts
        String id="";
        while (!id.equals("q"))
        {
            Scanner sc=new Scanner (System.in);
            System.out.print(" Enter an account ID: ");
            id=sc.nextLine();
            Account acc=findAccount(id);
            if (acc!=null)
                acc.print();
            else
                System.out.println(" Account has not been found");
        }
        
        // report total balances for all accounts for all given cities
        reportCity();
        
        //report balances in three ranges the fixed ones and using the rang array
        
        //System.out.println("bal<1000 "+  accountsCounInRange(0,1000));
        //System.out.println("1000<bal<10000 "+  accountsCounInRange(1000,10000));
        //System.out.println("10000<bal<1000000000 "+  accountsCounInRange(10000,1000000000));
        reportRanges();
     
    }

    public static void printAccounts()
    {
        for (Account acc:accounts)
        {
            acc.print();
        }
    }
    
	public static double  calcTotalBalance()
	{
		double total = 0;
        for (Account acc : accounts) 
		{
            total += acc.getBalance();
        }
		return total;
	}
	
    public  static Account findAccount(String id)
    {
        for( Account acc:accounts)
        {
            if (acc.getID().equals(id))
                return acc;  
        }
        return null;
    }
	
    public static double totalBalForCity( String city)
    {
        double total=0;
        for( Account acc:accounts)
        {
            if(acc.getCity().equals(city))
                total+=acc.getBalance();
        }
        return total;

    }
	
    public  static void  reportCity()
    {
        System.out.println();
        for (int i=0;i<cities.length;i++)
        {
            System.out.println(cities[i]+" "+totalBalForCity(cities[i]));
        }
        System.out.println();
              
    }
	
    public static int accountsCounInRange(double lower,double higher)
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
    }
  
}

