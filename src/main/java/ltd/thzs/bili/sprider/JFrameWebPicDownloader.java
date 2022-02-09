package ltd.thzs.bili.sprider;

import ltd.thzs.bili.sprider.JComponent.PicComponent;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class JFrameWebPicDownloader implements Runnable {
    public static boolean stop=false;
    @Override
    public void run() {
        while(!stop){
            lock.lock();
            while(list.size()==0){
                try {
                    //noinspection BusyWait
                    sleep(1000/15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            PicComponent pc=list.get(list.size()-1);
            list.remove(list.size()-1);
            lock.unlock();
            try {
                pc.ip=new ImageIcon(new URL(pc.HeadPicUrl)).getImage().getScaledInstance(pc.getHeight(),pc.getHeight(), Image.SCALE_SMOOTH);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            pc.repaint();
        }
    }
    public static ArrayList<PicComponent> list=new ArrayList<>();
    public static Lock lock = new ReentrantLock();
    public static void add(PicComponent pc){
        list.add(pc);
    }
}
