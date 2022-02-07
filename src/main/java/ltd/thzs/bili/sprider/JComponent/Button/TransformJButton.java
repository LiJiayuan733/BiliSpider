package ltd.thzs.bili.sprider.JComponent.Button;

import java.awt.Color;

import javax.swing.JFrame;

public class TransformJButton extends ToolsJButton{

	public TransformJButton(Class<? extends JFrame> toolsView, JFrame fromJFrame, Color BackGroundColor) {
		super(toolsView, fromJFrame, BackGroundColor);
	}

	private static final long serialVersionUID = 2994655872729345919L;

	@Override
	public void loadIcon() {
		loadIconFromRes("/pic/transform.png", fromJFrame);
	}
}
