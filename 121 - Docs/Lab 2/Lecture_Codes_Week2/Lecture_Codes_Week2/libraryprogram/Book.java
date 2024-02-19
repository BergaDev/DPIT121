package libraryprogram;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hooman
 */
enum Genres {Fantasy,ScienceFiction,Westerns,Romance,Thriller,Mystery,DetectiveStory,Dystopia}

public class Book 
{
    private long ISBN;
    private String name;
    private String author;
    private String location;
    private boolean available;
    private Genres genre; 
    private double price;
    public Book(long ibsn,String name, String auth,String loc,boolean avail,Genres gen, double price)
    {
        ISBN=ibsn;
        this.name=name;
        author=auth;
        location=loc;
        available=avail;
        genre=gen;
        this.price=price;
    }
    public long getISBN()
    {
        return ISBN;
    }
    public String getAuthor()
    {
        return author;
    }
    public boolean getAvailable()
    {
        return available;
    }
    public double getPrice()
    {
        return price;
    }
    public Genres getGenre()
    {
        return genre;
    }
    public void setAvailable(boolean avail)
    {
        available=avail;
    }
    public String getLocation()
    {
        return location;
    }
    public void setLocation(String loc)
    {
        location=loc;
    }
    public void print()
    {
        System.out.println("ISBN: "+ISBN+ " NAME: "+ name+ " Author: "+author+" Location: "+location+" Available: "+available+" price:"+price+" Genre: "+genre);
    }
    
    public static void printBooks(ArrayList<Book> books) // another design to put it in the library
    {
        for (Book book:books)
        {
            book.print();
        }  
    }
    
    public static ArrayList<Book> filterByName(ArrayList<Book> books,String pattern)
    {
        ArrayList<Book> filteredBooks= new ArrayList<Book>();
        for (Book book:books)
        {
            if (book.name.contains(pattern))
            {
                filteredBooks.add(book);
            }
        } 
        return filteredBooks; 
    }
 
}
