package Test7;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2015-12-21.
 */
public class SimpleFrameTest {
    public static void main(String [] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setUndecorated(true);
                frame.setVisible(true);
            }
        });
    }
}

class SimpleFrame extends JFrame{
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;
    public SimpleFrame(){
        //setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setBounds(100,100,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}