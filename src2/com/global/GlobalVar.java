package com.global;

import java.util.ArrayList;

import org.junit.Test;

import com.view.data.FaultDataCell;

public class GlobalVar {
	public static int node = 0;
	public static final double epsilon = 0.001;	//����ȽϾ���
	
	
	
	public static final int JMIndex = 0;
	public static final int GOIndex = 1;
	public static final int MOIndex = 2;
	public static final int BPIndex = 3;
	public static final int SVMIndex = 4;
	public static final String[] modelNodeName = {"J-Mģ��", "G-Oģ��", "M-Oģ��", "BP", "SVM"};
	public static final String rootNodeName = "����ɿ���ϵͳ";
	
	
	public static final String[] STYLE = {	"javax.swing.plaf.metal.MetalLookAndFeel",
			"javax.swing.plaf.nimbus.NimbusLookAndFeel",
			"com.sun.java.swing.plaf.motif.MotifLookAndFeel",
			"com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
			"com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"};
	public static final String projectPath = System.getProperty("user.dir");
	
	public static ArrayList<FaultDataCell> faultDataList = null;
	public static ArrayList<Double> faultDataDoubleList = null;
	
	public static String currentUserName = "";
	
	
	
	public static ArrayList<Double> getFaultDataDoubleList()
	{
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(new Double(0.0));
		for(FaultDataCell cell: faultDataList)
		{
			list.add(new Double(cell.gapTime));
		}
		return list;
	}
}
