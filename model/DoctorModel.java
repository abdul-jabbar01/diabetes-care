package model;

import java.sql.ResultSet;

public class DoctorModel {


	private Database dbObject;

	//Default Constructor
	public DoctorModel() {
		dbObject=new Database();
	}

	//Configure Device
	public boolean configureDevice(int patientId, boolean autoMode,int maxDose, int sensitivityModel) {

		return dbObject.executeQuery("UPDATE `device_configurations` SET  `autoMode`="+autoMode+"  , maxDose="+maxDose+", sensitivityModel="+sensitivityModel+", `deviceSetupByDoc`=1 WHERE patientId="+patientId);
	}

	//Get Pending Requests by patients
	public ResultSet getPendingRequests(String doctorEmail) {
		return dbObject.select("SELECT patient.patientId, user.userFirstName,user.userLastName,user.userEmail FROM patients as patient, device_configurations as device , users as user WHERE device.patientId=patient.patientId AND patient.patientDoctorEmail='"+doctorEmail+"' and device.deviceSetupByDoc=0 AND patient.patientUserId=user.userId");
	}
	
	//Get patients History
	public ResultSet getPatientsHistory(String doctorEmail) {
		return dbObject.select("SELECT patient.patientId, user.userFirstName,user.userLastName,user.userEmail FROM patients as patient, device_configurations as device , users as user WHERE device.patientId=patient.patientId AND patient.patientDoctorEmail='"+doctorEmail+"' and device.deviceSetupByDoc=1 AND patient.patientUserId=user.userId");
	}
	
	//Get All Doctors
	public ResultSet getAllDoctors() {
		return dbObject.select("select * from users where userType='Doctor'");
	}
	
	
}
