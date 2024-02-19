/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Hooman
 */
public class MazeTest 
{   
    public static void main(String[] args) 
    {
        // 2D Maze solving.
        char[][] theMaze={
                //  0   1   2   3   4   5   6   7   8   9
           /*0*/  {'x','x','x','x','x','x','x','x','x','x'},  
           /*1*/  {'x','S','x','x','x','x','x','x','x','x'}, 
           /*2*/  {'x',' ','x','x','x','x','x','x','x','x'}, 
           /*3*/  {'x',' ',' ',' ','x',' ','x','x','x','x'}, 
           /*4*/  {'x',' ','x','x','x',' ','x','x','x','x'}, 
           /*5*/  {'x',' ','x','x','x',' ','x','x','x','x'}, 
           /*6*/  {'x',' ',' ',' ',' ',' ',' ',' ','x','x'}, 
           /*7*/  {'x','x','x','x','x','x','x',' ','x','x'}, 
           /*8*/  {'x','x','x','x','x','G',' ',' ',' ','x'},
           /*9*/  {'x','x','x','x','x','x','x','x','x','x'}, 
        };
        
        int line=0;
        System.out.printf("  %s %s %s %s %s %s %s %s %s %s  %n",0,1,2,3,4,5,6,7,8,9);
        //output the Maze
        for (char[] row:theMaze )
        {
            System.out.printf("%.2s ", line);
            line++;
            for (char cell : row )
            {
               System.out.printf("%s ", cell);  
            }
            System.out.println();
        }
        // print out the move 
        System.out.println(MazeSolving.solve(theMaze, 1, 1,-1,-1)); 
        // (Array of the Maze, row,col, previous row(-1 at starting point),previous col(-1 at starting point)  )
    }
}
