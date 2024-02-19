import java.util.*;
public class ShallowAndDeepCopyBasic
{

    public static void main(String []args)
    {
        Account2 ac1=new Account2(10,"Robert",1000);
        Account2 ac2=new Account2(11,"Sara",100);
        Account2 ac3=new Account2(13,"John",10000);
        //ac1.print();
        /*ArrayList<Integer> numbers=new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(9);
        for ( int i:numbers)
            System.out.println(i);
        Collections.sort(numbers);
        for ( int i:numbers)
            System.out.println(i);*/
        ArrayList<Account2> accounts=new ArrayList<Account2>();
        ArrayList<Account2> shallowCopy=new ArrayList<Account2>();
        ArrayList<Account2> deepCopy=new ArrayList<Account2>();
        accounts.add(ac1);
        accounts.add(ac2);
        accounts.add(ac3);
        /*for (Account ac:accounts) //Shallow copy
        {
            shallowCopy.add(ac);
        }*/
        /*for (Account ac:accounts) //Deep copy
        {
            deepCopy.add(new Account (ac));
        }*/
        Account2.shallowCopy(accounts,shallowCopy);
        // or calling the other method
        //ArrayList<Account>shallowCopy1=Account.shallowCopy1(accounts);
        Account2.deepCopy(accounts,deepCopy);
        System.out.println("original list");
        Account2.printAccounts(accounts);
        System.out.println();
        System.out.println();
        System.out.println("Shallow copy list");
        Account2.printAccounts(shallowCopy);
        System.out.println();
        System.out.println();
        System.out.println("Deep copy list");
        Account2.printAccounts(deepCopy);
        System.out.println();
        System.out.println();
        
        accounts.get(0).setBalance(6666666);
        System.out.println("After change");
        System.out.println("original list");
        Account2.printAccounts(accounts);
        System.out.println();
        System.out.println();
        System.out.println("Shallow copy list");
        Account2.printAccounts(shallowCopy);
        System.out.println();
        System.out.println();
        System.out.println("Deep copy list");
        Account2.printAccounts(deepCopy);
        System.out.println();
        System.out.println();
        
        System.out.println("Before Sorting");
        //for ( Account account :accounts)
        //    System.out.println(account);
        //printAccounts(accounts);
        System.out.println("original list");
        Account2.printAccounts(accounts);
        System.out.println();
        System.out.println();
        System.out.println("Shallow copy list");
        Account2.printAccounts(shallowCopy);
        System.out.println();
        System.out.println();
        System.out.println("Deep copy list");
        Account2.printAccounts(deepCopy);
        System.out.println();
        System.out.println();
        Collections.sort(accounts);
        System.out.println("After Sorting");
        System.out.println("original list");
        Account2.printAccounts(accounts);
        System.out.println();
        System.out.println();
        System.out.println("Shallow copy list");
        Account2.printAccounts(shallowCopy);
        System.out.println();
        System.out.println();
        System.out.println("Deep copy list");
        Account2.printAccounts(deepCopy);
        System.out.println();
        System.out.println();
        //printAccounts(accounts);
        //for ( Account account :accounts)
        //    System.out.println(account);
            
    }
     
     /*public static void printAccounts(ArrayList<Account>accounts)
     {
        for ( Account account :accounts)
            System.out.println(account); 
     }*/
     
}

class Account2 implements Printable,Comparable<Account2>
{
    private int id;
    private String name;
    private double balance;
    public Account2(int id1,String name1,double bal1)
    {
        id=id1;
        name=name1;
        balance=bal1;
    }
    public Account2(Account2 ac)
    {
        id=ac.id;
        name=ac.name;
        balance=ac.balance;
    }
    public void setBalance(double bal)
    {
        balance=bal;
    }
    public void print()
    {
        System.out.println(id+ " "+name+ " "+balance);
    }
    public String toString()
    {
        return id+ " "+name+ " "+balance; 
    }
    public int compareTo(Account2 ac)
    {
        if (balance>ac.balance)
            return 1;
        else if (balance<ac.balance)
            return -1;
        else //if balance==ac.balance
            return 0;
    }
    public static void printAccounts(ArrayList<Account2>accounts)
    {
       for ( Account2 account :accounts)
           System.out.println(account); 
    }
    //We need to send both original and empty list to the method
    public static void shallowCopy(ArrayList<Account2>accounts,ArrayList<Account2> shallowCopy)
    {
       for (Account2 ac:accounts) //Shallow copy
       {
           shallowCopy.add(ac);
       }
    }
    //This method gets one list as input and creates a new list and returns it
    public static ArrayList<Account2> shallowCopy1(ArrayList<Account2> accounts)
    {
       ArrayList<Account2> shallowCopy =new ArrayList<Account2>();
       for (Account2 ac:accounts) //Shallow copy
       {
           shallowCopy.add(ac);
       }
       return shallowCopy;
    }
    public static void deepCopy(ArrayList<Account2>accounts,ArrayList<Account2> deepCopy)
    {
       for (Account2 ac:accounts) //Deep copy
       {
           deepCopy.add(new Account2 (ac));
       }
    }
    public static ArrayList<Account2> deepCopy1(ArrayList<Account2>accounts)
    {
       ArrayList<Account2> deepCopy =new ArrayList<Account2>();
       for (Account2 ac:accounts) //Deep copy
       {
           deepCopy.add(new Account2 (ac));
       }
       return deepCopy;
    }     
}