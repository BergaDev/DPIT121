/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package H_KeyboardAndMouseEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hooman
 */
public class MouseEventFrame extends JFrame 
{
    private final JPanel mousePanel= new JPanel();
    private final JLabel status= new JLabel("Mouse outside JPanel");
    
    public MouseEventFrame()  
    {
        super("Mouse Event");
        //this.setLayout(new FlowLayout());
        mousePanel.setBackground(Color.white);
        add(mousePanel,BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);

        MouseHandler h=new MouseHandler();
        mousePanel.addMouseListener(h);
        mousePanel.addMouseMotionListener(h);  
    }
   
    private class MouseHandler implements MouseListener,MouseMotionListener 
    {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
            status.setText(String.format("Clicked at [%d,%d]",
                e.getX(),e.getY()));
        }

        @Override
        public void mousePressed(MouseEvent e) 
        {
            status.setText(String.format("Pressed at [%d,%d]",
                e.getX(),e.getY() )); 
        }

        @Override
        public void mouseReleased(MouseEvent e) 
        {
            status.setText(String.format("Released at [%d,%d]",
                e.getX(),e.getY() ));
        }

        @Override
        public void mouseEntered(MouseEvent e) 
        {
            status.setText(String.format("Mouse entered  at [%d,%d]",
                e.getX(),e.getY() ));
            mousePanel.setBackground(Color.green);
        }

        @Override
        public void mouseExited(MouseEvent e) 
        {
            status.setText(String.format("Mouse exited  at [%d,%d]",
                e.getX(),e.getY() ));
            mousePanel.setBackground(Color.white);   
        }

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            status.setText(String.format("Mouse dragged  at [%d,%d]",
                e.getX(),e.getY() ));
        }

        @Override
        public void mouseMoved(MouseEvent e) 
        {
            status.setText(String.format("Mouse moved  at [%d,%d]",
                e.getX(),e.getY() ));
        }
    }   
}
