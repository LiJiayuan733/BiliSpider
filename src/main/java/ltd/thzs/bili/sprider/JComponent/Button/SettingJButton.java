package ltd.thzs.bili.sprider.JComponent.Button;

import java.awt.Color;

import javax.swing.JFrame;

public class SettingJButton extends ToolsJButton{

	private static final long serialVersionUID = -1988921909631971344L;

	public SettingJButton(Class<? extends JFrame> class1, JFrame fromJFrame, Color BackGroundColor) {
		super(class1, fromJFrame, BackGroundColor);
	}
	
	@Override
	public void loadIcon() {
		loadIconFromRes("/pic/setting.png", fromJFrame);
	}
}
