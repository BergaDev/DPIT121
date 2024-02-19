/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I_Drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Hooman
 */
public class PaintPanel extends JPanel
{
    private final ArrayList<Point> points = new ArrayList<>();

    public PaintPanel() 
    {
        this.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e) 
            {
                super.mouseDragged(e);
                points.add(e.getPoint());
                repaint();
            }
        });    
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        for (Point p:points)
        {
            g.fillOval(p.x, p.y, 10, 10);
        }
    }   
}
