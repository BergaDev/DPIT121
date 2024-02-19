/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package H_KeyboardAndMouseEvent;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Hooman
 */
public class AdapterFrame extends JFrame
{
 
    private String line1="";
    private final JLabel status= new JLabel("Click the Mouse");

    
    public AdapterFrame()  
    {
        super("Adaptor Test");   
        add(status,BorderLayout.SOUTH); 
        this.addMouseListener(new MouseHandler());
    }
  
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            int x= e.getX();
            int y=e.getY();
            line1= String.format("Clicked %d times",
                    e.getClickCount());
            if(e.isMetaDown())
                line1 += "with right mouse button";
            else if (e.isAltDown())
                line1 += "with center mouse button";
            else
                line1 += "with left mouse button";

            status.setText(line1);
        }    
    }   
}
