public class MyDate {
  
  protected int year;
  protected int month;
  protected int day;

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

  public String toString(){
    return "Expiry Year: " + year + " Month: " + month + " Day: " + day;
  }

  public void print(){
    System.out.println("Expiry Year: " + year + " Month " + month + " Day: " + day);
  }
}
