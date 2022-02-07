package ltd.thzs.bili.sprider.utils;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 用坐标法完成布局
 * */
public class PercentLocate {
	public static void SetPosition(int p1,int p2,Component window,Component comp) {
		comp.setLocation(window.getWidth()*p1/1000-comp.getWidth()/2, window.getHeight()*p2/1000-comp.getHeight()/2);
	}
	public static void SetPosition(int p1,int p2,Component window,Component comp,boolean isT) {
		comp.setLocation(window.getWidth()*p1/1000, window.getHeight()*p2/1000);
	}
	public static void SetSize(int p1,int p2,Component window,Component comp) {
		if(comp.getClass()==JPanel.class) {
			((JPanel)comp).setLayout(null);
			comp.setSize(new Dimension(window.getWidth()*p1/1000, window.getHeight()*p2/1000));
		}else {
			comp.setSize(window.getWidth()*p1/1000, window.getHeight()*p2/1000);
		}
	}
	public static void SetPCenter(Component window,Component comp) {
		SetPosition(500, 500, window, comp);
	}
	public static void SetPLeftCenter(Component window,Component comp,int margin) {
		comp.setLocation(0+margin, window.getHeight()*500/1000);
	}
	public static void SetPRightCenter(Component window,Component comp,int margin) {
		comp.setLocation(window.getWidth()-comp.getWidth()-margin, window.getHeight()*500/1000);
	}
	public static void SetPTopCenter(Component window,Component comp,int margin) {
		comp.setLocation(window.getWidth()*500/1000-comp.getWidth()/2, 0+margin);
	}
	public static void SetPBottomCenter(Component window,Component comp,int margin) {
		comp.setLocation(window.getWidth()*500/1000-comp.getWidth()/2, window.getHeight()-comp.getHeight()-margin);
	}
	/**
	 * 在参考控件的上面两者中间对齐
	 * @param comp1 参考控件
	 * @param margin 两控件距离
	 * @param comp2 需设置的控件
	 * */
	public static void SetPTopCenterCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+comp1.getWidth()/2-comp2.getWidth()/2, comp1.getY()-margin-comp2.getHeight());
	}
	public static void SetPBottomCenterCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+comp1.getWidth()/2-comp2.getWidth()/2, comp1.getY()+margin+comp1.getHeight());
	}
	public static void SetPLeftCenterCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()-margin-comp2.getWidth(), comp1.getY()+comp1.getHeight()/2-comp2.getHeight()/2);
	}
	public static void SetPRightCenterCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+margin+comp1.getWidth(), comp1.getY()+comp1.getHeight()/2-comp2.getHeight()/2);
	}
	public static void SetPTopLeftCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX(),comp1.getY()-margin-comp2.getHeight());
	}
	public static void SetPBottomLeftCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX(),comp1.getY()+margin+comp1.getHeight());
	}
	public static void SetPTopRightCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+comp1.getWidth()-comp2.getWidth(),comp1.getY()-margin-comp2.getHeight());
	}
	public static void SetPBottomRightCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+comp1.getWidth()-comp2.getWidth(),comp1.getY()+margin+comp1.getHeight());
	}
	public static void SetPLeftTopCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()-margin-comp2.getWidth(), comp1.getY());
	}
	public static void SetPRightTopCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+margin+comp1.getWidth(), comp1.getY());
	}
	public static void SetPRightBottomCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()+margin+comp1.getWidth(), comp1.getY()+comp1.getHeight()-comp2.getHeight());
	}
	public static void SetPLeftBottomCC(Component comp1,Component comp2,int margin) {
		comp2.setLocation(comp1.getX()-margin-comp2.getWidth(), comp1.getY()+comp1.getHeight()-comp2.getHeight());
	}
}
