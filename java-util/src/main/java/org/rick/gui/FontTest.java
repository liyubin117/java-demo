package org.rick.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Administrator on 2015-12-29.
 */
public class FontTest {
    public static void main(String[] args){
        //查看本机系统上的所有字体家族名
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String fontName : fontNames){
            System.out.println(fontName);
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new FontFrame();
                frame.setTitle("FontTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class FontFrame extends JFrame{
    public FontFrame(){
        add(new FontComponent());
        pack();
    }
}

class FontComponent extends JComponent{
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        String message = "Hello,World!";
        Font f = new Font("Sanserif",Font.BOLD,36);
        g2.setFont(f);

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(message, context);

        double x =(getWidth()-bounds.getWidth())/2;
        double y =(getHeight()-bounds.getHeight())/2;

        double ascent = -bounds.getY();
        double baseY = y+ascent;

        g2.drawString(message,(int)x,(int)baseY);
        g2.setPaint(Color.LIGHT_GRAY);

        g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
        Rectangle2D rect = new Rectangle2D.Double(x,y,bounds.getWidth(),bounds.getHeight());
        g2.draw(rect);
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}