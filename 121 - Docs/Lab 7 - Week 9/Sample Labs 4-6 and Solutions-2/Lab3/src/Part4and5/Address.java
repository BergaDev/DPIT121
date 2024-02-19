/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.io.Serializable;

public class Address implements Cloneable, Comparable<Address>, Serializable
{
    private int streetNum;
    private String streetName;
    private PostCode postCode;
    private String country;
    
    public Address (int snum, String sname, PostCode pcode, String cntr)
    {
        this.streetNum= snum;
        this.streetName= sname;
        this.postCode= pcode;
        this.country= cntr;
    }
    
    public PostCode getPostCode ()
    {
        return postCode;
    }
    
    public String getSuburb ()
    {
        return postCode.getSuburb();
    }
    
    public String getCity ()
    {
        return postCode.getCity();
    }
    
    public String getState ()
    {
        return postCode.getState();
    }
    
    public Address (Address addr)
    {
        this.streetNum = addr.streetNum;
        this.streetName = addr.streetName;
        this.postCode = new PostCode (addr.postCode);
        this.country = addr.country;
    }
    
    @Override
    public Address clone() throws CloneNotSupportedException
    {
        Address output = (Address) super.clone();
        output.postCode = postCode.clone();
        return output;
    }
    
    public void setCity (String newCity)
    {
        postCode.setCity(newCity);
    }
    
    public String toString ()
    {
        return streetNum+", "+streetName+", "+postCode+", "+country;
    }
    
    public String toDelimitedString ()
    {
        return streetNum+","+streetName+","+postCode.toDelimitedString()+","+country;
    }

    @Override
    public int compareTo(Address other) 
    {
        return postCode.compareTo(other.postCode);
    }
}

