package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.DeviceModel;

public class DeviceController {
	
	BasicController basicController;
	DeviceModel deviceModel;
	
	//Default Constructor
	public DeviceController() {
		
		basicController=new BasicController();
		deviceModel=new DeviceModel();
	}
	
	//Returns true if the device is setup for specific patient
	public boolean deviceExistForSpecificPatient(int userId) {
	
		ResultSet result=deviceModel.getDeviceByPatientId(userId);
		try {
			//If record exists
			while (result.next()) {
				//then return true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//else return false
		return false;
	}
	
	//Refill Insulin Catridge of Device
	public void refillInsulin(int patientId) {
		deviceModel.refillInsulin(patientId);
	}
	
	//Refill Glucagon Catridge of Device
	public void refillGlucagon(int patientId) {
		deviceModel.refillGlucagon(patientId);
	}
	
	//Returns true if manual injection is allowed for a patient
	public boolean checkManualInjectAllowed(int patientId) {
		
		return !this.getDeviceInformationByPatientId(patientId).getAutoMode();
	}
	
	//Returns the device information of specific patient
	public DeviceModel getDeviceInformationByPatientId(int patientId) {
		
		ResultSet result=deviceModel.getDeviceByPatientId(patientId);
		DeviceModel deviceInfo=new DeviceModel();
		try {
			while (result.next()) {
				 deviceInfo.setDeviceId(result.getInt("deviceId"));
				 deviceInfo.setRemGlucagonLevel(result.getInt("remGlucagonLevel"));
				 deviceInfo.setRemInsulinLevel(result.getInt("remInsulinLevel"));
				 deviceInfo.setRemBatteryLevel(result.getInt("remBatteryLevel"));
				 deviceInfo.setState(result.getBoolean("state"));
				 deviceInfo.setAutoMode(result.getBoolean("autoMode"));
				 deviceInfo.setDeviceSetupByDoc(result.getBoolean("deviceSetupByDoc"));
				 deviceInfo.setUserId(result.getInt("patientId"));
				 deviceInfo.setDeviceId(patientId);
				 deviceInfo.setMaxDose(result.getInt("maxDose"));
				 deviceInfo.setSensitivityModel(result.getInt("sensitivityModel"));	 
				 deviceInfo.setBreakFast(result.getString("breakFast"));
				 deviceInfo.setBreakFastCarb(result.getInt("breakFastCarb"));
				 deviceInfo.setLunch(result.getString("Lunch"));
				 deviceInfo.setLunchCarb(result.getInt("LunchCarb"));
				 deviceInfo.setDinner(result.getString("Dinner"));
				 deviceInfo.setDinnerCarb(result.getInt("DinnerCarb"));
				 
				 return deviceInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//Set the Food Routine of specific Patient
	public boolean setFood(int patientId, String breakFast, int breakFastCarb, String lunch, int lunchCarb, String dinner, int dinnerCarb) {

		JOptionPane.showMessageDialog(null, "Device Configured Successfully");
		return this.deviceModel.setFood(patientId, breakFast, breakFastCarb, lunch, lunchCarb, dinner, dinnerCarb);
		
	}
	
	//Returns true if device is setup by doctor
	public boolean checkDeviceSetup(int patientId) {
		
		ResultSet result=deviceModel.getDeviceByPatientId(patientId);
		try {
			//If record exists
			while (result.next()) {
				if(result.getBoolean("deviceSetupByDoc"))
					return true;
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	//Add the Device
	public boolean add(int userId) {
		return this.deviceModel.add(userId);

	}

}
