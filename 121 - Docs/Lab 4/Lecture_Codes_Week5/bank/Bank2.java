package bank;
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

class Bank2 
{
    public String name;
	public HashMap<Integer,Account>accounts;
    
    public Bank2(String name)
	{
		this.name=name;
		accounts=new HashMap<Integer,Account>();
	}
    

	public Account findAccount(int id) // find the account for the given id. Returns null if not found
    {
         /*for( Account acc:accounts)
        {
            if (acc.getID().equals(id))
                return acc;  
        }
        return null;*/
        return accounts.get(id);
    }
	
	public boolean addAccount(Account account)
    {
        if (findAccount(account.getID())==null)
        {
            accounts.put(account.getID(),account);
            return true;
        }
        else
            return false;
    }

    public void printAccounts()
    {
        for (Account acc:accounts.values())
        {
            acc.print();
        }
    }
    
    
    public HashMap<String,Double> getTotalBalancePerCity()
    {
        HashMap<String,Double> balances=new HashMap<String,Double>();
        for(Account acc:accounts.values())
        {
            Double bal=balances.get(acc.getCity());
            if (bal!=null)
            {
                balances.put(acc.getCity(),bal+acc.getBalance());
            }
            else
            {
                balances.put(acc.getCity(),acc.getBalance());
            }
        }
        return balances;
    }
	
    public HashMap<String,Integer> getTotalCountPerCity()       
    {
	HashMap<String,Integer> counts=new HashMap<String,Integer>();
        for(Account acc:accounts.values())
        {
            Integer count=counts.get(acc.getCity());
            if (count!=null)
            {
                counts.put(acc.getCity(),count+1);
            }
            else
            {
                counts.put(acc.getCity(),1);
            }
        }
        return counts;
    }
    
    public void  reportCity(HashMap<String,Double> balances,HashMap<String,Integer> counts)
    {
        System.out.println();
		System.out.println("\n City \t \t Total Balance \t \t Average Balance");
        for (String city: balances.keySet())
        {
            System.out.println(city+"\t \t "+balances.get(city)+" \t \t "+balances.get(city)/(double)counts.get(city)); 
        }
    }
	
    public HashMap<Integer,Integer> getTotalCountPerRange( ArrayList<Integer> ranges)
    {
        return null;
    }
    public void reportRanges( ArrayList<Integer> ranges,HashMap<Integer,Integer> countsPerRange)
    {

    }
   
}
 
