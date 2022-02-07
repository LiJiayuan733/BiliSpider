package ltd.thzs.bili.sprider.JComponent;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.border.Border;

import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.JComponent.Border.DeBugBorder;

public class YZPointCC extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7346901301995780401L;
	public boolean Debug=false;
	@Override
	public Border getBorder() {
		if(Debug) {
			return new DeBugBorder();
		}
		return super.getBorder();
	}
	public YZPointCC() {
		this.Debug=ConfigData.Debug;
		this.setSize(20, 20);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(Debug) {
			g.setColor(Color.cyan);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
