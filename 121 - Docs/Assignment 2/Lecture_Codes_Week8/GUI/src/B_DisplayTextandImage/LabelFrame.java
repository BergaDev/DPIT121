/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_DisplayTextandImage;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Hooman
 */
public class LabelFrame extends JFrame
{
    private JLabel label1= new JLabel("Label with text"); 
    private JLabel label2; 
    private JLabel label3= new JLabel();
    
    public LabelFrame() 
    {
        super("Testing JLabel");
        this.setLayout(new FlowLayout());
        label1.setToolTipText("This is Label1");
        this.add(label1);
        label1.setText("Change to this text");

        Icon bug =new ImageIcon(getClass().getResource("bug.png"));
        label2=new JLabel("Label with text",bug,SwingConstants.LEFT);  
        label2.setToolTipText("This is Label2");
        this.add(label2);

        label3.setText("Label with icon and text at bottom");
        label3.setIcon(bug);
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setVerticalTextPosition(SwingConstants.BOTTOM);
        label2.setToolTipText("This is Label3");
        this.add(label3);
    }  
}
