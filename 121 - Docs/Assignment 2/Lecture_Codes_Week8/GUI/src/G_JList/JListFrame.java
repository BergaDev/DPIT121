/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package G_JList;


import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Hooman
 */
public class JListFrame extends JFrame 
{    
    private final JList<String> colorJList1;
    private final JList<String> colorJList2;
    private final JList<String> colorJList3;
    private final JList<String> copyJList4=new JList<String>();
    private final JScrollPane colorPane= new JScrollPane();
    private static final String[] colorNames={"Black","Blue","Green","Yellow"};
    private static final Color[] colors={Color.BLACK,Color.BLUE,Color.GREEN,Color.YELLOW};
    private final JButton copyButton = new JButton("copy"); 
     
    public JListFrame() 
    {
        super("Testing JList");
        this.setLayout(new FlowLayout());   
        colorJList1= new JList<String>(colorNames);
        colorJList1.setVisibleRowCount(3);
        colorJList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(colorJList1));

        colorJList2= new JList<String>(colorNames);
        colorJList2.setVisibleRowCount(4);
        colorJList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorPane.setViewportView(colorJList2);
        colorPane.setVisible(true);
        add(colorPane);

        colorJList1.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                getContentPane().setBackground(
                    colors[((JList) e.getSource()).getSelectedIndex()]);
            }   
        });

        colorJList2.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                getContentPane().setBackground(
                    colors[((JList) e.getSource()).getSelectedIndex()]);
            }   
        });

        colorJList3= new JList<String>(colorNames);
        colorJList3.setVisibleRowCount(3);
        colorJList3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JScrollPane(colorJList3));

        copyJList4.setVisibleRowCount(3);
        copyJList4.setFixedCellWidth(100);
        copyJList4.setFixedCellHeight(15);
        copyJList4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        add(new JScrollPane(copyJList4)); 

        add(copyButton);
        copyButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {           
                copyJList4.setListData(colorJList3.getSelectedValuesList().toArray(new String[0]) );
            }
        });
    }    
}
