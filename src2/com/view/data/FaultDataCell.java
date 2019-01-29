package com.view.data;

public class FaultDataCell {
	public int order = -1;
	public double gapTime = 0.0;
	public int state = 0 ;
	
	public FaultDataCell(int order, double gapTime, int state)
	{
		this.order = order;
		this.gapTime = gapTime;
		this.state = state;
	}
}
