/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hooman
 */
class inheritenceFileSer 
{
    

    private String fname = "records.txt";
    private List<Parent2> records = new ArrayList<Parent2>();
    
    public static void main(String[] args)
    {
        new inheritenceFileSer ().run ();
    }    
    
    private void run ()
    {
        records.add (new Parent2 ("Sue", 100));
        records.add (new Child12 ("Kim", 105,"abc"));
        records.add (new Child22 ("Joe", 144.5,10));
        records.add (new Parent2 ("Sue1", 100));
        records.add (new Child12 ("Kim1", 105,"abcsdfdf"));
        records.add (new Child22 ("Joe1", 144.5,10));
        
        try {
                saveRecords ();
                loadRecords ();
            }
        catch (IOException e) 
            {
                System.out.println ("IO error: " + e);
            }
        
        System.out.println ("Save and load finished");
        records.forEach (System.out::println);
    }
    
    private void saveRecords () throws IOException 
    {
        ObjectOutputStream outputst = null;
        
        try
        {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get("test.ser")));           
        }
        catch(IOException ex)
        {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try
        {
            for (Parent2 rec : records)
            {
                outputst.writeObject(rec);
            }
        }
        catch(IOException ex)
        {
            System.err.println("error in adding the objects to the file ");
            System.exit(1);
        } 
        try
        {
            if(outputst !=null)
            outputst.close();           
        }
        catch(IOException ex)
        {
            System.err.println("error in closing the file ");
            System.exit(1);
        }
        
    }
    
    private void loadRecords () throws IOException
    {
        ObjectInputStream inputst=null;
        records.clear ();
        try
        {
            inputst = new ObjectInputStream(Files.newInputStream(Paths.get("test.ser")));           
        }
        catch(IOException ex)
        {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try
        {
           while(true)
            {
              Parent2 par = (Parent2) inputst.readObject();
              records.add(par);
            }
        }
        catch(EOFException ex)
        {
            System.out.println("no more record!");
        }
        catch (ClassNotFoundException ex) 
        {
            System.err.println("error in wrong class in the file ");
        } 
        catch(IOException ex)
        {
            System.err.println("error in add object to the file ");
            System.exit(1);
        }
        try
        {
            if(inputst !=null)
            inputst.close();           
        }
        catch(IOException ex)
        {
            System.err.println("error in close the file ");
            System.exit(1);
        }
    }
}

class Parent2 implements Serializable
{
    String  id;
    double  value;
    
    public Parent2 (String id, double value)
    {
        this.id = id;
        this.value = value;
    }
    
    public String toDelimitedString ()
    {
        return "P,"+id + "," + value;
    }
   
    public String toString ()
    {
        return id + " " + value;
    }
}
class Child12 extends Parent2
{
    private String name;
    public Child12(String id, double value,String name)
    {
        super(id,value);
        this.name=name;
    }
    public String toDelimitedString ()
    {
        //return super.toDelimitedString()+","+name;
        return "1,"+id + "," + value+","+name;
    }
   
    public String toString ()
    {
        return super.toString() +" "+name;
    }
    
}
class Child22 extends Parent2
{
    int num;
    public Child22(String id, double value,int num)
    {
        super(id,value);
        this.num=num;
    }
    public String toDelimitedString ()
    {
        //return super.toDelimitedString()+","+num;
        return "2,"+id + "," + value+","+num;
    }
   
    public String toString ()
    {
        return super.toString() +" "+num;
    }
    
}
   
