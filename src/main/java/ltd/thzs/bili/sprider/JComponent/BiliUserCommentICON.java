package ltd.thzs.bili.sprider.JComponent;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import ltd.thzs.bili.sprider.JComponent.Button.ListStyleMenuButton.state;

public class BiliUserCommentICON extends Component{
	public state St=state.inOut;
	public String url;
	public String desc;
	public BiliUserCommentICON(String url,String desc) {
		this.url=url;
		this.desc=desc;
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
	private static final long serialVersionUID = -6308439644784522962L;
	public Image ip=null;
	public void paint(Graphics g) {
		if(ip==null) {
			new Thread(new Runnable() {			
				@Override
				public void run() {
					try {
						ip=new ImageIcon(new URL(url)).getImage().getScaledInstance(getHeight(), getHeight(), Image.SCALE_SMOOTH);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}).start();
			return;
		}
		
	};
	@Override
	public String getName() {
		return "BiliUserCommentICON";
	}
}
