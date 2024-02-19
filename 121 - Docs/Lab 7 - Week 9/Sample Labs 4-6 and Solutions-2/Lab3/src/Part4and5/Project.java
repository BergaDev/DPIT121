/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.io.Serializable;

public class Project implements Cloneable, Serializable
{
    private String projectId;
    private String projectName;
    
    public Project (String id, String name)
    {
        this.projectId = id;
        this.projectName = name;
    }
    
    @Override
    public Project clone () throws CloneNotSupportedException
    {
        return (Project) super.clone();
    }
    
    @Override
    public String toString ()
    {
        return "PROJECT ID: "+projectId+" PROJECT NAME: "+projectName;
    }
    
    public String toDelimitedString ()
    {
        return projectId+","+projectName;
    }
}
