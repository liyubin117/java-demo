package com.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2015-12-21.
 */
//本地化框架的大小和位置

public class SizedFrameTest {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SizedFrame();
                frame.setTitle("SizedFrame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SizedFrame extends JFrame {
    public SizedFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth/2,screenHeight/2);
        setLocationByPlatform(true);

        Image img = new ImageIcon("icon.gif").getImage();
        setIconImage(img);
    }
}