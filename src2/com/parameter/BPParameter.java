package com.parameter;

import com.type.FaultDataType;

public class BPParameter {
	public static FaultDataType faultDataType = null;
	public static double learnCoefficient = 0.1;	//��Χ[0,1]��Խ��ѧϰԽ��
	public static int refactorDimension = 5;		//3-10,�����ڵ����
	public static int trainGeneration = 10000;		//10-10000;
	public static double deviation = 0;				//���
	public static String resultStr = "BPģ��δ��ʼ��";
	public static String getParamStr()
	{
		String result = "BPģ�Ͳ���\n";
		result+="ѧϰϵ����"+learnCoefficient+"\n";
		result+="�ع�ά����"+refactorDimension+"\n";
		result+="ѵ��������"+trainGeneration+"\n";
		result+="---------------------------\n";
		return result;
	}
	
	public static String getResultStr()
	{
		String result = getParamStr();
		result+="ȫ��ѧϰ��"+deviation+"\n";
		return result;
	}
}
