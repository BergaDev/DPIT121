package ExceptionHandling;

class Program
{
    public static void main( String [] args)
    {
        try
        {
            Account ac=new Account(5,-100);
        }
        catch (MyException e)
        {
            System.out.println(e);
        }
    }
}
class Account 
{
    int id;
    int balance;
    public Account (int id1 ,int bal) throws MyException
    {
        if ( bal<0)
            throw new MyException(-bal);
        id=id1;
        balance=bal;
    }
    
}
class MyException extends Exception
{
    int amount;
    public MyException (int amn)
    {
        amount=amn;
    }
    public String toString()
    {
        return "Your balance is negative. you need extra $"+amount;
    }
}
