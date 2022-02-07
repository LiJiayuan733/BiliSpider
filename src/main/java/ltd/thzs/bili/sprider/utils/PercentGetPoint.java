package ltd.thzs.bili.sprider.utils;

import java.awt.Component;
import java.awt.Point;

public class PercentGetPoint {
	public static Point[] GetDiCenterC(Component window,int width,int height) {
		Point[] p=new Point[2];
		int x=window.getWidth()/2-width/2;
		int y=window.getHeight()/2-height/2;
		p[0]=new Point(x,y);
		x=window.getWidth()/2+width/2;
		y=window.getHeight()/2+height/2;
		p[1]=new Point(x,y);
		return p;
	} 
}
