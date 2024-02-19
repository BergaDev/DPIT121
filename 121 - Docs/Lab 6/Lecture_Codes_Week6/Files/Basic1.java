/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class Basic1 {

    public static void main(String[] args)
    {
        try {
           
           /* BufferedWriter out = new BufferedWriter (new FileWriter ("Test01.txt"));
            out.write ("hello1\n");
            out.write ("hello2\n");
            out.write ("hello3\n");
            out.close ();*/
          
            /*BufferedReader in = new BufferedReader (new FileReader ("Test01.txt"));
            String line = in.readLine();
            while (line != null) {
                System.out.println (line);
                line = in.readLine();
                }
         */
            Path path = FileSystems.getDefault().getPath ("Test01.txt");
            List<String> lines = Files.readAllLines (path);
//          lines.forEach (x -> System.out.println (x));
            lines.forEach (System.out::println);
        }
        catch (IOException e) {
            System.out.println ("Main program: " + e);
        }
    }
}
    
    
   /* public static void mainExceptions(String[] args) 
    {
        try {
            double r = calc (10, 0);
            System.out.println ("Result: " + r);
            }
        catch (MyBadArg e) {
            System.out.println (e);
            }
        finally {
            System.out.println ("Finally something");
            }
        
        
        System.out.println ("Last line");
    }
    
    public static double calc (double x, double y) throws MyBadArg
    {
        if (y == 0)
            throw new MyBadArg ("Divide by zero");
        return x/y;
    }
}

class MyBadArg extends Exception
{
    public MyBadArg (String s)
    {
        super (s);
    }
}*/
    


