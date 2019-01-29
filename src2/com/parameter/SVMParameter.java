package com.parameter;

import com.type.KernalFunctionType;

public class SVMParameter {
	public static KernalFunctionType type = null;
	public static double errorDeviation = 0;
	public static String resultStr = "SVM模型未初始化。";
	
	public static String getParamStr()
	{
		String param = "SVM参数"+"\n"+
					   "核函数："+type.toString()+"\n"+
					   "----------------------------\n";
		return param;
	}
	
	public static String getResultStr()
	{
		String result = getParamStr();
		result+="预测误差："+errorDeviation+"\n";
		result+="-----------------------------\n";
		return result;
	}
}
