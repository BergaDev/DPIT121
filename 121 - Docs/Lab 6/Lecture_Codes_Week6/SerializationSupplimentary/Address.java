/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SerializationSupplimentary;

import java.io.Serializable;
/**
 *
 * @author Hooman
 */

public class Address implements Cloneable,Serializable
{
    private String streetNumber;
    private String streetName;
    private PostCode postCode;
    private String Country;
    public Address(String streetNumber, String streetName, PostCode postCode, String Country) 
    {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postCode = postCode;
        this.Country = Country;
    }

    public String getStreetNumber() 
    {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) 
    {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() 
    {
        return streetName;
    }

    public void setStreetName(String streetName) 
    {
        this.streetName = streetName;
    }

    public PostCode getPostCode() {
        return postCode;
    }

    public void setPostCode(PostCode postCode) 
    {
        this.postCode = postCode;
    }

    public String getCountry() 
    {
        return Country;
    }

    public void setCountry(String Country) 
    {
        this.Country = Country;
    }
    
    @Override
    protected Address clone() throws CloneNotSupportedException 
    {
        Address add1=(Address) super.clone();
        add1.postCode=this.postCode.clone();
        return add1; 
    }
   
    @Override
    public String toString() 
    {
        return String.format("Street No.: %s, Street Name: %s, %s, Country: %s", this.streetNumber,this.streetName,this.postCode,this.Country);  //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
