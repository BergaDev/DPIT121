import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InheritanceFilling 
{
    private String fileName = "employees.txt";
    private List<Employee> records = new ArrayList<> ();
    
    public static void main(String[] args) 
    {
        new InheritanceFilling ().run();
    }
    
    //TEXT FILE
    private void run ()
    {
        PostCode pc1 = new PostCode ("Keiraville", "Wollongong", "NSW");
        PostCode pc2 = new PostCode ("Jimbaran", "Badung", "Bali");
        PostCode pc3 = new PostCode ("Nusa Dua", "Badung", "Bali");
        
        Address addr1 = new Address (1, "Good Rd", pc1, "Australia");
        Address addr2 = new Address (20, "Rama Rd", pc2, "Indonesia");
        Address addr3 = new Address (15, "Kresna Rd", pc3, "Indonesia");
        
        Employee emp1 = new SalaryEmployee ("ID1", "Antonio", addr1, 23000);
        Employee emp2 = new SalaryEmployee ("ID2", "Intan", addr2, 90000);
        Employee emp3 = new CasualEmployee ("ID3", "Koko", addr3, 15, 30);
        
        records.add(emp1);
        records.add(emp2);
        records.add(emp3);

        try {
                saveRecords ();
                loadRecords ();
            }
        catch (IOException e) 
            {
                System.out.println ("IO error: " + e);
            }

        System.out.println ("Save and load finished\n");
        records.forEach (System.out::println);
    }
    
    private void saveRecords () throws IOException 
    {
        BufferedWriter out = new BufferedWriter (new FileWriter (fileName));
        for (Employee rec : records)
        {
            out.write (rec.toDelimitedString () + "\n");
        }
        out.close ();
    }
    
    private void loadRecords () throws IOException
    {
        records.clear ();
        
        BufferedReader in = new BufferedReader (new FileReader (fileName));
        String line = in.readLine();
        while (line != null) 
        {
            line = line.trim ();
            String[] field = line.split(",");
            switch (field[0])
            {
                case "CE":
                    String id = field[1];
                    String name = field[2];
                    int streetNum = Integer.parseInt(field[3]);
                    String streetName = field[4];
                    String suburb = field[5];
                    String city = field[6];
                    String state = field[7];
                    String country = field[8];
                    double hours = Double.parseDouble(field[9]);
                    double rate = Double.parseDouble(field[10]);
                    
                    PostCode pc1 = new PostCode (suburb, city, state);
                    Address addr1 = new Address (streetNum, streetName, pc1, country);
                    
                    records.add (new CasualEmployee (id, name, addr1, hours, rate));
                    break;
                case "SE":
                    id = field[1];
                    name = field[2];
                    streetNum = Integer.parseInt(field[3]);
                    streetName = field[4];
                    suburb = field[5];
                    city = field[6];
                    state = field[7];
                    country = field[8];
                    double salary = Double.parseDouble(field[9]);
                    
                    PostCode pc2 = new PostCode (suburb, city, state);
                    Address addr2 = new Address (streetNum, streetName, pc2, country);
                    
                    records.add (new SalaryEmployee (id, name, addr2, salary));
                    break;    
            }

            line = in.readLine();
            }
        in.close ();
    }
    
}
