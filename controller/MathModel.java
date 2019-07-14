package controller;

public class MathModel {

	double currentBGL;
	int currentCarbohydrates;
	int maxDose;
	int sensitivityModel;

	//Parameterized Constructor
	public MathModel(int currentBGL,int maxDose,int sensitivityModel) {	
		this.currentBGL=currentBGL;
		this.maxDose=maxDose;
		this.sensitivityModel=sensitivityModel;
	}
		
	//Set the BGL
	public void setBGL(double bgl) {
		this.currentBGL=bgl;
	}
	
	//Get the current BGL
	public double getCurrentBGL() {
		
		return currentBGL;
	}

	//Calculate and Return the Glucose Sensitivity
	public double getGlucoseSensitivity() {
		return sensitivityModel/maxDose;
	}
	
	//Get effective time in minutes for a food based on Glycemic index 
	public int getEffectiveTime(int glycemicIndex) {
		
		if(glycemicIndex < 20)
			return 150;
		
		else if(glycemicIndex > 20 && glycemicIndex < 90)
			return (int) ((1.5*glycemicIndex)+180);
		else
			return 45;
	}
	
	//Return the effect of Carbohydrates on the BGL at specific time(minutes)
	public double getEffectAtTime(int time, int totalEffectiveTime) {
		double effect=0;
		try {
			effect= Math.cos(((( ((double)time)/19)/(((double)totalEffectiveTime)/60)) - 3.14 ) / 2) + 0.5;
			return effect;
		}
		catch(Exception ex) {
			
		}
		
		return effect;
	}
	
	//Get the the Rise in BGL at specific time (minutes)
	public double getNextBGL(int glycemicIndex, int totalCarbohydrates, int time) {
		
		try {
			double effectAtTime=getEffectAtTime(time, getEffectiveTime(glycemicIndex));
			double glucoseSensitivity=getGlucoseSensitivity();
			double value2=((double)(totalCarbohydrates * glycemicIndex));
			double riseInBGL=(value2 /glucoseSensitivity) * ( ((double)effectAtTime)/100) * ( ((double)1)/100);
			
			currentBGL+=((double)currentBGL) * riseInBGL;
		}
		catch(Exception ex) {
			
		}
		return currentBGL;
	}
}
