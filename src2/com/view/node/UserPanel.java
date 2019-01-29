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
		JLabel label = new JLabel("��ӭ��" + GlobalVar.currentUserName + "!" + "\n" +  
								 "������" + calendar.get(Calendar.YEAR) + "��"+
								 calendar.get(Calendar.MONTH)+1 + "��" + 
								 calendar.get(Calendar.DAY_OF_MONTH) + "��");
		label.setFont(new Font("����", Font.BOLD, 30));
		panel.add(label);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
