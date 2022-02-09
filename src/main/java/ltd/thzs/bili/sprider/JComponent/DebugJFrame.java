package ltd.thzs.bili.sprider.JComponent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ltd.thzs.BiliSprider.Sprider.BiliCommentData;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;

public class DebugJFrame extends JFrame{

	@Serial
	private static final long serialVersionUID = 3076380599231108471L;
	public static DebugJFrame instance;
	public int count=0;
	public DebugJFrame() {
		DebugJFrame.instance=this;
		Dimension WindowSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("测试界面");
		this.setSize(WindowSize.width/5,WindowSize.height*8/10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(0,(int) (WindowSize.getHeight()/10));
    	this.setUndecorated(true);
		this.setVisible(true);
		
		this.getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this,20,0);
		addTopBorder();
	}
	public JScrollPane jsp;
	public JPanel jp;
	public FrameTopBorder tb;
	public void addTopBorder() {
		tb=new FrameTopBorder(this,getWidth()/20);
		this.getContentPane().add(tb);
		
		jsp=new JScrollPane();
		jp=new JPanel();
		jp.setLayout(null);
		
		jsp.setViewportView(jp);
		jsp.setLocation(0, tb.getHeight());
		jsp.setSize(getWidth(),getHeight()-tb.getHeight());
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getContentPane().add(jsp);
		this.repaint();
	}
	public void addUser(String oid,String url,String name,String message,String rpid,String mid,BiliCommentData bcd) {
		BiliUserCommentView bucv=new BiliUserCommentView(oid,url,name,message,rpid,mid,bcd);
		bucv.setSize(getWidth(),getHeight()/14);
		bucv.setLocation(0,count*(getHeight()/14));
		jp.add(bucv);
		count++;
		int height=count*(getHeight()/14)-getHeight()+tb.getHeight()>=0?count*(getHeight()/14)+tb.getHeight():jp.getHeight();
		jp.setPreferredSize(new Dimension(getWidth(),height));
		jsp.revalidate();
		this.repaint();
	}
	@Override
	public void dispose() {
		super.dispose();
		DebugJFrame.instance=null;
	}
}
