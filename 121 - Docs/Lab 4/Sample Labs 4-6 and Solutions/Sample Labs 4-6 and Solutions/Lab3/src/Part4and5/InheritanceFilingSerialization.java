
package Part4and5;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class InheritanceFilingSerialization 
{
    //private String fileName = "employees3.txt";
    private List<Employee> records = new ArrayList<> ();
    public static void main (String[] args) throws CloneNotSupportedException
    {
        new InheritanceFilingSerialization ().run();
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
    
    private void run () throws CloneNotSupportedException
    {
//        PostCode pc1 = new PostCode ("Keiraville", "Wollongong", "NSW");
//        PostCode pc2 = new PostCode ("Jimbaran", "Badung", "Bali");
//        PostCode pc3 = new PostCode ("Nusa Dua", "Badung", "Bali");
//        
//        Address addr1 = new Address (1, "Good Rd", pc1, "Australia");
//        Address addr2 = new Address (20, "Rama Rd", pc2, "Indonesia");
//        Address addr3 = new Address (15, "Kresna Rd", pc3, "Indonesia");
//        
//        Employee emp1 = new SalaryEmployee ("ID1", "Stephanie", addr1, 23000);
//        Employee emp2 = new SalaryEmployee ("ID2", "Intan", addr2, 90000);
//        Employee emp3 = new CasualEmployee ("ID3", "Koko", addr3, 15, 30);
//        
//        Project pr1 = new Project ("1", "Project 1");
//        Project pr2 = new Project ("2", "Project 2");
//        Project pr3 = new Project ("3", "Project 3");
//        Project pr4 = new Project ("4", "Project 4");
//        Project pr5 = new Project ("5", "Project 5");
//        Project pr6 = new Project ("6", "Project 6");
//        
//        emp1.addProjects(pr1);
//        emp1.addProjects(pr2);
//        emp1.addProjects(pr3);
//        
//        emp2.addProjects(pr4);
//        
//        emp3.addProjects(pr5);
//        emp3.addProjects(pr6);
//        
//        records.add(emp1);
//        records.add(emp2);
//        records.add(emp3);

        try {
                //saveRecords ();
                loadRecords ();
            }
        catch (IOException e) 
            {
                System.out.println ("IO error: " + e);
            }

        System.out.println ("Save and load finished\n");
        
        System.out.println("========================ORIGINAL ARRAYLIST========================");
        records.forEach (System.out::println);
        
        Collections.sort(records);
        System.out.println("========================SORTED ORIGINAL ARRAYLIST========================");
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
            for (Employee rec : records)
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
              Employee par = (Employee) inputst.readObject();
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
