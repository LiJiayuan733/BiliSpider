package ltd.thzs.bili.sprider.JComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class YZTextComponent extends Component {

	private static final long serialVersionUID = 1L;
	public Font MESSAGE_FONT=null;
	public String Message="";
	public ArrayList<String> ld;
	public YZTextComponent(Font MESSAGE_FONT) {
		this.MESSAGE_FONT=MESSAGE_FONT;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		NeedHeight(g, Message);
		g.setColor(new Color(72,72,72));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		drawString(g, ld);
	}
	public int NeedHeight(Graphics g,String Message){
		g.setFont(MESSAGE_FONT);
		this.Message=Message;
		ld=getLines(g, MESSAGE_FONT, Message);
		this.setPreferredSize(new Dimension(getWidth(), g.getFontMetrics().getAscent()*ld.size()));
		return g.getFontMetrics().getAscent()*ld.size();
	}
	public void drawString(Graphics g,ArrayList<String> ld) {
		FontMetrics fm = g.getFontMetrics();
		int lineHeight = fm.getAscent();
		for (int i = 1; i <= ld.size(); i++) {
			g.drawString(ld.get(i - 1), getWidth()/20, lineHeight * i); //params 3-4
		}
	}
	public ArrayList<String> getLines(Graphics g,Font MESSAGE_FONT,String Message){
		int lineSize = getWidth() -getWidth()/10; // param 1
		int tempSize = 0;
		g.setFont(MESSAGE_FONT);
		FontMetrics fm = g.getFontMetrics();
		String[] lines = Message.split("\n");
		StringBuffer sb = new StringBuffer();
		ArrayList<String> ld = new ArrayList<>();
		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				List<Integer> li = getWidthsString(line, fm);
				int im = li.get(i);
				char ia = line.charAt(i);
				tempSize += im;
				if (tempSize < lineSize) {
					sb.append(ia);
				} else {
					ld.add(sb.toString());
					sb = new StringBuffer();
					sb.append(ia);
					tempSize = im;
				}
				if (i == line.length() - 1 && tempSize != im) {
					ld.add(sb.toString());
					sb = new StringBuffer();
					tempSize = 0;
				}
			}
		}
		return ld;
	}
	public List<Integer> getWidthsString(String line,FontMetrics fm){
		ArrayList<Integer> ai=new ArrayList<>();
		char[] list=new char[line.length()];
		line.getChars(0, line.length(), list, 0);
		for(char i:list) {
			ai.add(fm.charWidth(i));
		}
		return ai;
	}
}
