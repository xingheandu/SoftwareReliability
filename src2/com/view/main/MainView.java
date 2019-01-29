package com.view.main;

import java.awt.BorderLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ExtensionFileFilter;

import com.global.GlobalVar;
import com.parameter.BPParameter;
import com.parameter.GOParameter;
import com.parameter.JMParameter;
import com.parameter.MOParameter;
import com.parameter.SVMParameter;
import com.type.ModelType;
import com.view.console.ConsolePanel;
import com.view.data.DataPanel;
import com.view.login.LoginMainView;
import com.view.node.MLModelPanel;
import com.view.node.ModelPanel;
import com.view.node.ModelSyntheticalPanel;
import com.view.node.UserPanel;

public class MainView extends JFrame implements ActionListener{
	//public static Font fontCN = new Font("宋体", Font.PLAIN, 16); // 中文字体
	public String currentPath = "sample.txt";
	public String currentName = "sample.txt";
	
	private JTree treeBar = null;
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem itemImport = new JMenuItem("数据导入");
	private JMenuItem itemExport = new JMenuItem("数据导出");
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(GlobalVar.rootNodeName);	//一级Node
	private DefaultMutableTreeNode modelNodes = new DefaultMutableTreeNode("模型种类");			//二级Node
	private DefaultMutableTreeNode dataNodes = new DefaultMutableTreeNode("失效数据");			//二级Node
	private DefaultMutableTreeNode jmNode = new DefaultMutableTreeNode(GlobalVar.modelNodeName[GlobalVar.JMIndex]);
	private DefaultMutableTreeNode goNode = new DefaultMutableTreeNode(GlobalVar.modelNodeName[GlobalVar.GOIndex]);
	private DefaultMutableTreeNode moNode = new DefaultMutableTreeNode(GlobalVar.modelNodeName[GlobalVar.MOIndex]);
	private DefaultMutableTreeNode bpNode = new DefaultMutableTreeNode(GlobalVar.modelNodeName[GlobalVar.BPIndex]);
	private DefaultMutableTreeNode svmNode = new DefaultMutableTreeNode(GlobalVar.modelNodeName[GlobalVar.SVMIndex]);
	private DefaultMutableTreeNode[] treeNodes = {jmNode, goNode, moNode, bpNode, svmNode};

	private JSplitPane horizonalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);	//JFrame分为左右，左是导航栏
	private JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);		//右边是一个JPanel,分为上下两部分
	private JPanel rightTopPanel = new JPanel(new BorderLayout());
	public static ConsolePanel consolePanel = new ConsolePanel();
	
	private UserPanel userPanel = new UserPanel();
	private DataPanel dataPanel = null;
	private ModelSyntheticalPanel syntheticalPanel = new ModelSyntheticalPanel();
	public static ModelPanel jmPanel = new ModelPanel(ModelType.JM);
	public static ModelPanel goPanel = new ModelPanel(ModelType.GO);
	public static ModelPanel moPanel = new ModelPanel(ModelType.MO);
	public static MLModelPanel bpPanel = new MLModelPanel(ModelType.BP);
	public static MLModelPanel svmPanel = new MLModelPanel(ModelType.SVM);
	
	public MainView() 
	{
		setTitle("软件可靠性建模与分析系统");
		setLocation(100, 100);
		setLayout(new BorderLayout());
		setSize(1000, 600);
		//init operations
		initTreeBar();
		initMeunBar();
		
		setJMenuBar(menuBar);
		consolePanel.getPanel().setBorder(BorderFactory.createTitledBorder("Console"));
		
		horizonalSplitPane.setLeftComponent(treeBar);
		verticalSplitPane.setTopComponent(rightTopPanel);
		verticalSplitPane.setBottomComponent(consolePanel.getPanel());
		verticalSplitPane.setDividerLocation(getHeight()*2/3);
		horizonalSplitPane.setRightComponent(verticalSplitPane);
		
		horizonalSplitPane.setOneTouchExpandable(true);
		horizonalSplitPane.setContinuousLayout(true);
		verticalSplitPane.setOneTouchExpandable(true);
		verticalSplitPane.setContinuousLayout(true);

		setContentPane(horizonalSplitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initTreeBar()
	{
		for(int i=0;i<treeNodes.length;i++)
		{
			modelNodes.add(treeNodes[i]);
		}
		rootNode.add(modelNodes);
		rootNode.add(dataNodes);
		treeBar = new JTree(rootNode);
		treeBar.setShowsRootHandles(true);
		treeBar.setRootVisible(true);
		treeBar.setBorder(BorderFactory.createTitledBorder(""));
		treeBar.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeBar.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				try {
					treeBarValueChangedHandler(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void treeBarValueChangedHandler(TreeSelectionEvent e) throws IOException
	{
		rightTopPanel.removeAll();
		String s = e.getNewLeadSelectionPath().toString();
		if(s.indexOf(GlobalVar.modelNodeName[GlobalVar.JMIndex])!=-1)
		{
			System.out.println("change to jm pane");
			if(jmPanel==null)
			{
				jmPanel = new ModelPanel(ModelType.JM);
			}
			rightTopPanel.add(jmPanel.getTabbedPane());
			consolePanel.setText(JMParameter.resultStr);
		}
		else if(s.indexOf(GlobalVar.modelNodeName[GlobalVar.GOIndex])!=-1)
		{
			System.out.println("go pane");
			if(goPanel == null)
			{
				goPanel = new ModelPanel(ModelType.GO);
			}
			rightTopPanel.add(goPanel.getTabbedPane());
			consolePanel.setText(GOParameter.resultStr);
			
		}
		else if(s.indexOf(GlobalVar.modelNodeName[GlobalVar.MOIndex])!=-1)
		{
			System.out.println("mo pane");
			if(moPanel == null)
			{
				moPanel = new ModelPanel(ModelType.MO);
			}
			rightTopPanel.add(moPanel.getTabbedPane());
			consolePanel.setText(MOParameter.resultStr);
		}
		else if(s.indexOf(GlobalVar.modelNodeName[GlobalVar.BPIndex])!=-1)
		{
			System.out.println("bp pane");
			if(bpPanel == null)
			{
				bpPanel = new MLModelPanel(ModelType.BP);
			}
			rightTopPanel.add(bpPanel.getTabbedPane());
			consolePanel.setText(BPParameter.resultStr);
		}
		else if(s.indexOf(GlobalVar.modelNodeName[GlobalVar.SVMIndex])!=-1)
		{
			System.out.println("svm pane");
			if(svmPanel == null)
			{
				svmPanel = new MLModelPanel(ModelType.SVM);
			}
			rightTopPanel.add(svmPanel.getTabbedPane());
			consolePanel.setText(SVMParameter.resultStr);
		}
		else if(s.indexOf("失效数据")!=-1)
		{
			System.out.println("data pane");
			if(dataPanel != null)
			{
				rightTopPanel.add(dataPanel.getTabbedPane());
			}
				
		}
		else if(s.indexOf("模型种类")!=-1)
		{
			System.out.println("sycthetical pane");
			if(syntheticalPanel == null)
			{
				syntheticalPanel = new ModelSyntheticalPanel();
			}
			rightTopPanel.add(syntheticalPanel.getTabbedPane());
		}
		else if(s.indexOf(GlobalVar.rootNodeName)!=-1)
		{
			System.out.println("root user pane");
			rightTopPanel.add(userPanel.getPanel());
				
		}
		rightTopPanel.repaint();
	}
	
	private void initMeunBar()
	{
		JMenu fileMenu = new JMenu("文件");
		fileMenu.add(itemExport);
		fileMenu.add(itemImport);
		menuBar.add(fileMenu);
		
		
		menuBar.add(new JMenu("数据分析"));
		menuBar.add(new JMenu("模型预测"));
		menuBar.add(new JMenu("模型拟合"));
		
		JMenu moreMenu = new JMenu("更多");
		JMenuItem itemHelp = new JMenuItem("帮助");
		JMenuItem itemAbout = new JMenuItem("关于");
		moreMenu.add(itemHelp);
		moreMenu.add(itemAbout);
		menuBar.add(moreMenu);
		
		itemImport.addActionListener(this);
		itemExport.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == itemImport)
		{
			System.out.println("Import is clicked");
			JFileChooser fileChooser = new JFileChooser(GlobalVar.projectPath);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "失效数据文件(*.txt, *.xls, *.xlsx)", "txt", "xls", "xlsx");//文件名过滤器
			fileChooser.setFileFilter(filter);
			int result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				currentName = file.getName();
				currentPath = file.getAbsolutePath();
				dataPanel = new DataPanel(currentPath);
				GlobalVar.faultDataList = dataPanel.getFaultDataList();
				GlobalVar.faultDataDoubleList = GlobalVar.getFaultDataDoubleList();
				rightTopPanel.removeAll();
				rightTopPanel.add(dataPanel.getTabbedPane());
				
			}
		}
		else if(e.getSource() == itemExport)
		{
			System.out.println("Export is clicked");
		}
	}
	
	private void initRightBottomPanel()
	{
		
	}
	
}
