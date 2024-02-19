public class Address {
  protected int streetNum;
  protected String street;
  protected String suburb;
  protected String city;

  //Constructor
  public Address (int streetNum, String street, String suburb, String city){
    this.streetNum = streetNum;
    this.street = street;
    this.suburb = suburb;
    this.city = city;
  }

//Starting setters
  public void setStreetNum(int streetNum){
    this.streetNum = streetNum;
  }
  public void setStreet(String street){
    this.street = street;
  }
  public void setSuburb(String suburb){
    this.suburb = suburb;
  }
  public void setCity(String city){
    this.city = city;
  }
//Starting getters
  public int getStreetNum(){
    return streetNum;
  }
  public String getStreet(){
    return street;
  }
  public String getSuburb(){
    return suburb;
  }
  public String getCity(){
    return city;
  }

  //Print address information per user
  public void print(){
    System.out.println("Street Number: " + streetNum + " Street Name: " + street + " Suburb: " + suburb + " City: " + city);
  }
}
