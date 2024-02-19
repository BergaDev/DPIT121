
package Part4and5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InheritanceFiling 
{
    private String fileName = "employees2.txt";
    private List<Employee> records = new ArrayList<> ();
    
    public static void main (String[] args) throws CloneNotSupportedException
    {
        new InheritanceFiling ().run();
    }
    
    public ArrayList<Employee> shallowCopy ()
    {
        ArrayList<Employee> shallow = new ArrayList<> ();
        for(Employee emp : records)
        {
            shallow.add(emp);
        }
        return shallow;
    }
    
    public ArrayList<Employee> deepCopyClone () throws CloneNotSupportedException
    {
        ArrayList<Employee> deepCopyC = new ArrayList<> ();
        for (Employee emp : records)
        {
            deepCopyC.add(emp.clone());
        }
        return deepCopyC;
    }
    
    //TEXT FILE
    private void run () throws CloneNotSupportedException
    {
        PostCode pc1 = new PostCode ("Keiraville", "Wollongong", "NSW");
        PostCode pc2 = new PostCode ("Jimbaran", "Badung", "Bali");
        PostCode pc3 = new PostCode ("Diponegoro", "Denpasar", "Bali");
        
        Address addr1 = new Address (1, "Good Rd", pc1, "Australia");
        Address addr2 = new Address (20, "Rama Rd", pc2, "Indonesia");
        Address addr3 = new Address (15, "Kresna Rd", pc3, "Indonesia");
        
        Employee emp1 = new SalaryEmployee ("ID1", "Carol", addr1, 23000);
        Employee emp2 = new SalaryEmployee ("ID2", "Intan", addr2, 90000);
        Employee emp3 = new CasualEmployee ("ID3", "Koko", addr3, 15, 30);
        
        Project pr1 = new Project ("1", "Project 1");
        Project pr2 = new Project ("2", "Project 2");
        Project pr3 = new Project ("3", "Project 3");
        Project pr4 = new Project ("4", "Project 4");
        Project pr5 = new Project ("5", "Project 5");
        Project pr6 = new Project ("6", "Project 6");
        
        emp1.addProjects(pr1);
        emp1.addProjects(pr2);
        emp1.addProjects(pr3);
        
        emp2.addProjects(pr4);
        
        emp3.addProjects(pr5);
        emp3.addProjects(pr6);
        
        records.add(emp3);
        records.add(emp2);
        records.add(emp1);

        try {
                saveRecords ();
                loadRecords ();
            }
        catch (IOException e) 
            {
                System.out.println ("IO error: " + e);
            }

        System.out.println ("Save and load finished\n");
        
        System.out.println("========================ORIGINAL ARRAYLIST========================");
        records.forEach (System.out::println);
        
        System.out.println("========================SORTED ORIGINAL ARRAYLIST========================");
        Collections.sort(records);
        records.forEach (System.out::println);
        
        System.out.println("========================SHALLOW COPY========================");
        ArrayList<Employee> shallowCopy = shallowCopy();
        shallowCopy.forEach(System.out::println);
        
        System.out.println("=================AFTER SORTING THE SHALLOW COPY BASED ON POST CODE (USING COMPARATOR)=================");
        Collections.sort(shallowCopy, new PostCodeComparator());
        shallowCopy.forEach(System.out::println);
        
        System.out.println("========================DEEP COPY========================");
        ArrayList<Employee> deepCopy = deepCopyClone();
        deepCopy.forEach(System.out::println);
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
                    double pay = Double.parseDouble(field[11]);
                    int numOfProjects = Integer.parseInt(field[12]);
                    
                    PostCode pc1 = new PostCode (suburb, city, state);
                    Address addr1 = new Address (streetNum, streetName, pc1, country);
                    CasualEmployee ce = new CasualEmployee (id, name, addr1, hours, rate);
                    
                    
                    for(int i=0;i<numOfProjects;i++)
                    {
                        String projectID = field[13+i*2];
                        String projectName = field[14+i*2];
                        ce.addProjects(new Project(projectID, projectName));
                    }
                    
                    records.add (ce);
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
                    pay = Double.parseDouble(field[10]);
                    int numOfProjects2 = Integer.parseInt(field[11]);
                    
                    PostCode pc2 = new PostCode (suburb, city, state);
                    Address addr2 = new Address (streetNum, streetName, pc2, country);
                    SalaryEmployee se = new SalaryEmployee (id, name, addr2, salary);
                    
                    for(int i=0;i<numOfProjects2;i++)
                    {
                        String projectID = field[12+i*2];
                        String projectName = field[13+i*2];
                        se.addProjects(new Project(projectID, projectName));
                    }
                    
                    records.add (se);
                    break;    
            }

            line = in.readLine();
            }
        in.close ();
    }
}
