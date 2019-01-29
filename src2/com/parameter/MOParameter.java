package com.parameter;

import com.global.GlobalVar;

public class MOParameter extends ModelParameter{
	public static double lambda = 0.1;
	public static double zeta = 0.1;
	public static String resultStr = "MO模型未初始化";
	public static String getParamStr()
	{
		String result = "MO模型参数：lambda="+lambda+", zeta="+zeta+"\n";
		return result;
	}
	
	public static String getResultStr()
	{
		if(UKSTest == null || YKSTest == null)
			return resultStr;
		String result = "参数：zeta=" + zeta + ", lambda="+lambda+"\n" +  
						"U Diagram K-S Distance:" + UKSTest[0] + "\n" + 
						"Y Diagram K-S Distance:" + YKSTest[0] + "\n";
		result += "基于U图的K-S检验结果：";
		if((UKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"失效数据样本不满足MO模型。\n";
		else
			result+=acceptStr+"失效数据样本满足MO模型。\n";
		
		result += "基于Y图的K-S检验结果：";
		if((YKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"失效数据样本不满足MO模型。\n";
		else
			result+=acceptStr+"失效数据样本满足MO模型。\n";
		return result;
	}
}
