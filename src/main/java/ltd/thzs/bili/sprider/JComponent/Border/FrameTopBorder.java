package ltd.thzs.bili.sprider.JComponent.Border;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class FrameTopBorder extends JComponent{
	private static final long serialVersionUID = 8796291274686814937L;
	public JFrame LockFrame;
	public int Height;
	public Color color;
	public int x1,y1;
	public FrameTopBorder(JFrame frame,int Height){
		super();
		this.LockFrame=frame;
		this.Height=Height;
		setBounds(0, 0, frame.getWidth(), Height);
		this.color=UIManager.getColor("FrameTopBorderColor");
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				LockFrame.setLocation(LockFrame.getX()+e.getX()-x1,LockFrame.getY()+e.getY()-y1);
			}
		});
		addMouseListener(new MouseAdapter() {	
			@Override
			public void mousePressed(MouseEvent e) {
				x1=e.getX();
				y1=e.getY();
			}
		});
		LockFrame.addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				if(e.getNewState()==JFrame.MAXIMIZED_BOTH) {
					setBounds(0, 0, e.getWindow().getWidth(), Height);
					e.getWindow().repaint();
				}
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillRect(0, 0, LockFrame.getWidth(), Height);
	}
}
