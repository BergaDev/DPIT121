/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.io.Serializable;


public class PostCode implements Cloneable, Comparable<PostCode> ,Serializable
{
    private String suburb;
    private String city;
    private String state;
    
    public PostCode (String sb, String ct, String st)
    {
        this.suburb= sb;
        this.city= ct;
        this.state= st;
    }
    
    public PostCode (PostCode pc)
    {
        this.suburb=pc.suburb;
        this.city=pc.city;
        this.state=pc.state;
    }
    
    public PostCode clone() throws CloneNotSupportedException
    {
        return (PostCode) super.clone();
    }
    
    public String getSuburb ()
    {
        return suburb;
    }
    
    public String getCity ()
    {
        return city;
    }
    
    public String getState ()
    {
        return state;
    }
    
    public void setCity (String newCity)
    {
        city=newCity;
    }
    
    public String toString ()
    {
        return suburb+", "+city+", "+state;
    }
    
    public String toDelimitedString ()
    {
        return suburb+","+city+","+state;
    }

    @Override
    public int compareTo(PostCode other) 
    {
        return toString().compareTo(other.toString());
    }
}
