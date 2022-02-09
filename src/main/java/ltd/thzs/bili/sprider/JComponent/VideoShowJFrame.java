package ltd.thzs.bili.sprider.JComponent;

import ltd.thzs.bili.sprider.utils.FrameButtonTools;

import javax.swing.*;
import java.awt.*;

public class VideoShowJFrame extends JFrame {
    public static final String defaultFile="/Users/pingguo/Desktop/不负韶光.mp4";
    public VideoJComponent video;
    public Dimension WindowSize;
    public int topBorderHeight;
    public VideoShowJFrame(){
        super();
        WindowSize= Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(WindowSize.width * 6 / 10, WindowSize.height * 6 / 10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);

        getContentPane().setLayout(null);
        FrameButtonTools.addDisposeButton(this, 40, 0);
        topBorderHeight = FrameButtonTools.addTopBorder(this,40);
    }
    public VideoShowJFrame(String FilePath){
        this();
        video=new VideoJComponent(FilePath);
        video.init();
        video.setSize(getWidth(), (int) (video.vHeight/video.vWidth*getWidth()));
        video.setLocation(0,topBorderHeight);
        this.getContentPane().add(video);
        video.play();
    }

    @Override
    public void dispose() {
        video.dispose();
        super.dispose();
    }
}
