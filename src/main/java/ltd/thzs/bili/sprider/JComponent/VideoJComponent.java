package ltd.thzs.bili.sprider.JComponent;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class VideoJComponent extends JComponent{
    public boolean isInit=false;

    //帧率
    public double fps;
    //视频宽度
    public double vWidth;
    //视频高度
    public double vHeight;
    //帧数
    public double frameCount;
    //视频长度
    public double len;


    public static Logger log=Logger.getLogger("VIDEO");
    public String FilePath;
    public VideoCapture video;
    public VideoJComponent(){
        super();
    }
    public VideoJComponent(String FilePath){
        this.FilePath=FilePath;
    }
    public double sLen=0;
    public void init(){
        video=new VideoCapture(FilePath);
        frameCount=video.get(7);
        fps=video.get(5);
        vWidth=video.get(3);
        vHeight=video.get(4);
        len=frameCount/fps*1000;
        isInit=true;
    }
    Mat nowFrame=new Mat();
    Image img;
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        if(video==null){
            g.fillRect(0,0,getWidth(),getHeight());
        }else{
            if(sLen==0){
                return;
            }
            if(isInit){
                video.read(nowFrame);
                if(nowFrame.width()<=0||nowFrame.height()<=0){
                    return;
                }
                if(System.currentTimeMillis()-sLen>=len){
                    sLen=System.currentTimeMillis();
                }
                video.set(Videoio.CAP_PROP_POS_MSEC,System.currentTimeMillis()-sLen);
                img=HighGui.toBufferedImage(nowFrame);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
                img=null;
                System.gc();
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
    public void play(){
        sLen=System.currentTimeMillis();
        repaint();
    }
    public void dispose(){
        video.release();
    }
}
