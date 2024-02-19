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
public class Student implements Cloneable,Comparable<Student>,Serializable
{
  
    private int studentID;  
    private String name;
    private Address address;
    private String courseID;   
    public Student(int studentID, String name, Address address,String courseID) 
    {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.courseID = courseID;
      
    }
    public int getStudentID() 
    {
        return studentID;
    }

    public void setStudentID(int studentID) 
    {
        this.studentID = studentID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Address getAddress() 
    {
        return address;
    }

    public void setAddress(Address address) 
    {
        this.address = address;
    }

    public String getCourseID() 
    {
        return courseID;
    }

    public void setCourseID(String courseID) 
    {
        this.courseID = courseID;
    }
  
    @Override
    public int compareTo(Student o) 
    {
        return Integer.compare(this.studentID, o.studentID); 
    }
    @Override
    public Student clone() throws CloneNotSupportedException 
    {
        Student student= (Student) super.clone();
        student.address=address.clone();
        return student;
    }
    
    public Student DeepCopy() throws CloneNotSupportedException 
    {
        return this.clone();
    } 
    public Student ShallowCopy() throws CloneNotSupportedException 
    {
        return this;
    }
    @Override
    public String toString() 
    {
        return String.format("Student ID: %s, Student Name: %s,\n Address: \n   %s \n Coourse ID: %s\n", this.studentID,this.name,this.address,this.courseID);  //To change body of generated methods, choose Tools | Templates.
    }    
}
