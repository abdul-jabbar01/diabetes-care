package model;

import java.sql.ResultSet;

public class NurseModel {


	private Database dbObject;

	//Default Constructor
	public NurseModel() {
		dbObject=new Database();
	}

	
	//Get patients History
	public ResultSet getPatientsHistory() {
		return dbObject.select("SELECT patient.patientId, user.userFirstName,user.userLastName,user.userEmail FROM patients as patient, device_configurations as device , users as user WHERE device.patientId=patient.patientId AND device.deviceSetupByDoc=1 AND patient.patientUserId=user.userId");
	}
	
	
	
}
