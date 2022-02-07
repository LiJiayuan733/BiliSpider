package ltd.thzs.bili.sprider.JComponent.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class DeBugBorder implements Border{

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(5, 5, 5, 5);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}

}
