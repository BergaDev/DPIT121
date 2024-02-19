/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A_JOptionPaneTest;

import javax.swing.JOptionPane;

/**
 *
 * @author Hooman
 */
public class Addtion 
{
    public static void main(String[] args) 
    {
        String fn=JOptionPane.showInputDialog("Enter First Number");
        String sn=JOptionPane.showInputDialog("Enter Second Number");
        int n1=Integer.parseInt(fn);
        int n2=Integer.parseInt(sn);
        JOptionPane.showMessageDialog(null, "The sum is " + (n1+n2)+".", "Sum of Two Integers", JOptionPane.PLAIN_MESSAGE);   
    }
}
