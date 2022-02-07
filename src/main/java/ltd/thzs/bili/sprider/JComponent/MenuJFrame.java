package ltd.thzs.bili.sprider.JComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;

import javax.swing.*;

import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.MenuButton;
import ltd.thzs.bili.sprider.JComponent.Button.SettingJButton;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import ltd.thzs.bili.sprider.utils.PercentLocate;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MenuJFrame extends JFrame{
	
	@Serial
	private static final long serialVersionUID = -8528129403776269920L;
	public static final int ViewCode=1;
	public static MenuJFrame instance;
	public MenuJFrame() {
		super();
		MenuJFrame.instance=this;
		ConfigData.hasGUIView=ViewCode;
		Dimension WindowSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("主界面");
		this.setSize(WindowSize.width*4/10,WindowSize.height*4/10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
    	this.setUndecorated(true);
		this.setVisible(true);
		
		FrameButtonTools.addDisposeButton(this,20,0);
		addTopBorder();
		addButton();
		if(ConfigData.Cookie==null) {
			new CookieConfigJFrame();
		}
	}
	public void addButton() {
		getContentPane().setLayout(null);
		SettingJButton sjb = new SettingJButton(SettingJFrame.class, this, Color.cyan);
		sjb.setSize(getWidth() * 5 / 100, getWidth() * 5 / 100);
		PercentLocate.SetPLeftCenter(this, sjb, 0);
		getContentPane().add(sjb);
		//中心定点一
		
		YZPointCC c1=new YZPointCC();
		PercentLocate.SetPCenter(this, c1);
		getContentPane().add(c1);
		
		//左
		
		MenuButton mb1=new MenuButton("评论区管理");
		PercentLocate.SetSize(190, 100, this, mb1);
		PercentLocate.SetPLeftCenterCC(c1, mb1, getWidth()/8);
		mb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new CommentJFrame();
				dispose();
			}
		});
		getContentPane().add(mb1);
		
		MenuButton mb2=new MenuButton("稿件筛选");
		PercentLocate.SetSize(190,100, this,mb2);
		PercentLocate.SetPRightCenterCC(mb1, mb2, getWidth()/10);
		mb2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new BVChooseJFrame();
				dispose();
			}
		});
		getContentPane().add(mb2);

		JButton jb=new JButton();
		jb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TestJFrame();
			}
		});
		PercentLocate.SetSize(200,180,this,jb);
		PercentLocate.SetPTopCenter(this,jb,getHeight()/10);
		jb.setSize(jb.getWidth(),jb.getWidth());
		Mat mt= Imgcodecs.imread(ConfigData.DIR_BASE+"/pic/icon.png");
		ImageIcon ii=new ImageIcon();
		Mat mt1=new Mat();
		Imgproc.resize(mt,mt1,new Size(jb.getWidth(),jb.getWidth()));
		ii.setImage(HighGui.toBufferedImage(mt1));
		jb.setIcon(ii);
		getContentPane().add(jb);
		repaint();
	}
	public void addTopBorder() {
		FrameTopBorder tb=new FrameTopBorder(this,getWidth()/20);
		this.getContentPane().add(tb);
		this.repaint();
	}
	@Override
	public void dispose() {
		ConfigData.hasGUIView=ConfigData.DEFALUT_GUIVIEW;
		super.dispose();
	}
}
