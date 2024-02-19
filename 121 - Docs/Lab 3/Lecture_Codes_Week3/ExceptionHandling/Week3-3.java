package ExceptionHandling;

class Program2
{
    public static void main( String [] args)
    {
        try
        {
            Account2 ac=new Account2(5,-100);
        }
        catch (ArithmeticException e)
        {
            System.out.println(e);
        }
    }
}
class Account2
{
    int id;
    int balance;
    public Account2 (int id1 ,int bal)
    {
        if ( bal<0)
            throw new ArithmeticException("bal should be positive");
        id=id1;
        balance=bal;
    }
    
}