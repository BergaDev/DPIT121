/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectureLambda;
import java.util.*;
/**
 *
 * @author Hooman
 */


public class A2_Functional_Interface 
{
    public static void main(String[] args) 
    {
        Test t1=new Mult2();
        Mult2 m1=new Mult2();
        Test t2=new Mult3();
        System.out.println("*2 "+t1.calc(5));
        System.out.println("*3 "+t2.calc(5));
        System.out.println("*2 "+m1.calc(5));
        /////////////////////////////////////////////
        Test t3= new Test() //inline
        {
            @Override
            public int calc(int data) 
            {
                return data*4;
            }
            
        };
        System.out.println("*4 "+t3.calc(5));
        
        //////////////////////////////////////////////
        Test t4=(x->x*10); //using Lambda expression 
        System.out.println("*10 "+t4.calc(5));
        
        ///////////////////////////////////////////////
        display(t1,5);
        display(t2,5);
        display(t3,5);
        display(t4,5);
        
       
    }
    
    public static void display(Test t,int data) // normal method with Function interface as input
    {
        System.out.println("Inside function: data= "+data+" output= "+ t.calc(data));
    }
     
}

interface Test  //Function interface
{
    public int calc(int data);
}

class Mult2 implements Test // implement the interface
{
    @Override
    public int calc(int data) 
    {
        return data*2;
    }   
}

class Mult3 implements Test
{
    @Override
    public int calc(int data) 
    {
        return data*3;
    }   
}
