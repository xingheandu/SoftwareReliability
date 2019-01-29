package com.parameter;

import com.global.GlobalVar;

public class JMParameter extends ModelParameter{
	public static double ex = 0.1;
	public static double ey = 0.1;
	public static String resultStr = "JM模型未初始化";
	
	public static String getParamStr()
	{
		String result = "JM模型参数：ex="+ ex + "," + "ey=" + ey + "\n";
		return result;
	}
	
	public static String getResultStr()
	{
		if(UKSTest == null || YKSTest == null)
			return resultStr;
		String result = "参数：ex="+ ex + "," + "ey=" + ey + "\n" + 
						"U Diagram K-S Distance:" + UKSTest[0] + "\n" + 
						"Y Diagram K-S Distance:" + YKSTest[0] + "\n";
		result += "基于U图的K-S检验结果：";
		if((UKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"失效数据样本不满足JM模型。\n";
		else
			result+=acceptStr+"失效数据样本满足JM模型。\n";
		
		result += "基于Y图的K-S检验结果：";
		if((YKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"失效数据样本不满足JM模型。\n";
		else
			result+=acceptStr+"失效数据样本满足JM模型。\n";
		return result;
	}
}
