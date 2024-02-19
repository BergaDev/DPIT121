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


public class A3_Functional_Interface_Generic 
{
    public static void main(String[] args) 
    {
        Test2 t1=new Math(); // seperate class
        
        Test2<Double,Double,Double> t2= new Test2<Double,Double,Double>() //inline
        {
            @Override
            public Double fun(Double data1,Double data2) 
            {
                return data1*data2;
            }    
        };
        
        Test2<Double,Double,Double> t3=(x,y)->x+2*y; //Lambda Expression
        Test2<Integer,Integer,Integer> t4=(x,y)->x+3*y;
        
        System.out.println("t1 "+t1.fun(5,5));
        System.out.println("t2 "+t2.fun(5.0,5.0));
        System.out.println("t3 "+t3.fun(5.0,5.0));
        System.out.println("t4 "+t4.fun(5,5));

        //////////////////////////////////////////////
       
        display(t1,5,5);
        display(t4,5,5);

    }
    
    public static void display(Test2 t,int data1,int data2) // normal method with Function interface as input
    {
        System.out.println("Inside function: data= "+data1+","+data2+" output= "+ t.fun(data1,data2));
    }
     
}

interface Test2<P,Q,R>  //Function interface
{
    public P fun(Q data1,R data2);
}

class Math implements Test2<Integer,Integer,Integer> // implement the interface
{
    @Override
    public Integer fun (Integer data1,Integer data2) 
    {
        return data1+data2;
    }   
}
