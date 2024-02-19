/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hooman
 */
public class InheritanceFiling 
{
    

    private String fname = "records.txt";
    private List<Parent> records = new ArrayList<Parent>();
    
    public static void main(String[] args)
    {
        new InheritanceFiling ().run ();
    }    
    
    private void run ()
    {
        records.add (new Parent ("Sue", 100));
        records.add (new Child1 ("Kim", 105,"abc"));
        records.add (new Child2 ("Joe", 144.5,10));
        records.add (new Parent ("Sue1", 100));
        records.add (new Child1 ("Kim1", 105,"abcsdfdf"));
        records.add (new Child2 ("Joe1", 144.5,10));
        
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
        BufferedWriter out = new BufferedWriter (new FileWriter (fname));
        for (Parent rec : records)
        {
            out.write (rec.toDelimitedString () + "\n");
        }
        out.close ();
    }
    
    private void loadRecords () throws IOException
    {
        records.clear ();
        
        BufferedReader in = new BufferedReader (new FileReader (fname));
        String line = in.readLine();
        while (line != null) 
        {
            line = line.trim ();
            String[] field = line.split(",");
            switch (field[0])
            {
                case "P":
                    String id = field[1];
                    double value = Double.parseDouble (field[2]);
                    records.add (new Parent (id, value));
                    break;
                case "1":
                    id = field[1];
                    value = Double.parseDouble (field[2]);
                    String name=field[3];
                    records.add (new Child1 (id, value,name));
                    break;
                case "2":
                    id = field[1];
                    value = Double.parseDouble (field[2]);
                    int num=Integer.parseInt(field[3]);
                    records.add (new Child2 (id, value,num));
                    break;     
            }

            line = in.readLine();
            }
        in.close ();
    }
}

class Parent
{
    String  id;
    double  value;
    
    public Parent (String id, double value)
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
class Child1 extends Parent
{
    String name;
    public Child1(String id, double value,String name)
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
class Child2 extends Parent
{
    int num;
    public Child2(String id, double value,int num)
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
   
