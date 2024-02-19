import java.util.*;
public class ShallowAndDeepCopyAdvanced
{

	public static void main(String []args) throws CloneNotSupportedException
	{
		Account ac1=new Account(10,"Robert",1000,new Address("2 Princes Hwy","Fairy Meadow",2519));
		Account ac2=new Account(11,"Sara",100,new Address("20 Crwon St","Wollongong",2500));
		Address address=new Address("5 Gipss Rd","Keiraville",2500);
		Account ac3=new Account(13,"John",10000,address);
		ac1.addtransaction(new Transaction(12,"Fruit",56.25));
		ac1.addtransaction(new Transaction(15,"Drink",16.75));
		ac1.addtransaction(new Transaction(12,"Mobile",69.00));
		ac2.addtransaction(new Transaction(17,"Fruit",26.55));
		ac2.addtransaction(new Transaction(166,"Drink",26.25));
		ac3.addtransaction(new Transaction(120,"Mobile",39.00));
		ac3.addtransaction(new Transaction(121,"Fruit",56.25));
		ac3.addtransaction(new Transaction(152,"Drink",56.75));
		ac3.addtransaction(new Transaction(123,"Rent",89.00));
		ArrayList<Account>accounts=new ArrayList<Account>();
		accounts.add(ac1);
		accounts.add(ac2);
		accounts.add(ac3); 
		ArrayList<Account> shallowCopy=Account.shallowCopy(accounts);
		ArrayList<Account> deepCopy1=Account.deepCopyByCopyConstructor(accounts);
		ArrayList<Account> deepCopy2=Account.deepCopyByClone(accounts);

		System.out.println("original list");
		Account.printAccounts(accounts);
		System.out.println();
		System.out.println();
		System.out.println("Shallow copy list");
		Account.printAccounts(shallowCopy);
		System.out.println();
		System.out.println();
		System.out.println("Deep copy list by copy constructor");
		Account.printAccounts(deepCopy1);
		System.out.println();
		System.out.println();
		System.out.println("Deep copy list by clone");
		Account.printAccounts(deepCopy2);
		System.out.println();
		System.out.println();
		
		
		accounts.get(0).setBalance(555);
		accounts.get(0).setPostCode(2525); // change the address
		accounts.get(0).addtransaction(new Transaction(45,"Rent",567)); // add transaction
		
		System.out.println("After change");
		
		System.out.println("original list");
		Account.printAccounts(accounts);
		System.out.println();
		System.out.println();
		System.out.println("Shallow copy list");
		Account.printAccounts(shallowCopy);
		System.out.println();
		System.out.println();
		System.out.println("Deep copy list by copy constructor");
		Account.printAccounts(deepCopy1);
		System.out.println();
		System.out.println();
		System.out.println("Deep copy list by clone");
		Account.printAccounts(deepCopy2);
		System.out.println();
		System.out.println();
		 
		Collections.sort(accounts);
		
		System.out.println("After Sorting");
		
		System.out.println("original list");
		Account.printAccounts(accounts);
		System.out.println();
		System.out.println();
		System.out.println("Shallow copy list");
		Account.printAccounts(shallowCopy);
		System.out.println();
		System.out.println();
		System.out.println("Deep copy list by copy constructor");
		Account.printAccounts(deepCopy1);
		System.out.println();
		System.out.println();
		System.out.println("Deep copy list by clone");
		Account.printAccounts(deepCopy2);
		System.out.println();
		System.out.println();
			
	}     
}

class Account implements Comparable<Account> ,Cloneable
{
    private int id;
    private String name;
    private double balance;
    private Address address;
    private ArrayList<Transaction> transactions;
    public Account(int id1,String name1,double bal1,Address address1)
    {
        id=id1;
        name=name1;
        balance=bal1;
        address=address1;
        transactions=new ArrayList<Transaction>();
    }
    public Account(Account ac) //copy constructor
    {
        id=ac.id;
        name=ac.name;
        balance=ac.balance;
        address=new Address(ac.address); // call address copy constructor
        transactions=new ArrayList<Transaction>();
        for (Transaction trans:ac.transactions)
        {
            transactions.add(new Transaction(trans)); // call Transaction copy constructor
        }
    }
    public void setBalance(double bal)
    {
        balance=bal;
    }
    public void addtransaction(Transaction trans)
    {
        transactions.add(trans); // we don't check for the id to be unique 
    }
    public void setPostCode(int newPostcode)
    {
        address.setPostCode(newPostcode); // understand that how we send a message to another class
    }
    public String toString()
    {
        String output= id+ " "+name+ " "+balance+ " Address: "+address+"\n"; // calls address.toString()
        output+="List of Transactions:\n";
        for (Transaction trans:transactions)
        {
            output+=trans+"\n"; // call Transaction toString()
        }
        return output;
    }
    public int compareTo1(Account ac) //based on balance
    {
        if (balance>ac.balance)
            return 1;
        else if (balance<ac.balance)
            return -1;
        else //if balance==ac.balance
            return 0;
    }
    public int compareTo(Account ac) // based on address which is based on postcode
    {
        return address.compareTo(ac.address);
    }
    public static void printAccounts(ArrayList<Account>accounts)
    {
        for ( Account account :accounts)
            System.out.println(account); 
    }
    @Override
    public Account clone() throws CloneNotSupportedException
    {
        //Account output=new Account(this); // copy contructor 
        Account output=(Account) super.clone(); // or you can copy each element in the other one manually
        output.address=address.clone();
        output.transactions=new ArrayList<Transaction>(); //clear the shallow copy
        for (Transaction tras:transactions)
        {
            output.transactions.add(tras.clone()); // use clone for a deep copy
        }
        return output;  
    }
//    public static void shallowCopy(ArrayList<Account>accounts,ArrayList<Account> shallowCopy)
//    { 
//        shallowCopy.clear();
//        for (Account ac:accounts) //Shallow copy 
//        {
//            shallowCopy.add(ac);
//        }
//    }
    public static ArrayList<Account> shallowCopy(ArrayList<Account> accounts)
    { 
        ArrayList<Account>shallowCopy=new ArrayList<Account>();
        for (Account ac:accounts) //Shallow copy 
        {
            shallowCopy.add(ac);
        }
        return shallowCopy;
    }
    
    public static ArrayList<Account> deepCopyByCopyConstructor(ArrayList<Account> accounts)
    {
        ArrayList<Account>deepCopy=new ArrayList<Account>();
        for (Account ac:accounts) //Deep copy by using copy constructor
        {
            deepCopy.add(new Account (ac));
        }
        return deepCopy;   
    }
    
    public static ArrayList<Account> deepCopyByClone(ArrayList<Account> accounts) throws CloneNotSupportedException
    {
        ArrayList<Account>deepCopy=new ArrayList<Account>();
        for (Account ac:accounts) //Deep copy by using copy constructor
        {
            deepCopy.add(ac.clone());
        }
        return deepCopy;
    }      
}

class Transaction implements Cloneable
{
    private int id;
    private String description;
    private double value;
    
    public Transaction(int id1,String des,double val)
    {
        id=id1;
        description=des;
        value=val;
    }
    public Transaction(Transaction trans) //copy constructor
    {
        id=trans.id;
        description=trans.description;
        value=trans.value;
    }
    public void setDescription( String newDescription )
    {
        description=newDescription;
    }
    public String toString()
    {
      return id+ " "+description+ " "+ value; 
    }
    @Override
    public Transaction clone() throws CloneNotSupportedException
    {
        return (Transaction)super.clone();
    }  
}

class Address implements Comparable<Address>, Cloneable
{
    private String street;
    private String suburb;
    private int postCode;
    public Address( String street1,String suburb1,int postCode1)
    {
        street=street1;
        suburb=suburb1;
        postCode=postCode1;     
    }
    public Address( Address addr) // copy constructor
    {
        street=addr.street;
        suburb=addr.street;
        postCode=addr.postCode;
    }
    public void setPostCode( int newPostCode)
    {
        postCode=newPostCode;
    }
    public int compareTo(Address otherAddress)
    {
        return postCode-otherAddress.postCode;
        //return Integer.compare(postCode, otherAddress.postCode);
    }
    public String toString()
    {
      return street+ " "+suburb+ " "+ postCode; 
    }
    @Override
    public Address clone() throws CloneNotSupportedException
    {
        return (Address) super.clone();
    }  
}