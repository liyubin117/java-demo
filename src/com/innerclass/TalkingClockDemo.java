package com.innerclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Administrator on 2015-12-15.
 */
public class TalkingClockDemo {
    public static void main(String [] args){
        TalkingClock clock = new TalkingClock(1000,true);
        clock.start();

        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}

class TalkingClock{
    private int interval;
    private boolean beep;

    public TalkingClock(int interval,boolean beep){
        this.interval = interval;
        this.beep = beep;
    }
    public void start(){
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval,listener);
        t.start();
    }
    public class TimePrinter implements ActionListener{
        public void actionPerformed(ActionEvent event){
            Date now = new Date();
            System.out.println("At the tone,the time is "+now);
            //Or complete:if(TalkingClock.this.beep)
            if (beep){
                //Or complete
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}