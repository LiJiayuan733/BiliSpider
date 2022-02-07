package ltd.thzs.bili.sprider.JComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ltd.thzs.BiliSprider.Sprider.BiliCommentData;
import ltd.thzs.BiliSprider.Sprider.BiliCommentsRequests;
import ltd.thzs.BiliSprider.Sprider.BiliProcessor;
import ltd.thzs.BiliSprider.Sprider.BiliSpriderConfig;
import ltd.thzs.BiliSprider.Sprider.BiliRequests.Function;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.MenuButton;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

public class CommentReJFrame extends JFrame {

	private static final long serialVersionUID = -8166756289220440946L;
	public static Font MESSAGE_FONT = new Font("微软雅黑", Font.PLAIN, 11);
	public BiliUserCommentView bucv = null;
	public Point BP = new Point();
	public static CommentReJFrame instance;
	public CommentReJFrame(BiliUserCommentView bucv) {
		CommentReJFrame.instance=this;
		this.bucv = bucv;
		BiliUserCommentView.nowClickedComment = this;
		Dimension WindowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("评论发送");
		this.setSize(WindowSize.width * 6 / 10, WindowSize.height / 5);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(WindowSize.width / 5, (int) (WindowSize.height * 7 / 10));
		this.setUndecorated(true);
		BP.setLocation(0, getWidth() / 40);
		this.setVisible(true);

		getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this, 40, 0);
		addTopBorder();
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(BiliUserCommentView.nowClickedComment==null) {
					new CommentReJFrame((BiliUserCommentView) e.getComponent());
				}else {
					BiliUserCommentView.nowClickedComment.updateComment((BiliUserCommentView)e.getComponent());
					BiliUserCommentView.nowClickedComment.repaint();
				}
			};
		});
		Text.NeedHeight(getGraphics(),bucv.message);
		jsp2.revalidate();
	}

	public void updateComment(BiliUserCommentView bucv) {
		this.bucv = bucv;
		Text.NeedHeight(getGraphics(),bucv.message);
		jsp2.revalidate();
		this.repaint();
	}
	String temprpid="0";
	int pn=1;
	public YZTextComponent Text;
	@Override
	public void paint(Graphics g) {
		if(bucv.commentLevel==1) {
			mb.setVisible(true);
			if(bucv.rpid!=temprpid) {
				jp.removeAll();
				count=0;
				temprpid=bucv.rpid;
				pn=1;
			}	
		}else {
			mb.setVisible(false);
		}	
		super.paint(g);
		g.translate(0, getWidth() / 40);
		g.setColor(new Color(72,72,72));
		g.drawImage(bucv.ip, 0, 0, Color.white, this);
		g.drawLine(0, bucv.ip.getWidth(this), (getWidth() - getHeight()) / 4, bucv.ip.getWidth(this));
	}

	public JScrollPane jsp;
	public JScrollPane jsp2;
	public JPanel jp;
	public FrameTopBorder tb;
	public MenuButton mb;
	public void addTopBorder() {
		tb = new FrameTopBorder(this, getWidth() / 40);
		this.getContentPane().add(tb);
		
		jsp=new JScrollPane();
		jp=new JPanel();
		jp.setLayout(null);
		
		jsp.setViewportView(jp);
		jsp.setLocation(getHeight()+(getWidth() - getHeight()) / 2, tb.getHeight());
		jsp.setSize((getWidth() - getHeight()) / 2,getHeight()-tb.getHeight());
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getContentPane().add(jsp);
		
		mb=new MenuButton("二级评论");
		mb.setSize(getHeight()*4/5,getHeight()*2/15);
		mb.setLocation(getHeight()/10, bucv.ip.getWidth(this)+this.getHeight()/5);
		mb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bucv.bcd.count==0) {
					return;
				}
				BiliCommentsRequests bilireq=new BiliCommentsRequests(bucv.oid,bucv.rpid,""+pn);
				BiliSpriderConfig.AddCookies(bilireq);
				Spider.create(new BiliProcessor()).addRequest(bilireq.setFunction(new Function() {
					@Override
					public void call(BiliProcessor process, Page page) {
						try {
							JSONObject jo=JSONObject.fromObject(page.getRawText());
							JSONObject data=(JSONObject) jo.get("data");
							JSONArray rplist=data.getJSONArray("replies");
							for(int i=0;i<rplist.size();i++) {
								JSONObject rpl=rplist.getJSONObject(i);
								JSONObject rpll=rpl.getJSONObject("member");
								String url=rpll.getString("avatar");
								String message=((JSONObject)(rpl.get("content"))).getString("message");
								String uname=rpll.getString("uname");
								String rpid=rpl.getString("rpid");;
								String mid=rpll.getString("mid");
								CommentJFrame.instance.Data.append(url+"\n");
								if(CommentReJFrame.instance!=null) {
									BiliCommentData bcd=new BiliCommentData(rpl);
									CommentReJFrame.instance.addUser(bucv.oid ,url,uname,message,rpid,mid,bcd);
								}
							}
							pn+=1;
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				})).thread(2).start();
				CommentJFrame.instance.Log.append("获取二级评论AID="+bucv.oid+"...\n");
				temprpid=bucv.rpid;
			}
		});
		this.getContentPane().add(mb);
		
		jsp2=new JScrollPane();
		Text=new YZTextComponent(MESSAGE_FONT);
		Text.setSize((getWidth() - getHeight()) / 2, getHeight()-tb.getHeight());
		jsp2.setViewportView(Text);
		jsp2.setSize((getWidth() - getHeight()) / 2, getHeight()-tb.getHeight());
		jsp2.setLocation(getHeight(), tb.getHeight());
		jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getContentPane().add(jsp2);
		this.repaint();
	}
	public int count=0;
	public void addUser(String oid,String url,String name,String message,String rpid,String mid,BiliCommentData bcd) {
		BiliUserCommentView bucv=new BiliUserCommentView(oid,url,name,message,rpid,mid,bcd);
		bucv.setSize((getWidth() - getHeight()) / 2,getHeight()/3);
		bucv.setLocation(0,count*(getHeight()/3));
		bucv.commentLevel=2;
		jp.add(bucv);
		count++;
		int height=count*(getHeight()/3)-getHeight()+tb.getHeight()>=0?count*(getHeight()/3)+tb.getHeight():jp.getHeight();
		jp.setPreferredSize(new Dimension(getWidth(),height));
		jsp.revalidate();
		jsp.repaint();
		this.repaint();
	}

	@Override
	public void dispose() {
		BiliUserCommentView.nowClickedComment = null;
		super.dispose();
	}
}
