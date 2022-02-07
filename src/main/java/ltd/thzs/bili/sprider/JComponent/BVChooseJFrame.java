package ltd.thzs.bili.sprider.JComponent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ltd.thzs.BiliSprider.Sprider.BiliProcessor;
import ltd.thzs.BiliSprider.Sprider.BiliSpriderConfig;
import ltd.thzs.BiliSprider.Sprider.BiliViewRequests;
import ltd.thzs.BiliSprider.Sprider.BiliRequests.Function;
import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.utils.FrameButtonTools;
import ltd.thzs.bili.sprider.utils.PercentLocate;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

public class BVChooseJFrame extends JFrame {

	private static final long serialVersionUID = 1218663603918800518L;
	public static String line = "";

	public BVChooseJFrame() {
		super();
		Dimension WindowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("BV筛选");
		this.setSize(WindowSize.width * 4 / 10, WindowSize.height * 4 / 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);

		this.getContentPane().setLayout(null);
		FrameButtonTools.addDisposeButton(this, 20, 0);
		addTopBorder();
		addButtom();
	}

	JTextField jtf1;
	JTextField jtf2;
	JButton jb1;

	public void addButtom() {
		jtf1 = new JTextField("文件路径");
		jtf2 = new JTextField("保存");
		jtf1.setSize(getWidth() * 2 / 3, getHeight() / 10);
		jtf2.setSize(getWidth() * 2 / 3, getHeight() / 10);
		PercentLocate.SetPTopCenter(this, jtf1, getHeight() / 20 + getHeight() / 20);
		PercentLocate.SetPBottomCenterCC(jtf1, jtf2, getHeight() / 20);
		jb1 = new JButton("确认");
		jb1.setSize(getWidth() / 6, getHeight() / 10);
		PercentLocate.SetPBottomRightCC(jtf2, jb1, getHeight() / 20);
		jb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(new File(jtf1.getText())));
					FileWriter fw = new FileWriter(new File(jtf2.getText()));
					ArrayList<String> list = new ArrayList<>();
					line = br.readLine();
					BiliViewRequests bvq = new BiliViewRequests(line);
					BiliSpriderConfig.AddCookies(bvq);

					Spider sp = Spider.create(new BiliProcessor()).addRequest(bvq.setFunction(new Function() {
						@Override
						public void call(BiliProcessor process, Page page) {
							System.out.println(page.getRawText());
							JSONObject jo = JSONObject.fromObject(page.getRawText());
							int code = jo.getInt("code");
							if (code == 0) {
								list.add(line);
							}
							try {
								line = br.readLine();
								if (line == null) {
									for (String lin : list) {
										fw.write(lin + "\n");
									}
									fw.flush();
									fw.close();
									br.close();
									return;
								}
								BiliViewRequests bvq = new BiliViewRequests(line);
								BiliSpriderConfig.AddCookies(bvq);
								bvq.setFunction(new MyFunction(br, fw, list));
								page.addTargetRequest(bvq);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					})).thread(1);
					sp.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		getContentPane().add(jtf1);
		getContentPane().add(jtf2);
		getContentPane().add(jb1);
	}

	public class MyFunction implements Function {
		public BufferedReader br;
		public FileWriter fw;
		public ArrayList<String> list;

		public MyFunction(BufferedReader br, FileWriter fw, ArrayList<String> list) {
			this.br = br;
			this.fw = fw;
			this.list = list;
		}

		@Override
		public void call(BiliProcessor process, Page page) {
			System.out.println(page.getRawText());
			JSONObject jo = JSONObject.fromObject(page.getRawText());
			int code = jo.getInt("code");
			if (code == 0) {
				list.add(line);
			}
			try {
				line = br.readLine();
				if (line == null) {
					for (String lin : list) {
						fw.write(lin + "\n");
					}
					fw.flush();
					fw.close();
					br.close();
					return;
				}
				BiliViewRequests bvq = new BiliViewRequests(line);
				BiliSpriderConfig.AddCookies(bvq);
				bvq.setFunction(new MyFunction(br, fw, list));
				page.addTargetRequest(bvq);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addTopBorder() {
		FrameTopBorder tb = new FrameTopBorder(this, getWidth() / 20);
		this.getContentPane().add(tb);
		this.repaint();
	}
}
