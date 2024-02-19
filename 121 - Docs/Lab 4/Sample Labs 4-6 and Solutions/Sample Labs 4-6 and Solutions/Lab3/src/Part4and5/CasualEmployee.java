/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.io.Serializable;


public class CasualEmployee extends Employee implements Serializable
{
    protected double hours;
    protected double rate;
    
    public CasualEmployee (String id, String name, Address addr, double hours, double rate)
    {
        super (id, name, addr);
        this.hours = hours;
        this.rate = rate;
    }
    
    public int getProjectsCount ()
    {
        return projects.size();
    }
    
    public String toString ()
    {
        String output = super.toString()+"\nHours: "+hours+"\nRate: "+rate+"\nPay: "+calcPay()+"\nProjects count: "+getProjectsCount();
        output+="\nList of projects: \n";
        for(Project prj : projects)
        {
            output+=prj+"\n";
        }
        return output;
    }
    
    public String toDelimitedString ()
    {
        String output = "CE"+","+id+","+name+","+address.toDelimitedString()+","+hours+","+rate+","+calcPay()+","+getProjectsCount()+",";
        for(Project prj : projects)
        {
            output+=prj.toDelimitedString()+",";
        }
        return output;
    }
    
    public double calcPay ()
    {
        return hours * rate;
    }

    
}

