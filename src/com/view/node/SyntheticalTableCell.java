package com.view.node;

public class SyntheticalTableCell {
	String modelName;
	double ksDistance;
	double mtbf;
	double lambda;
	
	public SyntheticalTableCell(String modelName, double ksDistance, double mtbf, double lambda)
	{
		this.modelName = modelName;
		this.ksDistance = ksDistance;
		this.mtbf = mtbf;
		this.lambda = lambda;
	}
}
