/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hooman
 */
public class Account 
{
    int id;
    String name;
    double balance;
    public Account(int id1,String name1,double bal)
    {
        id=id1;
        name=name1;
        balance=bal;
    }
    public int getId()
    {
        return id; 
    }
    public String getName()
    {
        return name;
    }
    public double getBal()
    {
        return balance;
    }
    public void setId(int i)
    {
        id=i; 
    }
    public void setName(String na)
    {
        name=na;
    }
    public  void setBal(double b)
    {
        balance=b;
    }
    public String toString()
    {
      return id+" "+name+" "+balance;  
    }
    
    
}
