/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package H_KeyboardAndMouseEvent;

import javax.swing.JFrame;
/**
 *
 * @author Hooman
 */
public class KeyboardAndMouseTest 
{
    
    public static void main(String[] args) 
    {
        KeyboardAndMouseTest a= new KeyboardAndMouseTest();  
        a.KeyboardEvent();
        a.MouseEvent();
        a.AdaptorEvent();
    }
   
    public void KeyboardEvent()
    {
        KeyboardEventFrame frame=new KeyboardEventFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setVisible(true);                   
    }
        
    public void MouseEvent()
    {
        MouseEventFrame frame=new MouseEventFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setVisible(true);                   
    }
    
    public void AdaptorEvent()
    {
        AdapterFrame frame=new AdapterFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setVisible(true);                   
    }         
}
