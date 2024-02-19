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
class inheritenceFileSer2 
{
    

    private String fname = "records.txt";
    private List<Parent3> records = new ArrayList<Parent3>();
    
    public static void main(String[] args)
    {
        new inheritenceFileSer2 ().run ();
    }    
    
    private void run ()
    {
        ArrayList<Record2> recs=new ArrayList<Record2>();
        ArrayList<Record2> recs2=new ArrayList<Record2>();
        recs.add(new Record2(1,"rec1"));
        recs.add(new Record2(2,"rec2"));
        recs.add(new Record2(3,"rec3"));
        recs2.add(new Record2(4,"rec4"));
        recs2.add(new Record2(5,"rec5"));
        recs2.add(new Record2(6,"rec6"));
        records.add (new Parent3 ("Sue", 100,recs));
        records.add (new Child13 ("Kim", 105,"abc",recs2));
        records.add (new Child23 ("Joe", 144.5,10,recs));
        records.add (new Parent3 ("Sue1", 100,recs2));
        records.add (new Child13 ("Kim1", 105,"abcsdfdf",recs));
        records.add (new Child23 ("Joe1", 144.5,10,recs2));
        
        try {
                saveRecords ();
                recs.clear();
                recs2.clear();
                records.clear();
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
            for (Parent3 rec : records)
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
        //records.clear ();
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
              Parent3 par = (Parent3) inputst.readObject();
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

class Parent3 implements Serializable
{
    String  id;
    double  value;
    ArrayList<Record2> records;
    
    public Parent3 (String id, double value, ArrayList<Record2> records)
    {
        this.id = id;
        this.value = value;
        this.records=records;
    }
    
    public String toString ()
    {
        String recs="";
        for ( Record2 rec:records)
        {
            recs+=rec;
        }
        return "Records:\n "+recs+id + " " + value;
    }
}

class Child13 extends Parent3
{
    private String name;
    
    public Child13(String id, double value,String name,ArrayList<Record2> records)
    {
        super(id,value,records);
        this.name=name;
    }
    
    public String toString ()
    {
        return super.toString() +" "+name;
    } 
}
class Child23 extends Parent3
{
    int num;
    
    public Child23(String id, double value,int num,ArrayList<Record2> records)
    {
        super(id,value,records);
        this.num=num;
    }
    
    public String toString ()
    {
        return super.toString() +" "+num;
    }
    
}
class Record2 implements Serializable
{
    int id;
    String name;
    public Record2(int i,String n)
    {
        id=i;
        name=n;
    }
    public String toString()
    {
        return id+" "+name+"\n";
    }   
}
   
