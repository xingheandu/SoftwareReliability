package com.parameter;

import com.type.KernalFunctionType;

public class SVMParameter {
	public static KernalFunctionType type = null;
	public static double errorDeviation = 0;
	public static String resultStr = "SVMģ��δ��ʼ����";
	
	public static String getParamStr()
	{
		String param = "SVM����"+"\n"+
					   "�˺�����"+type.toString()+"\n"+
					   "----------------------------\n";
		return param;
	}
	
	public static String getResultStr()
	{
		String result = getParamStr();
		result+="Ԥ����"+errorDeviation+"\n";
		result+="-----------------------------\n";
		return result;
	}
}
