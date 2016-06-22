package com.algo.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yzy on 2016/3/3.
 * 延迟几秒执行
 */
public class Reminder {

    private Timer timer;

    public Reminder(int seconds){
        timer = new Timer();
        timer.schedule(new MyTask2(),(seconds-2) * 1000);
        timer.schedule(new MyTask(),seconds * 1000);
    }

    public class MyTask extends TimerTask{
        @Override
        public void run() {
            System.out.println("执行once.....");
            timer.cancel();
        }
    }

    public class MyTask2 extends TimerTask{
        @Override
        public void run() {
            System.out.println("房价又涨了.....");
//            timer.cancel();
        }
    }

    public static void main(String[] args){
        new Reminder(5);
    }
}
