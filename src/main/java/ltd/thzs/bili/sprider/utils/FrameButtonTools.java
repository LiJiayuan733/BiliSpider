package ltd.thzs.bili.sprider.utils;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.formdev.flatlaf.icons.FlatWindowIconifyIcon;
import com.formdev.flatlaf.icons.FlatWindowMaximizeIcon;

import ltd.thzs.bili.sprider.JComponent.Border.FrameTopBorder;
import ltd.thzs.bili.sprider.JComponent.Button.DisposeButton;

public class FrameButtonTools {
	public static void addDisposeButton(JFrame frame, int size, int index) {
		//Icon disposeIcon = new FlatWindowCloseIcon();
		//JButton button = new JButton(disposeIcon);
		DisposeButton button=new DisposeButton();
		button.setSize(new Dimension(frame.getWidth() / size, frame.getWidth() / size));
		button.setLocation(frame.getWidth() - button.getWidth() - index * frame.getWidth() / size, 0);
		button.setBackground(new Color(231, 76, 60, 0));
		button.addActionListener(e -> {
			frame.dispose();
		});
		frame.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				button.setLocation(
						e.getWindow().getWidth() - button.getWidth() - index * e.getWindow().getWidth() / size, 0);
				e.getWindow().repaint();
			}
		});
		frame.getContentPane().add(button);
		frame.repaint();
	}

	public static void addMaxButton(JFrame frame, int size, int index) {
		Icon disposeIcon = new FlatWindowMaximizeIcon();
		JButton button = new JButton(disposeIcon);
		button.setSize(new Dimension(frame.getWidth() / size, frame.getWidth() / size));
		button.setLocation(frame.getWidth() - button.getWidth() - index * frame.getWidth() / size, 0);
		button.setBackground(new Color(241, 196, 15, 0));
		button.addActionListener(e -> {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		});
		frame.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				button.setLocation(
						e.getWindow().getWidth() - button.getWidth() - index * e.getWindow().getWidth() / size, 0);
				e.getWindow().repaint();
			}
		});
		frame.getContentPane().add(button);
		frame.repaint();
	}

	public static void addIconifiedButton(JFrame frame, int size, int index) {
		Icon disposeIcon = new FlatWindowIconifyIcon();
		JButton button = new JButton(disposeIcon);
		button.setSize(new Dimension(frame.getWidth() / size, frame.getWidth() / size));
		button.setLocation(frame.getWidth() - button.getWidth() - index * frame.getWidth() / size, 0);
		button.setBackground(new Color(26, 188, 156, 0));
		button.addActionListener(e -> {
			frame.setExtendedState(JFrame.ICONIFIED);
		});
		frame.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				button.setLocation(
						e.getWindow().getWidth() - button.getWidth() - index * e.getWindow().getWidth() / size, 0);
				e.getWindow().repaint();
			}
		});
		frame.getContentPane().add(button);
		frame.repaint();
	}
	public static int addTopBorder(JFrame window, int DevHeight){
		FrameTopBorder tb=new FrameTopBorder(window,window.getHeight()/DevHeight);
		window.getContentPane().add(tb);
		window.repaint();
		return window.getHeight()/DevHeight;
	}
}
