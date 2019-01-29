package com.parameter;

import com.global.GlobalVar;

public class GOParameter extends ModelParameter{
	public static double zeta = 0.001;
	public static String resultStr = "GOģ��δ��ʼ��";
	public static String getResultStr()
	{
		if(UKSTest == null || YKSTest == null)
			return resultStr;
		String result = "������zeta=" + zeta + "\n" +  
						"U Diagram K-S Distance:" + UKSTest[0] + "\n" + 
						"Y Diagram K-S Distance:" + YKSTest[0] + "\n";
		result += "����Uͼ��K-S��������";
		if((UKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"ʧЧ��������������GOģ�͡�\n";
		else
			result+=acceptStr+"ʧЧ������������GOģ�͡�\n";
		
		result += "����Yͼ��K-S��������";
		if((YKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"ʧЧ��������������GOģ�͡�\n";
		else
			result+=acceptStr+"ʧЧ������������GOģ�͡�\n";
		return result;
	}
	
	public static String getParamStr() {
		String result = "GOģ�Ͳ�����zeta=" + zeta + "\n";
		return result;
	}
	
}
