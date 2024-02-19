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

public class PostCode implements Cloneable,Serializable
{
    private String suburb;
    private String city;
    private String state;
    public PostCode(String suburb, String city, String state) 
    {
        this.suburb = suburb;
        this.city = city;
        this.state = state;
    }
    public String getSuburb() 
    {
        return suburb;
    }
    public void setSuburb(String suburb) 
    {
        this.suburb = suburb;
    }

    public String getCity() 
    {
        return city;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getState() 
    {
        return state;
    }

    public void setState(String state) 
    {
        this.state = state;
    }       
    @Override
    protected PostCode clone() throws CloneNotSupportedException{
        return (PostCode) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString() {
        return String.format("Suburb: %s, City: %s, State: %s", this.suburb,this.city,this.state); //To change body of generated methods, choose Tools | Templates.
    }
    
}
