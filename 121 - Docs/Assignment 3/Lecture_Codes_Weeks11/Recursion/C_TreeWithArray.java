/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hooman
 */

class TreeWithArray 
{
    static String[] label = { "A", "B", "C", "d", "e", "f", "g", "h" };
    
    static int[][] children = { { 1, 2, 3},
                                { 4, 5},
                                { 6, 7},
                                { },
                                { },
                                { },
                                { },
                                { } };
    
                     
    public static void main(String[] args) 
    {
        System.out.println ("Tree count (A) = " + countTree (0));
        System.out.println ("Tree count (B) = " + countTree (1));
        printTree(0);
    }
    
    public static int countTree (int nodeNo)
    {
        if (children[nodeNo].length == 0)
            return 1;
        
        int total = 1;
        for (int childNo : children[nodeNo])
        {
            total += countTree (childNo);
        }
        
        return total;
    }
    
    public static void printTree (int nodeNo)
    {
        System.out.println (label[nodeNo]);
        
        for (int childNo : children[nodeNo])
        {
            printTree (childNo);
        }
    }
}
