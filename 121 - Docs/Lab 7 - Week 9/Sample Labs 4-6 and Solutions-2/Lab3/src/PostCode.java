
import java.io.Serializable;


public class PostCode implements Serializable
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
    
    public String getCity ()
    {
        return city;
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
}
