package model;

import java.sql.ResultSet;

public class DeviceModel {

	private Database dbObject;
	private int deviceId;
	private int remGlucagonLevel;
	private int remInsulinLevel;
	private int remBatteryLevel;
	private boolean state;
	private boolean autoMode;
	private boolean deviceSetupByDoc;
	private int patientId;
	private int maxDose;
	private int sensitivityModel;
	
	private String breakFast;
	private int breakFastCarb;
	private String lunch;
	private int lunchCarb;
	private String dinner;
	private int dinnerCarb;
	
	//Default Constructor
	public DeviceModel() {

		this.dbObject=new Database();
	}
	
	//Set the name of food taken in breakfast
	public void setBreakFast(String breakFast) {
		this.breakFast=breakFast;
	}
			
	//Get the name of food taken in breakfast
	public String getBreakFast() {
		return this.breakFast;
	}
	
	//Set the total carbohydrates taken in breakfast
	public void setBreakFastCarb(int carb) {
		this.breakFastCarb=carb;
	}
	
	//Get the carbohydrates consumed in breakfast
	public int getBreakFastCarb() {
		return this.breakFastCarb;
	}
	
	//Set the name of food taken in lunch
	public void setLunch(String lunch) {
		this.lunch=lunch;
	}
	
	//Get the name of food taken in lunch
	public String getLunch() {
		return this.lunch;
	}

	//Set the total carbohydrates taken in lunch
	public void setLunchCarb(int carb) {
		this.lunchCarb=carb;
	}
	
	//Get the carbohydrates consumed in lunch
	public int getLunchCarb() {
		return this.lunchCarb;
	}
	
	//Set the name of food taken in dinner
	public void setDinner(String dinner) {
		this.dinner=dinner;
	}
	
	//Get the name of food taken in dinner
	public String getDinner() {
		return this.dinner;
	}
	
	//Set the total carbohydrates taken in dinner
	public void setDinnerCarb(int carb) {
		this.dinnerCarb=carb;
	}
	
	//Get the carbohydrates consumed in Dinner
	public int getDinnerCarb() {
		return this.dinnerCarb;
	}	
	
	//Set the sensitivity model
	public void setSensitivityModel(int sensitivityModel) {
		this.sensitivityModel=sensitivityModel;
	}
	
	//Get the sensitivity Model
	public int getSensitivityModel() {
		return this.sensitivityModel;
	}
	
	//Set the Max Dose
	public void setMaxDose(int maxDose) {
		this.maxDose=maxDose;
	}
	
	//Get the max Dose
	public int getMaxDose() {
		return this.maxDose;
	}
	
	//Set the device id
	public void setDeviceId(int deviceId) {
		this.deviceId=deviceId;
	}
	
	//Get the Device ID
	public int getDeviceId() {

		return deviceId;
	}

	//Set the remaining battery level
	public void setRemBatteryLevel(int remBatteryLevel) {
		
		this.remBatteryLevel=remBatteryLevel;
	}	
	
	//Get the remaining Battery Level
	public int getRemBatteryLevel() {

		return remBatteryLevel;
	}

	//Set the device state
	public void setState(boolean state) {
		this.state=state;
	}	
	
	//Get the state of device
	public boolean getState() {

		return state;
	}
	
	//Set the automode of device
	public void setAutoMode(boolean autoMode) {
		this.autoMode=autoMode;
	}
	
	//returns true if device is in auto mode
	public boolean getAutoMode() {
		
		return autoMode;
	}
	
	
	//Returns the patient id
	public int getPatientId() {
		
		return patientId;
	}
	
	//Set the remaining Glucagon Level
	public void setRemGlucagonLevel(int remGlucagonLevel) {
		
		this.remGlucagonLevel=remGlucagonLevel;
	}
	
	//Get the Remaining Glucagon Level
	public int getRemGlucagonLevel() {
		return remGlucagonLevel;
	}

	//Set the remaining Insulin Level
	public void setRemInsulinLevel(int remInsulinLevel) {
		this.remInsulinLevel=remInsulinLevel;
	}
		
	//Get the remaining Insulin Level
	public int getRemInsulinLevel() {

		return remInsulinLevel;
	}
	
	//Set the device setup by doctor
	public void setDeviceSetupByDoc(boolean deviceSetupByDoc) {
		this.deviceSetupByDoc=deviceSetupByDoc;
	}
	
	//returns true if device is setup by doctor
	public boolean getDeviceSetupByDoc() {
		
		return deviceSetupByDoc;
	}
	
	//Set the User Id
	public void setUserId(int userId) {
		this.patientId=userId;
	}

	//Set the Food Details of patient
	public boolean setFood(int patientId, String breakFast, int breakFastCarb, String lunch, int lunchCarb, String dinner, int dinnerCarb) {
		return this.dbObject.executeQuery("UPDATE `device_configurations` SET  `breakFast`='"+breakFast+"',`breakFastCarb`="+breakFastCarb+",`Lunch`='"+lunch+"',`LunchCarb`="+lunchCarb+",`Dinner`='"+dinner+"',`DinnerCarb`="+dinnerCarb+"  where patientId="+patientId);
	}
	
	//Refill Insulin
	public boolean refillInsulin(int patientId) {
		return this.dbObject.executeQuery("UPDATE `device_configurations` SET  `remInsulinLevel`=100 where  patientId="+patientId);

	}
	
	//Refill Glucagon
	public boolean refillGlucagon(int patientId) {
		return this.dbObject.executeQuery("UPDATE `device_configurations` SET  `remGlucagonLevel`=100  where patientId="+patientId);

	}

	//Get the device information of specific patient
	public ResultSet getDeviceByPatientId(int patientId) {

		return dbObject.select("SELECT * FROM device_configurations as device inner join patients as patient on patient.patientId=device.patientId where patient.patientId="+patientId);

	}
	
	//Add the Device
	public boolean add(int patientId) {
		return this.dbObject.executeQuery("INSERT INTO `device_configurations`(`remGlucagonLevel`, `remInsulinLevel`, `remBatteryLevel`, `state`, `autoMode`, `deviceSetupByDoc`, `patientId`, `breakFast`, `breakFastCarb`, `Lunch`, `LunchCarb`, `Dinner`, `DinnerCarb`) VALUES (100,100,100,1,1,0,"+patientId+",'',0,'',0,'',0)");

	}
}
