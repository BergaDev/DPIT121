/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerializationSupplimentary;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author Hooman
 */

public class FileWithSerialization 
{

    Student stu0;
    Student stu1;
    Student stu2;
    Scanner input;
    ArrayList<Student> students= new ArrayList<>();
    ArrayList<Student> students2= new ArrayList<>();
    private static ObjectOutputStream outputst;
    private static ObjectInputStream inputst;

    public static void main(String[] args) throws CloneNotSupportedException 
    {
        FileWithSerialization test = new FileWithSerialization();
        test.run();          
    }
    
    public void run() throws CloneNotSupportedException
    {

        stu0=new Student(0,"template",new Address("1","Crown Street",new PostCode("Wollongong","Wollongong","NSW"),"Australia"),"CSIT121");   
        stu1=new Student(1,"John Hanks",new Address("2","Crown Street",new PostCode("Sydney","Sydney","NSW"),"Australia"),"CSIT121"); 
        stu2=new Student(2,"Tom Hanks",new Address("3","Crown Street",new PostCode("Melbourne","Melbourne","VIC"),"Australia"),"CSIT121"); 
        students.add(stu0);
        students.add(stu1);
        students.add(stu2);
         // test write  serialization file  
        this.openOutFile();
        this.addRecord();
        this.closeOutFile();
        // test read  serialization file
        this.openInFile();
        this.readRecord();
        this.closeInFile();
        boolean flagQuit=true;
        do
        {
            int option= userOptionInput(); 
            if (option==1)
            {
                for ( Student stu:students2)
                    {
                        System.out.println(stu);
                    }    
            }
            else if(option==2)
            {
                userOption2(students2);
            } 
            else if(option==3)
            {
                userOption3(students2);
            }
            else
            {
                flagQuit=false;
            }
        }while(flagQuit);  
    }
   
    public void openOutFile()
    {
        try
        {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get("test.ser")));           
        }
        catch(IOException ex)
        {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }   
    }
    
    public void addRecord()
    {
        try
        {
            for(Student student: students)
            {
                outputst.writeObject(student);
            }
        }
        catch(IOException ex)
        {
            System.err.println("error in add object to the file ");
            System.exit(1);
        }     
    }
    
    public void closeOutFile()
    {
        try
        {
            if(outputst !=null)
                outputst.close();           
        }
        catch(IOException ex)
        {
            System.err.println("error in close the file ");
            System.exit(1);
        }     
    }
    
    public void openInFile()
    {
        try
        {
            inputst = new ObjectInputStream(Files.newInputStream(Paths.get("test.ser")));           
        }
        catch(IOException ex)
        {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }    
    }
    
    public void readRecord()
    {
        try
        {
            while(true)
            {
                Student student = (Student) inputst.readObject();
                students2.add(student);
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
 
    }
     
    public void closeInFile()
    {
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
    public int userOptionInput()
    {
        input = new Scanner(System.in);
        boolean flag =true;
        int option=0;
        do
        {
            try
            {
                System.out.print("Please Enter your option(1. list all current students, 2. copy student record with student ID, 3. deep clone student from the template, 4. Quit): ");  
                option = input.nextInt();
                if (option> 4 || option < 1)              
                {
                    throw new InputMismatchException();   
                }
                
                flag=false;
                input=new Scanner("");
                input.close();
            }
            catch (InputMismatchException ex)
            {
                System.out.println("Your enter the wrong option.");
                input.nextLine();
            }
        }while(flag);
        return option;   
    }
    
    public int userOption2(ArrayList<Student> students) throws CloneNotSupportedException
    {
        input = new Scanner(System.in);
        boolean flag =true;
        int ind=0;
        do
        {
            try
            {
                System.out.print("Please Enter student ID: ");  
                ind = input.nextInt();
                if ((students.size()-1)<ind || ind<0)
                {
                    throw new InputMismatchException();   
                }
                else
                {
                    Student copyStudent = students.get(ind).ShallowCopy();
                    copyStudent.setStudentID(students.size());
                    students.add(copyStudent);
                } 
                flag=false;
                input=new Scanner("");
                input.close();
            }
            catch (InputMismatchException ex)
            {
                System.out.println("Your enter the wrong student ID. ");
                input.nextLine();
            }
        }while(flag);
        return ind;   
    }
    
    public void userOption3(ArrayList<Student> students) throws CloneNotSupportedException
    {
        input = new Scanner(System.in);
        boolean flag =true;
        int i=0;
        Student cloneStu = students.get(i).DeepCopy();
        cloneStu.setStudentID(students.size());
        do
        {
            try
            {
                System.out.println("Student Template is:");
                System.out.println(cloneStu);

                System.out.print("Please enter student name or enter 0 to remaind unchanged: ");  
                String name = input.next();
                if(!name.contentEquals("0"))
                {
                    cloneStu.setName(name);
                }
                System.out.print("Please enter student course ID or enter 0 to remaind unchanged: ");  
                String CourseID = input.next();        
                if(!CourseID.contentEquals("0"))
                {                                            
                    cloneStu.setCourseID(CourseID);
                }

                System.out.print("Please enter Street Number or enter 0 to remaind unchanged: ");  
                String streetNo = input.next();        
                if(!streetNo.contentEquals("0"))
                {                                            
                    cloneStu.getAddress().setStreetNumber(streetNo);
                }
                
                System.out.print("Please enter Street Name or enter 0 to remaind unchanged: ");  
                String streetName = input.next();        
                if(!streetName.contentEquals("0"))
                {                                            
                    cloneStu.getAddress().setStreetName(streetName);
                }

                System.out.print("Please enter suburb or enter 0 to remaind unchanged: ");  
                String suburb = input.next();        
                if(!suburb.contentEquals("0"))
                {                                            
                    cloneStu.getAddress().getPostCode().setSuburb(suburb);
                }
                System.out.print("Please enter city or enter 0 to remaind unchanged: ");  
                String city = input.next();        
                if(!city.contentEquals("0"))
                {                                            
                    cloneStu.getAddress().getPostCode().setCity(city);
                }

                System.out.print("Please enter state or enter 0 to remaind unchanged: ");  
                String state = input.next();        
                if(!state.contentEquals("0"))
                {                                            
                    cloneStu.getAddress().getPostCode().setState(state);
                }

                System.out.print("Please enter Country or enter 0 to remaind unchanged: ");  
                String country = input.next();        
                if(!country.contentEquals("0"))
                {                                            
                    cloneStu.getAddress().setCountry(country);
                }
                flag=false;
                input=new Scanner("");
                input.close();
            }
            catch (InputMismatchException ex)
            {
                System.out.println("Your enter the wrong data ");
                input.nextLine();
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Your enter the wrong data. It should be a number  ");
                input.nextLine();    
            }   
        }while(flag);
        students.add(cloneStu);
    }
    
    public void test(){
        String s = "Hello, This is JavaTpoint.";  
        //Create scanner Object and pass string in it  
        Scanner scan = new Scanner(s);  
        //Check if the scanner has a token  
        System.out.println("Boolean Result: " + scan.hasNext());  
        //Print the string  
        System.out.println("String: " +scan.nextLine());  
        scan.close();           
        System.out.println("--------Enter Your Details-------- ");  
        Scanner in = new Scanner(System.in);  
        System.out.print("Enter your name: ");    
        String name = in.next();   
        System.out.println("Name: " + name);
        in = new Scanner("hello");  
        in.close();
        in = new Scanner(System.in);  
        System.out.print("Enter your age: ");  
        int i = in.nextInt();  
        System.out.println("Age: " + i);  
        System.out.print("Enter your salary: ");  
        double d = in.nextDouble();  
        System.out.println("Salary: " + d);          
        in.close();           
    }
       
}
