import java.util.*;

public class Interface1
{
    public static void main(String[] args) 
    {
        Card c = new Card ("Kim", 100);
        Item i = new Item ("I003", 1.25, 37);
        
        System.out.println (c.toString ());

        Printable p,q;
        p = c;
        q = i;
        
        p.print ();
        q.print ();
		p=i;
		p.print();
        
        //System.out.println (p.getBalance ()); // error
        
        ArrayList<Printable> vals = new ArrayList<>();
        vals.add (c);
        vals.add (i);
        
        for (Printable v : vals)
            v.print ();   
    }
}

interface Printable
{
    public void print ();
}

class Card implements Printable 
{
    private String id;
    private int balance;
    
    public Card (String id, int balance)
    {
        this.id = id;
        this.balance = balance;
    }
    
    public int getBalance ()
    {
        return balance;
    }
   
    public void print ()
    {
        System.out.println (id + " " + balance);
    }
    
    public String toString ()
    {
        return id + " " + balance;
    }
}

class Item implements Printable 
{
    private String id;
    private double price;
    private int stock;
    
    public Item (String id, double price, int stock)
    {
        this.id = id;
        this.price = price;
        this.stock = stock;
    }
    
    public void print ()
    {
        System.out.println (id + " " + price + " " + stock);
    }
}
