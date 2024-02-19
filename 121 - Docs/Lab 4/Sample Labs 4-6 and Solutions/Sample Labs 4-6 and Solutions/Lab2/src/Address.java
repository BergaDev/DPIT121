
public class Address implements Cloneable, Comparable<Address>
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
    @Override
    public Address clone() throws CloneNotSupportedException
    {
        Address output = (Address) super.clone();
        output.postCode=postCode.clone();
        return output;
    }

    @Override
    public int compareTo(Address otherAddress) 
    {
        return postCode.compareTo(otherAddress.postCode);
    }
}
