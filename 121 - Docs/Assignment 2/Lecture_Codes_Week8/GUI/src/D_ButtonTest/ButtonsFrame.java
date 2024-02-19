/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package D_ButtonTest;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 *
 * @author Hooman
 */
public class ButtonsFrame extends JFrame
{
    private final JButton plainButton = new JButton("Reset Font and Color");  
    private final JButton blackButton = new JButton("Reset Color");
    private final JRadioButton choice1Button = new JRadioButton("Red");
    private final JRadioButton choice2Button = new JRadioButton("Yellow");
    private final JRadioButton choice3Button = new JRadioButton("Blue");
    private final JRadioButton choice4Button = new JRadioButton("Green");
    private final ButtonGroup ColorGroup= new ButtonGroup();
    private final JCheckBox boldBox= new JCheckBox("Bold");
    private final JCheckBox italicBox= new JCheckBox("Italic");
    private JTextField textField1 = new JTextField("Enter Text from here");   
    
    private final JPanel p1=new JPanel();
    private final JPanel p2=new JPanel();
    private final JPanel p3=new JPanel();
     
    public ButtonsFrame() 
    {
        super("Testing JLabel");
        setLayout(new FlowLayout());
        Font plain=new Font("Serif",Font.PLAIN,18);
        Font bold=new Font("Serif",Font.BOLD,18);
        Font italic=new Font("Serif",Font.ITALIC,18);
        textField1.setFont(plain);

        //add radio button to button group
        ColorGroup.add(choice1Button);
        ColorGroup.add(choice2Button);
        ColorGroup.add(choice3Button);
        ColorGroup.add(choice4Button);

        add(textField1);
        
        //add to panel
        p1.setSize(450, 150);
        p1.setLayout(new FlowLayout());
        p1.add(plainButton);
        p1.add(blackButton);
        add(p1);
        
        p2.setSize(450, 150);
        p2.setLayout(new FlowLayout());
        p2.add(choice1Button);
        p2.add(choice2Button);
        p2.add(choice3Button);
        p2.add(choice4Button);
        add(p2);
        
        p3.setSize(450, 150);
        p3.setLayout(new FlowLayout());
        p3.add(boldBox);
        p3.add(italicBox);
        add(p3);

       // handle the event  
       
       // anonymous inner class
        this.plainButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {            
                textField1.setFont(plain); 
                textField1.setForeground(Color.black);
            }
        });
        
        this.blackButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {            
                textField1.setForeground(Color.black);
                
                List<AbstractButton> listRadioButton = Collections.list(ColorGroup.getElements());
                for (AbstractButton button:listRadioButton)
                {
                    ((JRadioButton) button).setForeground(Color.black);            
                }
            }
        });
        
        this.choice1Button.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                textField1.setForeground(Color.red);
                ((JRadioButton) e.getSource()).setForeground(Color.red);
            }          
        });
       
        this.choice2Button.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                textField1.setForeground(Color.yellow);
                ((JRadioButton) e.getSource()).setForeground(Color.yellow);
            }          
        });
      
        // Lambda
        this.choice3Button.addItemListener((ItemEvent e) -> 
        {
            textField1.setForeground(Color.blue);
            ((JRadioButton) e.getSource()).setForeground(Color.blue);
        });
        
        this.choice4Button.addItemListener((ItemEvent e) -> 
        {
           textField1.setForeground(Color.green);
           ((JRadioButton) e.getSource()).setForeground(Color.green);
        });

        // inner private class with a constructor
        this.boldBox.addItemListener(new CheckBoxHandler(bold));
        this.italicBox.addItemListener(new CheckBoxHandler(italic)); 
    }
     
    private class CheckBoxHandler implements ItemListener 
    {
        Font f;
        CheckBoxHandler(Font f)
        {
            this.f=f;
        }
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            textField1.setFont(f);
        }
    }   
}
