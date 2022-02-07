package ltd.thzs.bili.sprider.JComponent.Button;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.border.Border;

import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.JComponent.Border.DeBugBorder;

public class DisposeButton extends ListStyleMenuButton{

	private static final long serialVersionUID = 5248881121546793221L;
	
	public boolean Debug=false;
	public DisposeButton() {
		super();
		this.Debug=ConfigData.Debug;
	}
	@Override
	public Border getBorder() {
		if(Debug) {
			return new DeBugBorder();
		}
		return super.getBorder();
	}
	@Override
	public void onEntered(Graphics g) {
		g.setColor(new Color(229, 43, 80,200));
		g.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight()/5, getHeight()/5);
	}
	@Override
	public void onPressed(Graphics g) {
		g.setColor(new Color(229, 43, 80,200));
		g.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight()/5, getHeight()/5);
	}
	@Override
	public void onInout(Graphics g) {
		g.setColor(new Color(229, 43, 80,100));
		g.fillRoundRect(0, 0, getWidth(), getHeight()/10, getHeight()/5, getHeight()/5);
		g.fillRect(0, 0, getWidth(), getHeight()/20);
	}
	@Override
	public void loadIcon() {
		
	}
}
