package ltd.thzs.bili.sprider.JComponent.Button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class ListStyleMenuButton extends JButton {
	public enum state{
		Entered,Pressed,inOut
	};
	public ListStyleMenuButton() {
		addDefListener();
	}
	public Image icon=null;
	public state St=state.inOut;
	private static final long serialVersionUID = -3087017298379570336L;
	@Override
	public void paint(Graphics g) {
		if(icon==null) {
			loadIcon();
		}
		switch (St) {
			case Entered:
				onEntered(g);
				break;
			case Pressed:
				onPressed(g);
				break;
			default:
				onInout(g);
				break;
		}
	}
	/**
	 * <h1>Must Override</h1>
	 * <p>Icon must draw for your</p>
	 * @return 
	 * */
	public abstract void loadIcon();
	public abstract void onEntered(Graphics g);
	public abstract void onPressed(Graphics g);
	public abstract void onInout(Graphics g);
	public void loadIconFromRes(String path,JFrame loadJFrame) {
		icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
		icon = icon.getScaledInstance(getWidth(), getWidth() * icon.getHeight(loadJFrame) / icon.getWidth(loadJFrame),
				Image.SCALE_DEFAULT);
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
		});
	}
}
