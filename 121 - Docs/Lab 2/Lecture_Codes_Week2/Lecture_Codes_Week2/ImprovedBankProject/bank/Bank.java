package bank;

/**
 *
 * @author hooman
 */


import java.util.*;

public class Bank
{
    private String name;
    private ArrayList<Account>accounts;
    private static final String [] cities= {"Sydney","Wollongong","Melbourne","Tehran"}; // bad way and hardcoded
    private static final int [] ranges={0,1000,10000,100000000}; // ranges 

    public Bank(String name)
    {
            this.name=name;
            accounts=new ArrayList<Account>();
    }
    
    public Account findAccount(int id) // find the account for the given id. Returns null if not found
    {
        for( Account acc:accounts)
        {
            if (acc.getID()==id)
                return acc;  
        }
        return null;
    }
	
    public boolean addAccount(Account account)
    {
        if (findAccount(account.getID())==null)
        {
            accounts.add(account);
            return true;
        }
        else
            return false;
    }

    public void printAccounts()
    {
        for (Account acc:accounts)
        {
            acc.print();
        }
    }
    
    public double  calcTotalBalance()
    {
        double total = 0;
        for (Account acc : accounts) 
        {
            total += acc.getBalance();
        }
        return total;
    }
	
 
    public double totalBalForCity( String city)
    {
        double total=0;
        for( Account acc:accounts)
        {
            if(acc.getCity().equals(city))
                total+=acc.getBalance();
        }
        return total;

    }
	
    public  void  reportCity1() // bad way assuming that the list of distinct cities are fixed and hardcoded
    {
        System.out.println();
        for (int i=0;i<cities.length;i++)
        {
            System.out.println(cities[i]+" "+totalBalForCity(cities[i]));
        }
        System.out.println();         
    }
	
    public ArrayList<String> populateDistinctCityList()
    {
        ArrayList<String> cities =new ArrayList<String>();
        for (Account account:accounts)
        {
            boolean found=false;
            for (String city:cities)
            {
                if (account.getCity().equals(city))
                {
                    found=true;
                    break;
                }
            }
            if (!found)
                cities.add(account.getCity());
        }
        return cities; 
    }
	
    public ArrayList<Double> getTotalBalancePerCity(ArrayList<String> cities)      
    {
        ArrayList<Double> totalBalances=new ArrayList<Double>();
        double balance=0;
        for (String city:cities)
        {
//            balance=0;
//            for (Account account:accounts)
//            {
//                if(account.getCity().equals(city))
//                    balance+=account.getBalance();
//            }
//            totalBalances.add(balance); 
            //or using totalBalForCity( String city) which is easier to read
            totalBalances.add(totalBalForCity(city));	
        }
        return totalBalances;
    }
    
    public ArrayList<Integer> getTotalCountPerCity(ArrayList<String> cities)        
    {
        ArrayList<Integer> totalCounts=new ArrayList<Integer>();
        int count=0;
        for (String city:cities)
        {
            count=0;
            for (Account account:accounts)
            {
                if(account.getCity().equals(city))
                    count++;
            }
            totalCounts.add(count);
	}
        return totalCounts;
    }
    public void reportTotalPerCity(ArrayList<String> cities, ArrayList<Integer> counts,ArrayList<Double> totals)
    {
        System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (int i=0;i<counts.size();i++)
            System.out.println(cities.get(i)+" \t \t "+totals.get(i)+" \t \t "+totals.get(i)/(double)counts.get(i));  
    }
    public int accountsCounInRange(double lower,double higher)
    {
        int count=0;
        for( Account acc:accounts)
        {
            if((acc.getBalance()>=lower)&&(acc.getBalance()<higher))
                count++;
        }
        return count;    
    }
    
    public void reportRanges1() // basic one with hard coded range
    {
        System.out.println();
        for (int i=0;i<ranges.length-1;i++)
        {
            System.out.println("Number of accounts between "+ranges[i]+" and "+ranges[i+1]+"= "+accountsCounInRange(ranges[i],ranges[i+1]) );
        }
        System.out.println();   
    }
	
    public ArrayList<Integer> getTotalCountPerRange(ArrayList<Integer> ranges)      
    {
        ArrayList<Integer> totalCounts=new ArrayList<Integer>();
        int count=0;
        for (int i=0;i<ranges.size()-1;i++)
        {    
//            count=0;
//            for( Account acc:accounts)
//            {
//                    if((acc.getBalance()>=ranges.get(i))&&(acc.getBalance()<ranges.get(i+1)))
//                            count++;
//            }
//            totalCounts.add(count);
            // or by using accountsCounInRange(double lower,double higher)
            totalCounts.add(accountsCounInRange(ranges.get(i),ranges.get(i+1)));
	}
        return totalCounts;
    }
	public void reportRanges(ArrayList<Integer> ranges, ArrayList<Integer> counts) 
    {
        System.out.println();
        for (int i=0;i<ranges.size()-1;i++)
        {
            System.out.println("Number of accounts between "+ranges.get(i)+" and "+ranges.get(i+1)+" = "+counts.get(i) );
        }
        System.out.println();   
    }	
}

