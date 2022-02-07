package ltd.thzs.bili.sprider.JComponent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.MenuButton;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import ltd.thzs.bili.sprider.utils.PercentLocate;

public class CookieConfigJFrame extends JFrame{

	private static final long serialVersionUID = 7314613538473464619L;
	public static CookieConfigJFrame instance;
	public CookieConfigJFrame() {
		super();
		CookieConfigJFrame.instance=this;
		
		Dimension WindowSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("输入Cookie");
		this.setSize(WindowSize.width*6/10,WindowSize.height*6/10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
    	this.setUndecorated(true);
		this.setVisible(true);
		
		getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this,40,0);
		addTopBorder();
		addJcomp();
	}
	public void addJcomp() {
		YZPointCC point1 = new YZPointCC();
		PercentLocate.SetPTopCenter(this, point1, getHeight() / 10);
		this.getContentPane().add(point1);
		
		JTextField Cookie = new JTextField();
		
		JButton gCom = new JButton("设置Cookie");
		PercentLocate.SetSize(160, 80, this, gCom);
		PercentLocate.SetPLeftBottomCC(point1, gCom, getWidth() / 6);
		gCom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConfigData.Cookie=Cookie.getText();
				dispose();
			}
		});
		this.getContentPane().add(gCom);
		
		Cookie.setSize(new Dimension(gCom.getWidth()*3,gCom.getHeight()));
		PercentLocate.SetPRightCenterCC(gCom, Cookie, getWidth()/10);
		this.getContentPane().add(Cookie);
		
		
		MenuButton mb=new MenuButton("加载配置");
		JTextField Config = new JTextField();
		PercentLocate.SetSize(160, 80, this, mb);
		PercentLocate.SetPBottomCenterCC(gCom, mb, getHeight()/15);
		mb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConfigData.proInit(Config.getText());
				dispose();
			}
		});
		this.getContentPane().add(mb);
		
		Config.setSize(new Dimension(gCom.getWidth()*3,gCom.getHeight()));
		PercentLocate.SetPRightCenterCC(mb, Config, getWidth()/10);
		this.getContentPane().add(Config);
		repaint();
	}
	public void addTopBorder() {
		FrameTopBorder tb=new FrameTopBorder(this,getWidth()/40);
		this.getContentPane().add(tb);
		this.repaint();
	}
}
