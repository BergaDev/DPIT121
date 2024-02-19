/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectureLambda;

/**
 *
 * @author Hooman
 */

// Generic classes 

//  < > to specify the type 
class Data<T> 
{ 
	T info; // An object of type T is declared 
	public Data(T t) 
        { 
            info = t; 
        } // constructor 
	public T getInfo() 
        { 
            return this.info; 
        } 
        public String toString()
        {
            return info.toString();
        }
} 

// Use generic class
public class A1_GenericClass
{ 
	public static void main (String[] args) 
	{ 
		// instance of Integer type 
		Data <Integer> dataInt = new Data<Integer>(15); 
		System.out.println(dataInt); 

		// instance of String type 
		Data <String> dataStr = new Data<String>("Hooman"); 
		System.out.println(dataStr); 
	} 
}
