/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I_Drawing;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author Hooman
 */
public class DrawingTest 
{
    public static void main(String[] args)
    {
        JFrame frame=new JFrame();
        PaintPanel p=new  PaintPanel();
        frame.add(p,BorderLayout.CENTER);
        frame.add(new JLabel("Drag the mouse to draw"),
               BorderLayout.SOUTH );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.setVisible(true);
    }   
}
