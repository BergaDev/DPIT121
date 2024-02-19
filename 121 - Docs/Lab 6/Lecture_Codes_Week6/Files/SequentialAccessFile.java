/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;


import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class SequentialAccessFile 
{
    
    private static Formatter outputst;
    private static Scanner inputst;

    public static void main(String[] args) throws CloneNotSupportedException, IOException 
    {
        SequentialAccessFile test = new SequentialAccessFile();
        test.run();       
    }
    public void run() throws IOException 
    {
        // test write   file  
        this.openOutFile();
        this.addRecord();
        this.closeOutFile();
        // test read   file
        this.openInFile();
        this.readRecord();
        this.closeInFile();
    }    
    public void openOutFile() throws IOException
    {
        try
        {
            File file = new File(Paths.get("test.txt").toUri());
            //outputst = new Formatter(file);
            outputst = new Formatter("test.txt");
            //outputst = new Formatter(Files.newOutputStream(Paths.get("test.txt")));           
        }
        catch(SecurityException ex)
        {
            System.err.println("Write permission denined. Terminating");
            System.exit(1); // terminate program
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("Error in open the file Terminating.");
            System.exit(1);
        }
             
    }
    
    public void addRecord()
    { 
        Scanner input =new Scanner(System.in);
        String temp = String.format("%s%n%s%n? ","Enter Account number, first name, last name and balance.","Enter q to end");
        System.out.print(temp);
        boolean flag1 = true; // loop for n accounts
        boolean flag2 = true; // input mismatch for account number
        String q=""; 
        int accountNumber =0; 
        while(flag1) // read several account in a loop
        {  
            while(flag2) // read one account
            {
                try
                {
                    q=input.next();
                    if(q.equalsIgnoreCase("q"))
                    {
                        flag2=false;//break both loops
                        flag1=false;
                    }
                    else
                    {
                        accountNumber=Integer.parseInt(q);
                        flag2=false; //means number is entered correctly
                    }  
                }
                catch(InputMismatchException e)
                {
                    System.out.print("Input Mismatch");
                    input.nextLine();
                }
                catch(Exception e)
                {
                    System.out.printf("If you want to exit type q%n? ");
                    input.nextLine();
                }
            }//while till user enters a number  
            flag2=true;  //reset for next account  
            if (flag1) // is q entered to break?
            {
                try
                {    
                    outputst.format("%d %s %s %.2f%n",accountNumber, input.next(),input.next(),input.nextDouble());  
                    accountNumber=0;
                    input.nextLine();  
                }
                catch(FormatterClosedException ex)
                {
                    System.err.println("Error writing in to the file. Terminating.");
                    break;             
                }
                catch(NoSuchElementException ex)
                {
                    System.err.println("Invalid input. Please try again.");
                    input.nextLine();             
                }
                System.out.print("? ");
            }   
        }// end while   
    }
     
    public void closeOutFile()
    {
        if(outputst !=null)
            outputst.close();       
    }
    
    public void openInFile()
    {
        try
        {
            inputst = new Scanner(Paths.get("test.txt"));           
        }
        catch(IOException ex)
        {
            System.err.println("error in open the file.");
            System.exit(1);
        }   
    }
    public void readRecord()
    {
       String temp = String.format("%-15s%-12s%-12s%10s%n","Account number", "First name", "Last name", "Balance");
       System.out.print(temp);             
        try
        { 
            while(inputst.hasNext())
            {
                System.out.printf("%-15s%-12s%-12s%10s%n",inputst.nextInt(),inputst.next(), inputst.next(), inputst.nextDouble());
            }
        }
        catch(NoSuchElementException ex)
        {
            System.out.println("File improperly formed. Terminating.");
        }
        catch (IllegalStateException ex)
        {
            System.err.println("error in reading in the file ");
        }     
    }    
    public void closeInFile()
    {
        if(inputst !=null)
            inputst.close();   
    }             
}
