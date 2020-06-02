package com.view.data;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.data.xy.XYSeries;


import com.type.ChartType;

public class DataPanel {
	private static String path = "sample.txt";
	private ArrayList<FaultDataCell> faultDataList;
	//private JPanel panel = new JPanel(new BorderLayout());
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel tablePanel = new JPanel(new BorderLayout());
	private JPanel chartPanel = new JPanel(new BorderLayout());
	
	public DataPanel(String filePath)
	{
		if(filePath != "" && filePath!=null)
			path=filePath;
		try 
		{
			initTablePanel();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		initChartPanel();
		tabbedPane.add(tablePanel,"数据");
		tabbedPane.add(chartPanel,"散点图");
	}
	private void initChartPanel()
	{
		/*
		 * TODO:需要新增接口：添加若干series
		 * 
		 */
		JChart drawChart = new JChart("Fault Data", "x", "y", ChartType.Points);
		XYSeries series = new XYSeries(path);
		
		double sum = 0.0;
		for(FaultDataCell e: faultDataList)
		{
			sum+=e.gapTime;
			series.add(e.order,sum);
		}
		
		drawChart.addDataSeries(series);
		chartPanel.add(drawChart.getChartPanel());
		
	}
	
	private void initTablePanel() throws IOException
	{
		/*
		 * TODO: 从文件中读取数据，放到data
		 */
		String[] colNames = {"失效次数", "失效间隔","观测状态"};
		faultDataList = readDataFromFile();
		String[][] data = new String[faultDataList.size()][3];
		for(int i=0;i<faultDataList.size();i++)
		{
			data[i][0] = faultDataList.get(i).order+"";
			data[i][1] = faultDataList.get(i).gapTime+"";
			data[i][2] = faultDataList.get(i).state+"";
		}
		
		JTable table = new JTable(data, colNames);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class,tcr);
		
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePanel.add(scroll);
	}
	
	private ArrayList<FaultDataCell> readDataFromFile() throws IOException
	{
		ArrayList<FaultDataCell> list = new ArrayList<FaultDataCell>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String s;
		while((s=br.readLine())!=null)
		{
			String[] t = s.split("\\s");
			FaultDataCell faultData = new FaultDataCell(Integer.valueOf(t[0]), Double.valueOf(t[1]), Integer.valueOf(t[2]));
			list.add(faultData);
		}
		br.close();
		return list;
	}
	
	public JTabbedPane getTabbedPane()
	{
		return this.tabbedPane;
	}
	
	public ArrayList<FaultDataCell> getFaultDataList()
	{
		return faultDataList;
	}
	
	
	
	public static void main(String[] args) throws IOException 
	{
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.pack();
		DataPanel dataPanel = new DataPanel(null);
		frame.add(dataPanel.getTabbedPane());
	}

}
