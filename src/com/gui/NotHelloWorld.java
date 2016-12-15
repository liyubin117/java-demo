package com.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2015-12-21.
 */
public class NotHelloWorld {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new NotHelloWorldFrame();
                frame.setTitle("NotHelloWorld");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                JFrame frame2 = new NotHelloWorldFrame();
                frame2.setTitle("NotHelloWorld 2");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setVisible(true);
            }
        });
    }
}

class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame(){
        add(new NotHelloWorldComponent());
        pack();
    }
}

class NotHelloWorldComponent extends JComponent{
    private static final int MESSAGE_X=75;
    private static final int MESSAGE_Y=100;

    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;  //设置颜色
        g2.setPaint(Color.red);
        g2.drawString("Not a Hello,World program",MESSAGE_X,MESSAGE_Y);
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
