package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Administrator on 2015-12-22.
 */
public class DrawTest {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new DrawFrame();
                frame.getContentPane().setBackground(SystemColor.textHighlight);
                frame.setTitle("DrawTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class DrawFrame extends JFrame{
    public DrawFrame(){
        DrawComponent p = new DrawComponent();
//        p.setBackground(Color.red);
        add(p);
        pack();
    }
}

class DrawComponent extends JComponent{
    private static final int DEFAULT_WIDTH=400;
    private static final int DEFAULT_HEIGHT=400;

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        g2.setPaint(Color.MAGENTA);

        //矩形
        Rectangle2D rect = new Rectangle2D.Double(leftX,topY,width,height);
        g2.draw(rect);

        //椭圆
        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(ellipse);
        g2.fill(ellipse);

        //线
        g2.setPaint(Color.black);
        g2.draw(new Line2D.Double(leftX,topY,leftX+width,topY+height));

        //圆
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;
        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX,centerY,centerX+radius,centerY+radius);
        g2.draw(circle);

        g2.drawString("Hello!",(float)centerX,(float)centerY);
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}