package com.view.main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
public class LoadingView extends JWindow implements Runnable {
	Thread splashThread; // 进度条更新线程
	JProgressBar progress; // 进度条
 
	public LoadingView() {
		Container container = getContentPane(); // 得到容器
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // 设置光标
		String icon = "res/Android.png";
		if (icon != null) {
			container.add(new JLabel(new ImageIcon(icon)), BorderLayout.CENTER); // 增加图片
		}
		progress = new JProgressBar(1, 100); // 实例化进度条
		progress.setStringPainted(true); // 描绘文字
		progress.setString("加载程序中,请稍候......"); // 设置显示文字
		progress.setBackground(Color.white); // 设置背景色
		container.add(progress, BorderLayout.SOUTH); // 增加进度条到容器上
 
		Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸
		pack(); // 窗口适应组件尺寸
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2); // 设置窗口位置
	}
 
	public void start() {
		this.toFront(); // 窗口前端显示
		splashThread = new Thread(this); // 实例化线程
		splashThread.start(); // 开始运行线程
	}
 
	public void run() {
		setVisible(true); // 显示窗口
		try {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(50); // 线程休眠
				progress.setValue(progress.getValue() + 1); // 设置进度条值
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dispose(); // 释放窗口
		showFrame(); // 运行主程序
	}
 
	static void showFrame() {
		MainView view = new MainView();
		view.setVisible(true);
	}
 
//	public static void main(String[] args) {
//		LoadingView loading = new LoadingView();
//		loading.start();
//	}
}
 
