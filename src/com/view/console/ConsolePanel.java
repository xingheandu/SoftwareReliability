package com.view.console;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel {
	JPanel panel = new JPanel(new BorderLayout());
	JTextArea textArea = new JTextArea("I am the console!");
	
	public ConsolePanel()
	{
		textArea.setBackground(panel.getBackground());
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
	}
	
	public void setText(String s)
	{
		textArea.setText(s);
	}
	
	public void appendText(String s)
	{
		textArea.append(s);
	}
	
	public void appendTextln(String s)
	{
		textArea.append(s+"\n");
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

}
