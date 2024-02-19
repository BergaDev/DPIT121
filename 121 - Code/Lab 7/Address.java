//Matthew Bergamini - 8137225

import java.io.Serializable;

public class Address implements Cloneable, Comparable<Address>, Serializable{
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

  public Address (Address addressCopy){
    this.streetNum = addressCopy.streetNum;
    this.street = addressCopy.street;
    this.suburb = addressCopy.suburb;
    this.city = addressCopy.city;
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
    System.out.println("Street Number: " + streetNum + ", Street Name: " + street + ", Suburb: " + suburb + ", City: " + city);
  }

  public Address clone() throws CloneNotSupportedException{
    return (Address) super.clone();
  }

  //Works
  @Override
  public int compareTo(Address Address) {
    if (streetNum == Address.streetNum && street == Address.street && suburb == Address.suburb && city == Address.city){
      return 69;
    } else {
      return 404;
    }
  }

  public String toDelimitedString() {
    return streetNum + "," + street + "," + suburb + "," + city;
  }
}
