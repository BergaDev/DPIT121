


import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hooman
 */
public class MazeSolving 
{   
    public static boolean solve(char[][] theMaze, int row, int col, int prerow, int precol)
    {  
        char right = theMaze[row][col + 1];
        char left = theMaze[row][col - 1];
        char up = theMaze[row - 1][col];
        char down = theMaze[row + 1][col];
        boolean result=false;

        if (right == ' ' && !(row==prerow && col+1==precol) && !result)
        {  //move right
             System.out.println("move position=>"+"("+row + ":" + col+")");      
             result= solve(theMaze,row,col+1,row,col);
        }
        if (left == ' ' && !(row==prerow && col-1==precol) && !result) 
        { //move left
            System.out.println("move position=>"+"("+row + ":" + col+")");
            result= solve(theMaze,row,col-1,row,col);
        }
        if (up == ' ' && !(row-1==prerow && col==precol) && !result) 
        { //move up
            System.out.println("move position=>"+"("+row + ":" + col+")");
            result= solve(theMaze,row-1,col,row,col);
        }
        if (down == ' ' && !(row+1==prerow && col==precol) && !result) 
        { //move down
            System.out.println("move position=>"+"("+row + ":" + col+")");
            result= solve(theMaze,row+1,col,row,col);
        }
        
        // exceptional case: fail to find goal in the particular path.
        if (result==false)
        {  // all move fail
            System.out.println("move position=>"+"("+row + ":" + col+")");    
        }

        // base case : find goal
        if (right == 'G' || left == 'G' || up == 'G' || down == 'G') 
        { // found the goal
            int rowfinal=row;
            int colfinal=col;
            if (right == 'G')
            colfinal++;
            else if (left == 'G' )
            colfinal--;
            else if (up == 'G' )
            rowfinal--;  
            else if (down == 'G' )
            rowfinal++;          
            
            System.out.println("position=>"+"("+rowfinal + ":" + colfinal+")");
            result= true;
        }
        return result; // return the result
    }   
}
