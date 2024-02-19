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
public class Library 
{
    ArrayList<Book>books;
    ArrayList<User> users;
    // users to be added later
    public Library()
    {
        books=new ArrayList<Book>();
        users=new ArrayList<User>();
    }
    public Book findBook(long ISBN) //This one also can be moved to Book  Book findBook(ArrayList<Book> books, int userID)
    {
        for (Book book:books)
        {
            if (book.getISBN()==ISBN)
                return book;  
        }
        return null;      
    }
    public boolean addBook(Book book)
    {
        if (findBook(book.getISBN())==null)
        {
            books.add(book);
            return true;
        }
        else
            return false;
    }
    public void printBooks()
    {
        System.out.println("\n Book List in Library  ");
//        for (Book book:books)
//        {
//            book.print();
//        }
        Book.printBooks(books); // Use the Book method or do it here .
    }
    
    
    
    public User findUser(int userID) // This is different from Library.findBook to show another design
    {
//        for (User user:users)
//        {
//            if (user.getID()==userID)
//                return user;  
//        }
//        return null; 
        return User.findUser(users,userID); // call User static findUser
    }
    public boolean addUser(User user) // This is different from Library.addBook to show another design
    {
//        if (findUser(user.getID())==null)
//        {
//            users.add(user);
//            return true;
//        }
//        else
//            return false;
        return User.addUser(users, user); // call User static addUser
    }
    public void printUsers() //Also you can move it to User
    {
        for (User user:users)
        {
            user.print();
        }  
    }
//    public boolean borrowBook(int ISBN)
//    {
//        Book book=findBook(ISBN);
//        if ((book!=null)&&(book.getAvailable()))   
//            {
//              book.setAvailable(false);
//              return true;
//            }
//        return false;  
//    }
    public boolean borrowBook(long ISBN,int userID)
    {
         Book book=findBook(ISBN);
         User user=findUser(userID);
        if ((book!=null)&&(user!=null)&&(book.getAvailable())&&(user.borrowBook(book)))   
            {  
              return true;
            }
        return false;    
    }
    public boolean returnBook(long ISBN,int userID)
    {
         Book book=findBook(ISBN);
         User user=findUser(userID);
        if ((book!=null)&&(user!=null)&&(!book.getAvailable())&&(user.returnBook(book)))   
            {  
              return true;
            }
        return false;    
    }
    public void printAllBorrowedBooks()
    {
        System.out.println("\n all Borrowed Books in Library  ");
        for (Book book:books)
        {
            if (!book.getAvailable())
                book.print();
        } 
        
    }
     public ArrayList<Book> filterBooksByName(String pattern)
    {
        return Book.filterByName(books, pattern); // Use the book method
        
    }
    public ArrayList<Book> filterBooksByAuthor(String pattern) // This one does the whole logic here 
    {
        ArrayList<Book> filteredBooks= new ArrayList<Book>();
        for (Book book:books)
        {
            if (book.getAuthor().contains(pattern))
            {
                filteredBooks.add(book);
            }
        } 
        return filteredBooks;     
    }
    public ArrayList<Double> getTotalPricePerGenre()
    {
        ArrayList<Double> totalPrice=new ArrayList<Double>();
        double total=0;
        for (Genres genre:Genres.values())
        {
            total=0;
            for (Book book:books)
            {
                if(book.getGenre().equals(genre))
                    total+=book.getPrice();
            }
            totalPrice.add(total);
        }
        return totalPrice;
    }
        
 
    public void reportTotalPricePerGenre(ArrayList<Double> prices)
    {
        System.out.println("\n Total Price Per Genre ");
        for (int i=0;i<prices.size();i++)
            System.out.println(Genres.values()[i]+"     "+prices.get(i));  
    }
    public ArrayList<String> populateDistinctAuthorList()
    {
        ArrayList<String> authors =new ArrayList<String>();
        for (Book book:books)
        {
            boolean found=false;
            for (String author:authors)
            {
                if (book.getAuthor().equals(author))
                {
                    found=true;
                    break;
                }
            }
            if (!found)
                authors.add(book.getAuthor());
        }
        return authors;   
    }
    public ArrayList<Integer> getTotalPerAuthor(ArrayList<String> authors)
            
    {
        ArrayList<Integer> totalCount=new ArrayList<Integer>();
        int count=0;
        for (String author:authors)
        {
            count=0;
            for (Book book:books)
            {
                if(book.getAuthor().equals(author))
                    count++;
            }
            totalCount.add(count);
        }
        return totalCount;
    }
    public void reportTotalPPerAuthor(ArrayList<String> authors, ArrayList<Integer> counts)
    {
        System.out.println("\n Total Number of books Per Author ");
        for (int i=0;i<counts.size();i++)
            System.out.println(authors.get(i)+"     "+counts.get(i));  
    }
    
    
   
}
