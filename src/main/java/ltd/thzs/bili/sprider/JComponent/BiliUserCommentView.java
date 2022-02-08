package ltd.thzs.bili.sprider.JComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;


import ltd.thzs.BiliSprider.Sprider.BiliCommentData;
import ltd.thzs.bili.sprider.JComponent.Button.ListStyleMenuButton.state;
import ltd.thzs.bili.sprider.JFrameWebPicDownloader;
import ltd.thzs.bili.sprider.PicComponent;

public class BiliUserCommentView extends PicComponent {
	public static CommentReJFrame nowClickedComment=null;
	public state St=state.inOut;
	
	public static Color LikeFont=new Color(255,3,62);
	public static Color RCountFont=new Color(242,243,244);
	public static Color LikeColor=new Color(255,3,62,80);
	public static Color DisLikeColor=new Color(61,43,31,80);
	public static Font NAME_FONT=new Font("微软雅黑",Font.PLAIN,14);
	public static Font MESSAGE_FONT=new Font("微软雅黑",Font.PLAIN,8);
	@Serial
	private static final long serialVersionUID = -5432199697780128875L;
	public String message;
	public String uname;
	public String rpid;
	public String mid;
	public BiliCommentData bcd;
	public String oid;
	public int commentLevel=1;
	public BiliUserCommentView(String oid,String HeadPicUrl,String uname,String message,String rpid,String mid,BiliCommentData bcd) {
		this.HeadPicUrl=HeadPicUrl;
		this.uname=uname;
		this.message=message;
		this.rpid=rpid;
		this.mid=mid;
		this.bcd=bcd;
		this.oid=oid;
		this.addDefListener();
	}
	@Override
	public void paint(Graphics g) {
		if(ip==null) {
			/*new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						ip=new ImageIcon(new URL(HeadPicUrl)).getImage().getScaledInstance(getHeight(), getHeight(), Image.SCALE_SMOOTH);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}).start();*/
			JFrameWebPicDownloader.add(this);
			return;
		}
		int size=getHeight();
		g.drawImage(ip, 0, 0, size, size, 0, 0, size, size, DebugJFrame.instance);
		g.setColor(Color.white);
		g.setFont(NAME_FONT);
		FontMetrics fm=g.getFontMetrics();
		int height=fm.getAscent();
		g.drawString(uname, size+getWidth()/25, height);
		
		g.setFont(MESSAGE_FONT);
		fm=g.getFontMetrics();
		int linesize=getWidth()-(size+getWidth()/25)-getWidth()/10;
		int sumSize=0,ii=0;
		ArrayList<Integer> ls=new ArrayList<>();
		ls.add(0);
		for(int i:getWidthsString(message, fm)) {
			if(i==0) {
				continue;
			}
			sumSize+=i;
			ii++;
			if(sumSize>linesize) {
				ls.add(ii);
				sumSize=i;
			}
		}
		if(ls.get(ls.size()-1)!=message.length()) {
			ls.add(message.length());
		}
		for(int i=1;i<ls.size();i++) {
			g.drawString(message.substring(ls.get(i-1), ls.get(i)), size+getWidth()/25, height+(g.getFontMetrics().getAscent()*2)*i);
		}
		if(St==state.Entered) {
			g.setColor(new Color(93, 138, 168));
			g.drawRect(1, 1, getWidth()-1, getHeight()-1);
		}
		paintInfo(g);
	}
	public List<Integer> getWidthsString(String line,FontMetrics fm){
		ArrayList<Integer> ai=new ArrayList<>();
		char[] list=new char[line.length()];
		line.getChars(0, line.length(), list, 0);
		for(char i:list) {
			ai.add(fm.charWidth(i));
		}
		return ai;
	}
	private void addDefListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				St=state.Entered;
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				St=state.inOut;
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				St=state.Pressed;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(St==state.Pressed) {
					St=state.Entered;
					repaint();
				}
			}
			public void mouseClicked(MouseEvent e) {
				if(BiliUserCommentView.nowClickedComment==null) {
					new CommentReJFrame((BiliUserCommentView) e.getComponent());
				}else {
					BiliUserCommentView.nowClickedComment.updateComment((BiliUserCommentView)e.getComponent());
					BiliUserCommentView.nowClickedComment.repaint();
				}
			}
		});
	}
	public void paintInfo(Graphics g) {
		int width_temp;
		boolean countmore= bcd.count > 999;
		boolean likemore= bcd.like >= 9999;

		g.setFont(NAME_FONT);
		FontMetrics fm=g.getFontMetrics();
		width_temp=fm.stringWidth("009999");
		int width_temp2=fm.stringWidth("0000");
		if(bcd.action==1) {
			g.setColor(LikeColor);
			g.fillRect(getWidth()-width_temp*2, 0, width_temp, fm.getAscent());
		}else if(bcd.action==2){
			g.setColor(DisLikeColor);
			g.fillRect(getWidth()-width_temp*2, 0, width_temp, fm.getAscent());
		}
		
		g.setColor(LikeFont);
		if(likemore) {
			g.drawString("9999", getWidth()-width_temp,fm.getAscent());
		}else {
			g.drawString(""+bcd.like, getWidth()-width_temp,fm.getAscent());
		}
		g.setColor(RCountFont);
		if(countmore){
			g.drawString("999", getWidth()-width_temp-width_temp2,fm.getAscent());
		}else {
			g.drawString(""+bcd.count, getWidth()-width_temp-width_temp2,fm.getAscent());
		}
	}
}
