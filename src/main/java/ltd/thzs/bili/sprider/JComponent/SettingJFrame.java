package ltd.thzs.bili.sprider.JComponent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.MenuButton;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import ltd.thzs.bili.sprider.utils.PercentLocate;

public class SettingJFrame extends JFrame{

	private static final long serialVersionUID = -1512162212124139672L;
	public static SettingJFrame instance;
	
	public SettingJFrame() {
		SettingJFrame.instance=this;
		Dimension WindowSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("测试界面");
		this.setSize(WindowSize.width*3/10,WindowSize.height*8/10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,(int) (WindowSize.getHeight()/10));
    	this.setUndecorated(true);
		this.setVisible(true);
		
		this.getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this,20,0);
		addTopBorder();
		//TODO: 完成配置文件默认设置
		addComp();
	}
	public void addComp() {
		JLabel jl1=new JLabel("配置文件路径");
		JTextField jtf1=new JTextField();
		PercentLocate.SetSize(200, 50, this, jl1);
		jl1.setLocation(getWidth()/25, getWidth()/10);
		this.getContentPane().add(jl1);
		PercentLocate.SetSize(650, 50, this, jtf1);
		PercentLocate.SetPRightCenterCC(jl1, jtf1, getWidth()/20);
		jtf1.setText(ConfigData.CONFIG_DEF_PATH);
		this.getContentPane().add(jtf1);
		
		MenuButton mb1=new MenuButton("加载配置");
		MenuButton mb2=new MenuButton("保存配置");
		PercentLocate.SetSize(300, 50, this, mb1);
		PercentLocate.SetSize(300, 50, this, mb2);
		PercentLocate.SetPBottomCenterCC(jl1, mb1, getWidth()/20);
		PercentLocate.SetPRightCenterCC(mb1, mb2, getWidth()/20);
		mb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConfigData.proInit(jtf1.getText());
			}
		});
		mb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConfigData.proSave(jtf1.getText());
			}
		});
		this.getContentPane().add(mb1);
		this.getContentPane().add(mb2);
	}
	public void addTopBorder() {
		FrameTopBorder tb=new FrameTopBorder(this,getWidth()/20);
		this.getContentPane().add(tb);
		this.repaint();
	}
}
