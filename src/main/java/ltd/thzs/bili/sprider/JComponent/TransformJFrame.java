package ltd.thzs.bili.sprider.JComponent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ltd.thzs.BiliSprider.Sprider.BiliProcessor;
import ltd.thzs.BiliSprider.Sprider.BiliRequests.Function;
import ltd.thzs.BiliSprider.Sprider.BiliSpriderConfig;
import ltd.thzs.BiliSprider.Sprider.BiliViewRequests;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.MenuButton;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import ltd.thzs.bili.sprider.utils.PercentLocate;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

public class TransformJFrame extends JFrame{

	@Serial
	private static final long serialVersionUID = 2364129719627313708L;
	public static TransformJFrame instance=null;
	public TransformJFrame() {
		super();
		TransformJFrame.instance = this;
		Dimension WindowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("评论管理");
		this.setSize(WindowSize.width * 2 / 10, WindowSize.height * 6 / 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(WindowSize.width * 4 / 5,(int) (WindowSize.getHeight()/10));
		this.setUndecorated(true);
		this.setVisible(true);
		
		getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this, 20, 0);
		addTopBorder();
		addJComp();
	}
	JTextField inp1;
	JTextField inp2;
	MenuButton commit;
	MenuButton commit2;
	public void addJComp() {
		inp1=new JTextField("输入BVID");
		inp1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(((JTextField)e.getComponent()).getText().equals("输入BVID")) {
					((JTextField)e.getComponent()).setText("");
				}
				super.focusGained(e);
			}
		});
		inp2=new JTextField("输入AID");
		inp2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(((JTextField)e.getComponent()).getText().equals("输入AVID")) {
					((JTextField)e.getComponent()).setText("");
				}
				super.focusGained(e);
			}
		});
		commit=new MenuButton("转换");
		commit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(inp1.getText().startsWith("BV")) {
					BiliViewRequests bvq=new BiliViewRequests(inp1.getText());
					BiliSpriderConfig.AddCookies(bvq);
					Spider.create(new BiliProcessor()).addRequest(bvq.setFunction(new Function() {
						@Override
						public void call(BiliProcessor process, Page page) {
							JSONObject jo=JSONObject.fromObject(page.getRawText());
							String aid=jo.getJSONObject("data").getString("aid");
							inp2.setText("AID: "+aid);
							inp1.setText("BID: "+inp1.getText());
						}
					})).thread(1).start();
				}
			}
		});

		commit2=new MenuButton("视频测试");
		commit2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new VideoShowJFrame(VideoShowJFrame.defaultFile);
				//App.instance.play(UtilsInstance.buildStrPointer(VideoShowJFrame.defaultFile));
			}
		});

		PercentLocate.SetSize(800, 80, this, inp1);
		PercentLocate.SetPTopCenter(this, inp1, getHeight()/15);
		getContentPane().add(inp1);
		
		PercentLocate.SetSize(800, 80, this, inp2);
		PercentLocate.SetPBottomCenterCC(inp1, inp2, getHeight()/30);
		getContentPane().add(inp2);

		PercentLocate.SetSize(500, 60, this, commit);
		PercentLocate.SetPBottomCenter(this, commit, getHeight()/15);
		getContentPane().add(commit);

		commit2.setSize(commit.getSize());
		PercentLocate.SetPTopCenterCC(commit,commit2,getHeight()/15);
		getContentPane().add(commit2);
	}
	public void addTopBorder() {
		FrameTopBorder tb = new FrameTopBorder(this, getWidth() / 20);
		this.getContentPane().add(tb);
		this.repaint();
	}

	@Override
	public void dispose() {
		TransformJFrame.instance=null;
		super.dispose();
	}
}
