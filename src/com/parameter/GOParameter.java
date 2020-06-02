package com.parameter;

import com.global.GlobalVar;

public class GOParameter extends ModelParameter{
	public static double zeta = 0.001;
	public static String resultStr = "GO模型未初始化";
	public static String getResultStr()
	{
		if(UKSTest == null || YKSTest == null)
			return resultStr;
		String result = "参数：zeta=" + zeta + "\n" +  
						"U Diagram K-S Distance:" + UKSTest[0] + "\n" + 
						"Y Diagram K-S Distance:" + YKSTest[0] + "\n";
		result += "基于U图的K-S检验结果：";
		if((UKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"失效数据样本不满足GO模型。\n";
		else
			result+=acceptStr+"失效数据样本满足GO模型。\n";
		
		result += "基于Y图的K-S检验结果：";
		if((YKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"失效数据样本不满足GO模型。\n";
		else
			result+=acceptStr+"失效数据样本满足GO模型。\n";
		return result;
	}
	
	public static String getParamStr() {
		String result = "GO模型参数：zeta=" + zeta + "\n";
		return result;
	}
	
}
