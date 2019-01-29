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
	Thread splashThread; // �����������߳�
	JProgressBar progress; // ������
 
	public LoadingView() {
		Container container = getContentPane(); // �õ�����
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // ���ù��
		String icon = "res/Android.png";
		if (icon != null) {
			container.add(new JLabel(new ImageIcon(icon)), BorderLayout.CENTER); // ����ͼƬ
		}
		progress = new JProgressBar(1, 100); // ʵ����������
		progress.setStringPainted(true); // �������
		progress.setString("���س�����,���Ժ�......"); // ������ʾ����
		progress.setBackground(Color.white); // ���ñ���ɫ
		container.add(progress, BorderLayout.SOUTH); // ���ӽ�������������
 
		Dimension screen = getToolkit().getScreenSize(); // �õ���Ļ�ߴ�
		pack(); // ������Ӧ����ߴ�
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2); // ���ô���λ��
	}
 
	public void start() {
		this.toFront(); // ����ǰ����ʾ
		splashThread = new Thread(this); // ʵ�����߳�
		splashThread.start(); // ��ʼ�����߳�
	}
 
	public void run() {
		setVisible(true); // ��ʾ����
		try {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(50); // �߳�����
				progress.setValue(progress.getValue() + 1); // ���ý�����ֵ
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dispose(); // �ͷŴ���
		showFrame(); // ����������
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
 
