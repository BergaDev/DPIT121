/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;


public abstract class Employee implements Cloneable, Serializable, Comparable<Employee>
{
    protected String id;
    protected String name;
    protected Address address;
    protected ArrayList<Project> projects;
    
    public Employee (String id, String name, Address addr)
    {
        this.id = id;
        this.name = name;
        this.address = addr;
        this.projects = new ArrayList<> ();
    }
    
    public String getSuburb ()
    {
        return address.getSuburb();
    }
    
    public String getCity ()
    {
        return address.getCity();
    }
    
    public String getState ()
    {
        return address.getState();
    }
    
    public String getPostCode ()
    {
        return address.getPostCode().toString();
    }
    
    @Override
    public Employee clone () throws CloneNotSupportedException
    {
        Employee output = (Employee) super.clone();
        output.address=address.clone();
        output.projects=new ArrayList<Project>();
        for (Project prj : projects)
        {
            output.projects.add(prj.clone());
        }
        return output;
    }
    
    public void addProjects (Project project)
    {
        projects.add(project);
    }
    
    public String toString ()
    {
        return "ID: "+id+"\nName: "+name+"\nAddress: "+address;
    }
    
    @Override
    public int compareTo (Employee other)
    {
        return name.compareTo(other.name);
    }
    
    
    public abstract String toDelimitedString ();
    
    public abstract double calcPay ();
    
    public abstract int getProjectsCount ();
}

