package com.view.node;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.global.GlobalVar;
import com.mysql.cj.fabric.xmlrpc.base.Data;

public class UserPanel{
	JPanel panel;
	
	public UserPanel()
	{
		Calendar calendar = Calendar.getInstance();
		panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("欢迎，" + GlobalVar.currentUserName + "!" + "\n" +  
								 "今天是" + calendar.get(Calendar.YEAR) + "年"+
								 calendar.get(Calendar.MONTH)+1 + "月" + 
								 calendar.get(Calendar.DAY_OF_MONTH) + "日");
		label.setFont(new Font("宋体", Font.BOLD, 30));
		panel.add(label);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
