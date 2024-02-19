import java.util.*;

public class CompareTo 
{
    public static void main(String[] args) 
    {
        Account1 acc1 = new Account1 ("Joe", 150);
        Account1 acc2 = new Account1 ("Sue", 225);
        Account1 acc3 = new Account1 ("Kim", 125);
        Account1 acc4 = new Account1 ("Jil", 700);
        
//        System.out.println (acc1); //test methods
//        System.out.println (acc2);
//        acc1.increaseBalance(100);
//        System.out.println (acc1);
//
//        acc2.transferTo(acc1, 50);
//        System.out.println (acc1);
//        System.out.println (acc2);
        
        List<Account1> accs = new ArrayList<Account1>();
        accs.add (acc1);
        accs.add (acc2);
        accs.add (acc3);
        accs.add (acc4);
        
        Collections.sort (accs);
        
        for (Account1 acc : accs) //print the sorted list
            acc.print ();   
    }
}

class Account1 implements Comparable<Account1>
{
    private String id;
    private double balance;
    
    public Account1 (String id, double balance)
    {
        this.id = id;
        this.balance = balance;
    }
    
    public int compareTo1 (Account1 other) //compare based on ID
    {
        return this.id.compareTo (other.id);
    }
    
    public int compareTo (Account1 other) // comapre based on balance
    {   
        if (this.balance == other.balance)
            return 0;
/*        
        if (this.balance > other.balance)
            return 1;
        else
            return -1;
*/
        return this.balance > other.balance ? 1 : -1;   
    }
    
    public String toString ()
    {
        return id + " " + balance;
    }
    
    public void print ()
    {
        System.out.println (this);
    }
    
    public void increaseBalance (double amount)
    {
        this.balance += amount;
    }
    
    public boolean transferTo (Account1 other, double amount)
    {
        if (this.balance < amount)
            return false;
        
        this .balance -= amount;
        other.balance += amount;
        return true;
    }
}
