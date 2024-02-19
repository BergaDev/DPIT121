/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part4and5;

import java.util.Comparator;

public class PostCodeComparator implements Comparator<Employee>
{

    @Override
    public int compare (Employee e1, Employee e2)
    {
       return e1.address.compareTo(e2.address);
    }
   
}
