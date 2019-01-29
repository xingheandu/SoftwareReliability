package com.parameter;

import com.global.GlobalVar;

public class JMParameter extends ModelParameter{
	public static double ex = 0.1;
	public static double ey = 0.1;
	public static String resultStr = "JMģ��δ��ʼ��";
	
	public static String getParamStr()
	{
		String result = "JMģ�Ͳ�����ex="+ ex + "," + "ey=" + ey + "\n";
		return result;
	}
	
	public static String getResultStr()
	{
		if(UKSTest == null || YKSTest == null)
			return resultStr;
		String result = "������ex="+ ex + "," + "ey=" + ey + "\n" + 
						"U Diagram K-S Distance:" + UKSTest[0] + "\n" + 
						"Y Diagram K-S Distance:" + YKSTest[0] + "\n";
		result += "����Uͼ��K-S��������";
		if((UKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"ʧЧ��������������JMģ�͡�\n";
		else
			result+=acceptStr+"ʧЧ������������JMģ�͡�\n";
		
		result += "����Yͼ��K-S��������";
		if((YKSTest[0]-0.0)<GlobalVar.epsilon)
			result+=refuseStr+"ʧЧ��������������JMģ�͡�\n";
		else
			result+=acceptStr+"ʧЧ������������JMģ�͡�\n";
		return result;
	}
}
