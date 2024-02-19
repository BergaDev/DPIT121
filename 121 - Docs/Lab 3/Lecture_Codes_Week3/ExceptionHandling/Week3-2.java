package ExceptionHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

class Program1{

     public static void main(String []args){
        
        Boolean success=false;
        System.out.println("Please enter a number ?");
        while(!success)
        {
            try
            {
                Scanner sc=new Scanner(System.in);
                int num=sc.nextInt();
                int i=5/num;
                System.out.println(i);
                success=true;
            }
            catch (ArithmeticException e)
            {
                System.err.println("error in calculation : "+e);
                System.out.println("Please enter the number again"); 
            }
            catch (InputMismatchException e)
            {
                System.err.println("error in Input : "+e);
                System.out.println("Please enter the value again"); 
            }
            
        }
        
        
     }
}