package com.parameter;

import com.global.GlobalVar;

public class MOParameter extends ModelParameter{
	public static double lambda = 0.1;
	public static double zeta = 0.1;
	public static String resultStr = "MOģ��δ��ʼ��";
	public static String getParamStr()
	{
		String result = "MOģ�Ͳ�����lambda="+lambda+", zeta="+zeta+"\n";
		return result;
	}
	
	public static String getResultStr()
	{
		if(UKSTest == null || YKSTest == null)
			return resultStr;
		String result = "������zeta=" + zeta + ", lambda="+lambda+"\n" +  
						"U Diagram K-S Distance:" + UKSTest[0] + "\n" + 
						"Y Diagram K-S Distance:" + YKSTest[0] + "\n";
		result += "����Uͼ��K-S��������";
		if((UKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"ʧЧ��������������MOģ�͡�\n";
		else
			result+=acceptStr+"ʧЧ������������MOģ�͡�\n";
		
		result += "����Yͼ��K-S��������";
		if((YKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"ʧЧ��������������MOģ�͡�\n";
		else
			result+=acceptStr+"ʧЧ������������MOģ�͡�\n";
		return result;
	}
}
