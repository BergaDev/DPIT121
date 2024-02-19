//Matthew Bergamini - 8137225
import java.util.ArrayList;
import java.lang.*;

public class MyDate implements Cloneable, Comparable<MyDate>{
  
  protected int year;
  protected int month;
  protected int day;

  public MyDate (int year, int month, int day){
    this.year = year;
    this.month = month;
    this.day = day;
  }

  MyDate (MyDate mdCopy){
    this.year = mdCopy.year;
    this.month = mdCopy.month;
    this.day = mdCopy.day;
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

  public MyDate clone() throws CloneNotSupportedException
  {
      return (MyDate) super.clone();
  }

  //Works, want to check which output to use 
  @Override
  public int compareTo(MyDate other) {
    if (this.year == other.year && this.month == other.month && this.day == other.day) {
      return 0;
  } else if (this.year < other.year || (this.year == other.year && this.month < other.month)
          || (this.year == other.year && this.month == other.month && this.day < other.day)) {
      return -1;
  } else {
      return 1;
  }
  }

  
}
