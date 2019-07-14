package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import view.LiveGraph;

public class Simulation {

	MathModel model;

	//List to store the BGL values at each interval
	public ArrayList<Double> injectedBGL=new ArrayList<Double>();
	public ArrayList<Double> BGLValues=new ArrayList<Double>();
	public ArrayList<String> timeList=new ArrayList<String>();

	//Breakfast
	int breakfastTotalCarbohydrates;
	int breakfastGlycemicIndex;

	//Lunch
	int lunchTotalCarbohydrates;
	int lunchGlycemicIndex;

	//Dinner
	int dinnerTotalCarbohydrates;
	int dinnerGlycemicIndex;

	//Food Effect
	boolean foodEffect;
	int totalFoodEffectTime;
	int currentFoodEffectTime;
	int totalCarbohydrates;
	int glycemicIndex;
		
	//Simulation Values
	double currentBGL;
	int startingBGL;
	int maxDose;
	int sensitivityModel;
	int correctionFactor;
	String startTime;
	int timeIntervalMinutes;
	int totalDays;
	int simulationDelay;
	final int insulinEffectCarbohydrates=15;
	String insulinMaxEffectTime;
	final int injectionMaxTime=90;
	int injectionCurrentEffectTime=0;
	double totalInsulinInjected;
	double totalInsulinCalculated;
	double riseBGL=0;
	double BGLAfterInjection;
	
	//Parameterized Constructor
	public Simulation(int startingBGL) {
		timeIntervalMinutes=10;
		startTime="7:40";
		foodEffect=false;
		totalFoodEffectTime=0;
		totalCarbohydrates=0;
		glycemicIndex=0;
		this.startingBGL=startingBGL;
		sensitivityModel=1500;
		correctionFactor=20;
		model=new MathModel(startingBGL, correctionFactor, sensitivityModel);
		maxDose=50;
		totalDays=1;
	}
	
	//Function to simulate the whole process
	public void simulate() {
		
		for(int j=0;j<totalDays;j++)	
			for(int i=0;i<143;i++) {

				currentBGL=getNextBGL(startTime);

				startTime=getNextTime(startTime);
//				System.out.println("Time: "+startTime);
//				System.out.println("Current effective time: "+currentFoodEffectTime);
//				System.out.println("Total effective time: "+totalFoodEffectTime);
//				System.out.println("CurrentBGL: "+currentBGL);

				if(currentBGL > 120 && totalInsulinCalculated!=0 && totalInsulinInjected<=totalInsulinCalculated) {// injectionCurrentEffectTime <= injectionMaxTime) {
					inject();
				}

				BGLValues.add(model.getCurrentBGL());
				timeList.add(startTime);
				injectedBGL.add(this.BGLAfterInjection);

				if(currentBGL > 120 && totalInsulinCalculated!=0 && totalInsulinInjected<=totalInsulinCalculated) {// injectionCurrentEffectTime <= injectionMaxTime) {

					model.setBGL(BGLAfterInjection);
				}
				System.out.println("------------------------------------");
			}
		
//		SwingUtilities.invokeLater(() -> {
//	      Graph example = new Graph("Blood Sugar Level Chart ",this);
//	    });
		
		final LiveGraph demo = new LiveGraph("Blood Sugar Level over time",this);
		demo.pack();
        demo.setVisible(true);

	}

	//Function to set the total number of days for simulation
	public void setTotalDays(int totalDays) {
		this.totalDays=totalDays;
	}
	
	//Set the delay in between each interval for simulation 
	public void setSimulationDelay(int simulationDelay) {
		this.simulationDelay=simulationDelay;
	}
	
	//Get the delay
	public int getSimulationDelay() {
		return this.simulationDelay;
	}

	//Function to set the Breakfast values
	public void setBreakfast( int breakfastTotalCarbohydrates, int breakfastGlycemicIndex) {
		this.breakfastTotalCarbohydrates=breakfastTotalCarbohydrates;
		this.breakfastGlycemicIndex=breakfastGlycemicIndex;
	}

	//Function to set the Lunch values
	public void setLunch( int lunchTotalCarbohydrates, int lunchGlycemicIndex) {
		this.lunchTotalCarbohydrates=lunchTotalCarbohydrates;
		this.lunchGlycemicIndex=lunchGlycemicIndex;
	}

	//Function to set the dinner values
	public void setDinner(int dinnerTotalCarbohydrates, int dinnerGlycemicIndex) {
		this.dinnerTotalCarbohydrates=dinnerTotalCarbohydrates;
		this.dinnerGlycemicIndex=dinnerGlycemicIndex;
	}

	//Function to set the Sensitivity Model
	public void setSensitivityModel(int sensitivityModel) {
		this.sensitivityModel=sensitivityModel;
	}
	
	//Function to set the max Dose
	public void setMaxDose(int maxDose) {
		this.maxDose=maxDose;
	}
	
	//Return the next time when dose BGL should be checked
	public String getNextTime(String currentTime) {

		String newTime =currentTime;
		try {
			String myTime = currentTime;
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			java.util.Date d= df.parse(myTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MINUTE, timeIntervalMinutes);
			newTime = df.format(cal.getTime());
			return newTime;
		} catch (ParseException e) {
			e.printStackTrace();
		} 

		return newTime;
	}

	//Calculate and return the dose that should be injected per interval
	public double getDosePerInterval(double totalDose) {

		return totalDose / (injectionMaxTime/timeIntervalMinutes);
	}

	
	//Function to inject the Dose
	public void inject() {

		totalInsulinInjected+=getDosePerInterval(totalInsulinCalculated);
		this.BGLAfterInjection=currentBGL - getDosePerInterval(totalInsulinCalculated)*50;
//		System.out.println("\nDose injected");
//		System.out.println("Dose For this interval: "+getDosePerInterval(totalInsulinCalculated));
//		System.out.println("BGL afer Injection: "+BGLAfterInjection);
//		System.out.println("Current effective time (Injection): "+injectionCurrentEffectTime);
//		System.out.println("Total Insulin Injected: "+totalInsulinInjected);
//		System.out.println("Total Insulin Calculated: "+totalInsulinCalculated);
		injectionCurrentEffectTime+=timeIntervalMinutes;


	}

	//Function to get the Next BGL after specific time interval
	public double getNextBGL(String time) {

		//Check if current time is to take the food
		checkFoodTime(time);
		
		double currentBGL=0;
		
		if(foodEffect) {
			
			//If there is still food effect remaining
			if(currentFoodEffectTime+timeIntervalMinutes < totalFoodEffectTime) {
				//Run the algorithm for each of the minute in this time interval
				for(int i=0;i < timeIntervalMinutes;i++) {
					currentBGL=model.getNextBGL(glycemicIndex, totalCarbohydrates, currentFoodEffectTime);
					currentFoodEffectTime++;
				}	
			}
			else
			{
				foodEffect=false;
				glycemicIndex=0;
				totalCarbohydrates=0;
				currentFoodEffectTime=0;
				currentBGL=model.getNextBGL(0, 0, 0);
			}
		}
		else {
			currentBGL=model.getNextBGL(0, 0, 0);
		}

		return currentBGL;
	}
	
	//Function to calculate the dose based on carbohydrates
	public void calculateDose(int totalCarbohydrates) {

		totalInsulinCalculated=0;
		totalInsulinInjected=0;

		double insulinUnits=totalCarbohydrates/insulinEffectCarbohydrates;
		double correctionFactor=(currentBGL-120)/maxDose;
		totalInsulinCalculated=insulinUnits;
		totalInsulinCalculated=insulinUnits+correctionFactor;

	}

	//Function to check if current time is to take the food
	public void checkFoodTime(String time) {

		//Time for Breakfast
		if(time.equals("08:00")) {

			if(breakfastGlycemicIndex!=0 && breakfastTotalCarbohydrates!=0) 
			{
				foodEffect=true;
				totalCarbohydrates=breakfastTotalCarbohydrates;
				glycemicIndex=breakfastGlycemicIndex;
				totalFoodEffectTime= model.getEffectiveTime(glycemicIndex);
				calculateDose(totalCarbohydrates);
			}
		}
		//Time for Lunch
		else if(time.equals("13:00")) {

			if(lunchGlycemicIndex!=0 && lunchTotalCarbohydrates!=0) {
				foodEffect=true;
				totalFoodEffectTime= model.getEffectiveTime(lunchGlycemicIndex);
				glycemicIndex=lunchGlycemicIndex;
				totalCarbohydrates=lunchTotalCarbohydrates;
				calculateDose(lunchTotalCarbohydrates);
			}
		}
		//Time for Dinner
		else if(time.equals("18:00")) {

			if(dinnerGlycemicIndex!=0 && dinnerTotalCarbohydrates!=0) {
				foodEffect=true;
				totalFoodEffectTime= model.getEffectiveTime(dinnerGlycemicIndex);
				glycemicIndex=dinnerGlycemicIndex;
				totalCarbohydrates=dinnerTotalCarbohydrates;
				calculateDose(dinnerTotalCarbohydrates);
			}
		}
	}
}
