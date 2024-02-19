/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package H_KeyboardAndMouseEvent;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Hooman
 */
public class KeyboardEventFrame extends JFrame implements KeyListener  
{
    private String line1="";
    private String line2="";
    private String line3="";  
    private final JTextArea text = new JTextArea(10, 15);
    
    public KeyboardEventFrame()  
    {
        super("Keboard Event Test");
        //this.setLayout(new FlowLayout());
        text.setText("Press any key on the keyboard...");
        text.setEnabled(false);
        text.setDisabledTextColor(Color.BLACK);
        add(text);
        this.addKeyListener(this);  // The same class for event handling  
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        line1= String.format("Key typed: %s", e.getKeyChar());   
        setLine2and3(e);
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        line1= String.format("Key pressed: %s", 
            KeyEvent.getKeyText(e.getKeyCode())); 
        setLine2and3(e);
    }
    @Override
    public void keyReleased(KeyEvent e) 
    {
        line1= String.format("Key released: %s", 
            KeyEvent.getKeyText(e.getKeyCode()));  
        setLine2and3(e);
    }
    
    private void setLine2and3(KeyEvent event)
    {
        line2 =String.format("This key is %s an action key",
                (event.isActionKey()?"":"not"));
        String temp = KeyEvent.getKeyModifiersText(event.getModifiers());
        line3=String.format("Modifier keys pressed: %s",
                (temp.equals("")?"none":temp)); // out modifiers
        text.setText(String.format("%s\n%s\n%s\n",line1,line2,line3));
    }   
}
