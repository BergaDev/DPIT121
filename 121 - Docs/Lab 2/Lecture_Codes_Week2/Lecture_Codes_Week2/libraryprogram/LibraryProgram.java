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
public class LibraryProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Book book1=new Book(1476796629,"Belonging: A German Reckons with History and Home","Nora Krug","Level 2 Section 5",true,Genres.Romance,100);
        Book book2=new Book(399578285,"Nopalito: A Mexican Kitchen","Gonzalo Guzmán","Level 1 Section 6",true,Genres.Fantasy,70);
        User user1=new User(12,"Robert",Type.Student);
        User user2=new User(16,"Sara",Type.Staff);
        Library library=new Library();
        addBook(library,book1);
        addBook(library,book2);
        addUser(library,user1);
        addUser(library,user2);
//        if (library.addBook(book1))
//            System.out.println("The book has been added successfully");
//        else
//            System.out.println("The book can not be added as the ISBN already exists");
//        if (library.addBook(book2))
//            System.out.println("The book has been added successfully");
//        else
//            System.out.println("The book can not be added as the ISBN already exists");

        // test find book
        Book book=library.findBook(1476796629);
        if (book==null)
        {
            System.out.println("The ISBN does not exist");
        }
        else
        {
            book.print();
            book.setLocation("Level 3 Section 1");
        }
        
        
//        if (library.borrowBook(1476796629,12))
//        {
//          System.out.println("Borrowed successfully");  
//        }
//        else
//        {
//            System.out.println("not successful");  
//        }
        borrowBook(library,1476796629,12);
        borrowBook(library,1476796629,12);
        borrowBook(library,1476796629,11);
        borrowBook(library,9999,120);
        returnBook(library,1476796629,12);
        returnBook(library,1476796629,16);
        borrowBook(library,1476796629,16);
        //borrowBook(library,399578285,16);
        user1.print();
        user2.print();
        
        filterByName(library,"German");
        filterByAuthor(library,"a");
        
        ArrayList<String> authors=library.populateDistinctAuthorList();
        ArrayList<Integer> counts=library.getTotalPerAuthor(authors);
        library.reportTotalPPerAuthor(authors, counts);
        
        library.printBooks();
        library.printAllBorrowedBooks();
    }
    public static void addBook(Library library, Book book)
    {
       if (library.addBook(book))
            System.out.println("The book has been added successfully");
        else
            System.out.println("The book can not be added as the ISBN already exists");    
    }
    public static void addUser(Library library, User user)
    {
       if (library.addUser(user))
            System.out.println("The user has been added successfully");
        else
            System.out.println("The user can not be added as the ID already exists");    
    }
    public static void borrowBook(Library library, long ISBN,int userID)
    {
        if (library.borrowBook(ISBN,userID))
        {
          System.out.println("Borrowed successfully");  
        }
        else
        {
            System.out.println("Borrowing was not successful");  
        }
    }
    public static void returnBook(Library library, long ISBN,int userID)
    {
        if (library.returnBook(ISBN,userID))
        {
          System.out.println("Returned successfully");  
        }
        else
        {
            System.out.println("Returning was not successful");  
        }
    }
    
    public static void filterByName( Library library, String pattern)
    {
        System.out.println("\u001B[31m Filter books by title");
        ArrayList<Book> bookList= library.filterBooksByName(pattern);
        Book.printBooks(bookList);
    }
    
    public static void filterByAuthor( Library library, String pattern)
    {
        System.out.println("\u001B[31m Filter books by author");
        ArrayList<Book> bookList= library.filterBooksByAuthor(pattern);
        Book.printBooks(bookList);
    }
    
}
