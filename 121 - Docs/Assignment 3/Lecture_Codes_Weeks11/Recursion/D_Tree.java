/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hooman
 */

import java.util.*;
import java.util.function.*;

class Tree 
{
    public static void main(String[] args) 
    {
        Node a = new Node ("A");
        Node b = new Node ("B");
        Node c = new Node ("C");
        Node d = new Node ("d", 10);
        Node e = new Node ("e", 15);
        Node f = new Node ("f", 15);
        Node g = new Node ("g", 25);
        Node h = new Node ("h", 10);
        
        a.addChild (b);
        a.addChild (c);
        a.addChild (d);
        
        b.addChild (e);
        b.addChild (f);
        
        c.addChild (g);
        c.addChild (h);
        
        // d.addChild (a); wrong a loop in the tree
        a.printTree ();

        System.out.println ("Tree count for " + a.label + " is: " + a.countTree ());
        System.out.println ("Tree value for " + a.label + " is: " + a.valueTree ());
        
        // This section is Tree and Lambda just for advanced students
        a.printTree ();
        a.map (x->x+1); // add 1 to each tree node
        System.out.println("printing after adding 1 to all values");
        a.printTree ();

        a.printTree ();
        a.mod (n -> { n.mod1 (); }); // add _1 to all labels
        a.mod (Node::mod1); //add another _1 to all labels
        System.out.println(" _1_1 is added to all labels");
        a.printTree ();


        List<Node> rList = a.leaves (); // a list of leaves with inorder sequance
        System.out.println(" print the inorder list of Nodes");
        rList.forEach (System.out::println);
    }
}


class Node
{
    String label;
    private double value;
    private List<Node> children; //recursive data strcuture to define the tree
    
    public Node (String label)
    {
        this.label = label;
        this.value = 0;
        this.children = new ArrayList<Node>();
    }
    
    public Node (String label, double value)
    {
        this.label = label;
        this.value = value;
        this.children = new ArrayList<Node>();
    }
    
    public void addChild (Node child)
    {
        children.add (child);
    }
    
    public String toString ()
    {
        return label + " " + value;
    }
    
    public void printTree ()
    {
        System.out.println (this); //preorder tree traversal
        for (Node child : children)
            child.printTree ();
    }
    
    public int countTree ()
    {
       if (children.size () == 0) 
           return 1;
       
       int total = 1;
       for (Node child : children)
           total += child.countTree ();
       return total;
    }
   
    public double valueTree ()
    {
       if (children.size () == 0) 
           return this.value;
       
       double total = 0;
       for (Node child : children)
           total += child.valueTree ();
       return total;
    }
    public List<Node> leaves ()
    {
        List<Node> result = new ArrayList<Node>();
        leaves_(result);
        return result;
    }
    
    private void leaves_ (List<Node> result) // create a list of leaves in order of tree traversal
    {
        if (children.size () == 0)
            result.add (this);
        else 
        {  
            for (Node child : children)
                child.leaves_ (result);
        }         
    }
    
    public List<Node> leaves1 () // another implementation 
    {
        List<Node> result = new ArrayList<Node>();
        if (children.size () == 0)
            result.add (this);
        else 
        {  
            for (Node child : children)
                result.addAll (child.leaves1 ());            
        } 
        
        return result;
    }
    
    public void mod1 ()
    {
        label += "_1";
    }
    
    public void mod (ModifyNodeFunction f)
    {
        f.apply (this);       
        for (Node child : children)
            child.mod (f);  
    }
    
    public void map (Function<Double,Double> f) // change the value of nodes based on the Function and by using map
    {
        if (children.size () == 0) 
        {
            this.value = f.apply(this.value);
            return;
        }
       
        for (Node child : children)
            child.map (f); //recursive call 
    }
    
    
}

interface ModifyNodeFunction
{
    void apply (Node n);
}