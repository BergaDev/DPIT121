/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.*;
import java.util.*;

public class Basic2 
{
    private String fname = "records.txt";
    private List<Record> records = new ArrayList<Record>();
    
    public static void main(String[] args)
    {
        new Basic2 ().run ();
    }    
    
    private void run ()
    {
        records.add (new Record ("Sue", 100));
        records.add (new Record ("Kim", 105));
        records.add (new Record ("Joe", 144));
        
        try {
                saveRecords ();
                records.clear ();
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
        for (Record rec : records)
        {
            out.write (rec.toDelimitedString () + "\n");
        }
        out.close ();
    }
    
    private void loadRecords () throws IOException
    {
        //records.clear ();
        BufferedReader in = new BufferedReader (new FileReader (fname));
        String line = in.readLine();
        while (line != null) {
            line = line.trim ();
            String[] field = line.split(",");           
            String id = field[0];
            double value = Double.parseDouble (field[1]);
            records.add (new Record (id, value));
            line = in.readLine();
            }
        in.close ();
    }
}

class Record
{
    String  id;
    double  value;
    
    public Record (String id, double value)
    {
        this.id = id;
        this.value = value;
    }
    
    public String toDelimitedString ()
    {
        return id + "," + value;
    }
   
    public String toString ()
    {
        return id + " " + value;
    }
}