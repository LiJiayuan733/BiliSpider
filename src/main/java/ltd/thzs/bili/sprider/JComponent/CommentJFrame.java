package ltd.thzs.bili.sprider.JComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ltd.thzs.BiliSprider.Sprider.BiliCommentData;
import ltd.thzs.BiliSprider.Sprider.BiliCommentsRequests;
import ltd.thzs.BiliSprider.Sprider.BiliProcessor;
import ltd.thzs.BiliSprider.Sprider.BiliRequests.Function;
import ltd.thzs.BiliSprider.Sprider.BiliSpriderConfig;
import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.BackJButton;
import ltd.thzs.bili.sprider.JComponent.Button.ToolsJButton;
import ltd.thzs.bili.sprider.JComponent.Button.TransformJButton;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import ltd.thzs.bili.sprider.utils.PercentLocate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

public class CommentJFrame extends JFrame {

	private static final long serialVersionUID = -8200430977406288850L;
	public static final int ViewCode = 2;
	public static CommentJFrame instance;
	public JScrollPane jspleft;
	public JScrollPane jspright;
	public JTextArea Log;
	public JTextArea Data;
	public JTextField Params;

	public CommentJFrame() {
		super();
		CommentJFrame.instance = this;
		ConfigData.hasGUIView = ViewCode;

		Dimension WindowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("评论管理");
		this.setSize(WindowSize.width * 6 / 10, WindowSize.height * 6 / 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(WindowSize.width / 5, (int) (WindowSize.getHeight() / 10));
		this.setUndecorated(true);
		this.setVisible(true);

		getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this, 40, 0);
		addTopBorder();
		addListButton();
		addJcomp();
	}

	public void addJcomp() {
		YZPointCC point1 = new YZPointCC();
		PercentLocate.SetPBottomCenter(this, point1, getHeight() / 10);
		this.getContentPane().add(point1);

		JButton gCom = new JButton("获取评论");
		PercentLocate.SetSize(160, 80, this, gCom);
		PercentLocate.SetPLeftBottomCC(point1, gCom, getWidth() / 6);
		gCom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] p = Params.getText().split(" ");
				BiliCommentsRequests bilireq = new BiliCommentsRequests(p[0], p[1]);
				BiliSpriderConfig.AddCookies(bilireq);
				Spider.create(new BiliProcessor()).addRequest(bilireq.setFunction(new Function() {
					@Override
					public void call(BiliProcessor process, Page page) {
						try {
							JSONObject jo = JSONObject.fromObject(page.getRawText());
							JSONObject data = (JSONObject) jo.get("data");
							JSONArray rplist = data.getJSONArray("replies");
							for (int i = 0; i < rplist.size(); i++) {
								JSONObject rpl = rplist.getJSONObject(i);
								JSONObject rpll = rpl.getJSONObject("member");
								String url = rpll.getString("avatar");
								String message = ((JSONObject) (rpl.get("content"))).getString("message");
								String uname = rpll.getString("uname");
								String rpid = rpl.getString("rpid");
								;
								String mid = rpll.getString("mid");
								Data.append(url + "\n");
								if (DebugJFrame.instance != null) {
									BiliCommentData bcd = new BiliCommentData(rpl);
									DebugJFrame.instance.addUser(Params.getText().split(" ")[0], url, uname, message,
											rpid, mid, bcd);
								}
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}

					}
				})).thread(2).start();
				Log.append("获取评论AID=" + p[0] + "...\n");
			}
		});
		this.getContentPane().add(gCom);

		Params = new JTextField();
		Params.setSize(gCom.getSize().width * 3, gCom.getSize().height);
		PercentLocate.SetPRightCenterCC(gCom, Params, getWidth() / 10);
		this.getContentPane().add(Params);

		// 左
		Log = new JTextArea();
		PercentLocate.SetSize(260, 700, this, Log);
		jspleft = new JScrollPane();
		jspleft.setViewportView(Log);
		PercentLocate.SetSize(260, 700, this, jspleft);
		PercentLocate.SetPTopCenterCC(gCom, jspleft, getHeight() / 20);
		this.getContentPane().add(jspleft);

		YZPointCC point2 = new YZPointCC();
		PercentLocate.SetPRightBottomCC(point1, point2, getWidth() / 12);
		PercentLocate.SetSize(160, 80, this, point2);
		this.getContentPane().add(point2);

		// 右
		Data = new JTextArea();
		PercentLocate.SetSize(430, 700, this, Data);
		jspright = new JScrollPane();
		jspright.setViewportView(Data);
		PercentLocate.SetSize(430, 700, this, jspright);
		PercentLocate.SetPTopCenterCC(point2, jspright, getHeight() / 10);
		this.getContentPane().add(jspright);

		repaint();
	}

	public void addListButton() {
		// 返回按钮
		BackJButton bjb = new BackJButton(MenuJFrame.class, this, Color.cyan);
		bjb.setSize(getWidth() * 3 / 100, getWidth() * 3 / 100);
		PercentLocate.SetPLeftCenter(this, bjb, 0);
		getContentPane().add(bjb);

		ToolsJButton tjb = new ToolsJButton(DebugJFrame.class, this, Color.cyan);
		tjb.setSize(getWidth() * 3 / 100, getWidth() * 3 / 100);
		PercentLocate.SetPBottomLeftCC(bjb, tjb, 0);
		getContentPane().add(tjb);

		TransformJButton tfjb = new TransformJButton(TransformJFrame.class, this, Color.cyan);
		tfjb.setSize(getWidth() * 3 / 100, getWidth() * 3 / 100);
		PercentLocate.SetPRightCenter(this, tfjb, 0);
		getContentPane().add(tfjb);
	}

	public void addTopBorder() {
		FrameTopBorder tb = new FrameTopBorder(this, getWidth() / 40);
		this.getContentPane().add(tb);
		this.repaint();
	}

	@Override
	public void dispose() {
		ConfigData.hasGUIView = ConfigData.DEFALUT_GUIVIEW;
		super.dispose();
	}
}
