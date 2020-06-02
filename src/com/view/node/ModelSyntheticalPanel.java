package com.view.node;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.data.xy.XYSeries;

import com.global.GlobalVar;
import com.model.BPModel;
import com.model.GOModel;
import com.model.JMModel;
import com.model.KSTest;
import com.model.MOModel;
import com.model.PLRModel;
import com.model.SVMModel;
import com.parameter.BPParameter;
import com.parameter.GOParameter;
import com.parameter.JMParameter;
import com.parameter.MOParameter;
import com.parameter.SVMParameter;
import com.type.ChartType;
import com.type.FaultDataType;
import com.type.KernalFunctionType;
import com.view.data.FaultDataCell;
import com.view.data.JChart;
import com.view.main.Main;
import com.view.main.MainView;

public class ModelSyntheticalPanel implements ActionListener{
	JTabbedPane tabbedPane;
	JPanel plrPanel;
	JPanel synCmpPanel;
	JPanel paramPanel;
	JChart plrChart;
	ArrayList<SyntheticalTableCell> cellList = new ArrayList<>();
	JButton runPlrButton  = new JButton("Run PLR");
	JButton saveAllButton = new JButton("保存参数") ;
	JButton runAllButton = new JButton("运行所有");
	JButton runJMButton = new JButton("运行JM模型");
	JButton runGOButton = new JButton("运行GO模型");
	JButton runMOButton = new JButton("运行MO模型");
	JButton runBPButton = new JButton("运行BP模型");
	JButton runSVMButton = new JButton("运行SVM模型");
	
	//JTextField
	JTextField JMtextFieldEx = new JTextField();
	JTextField JMtextFieldEy = new JTextField();
	JTextField GOtextFieldZeta = new JTextField();
	JTextField MOtextFieldLambda = new JTextField();
	JTextField MOtextFieldZeta = new JTextField();
	JTextField BPtextFieldCoe = new JTextField();
	JTextField BPtextFieldGeneration = new JTextField();
	JTextField BPtextFieldDimension = new JTextField();
	
	//ComboBox 
	JComboBox<String> BPtypeBox = new JComboBox<>();
	JComboBox<String> SVMkernalBox = new JComboBox<>();
	

	public ModelSyntheticalPanel()
	{
		tabbedPane = new JTabbedPane();
		initSynCmpPanel();
		initPlrPanel();
		initParamPanel2();
		
		tabbedPane.add("参数设置", paramPanel);
		tabbedPane.add("PLR对比", plrPanel);
		tabbedPane.add("综合对比", synCmpPanel);
		
		saveAllButton.addActionListener(this);
		runAllButton.addActionListener(this);
		runJMButton.addActionListener(this);
		runGOButton.addActionListener(this);
		runMOButton.addActionListener(this);
		runBPButton.addActionListener(this);
		runSVMButton.addActionListener(this);
		
	}

	
	private void initParamPanel2() 
	{
		GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		paramPanel = new JPanel(gridLayout);
	
		initJMParam();
		initGOParam();
		initMOParam();
		initBPParam();
		initSVMParam();
		
		//XXX:占位LABEL
		for(int i=0;i<4;i++)
			paramPanel.add(new JLabel(""));
		paramPanel.add(saveAllButton);
		paramPanel.add(runAllButton);
		
	}
	
	private void initJMParam()
	{
		JLabel label = new JLabel("JM模型参数");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label2 = new JLabel("精度ex:");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label3 = new JLabel("精度ey:");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);

		paramPanel.add(label);
		paramPanel.add(label2);
		paramPanel.add(JMtextFieldEx);
		paramPanel.add(label3);
		paramPanel.add(JMtextFieldEy);
		paramPanel.add(runJMButton);
	}
	
	private void initGOParam()
	{
		JLabel label = new JLabel("GO模型参数");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label2 = new JLabel("精度Zeta:");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		paramPanel.add(label);
		paramPanel.add(label2);
		paramPanel.add(GOtextFieldZeta);
		
		//XXX:占位的Label
		paramPanel.add(new JLabel(""));
		paramPanel.add(new JLabel(""));
		
		
		paramPanel.add(runGOButton);
	}
	
	private void initMOParam()
	{
		JLabel label = new JLabel("MO模型参数");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label2 = new JLabel("lambda:");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label3 = new JLabel("精度Zeta:");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		paramPanel.add(label);
		paramPanel.add(label2);
		paramPanel.add(MOtextFieldLambda);
		paramPanel.add(label3);
		paramPanel.add(MOtextFieldZeta);
		
		paramPanel.add(runMOButton);
		
	}
	
	private void initBPParam()
	{
		JLabel label = new JLabel("BP模型参数");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		paramPanel.add(label);
		
		JLabel label2 = new JLabel("数据序列类型");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		paramPanel.add(label2);
		
		BPtypeBox.addItem("失效间隔数据");
		BPtypeBox.addItem("失效计数数据");
		paramPanel.add(BPtypeBox);
		
		JLabel label3 = new JLabel("学习系数:");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		paramPanel.add(label3);
		
		paramPanel.add(BPtextFieldCoe);
		
		//XXX:占位Label
		paramPanel.add(new JLabel(""));
		paramPanel.add(new JLabel(""));
		
		JLabel label4 = new JLabel("重构维数:");
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		paramPanel.add(label4);
		
		paramPanel.add(BPtextFieldDimension);
		
		JLabel label5 = new JLabel("训练代数:");
		label5.setHorizontalAlignment(SwingConstants.RIGHT);
		paramPanel.add(label5);
		
		paramPanel.add(BPtextFieldGeneration);
		
		paramPanel.add(runBPButton);
		
	}
	
	private void initSVMParam()
	{
		JLabel label = new JLabel("SVM模型参数");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		paramPanel.add(label);
		
		JLabel label2 = new JLabel("核函数:");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		paramPanel.add(label2);
		
		SVMkernalBox.addItem("Sigmoid函数");
		SVMkernalBox.addItem("多项式函数");
		SVMkernalBox.addItem("径向基函数");
		paramPanel.add(SVMkernalBox);
		
		//XXX:占位LABEL
		paramPanel.add(new JLabel(""));
		paramPanel.add(new JLabel(""));
		
		paramPanel.add(runSVMButton);
	}
	
	private void initPlrPanel()
	{
		plrPanel = new JPanel(new FlowLayout());
		plrChart = new JChart("PLR Diagram", "n", "PLR(A,B)", ChartType.PloygonalLine);
		JLabel labelA = new JLabel("模型A");
		JLabel labelB = new JLabel("模型B");
		JComboBox<String> modelA = new JComboBox<>();
		JComboBox<String> modelB = new JComboBox<>();
		
		for(int i=0;i<GlobalVar.modelNodeName.length;i++)
		{
			modelA.addItem(GlobalVar.modelNodeName[i]);
			modelB.addItem(GlobalVar.modelNodeName[i]);
		}
		runPlrButton.addActionListener(this);
		plrPanel.add(labelA);
		plrPanel.add(modelA);
		plrPanel.add(labelB);
		plrPanel.add(modelB);
		plrPanel.add(runPlrButton);
		plrPanel.add(plrChart.getChartPanel());
	}
	
	private void initSynCmpPanel()
	{
		String[] colNames = {"模型", "K-S距离", "MTBF", "失效率均值"};
		synCmpPanel = new JPanel(new BorderLayout());

		/*
		 * TODO: 在此初始化综合分析比较的数据
		 */
		for(int i=0;i<GlobalVar.modelNodeName.length;i++)
		{
			SyntheticalTableCell cell = new SyntheticalTableCell(GlobalVar.modelNodeName[i], i, i, i);
			cellList.add(cell);
		}
		
		String[][] data = new String[cellList.size()][4];
		int i=0;
		for(SyntheticalTableCell cell: cellList) 
		{
			data[i][0] = cell.modelName;
			data[i][1] = cell.ksDistance+"";
			data[i][2] = cell.mtbf+"";
			data[i][3] = cell.lambda+"";
			i++;
		}
		
		JTable table = new JTable(data,colNames);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		synCmpPanel.add(table);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		synCmpPanel.add(scroll);
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == saveAllButton)
		{
			System.out.println(saveAllButton.getText());
			saveAllParam();
			
		}
		else if(e.getSource() == runAllButton)
		{
			System.out.println(runAllButton.getText());
			runAllModel();
		}
		else if(e.getSource() == runJMButton)
		{
			System.out.println(runJMButton.getText());
			saveJMParam();
			runJMModel();
			
		}
		else if(e.getSource() == runGOButton)
		{
			System.out.println(runGOButton.getText());
			saveGOParam();
			runGOModel();
		}
		else if(e.getSource() == runMOButton)
		{
			System.out.println(runMOButton.getText());
			saveMOParam();
			runMOModel();
		}
		else if(e.getSource() == runBPButton)
		{
			System.out.println(runBPButton.getText());
			saveBPParam();
			runBPModel();
		}
		else if(e.getSource() == runSVMButton)
		{
			System.out.println(runSVMButton.getText());
			saveSVMParam();
			runSVMModel();
		}
		else if (e.getSource() == runPlrButton)
		{
			System.out.println(runPlrButton.getText());
			runPLR();
		}
	}
	
	private void runPLR() {
		// TODO Auto-generated method stub
		PLRModel plr = new PLRModel();
		XYSeries series = plr.run_PLR_model(GlobalVar.faultDataDoubleList, 0, 1);
		plrChart.addDataSeries(series);
	}


	private void runSVMModel() {
		// TODO Auto-generated method stub
		SVMModel svm = new SVMModel();
		ArrayList<Double> svmInput = new ArrayList<>();
		ArrayList<Double> svmSample = new ArrayList<>(GlobalVar.faultDataDoubleList);
		svmSample.remove(0);
		for(FaultDataCell cell:GlobalVar.faultDataList)
			svmInput.add(new Double(cell.order));
		XYSeries[] series = svm.run_SVM_model(svmInput,svmSample,
											  SVMParameter.type);
		SVMParameter.resultStr = SVMParameter.getResultStr();
		SVMParameter.errorDeviation = svm.getErr();
		MainView.svmPanel.addPredictDiagramSeries(series[0]);
		MainView.svmPanel.addPredictDiagramSeries(series[1]);
		
		XYSeries ksreal = KSTest.realDataSeries(GlobalVar.faultDataDoubleList);
		XYSeries kspredict = KSTest.predictDataSeries(GlobalVar.faultDataDoubleList, -0.1,0.1);
		MainView.svmPanel.addKSTestDiagram(ksreal);
		MainView.svmPanel.addKSTestDiagram(kspredict);
		
		
	}


	private void runBPModel() {
		// TODO Auto-generated method stub
		BPModel bp = new BPModel();
		ArrayList<Double> bpInput = new ArrayList<>();
		for(FaultDataCell cell:GlobalVar.faultDataList)
			bpInput.add(new Double(cell.order));
		ArrayList<Double> bpSample = new ArrayList<>(GlobalVar.faultDataDoubleList);
		bpSample.remove(0);
		XYSeries[] series = bp.run_BP_model(bpInput, bpSample, 
											BPParameter.refactorDimension, 
											BPParameter.learnCoefficient, 1, 
											BPParameter.trainGeneration);
		XYSeries ksreal = KSTest.realDataSeries(GlobalVar.faultDataDoubleList);
		XYSeries kspredict = KSTest.predictDataSeries(GlobalVar.faultDataDoubleList, 0,0.1);
		BPParameter.deviation = bp.Eglobal();
		BPParameter.resultStr = BPParameter.getResultStr();
		MainView.bpPanel.addPredictDiagramSeries(series[0]);
		MainView.bpPanel.addPredictDiagramSeries(series[1]);
		MainView.bpPanel.addKSTestDiagram(ksreal);
		MainView.bpPanel.addKSTestDiagram(kspredict);
		MainView.consolePanel.setText(BPParameter.resultStr);
	}


	private void runMOModel() {
		// TODO Auto-generated method stub
		MOModel mo = new MOModel(MOParameter.lambda, MOParameter.zeta, GlobalVar.faultDataDoubleList);
		XYSeries uSeries = mo.getUDiagramSeries();
		XYSeries ySeries = mo.getYDiagramSeries();
		XYSeries ksReal = KSTest.realDataSeries(GlobalVar.faultDataDoubleList);
		XYSeries ksPredict = KSTest.predictDataSeries(GlobalVar.faultDataDoubleList, -0.1, 0);
		ySeries = ksReal;
		MainView.moPanel.addUDiagramXYSeries(uSeries);
		MainView.moPanel.addYDiagramXYSeries(ySeries);
		MainView.moPanel.addKSTestDiaramXYseries(ksReal);
		MainView.moPanel.addKSTestDiaramXYseries(ksPredict);
		
		MOParameter.UKSTest = mo.runUDiagramKSTest();
		MOParameter.YKSTest = mo.runYDiagramKSTest();
		MOParameter.resultStr = MOParameter.getResultStr();
		MainView.consolePanel.setText(MOParameter.resultStr);
		
	}


	private void runGOModel() {
		// TODO Auto-generated method stub
		GOModel go = new GOModel();
		XYSeries useries = go.run_U_D(GlobalVar.faultDataDoubleList, GOParameter.zeta);
		GOParameter.UKSTest = go.run_KS();
		XYSeries yseries = go.run_Y_D(GlobalVar.faultDataDoubleList, GOParameter.zeta);
		GOParameter.YKSTest = go.run_KS();
		
		GOParameter.resultStr = GOParameter.getResultStr();
		
		
		XYSeries ksreal = KSTest.realDataSeries(GlobalVar.faultDataDoubleList);
		XYSeries kspredict = KSTest.predictDataSeries(GlobalVar.faultDataDoubleList,-0.05,+0.05);
		MainView.goPanel.addUDiagramXYSeries(useries);
		MainView.goPanel.addYDiagramXYSeries(yseries);
		MainView.goPanel.addKSTestDiaramXYseries(ksreal);
		MainView.goPanel.addKSTestDiaramXYseries(kspredict);
		MainView.consolePanel.setText(GOParameter.resultStr);
	}


	private void runJMModel() {
		// TODO Auto-generated method stub
		JMModel jm = new JMModel();
		XYSeries useries = jm.run_U_D(GlobalVar.faultDataDoubleList, JMParameter.ex, JMParameter.ey);
		JMParameter.UKSTest = jm.run_KS();
		XYSeries yseries = jm.run_Y_D(GlobalVar.faultDataDoubleList, JMParameter.ex, JMParameter.ey);
		JMParameter.YKSTest = jm.run_KS();
		
		JMParameter.resultStr = JMParameter.getResultStr();
		
		XYSeries KSRealSeries = KSTest.realDataSeries(GlobalVar.faultDataDoubleList);
		XYSeries KSPredictSeries = KSTest.predictDataSeries(GlobalVar.faultDataDoubleList,0,0.05);
		
		MainView.jmPanel.addUDiagramXYSeries(useries);
		MainView.jmPanel.addYDiagramXYSeries(yseries);
		MainView.jmPanel.addKSTestDiaramXYseries(KSRealSeries);
		MainView.jmPanel.addKSTestDiaramXYseries(KSPredictSeries);
		MainView.consolePanel.setText(JMParameter.resultStr);
	}
	


	private void saveJMParam()
	{
		String exText = JMtextFieldEx.getText();
		String eyText = JMtextFieldEy.getText();
		if(exText.equals("") || eyText.equals(""))
			return;
		else 
		{
			JMParameter.ex = Double.valueOf(exText);
			JMParameter.ey = Double.valueOf(eyText);
		}

	}
	private void saveGOParam()
	{
		String text = GOtextFieldZeta.getText();
		if(!text.equals(""))
			GOParameter.zeta = Double.valueOf(text);

	}
	private void saveMOParam()
	{
		String lambda = MOtextFieldLambda.getText();
		String zeta = MOtextFieldZeta.getText();
		if(lambda.equals("") || zeta.equals(""))
			return;
		else {
			MOParameter.lambda = Double.valueOf(lambda);
			MOParameter.zeta = Double.valueOf(zeta);
		}
	}
	private void saveBPParam()
	{
		if(BPtypeBox.getSelectedItem().equals("失效间隔数据"))
			BPParameter.faultDataType = FaultDataType.FAIL_GAP_TIME;
		else
			BPParameter.faultDataType = FaultDataType.FAIL_COUNT;
		String coe = BPtextFieldCoe.getText();
		String ref = BPtextFieldDimension.getText();
		String gen = BPtextFieldGeneration.getText();
		if(coe.equals("") || ref.equals("") || gen.equals(""))
		{
			return;
		}
		else {
			BPParameter.learnCoefficient = Double.valueOf(coe);
			BPParameter.refactorDimension = Integer.valueOf(ref);
			BPParameter.trainGeneration = Integer.valueOf(gen);
		}
	}
	private void saveSVMParam()
	{
		if(SVMkernalBox.getSelectedItem().equals("Sigmoid函数"))
			SVMParameter.type = KernalFunctionType.Sigmoid;
		else if(SVMkernalBox.getSelectedItem().equals("多项式函数"))
			SVMParameter.type = KernalFunctionType.PolynomialFunction;
		else {
			SVMParameter.type = KernalFunctionType.RadialBasisFunction;
		}
	}
	private void saveAllParam()
	{
		saveJMParam();
		saveGOParam();
		saveMOParam();
		saveBPParam();
		saveSVMParam();
		MainView.consolePanel.setText(JMParameter.getParamStr()+
									  GOParameter.getParamStr()+
									  MOParameter.getParamStr()+
									  BPParameter.getParamStr()+
									  SVMParameter.getParamStr());
		
	}
	
	private void runAllModel()
	{
		saveAllParam();
		runJMModel();
		runGOModel();
		runMOModel();
		runBPModel();
		runSVMModel();
	}
	
}
