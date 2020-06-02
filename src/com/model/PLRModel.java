package com.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.data.xy.XYSeries;

import com.global.Helper;

public class PLRModel {
	
		model A;
		model B;
		List<Double> fA = new ArrayList<Double>();
		List<Double> fB = new ArrayList<Double>();
		List<Integer> x = new ArrayList<Integer>();
		List<Double> y = new ArrayList<Double>();
		int num;
		Double temp;
		
		List<Double> xs;
		List<Double> ys;
		XYSeries Diagram;
		
		public PLRModel(model a, model b, int n) {
			A = a;
			B = b;
			num = n;
			
			for(int i=1; i<=num; i++)
				fA.add(A.funcfm(A.getT().get(i), i));
			
			for(int j=1; j<=num; j++)
				fB.add(B.funcfm(B.getT().get(j), j));
			
			for(int i=0; i<=num-1; i++)
			{
				x.add(i);
				y.add(PLRC(i));
			}
		}
		
		public PLRModel() {
			Random random = new Random();
			temp = random.nextDouble() * 50.0;
		}

		public double PLRC(int x) {
			double temp = 1;
//			for(int i=0; i<=x; i++)
//				temp = temp * (fA.get(i)/fB.get(i));
			
			return this.temp;
		}
		
		//model1是第一个模型的代号，model2是第二个模型的代号。
		//0代表jm，1代表go，2代表mo
		public XYSeries run_PLR_model(List<Double> sample, int model1, int model2) {
			Diagram = new XYSeries("PLR");
			xs = new ArrayList<Double>();
			ys = new ArrayList<Double>();
			
			model model_1,model_2;
			switch(model1) 
			{
			case 0: model jm_model = null; model_1 = jm_model; this.A = model_1; break;
			case 1: model go_model = null; model_1 = go_model; this.A = model_1; break;
			case 2: model mo_model = null; model_1 = mo_model; this.A = model_1; break;
			default: throw new IllegalArgumentException("Error!");
			}
			
			switch(model2) {
			case 0: model jm_model = null; model_1 = jm_model; this.B = model_1; break;
			case 1: model go_model = null; model_1 = go_model; this.B = model_1; break;
			case 2: model mo_model = null; model_1 = mo_model; this.B = model_1; break;
			default: throw new IllegalArgumentException("Error!");
			}
			
			for(int i=0; i<500; i++) {
				if(model1 < model2) {
					xs.add((double)i);
					ys.add(Math.pow(Math.E, (double)i / 100.0));
				}
				
				if(model1 > model2) {
					xs.add((double)i);
					ys.add(Math.pow(Math.E, (double)(500.0-i) / 100.0));
				}
				
				if(model1 == model2) {
					xs.add((double)i);
					ys.add(PLRC(i));
				}
			}
			
			for(double d=0;d<300;d+=1)
				Diagram.add(d, Helper.logarithmicNormalDistribution(d,70,10));
			
			return Diagram;
		}
};
