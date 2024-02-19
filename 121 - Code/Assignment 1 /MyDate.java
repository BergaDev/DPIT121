//Matthew Bergamini - 8137225
import java.util.ArrayList;

public class MyDate {
  
  protected int year;
  protected int month;
  protected int day;

  public MyDate (int year, int month, int day){
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public void setYear(int year){
    this.year = year;
  }
  public void setMonth(int month){
    this.month = month;
  }
  public void setDay(int day){
    this.day = day;
  }
  public int getYear(){
    return year;
  }
  public int getMonth(){
    return month;
  }
  public int getDay(){
    return day;
  }
  /* 
  public MyDate getExpiryDate(){
    return expiryDate;
  }
  */

  public String toString(){
    return "Expiry Year: " + year + " Month: " + month + " Day: " + day;
  }

  public void print(){
    System.out.println("Expiry Year: " + year + " Month " + month + " Day: " + day);
  }


  Boolean isExpired(MyDate expiryDate){
    if (year > expiryDate.year){
      return false;
    } else if (year == expiryDate.year && month > expiryDate.month){
      return false;
    } else if (year == expiryDate.year && month == expiryDate.month && day > expiryDate.day){
      return false;
    } else{
    return true;
    }
  }

  
}
