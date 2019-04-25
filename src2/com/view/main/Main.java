package com.view.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.global.GlobalVar;
import com.view.login.LoginMainView;

public class Main {
	private static LoginMainView login = new LoginMainView();
	
	public static void main(String[] args) 
	throws ClassNotFoundException, InstantiationException, 
	IllegalAccessException, UnsupportedLookAndFeelException 
	{
		UIManager.setLookAndFeel(GlobalVar.STYLE[1]);
//		login.getFrame().setVisible(true);
		MainView view = new MainView();
		view.setVisible(true);
	}
	
	public static void runProgram()
	{
		login.getFrame().setVisible(false);
		
		LoadingView loading = new LoadingView();
		loading.start();

	}
}
