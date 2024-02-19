/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C_TextFeildAndEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Hooman
 */
public class TextFieldFrame extends JFrame
{
    private JPasswordField passwordField = new JPasswordField("Password");
    private JTextField textField1;  
    private JTextField textField2 = new JTextField("Enter Text from here");   
    private JTextField textField3= new JTextField("set Text and size from here",25);   
    private JTextField textField4; 
    private JButton button1;
    
    public TextFieldFrame() 
    {
       super("Testing JLabel");
       this.textField1 = new JTextField(10);
       this.textField4=new JTextField(10);
       this.button1=new JButton("COPY");
       
       setLayout(new FlowLayout());
       add(textField1);
       add(textField2);
       add(textField3);
       add(textField4);
       add(passwordField);
       add(button1);
       
       textField3.setEditable(false);
       
       TextFieldHandler handler = new TextFieldHandler();
       
       textField1.addActionListener(handler);
       textField2.addActionListener(handler);
       textField3.addActionListener(handler);
       passwordField.addActionListener(handler);
       
       button1.addActionListener(new ButtonHandler());
    }
    
    private class TextFieldHandler implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
           String out="";

            if(e.getSource()==textField1)
            {
                out= "textField1: "+e.getActionCommand();  
            }

            else if(e.getSource()==textField2)
            {
                out= "textField2: "+e.getActionCommand();  
            }
            else if(e.getSource()==textField3)
            {
                out= "textField3: "+e.getActionCommand();  
            }
            else if(e.getSource()==passwordField)
            {
                out= "passwordField: "+e.getActionCommand();  
            }
           JOptionPane.showMessageDialog(null,out);     
        }
    }
    
    private class ButtonHandler implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
          textField4.setText(textField1.getText());
        }
    }
    
}
    
