
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Hooman
 */

public class TowerOfHanoi 
{  
    public static String solveTower(int disks, int sourcePeg,int destPeg,int tempPeg)
    {
        String output="";
        if (disks==1)
        {
            output += String.format("%n%d  --->  %d", sourcePeg, destPeg);
            return output;
        }
        
        output += solveTower(disks-1,sourcePeg,tempPeg, destPeg);
        
        output += String.format("%n%d  --->  %d", sourcePeg, destPeg);
        
        output += solveTower(disks-1,tempPeg, destPeg, sourcePeg);
        
        return output;
    } 
    public static void main(String[] args) 
    {
        System.out.println(solveTower(6,0,2,1));
    }
      
}
