package ltd.thzs.bili.sprider.JComponent.Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class BackJButton extends ListStyleMenuButton {

	private static final long serialVersionUID = -1447029237691021923L;
	public Class<? extends JFrame> toJFrame;
	public JFrame closeJFrame;
	public Color BackGroundColor;

	public BackJButton(Class<? extends JFrame> toJFrame, JFrame closeJFrame, Color BackGroundColor) {
		super();
		this.toJFrame = toJFrame;
		this.closeJFrame = closeJFrame;
		this.BackGroundColor = BackGroundColor;

		this.addListener();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawImage(icon, 0, 0, closeJFrame);
	}

	@Override
	public void loadIcon() {
		loadIconFromRes("/pic/back.png", closeJFrame);
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
	private void addListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					toJFrame.getDeclaredConstructor().newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				closeJFrame.dispose();
			}
		});
	}
}
