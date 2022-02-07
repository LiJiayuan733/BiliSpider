package ltd.thzs.bili.sprider.JComponent;

import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TestJFrame extends JFrame {
    public static TestJFrame instance;
    public int TopHeight;
    public TestJFrame(){
        super();
        TestJFrame.instance=this;
        Dimension WindowSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("测试界面");
        this.setSize(WindowSize.width*3/10,WindowSize.width*3/10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(MenuJFrame.instance);
        this.setUndecorated(true);
        addButton();
        this.setVisible(true);
        FrameButtonTools.addDisposeButton(this,20,0);
        TopHeight = FrameButtonTools.addTopBorder(this,20);
    }
    public MatOfByte mat2=new MatOfByte();
    public Mat mat;
    public void addButton(){
        BufferedImage bi=new BufferedImage(200,200,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d=bi.createGraphics();
        g2d.setColor(new Color(102, 204, 255));
        g2d.fillRect(10,10,180,180);
        g2d.dispose();

        mat=Mat.eye(200,200, CvType.CV_8UC4);
        mat.put(0,0,((DataBufferByte) bi.getRaster().getDataBuffer()).getData());
        Imgcodecs.imencode(".bmp",mat,mat2);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setClip(0,TopHeight,getWidth(),getHeight());
        g.translate(0,TopHeight);
        try {
            Image im= ImageIO.read(new ByteArrayInputStream(mat2.toArray()));
            g.drawImage(im,0,0,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
