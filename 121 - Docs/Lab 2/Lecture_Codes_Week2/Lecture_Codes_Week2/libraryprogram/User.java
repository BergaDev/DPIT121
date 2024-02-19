/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryprogram;

import java.util.ArrayList;

/**
 *
 * @author hooman
 */
// enum Type {Student,Staff,Admin} // version 1
enum Type 
{
    Student(3),Staff(5),Admin(7);
    int borrowLimit;
    Type(int limit)
    {
        borrowLimit=limit;
    }
    int getBorrowingLimit()
    {
        return borrowLimit;
    }
    
}


public class User 
{
    //private static final int[] maxBorrow={3,5,7};//version 1
    private int ID;
    private String name;
    private Type type;
    private ArrayList<Book> books;
    public User (int id,String name1,Type type1)
    {
        ID=id;
        name=name1;
        type=type1;
        books=new ArrayList<Book>();
    }
    public int getID()
    {
        return ID;
    }
    public static User findUser(ArrayList<User> users,int userID)  // This is different from Library.findBook to show another design
    {
        for (User user:users)
        {
            if (user.getID()==userID)
                return user;  
        }
        return null;      
    }
    public static boolean addUser(ArrayList<User> users,User user) // This is different from Library.addBook to show another design
    {
        if (findUser(users,user.getID())==null)
        {
            users.add(user);
            return true;
        }
        else
            return false;
    }
    public boolean borrowBook(Book book)
    {
        // if ((book.getAvailable())&&(books.size()<maxBorrow[type.ordinal()]))// version 1
        if ((book.getAvailable())&&(books.size()<type.getBorrowingLimit()))
        {
            books.add(book);
            book.setAvailable(false);
            return true;
        }
        else
            return false;
    }
    public boolean returnBook(Book book)
    {
        for (Book bookItem:books)
        {
            if (bookItem.getISBN()==book.getISBN())
            {
                books.remove(bookItem);
                bookItem.setAvailable(true);
                return true;
            }
        }
        return false;
    }
    public void print()
    {
         System.out.println("ID: "+ID+" Name: "+name+"Type: "+type);
         printBorrowedBooks();
    }
    public void printBorrowedBooks()
    {
        System.out.println("\n Borrowing Record ");
        for (Book book:books)
            book.print();
    }
    public int numBorrowedBooks()
    {
        return books.size();
    }
    
    
    
}
    
    
    

