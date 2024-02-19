/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.io.Serializable;


public class SalaryEmployee extends Employee implements Serializable
{
    protected double salary;
    
    public SalaryEmployee (String id, String name, Address addr,double salary)
    {
        super (id, name, addr);
        this.salary = salary;
    }
    
    public int getProjectsCount ()
    {
        return projects.size();
    }
    
    public String toString ()
    {
        String output = super.toString()+"\nSalary: "+salary+"\nPay: "+calcPay()+"\nProjects count: "+getProjectsCount();
        output+="\nList of projects: \n";
        for(Project prj : projects)
        {
            output+=prj+"\n";
        }
        return output;
    }
    
    public String toDelimitedString ()
    {
        String output = "SE"+","+id+","+name+","+address.toDelimitedString()+","+salary+","+calcPay()+","+getProjectsCount()+",";
        for(Project prj : projects)
        {
            output+=prj.toDelimitedString()+",";
        }
        return output;
    }
    
    public double calcPay ()
    {
        return salary / 52.3;
    }
}
