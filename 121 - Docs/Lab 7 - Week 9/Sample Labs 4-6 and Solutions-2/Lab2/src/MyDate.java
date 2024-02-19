
enum Month
{
    January(1), February(2), March(3), April(4), May(5), June(6), July(7), August(8), September(9), October(10), November(11), December(12);
    int num;
    int numOfDays;
    Month (int n)
    {
        num=n;
    }
    int getNum ()
    {
        return num;
    }     
}
        
public class MyDate implements Comparable<MyDate>, Cloneable
{
    private int year;
    private Month month;
    private int day;
    
    public MyDate (int y, Month m, int d)
    {
        year=y;
        month=m;
        day=d;
    }
    
    public MyDate (MyDate myDate)
    {
        year=myDate.year;
        month=myDate.month;
        day=myDate.day;
    }
    
    public MyDate clone () throws CloneNotSupportedException
    {
        return (MyDate) super.clone();
    }
    
    public int getMonthNum ()
    {
        return month.num;
    }
    
    public int compareTo (MyDate other)
    {
        if(year>other.year)
        {
            return 1;
        }
        else if (year<other.year)
        {
            return -1;
        }
        else
        {
            if(month.getNum()>other.getMonthNum())
            {
                return 1;
            }
            else if (month.getNum()<other.getMonthNum())
            {
                return -1;
            }
            else
            {
                if(day>other.day)
                {
                    return 1;
                }
                else if(day<other.day)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }
    
    
    public String toString ()
    {
        return day+" "+month+" "+year;
    }

    /*@Override
    public int compareTo1(MyDate other) 
    {
        if(year<other.year)
        {
            return -1;
        }
        else if(year==other.year)
        {
            return compareMonth(other);
        }
        else
            return 1;
    }
    
    public int compareMonth (MyDate other)
    {
        if(month.num==other.getMonthNum())
        {
            return compareDay(other);
        }
        else if(month.num>other.getMonthNum())
        {
            return 1;
        }
        else
            return 0;
    }
    
    public int compareDay (MyDate other)
    {
        if(day<other.day)
            return -1;
        else if (day>other.day)
            return 1;
        else
            return 0;
    }
    */
    
    
    
}
