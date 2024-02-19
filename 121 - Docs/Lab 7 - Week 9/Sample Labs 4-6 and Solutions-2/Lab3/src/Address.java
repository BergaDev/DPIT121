
import java.io.Serializable;

public class Address implements Serializable
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
    
    public Address (Address addr)
    {
        this.streetNum = addr.streetNum;
        this.streetName = addr.streetName;
        this.postCode = new PostCode (addr.postCode);
        this.country = addr.country;
    }
    
    public String getCity ()
    {
        return postCode.getCity();
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
}
