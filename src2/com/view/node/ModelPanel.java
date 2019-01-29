package com.view.node;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.data.xy.XYSeries;

import com.type.ChartType;
import com.type.ModelType;
import com.view.data.JChart;


public class ModelPanel {
	private JTabbedPane tabbedPane;
	private JPanel paramPanel;
	private JChart UDiagram, YDiagram;
	private JChart KSTestDiagram;
	private ModelType modelType;
	
	public ModelPanel(ModelType type) {
		this.modelType = type;
		tabbedPane = new JTabbedPane();
		initParamPanel();
		initUDiagram();
		initYDiagram();
		initKSTestPanel();
		//tabbedPane.add("参数设置", paramPanel);
		tabbedPane.add("U图", UDiagram.getChartPanel());
		tabbedPane.add("Y图", YDiagram.getChartPanel());
		tabbedPane.add("K-S检验",KSTestDiagram.getChartPanel());
	}
	

	


	private void initParamPanel() 
	{
		paramPanel = new JPanel(new BorderLayout());
		if(modelType == ModelType.JM)
		{
			initJMParamPanel();
		}
		else if(modelType == ModelType.GO)
		{
			initGOParamPanel();
		}
		else if(modelType == ModelType.MO)
		{
			initMOParamPanel();
		}
		else if(modelType == ModelType.BP)
		{
			initBPParamPanel();
		}
		else if(modelType == ModelType.SVM)
		{
			initSVMParamPanel();
		}
	}
	
	private void initSVMParamPanel() {
		// TODO Auto-generated method stub
		paramPanel.add(new JButton("SVM Param"));
	}





	private void initBPParamPanel() {
		// TODO Auto-generated method stub
		paramPanel.add(new JButton("BP Param"));
	}





	private void initMOParamPanel() {
		// TODO Auto-generated method stub
		paramPanel.add(new JButton("MO Param"));
	}





	private void initGOParamPanel() {
		// TODO Auto-generated method stub
		paramPanel.add(new JButton("GO Param"));
		
	}





	private void initJMParamPanel() {
		// TODO Auto-generated method stub
		paramPanel.add(new JButton("JM Model Param"));
	}





	private void initKSTestPanel() {
		KSTestDiagram = new JChart("K-S Test", "X", "Y"	, ChartType.PloygonalLine);
		KSTestDiagram.addDefaultSeries(1);
		
	}

	private void initYDiagram() {
		YDiagram = new JChart("Y Diagram", "x", "y", ChartType.CurveLine);
		YDiagram.addDefaultSeries(1);
		
	}

	private void initUDiagram() {
		UDiagram = new JChart("U Diagram", "x", "y", ChartType.CurveLine);
		UDiagram.addDefaultSeries(1);
		
	}
	
	public JTabbedPane getTabbedPane() 
	{
		return tabbedPane;
	}
	
	public void addUDiagramXYSeries(XYSeries series)
	{
		UDiagram.addDataSeries(series);
	}
	
	public void addYDiagramXYSeries(XYSeries series)
	{
		YDiagram.addDataSeries(series);
	}
	
	public void addKSTestDiaramXYseries(XYSeries series)
	{
		KSTestDiagram.addDataSeries(series);
	}

}
