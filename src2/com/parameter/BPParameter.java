package com.parameter;

import com.type.FaultDataType;

public class BPParameter {
	public static FaultDataType faultDataType = null;
	public static double learnCoefficient = 0.1;	//范围[0,1]，越大学习越快
	public static int refactorDimension = 5;		//3-10,输入层节点个数
	public static int trainGeneration = 10000;		//10-10000;
	public static double deviation = 0;				//误差
	public static String resultStr = "BP模型未初始化";
	public static String getParamStr()
	{
		String result = "BP模型参数\n";
		result+="学习系数："+learnCoefficient+"\n";
		result+="重构维数："+refactorDimension+"\n";
		result+="训练代数："+trainGeneration+"\n";
		result+="---------------------------\n";
		return result;
	}
	
	public static String getResultStr()
	{
		String result = getParamStr();
		result+="全局学习误差："+deviation+"\n";
		return result;
	}
}
