package ltd.thzs.bili.sprider.JComponent.Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class ToolsJButton extends ListStyleMenuButton {

	private static final long serialVersionUID = -6350119340444395824L;
	public Class<? extends JFrame> toolsView;
	public JFrame fromJFrame;
	public Color BackGroundColor;
	public ToolsJButton(Class<? extends JFrame> toolsView,JFrame fromJFrame,Color BackGroundColor) {
		super();
		this.toolsView = toolsView;
		this.fromJFrame = fromJFrame;
		this.BackGroundColor = BackGroundColor;

		this.addListener();
	}
	
	private void addListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(toolsView.getField("instance").get(toolsView)!=null) {
						((JFrame)(toolsView.getField("instance").get(toolsView))).dispose();
						toolsView.getField("instance").set(toolsView, null);
						return;
					}
					toolsView.getDeclaredConstructor().newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawImage(icon, 0, 0, fromJFrame);
	}
	@Override
	public void loadIcon() {
		loadIconFromRes("/pic/tools.png", fromJFrame);
	}

	@Override
	public void onEntered(Graphics g) {
		g.setColor(new Color(93, 138, 168));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void onPressed(Graphics g) {
		g.setColor(new Color(93, 138, 168, 100));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void onInout(Graphics g) {
		g.setColor(new Color(102, 204, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
