package model;

import java.sql.ResultSet;

public class PatientModel {

	private int patientId;
	private int patientUserId;
	private String patientDoctorEmail;
	private String patientCareTakerEmail;


	private Database dbObject;

	//Default Constructor
	public PatientModel() {

		this.dbObject=new Database();
	}

	//Set the Patient ID
	public void setPatientId(int patientId) {
			
		this.patientId=patientId;		
	}
		
	//Get the Patient ID
	public int getPatientId() {
		
		return this.patientId;
	}
	
	//Set the User ID of Patient
	public void setPatientUserId(int userId) {
		this.patientUserId=userId;
	}
	
	//Get the Patient User ID
	public int getPatientUserId() {
		return this.patientUserId;
	}

	//Set the doctor email of patient
	public void setPatientDocEmail(String email) {
		this.patientDoctorEmail=email;
	}
	
	//Get the Doctor Email of Patient
	public String getPatientDocEmail() {
		return this.patientDoctorEmail;
	}

	//Set the care taker email of patient
	public void setPatientCareTakerEmail(String email) {
		this.patientCareTakerEmail=email;
	}
	
	//Get the Care Take email of patient
	public String getPatientCareTakerEmail() {
		return this.patientCareTakerEmail;
	}
	
	//Set doctor setup
	public boolean setDocSetup(int setup,int patientId) {
		return this.dbObject.executeQuery("UPDATE `device_configurations` SET `deviceSetupByDoc`='"+setup+"' WHERE patientId="+patientId);
	}
	
	//Add the patient
	public boolean add(int patientUserId,String patientDoctorEmail,String patientCareTakerEmail ) {

		return this.dbObject.executeQuery("INSERT INTO `patients`( `patientUserId`, `patientDoctorEmail`, `patientCareTakerEmail`) VALUES ("+patientUserId+",'"+patientDoctorEmail+"','"+patientCareTakerEmail+"')");
	}

	//Request to Doctor
	public boolean requestDoctor(String doctorEmail,int patientId) {
		
		return this.dbObject.executeQuery("UPDATE `patients` SET `patientDoctorEmail`='"+doctorEmail+"' WHERE patientId="+patientId);

	}
	
	//Get the id of last record of patient
	public ResultSet getLastPatientId() {
		
		return dbObject.select("SELECT `patientId` FROM `patients` ORDER BY patientId DESC LIMIT 1");
	}

	//Get the patient detail by userid
	public ResultSet getPatientDetailsByUserId(int userId) {
		return dbObject.select("SELECT * FROM patients where patientUserId="+userId);
	}
	
	//Get the patient detail by patient id
	public ResultSet getPatientDetailsByPatientId(int patientId) {
		return dbObject.select("SELECT * FROM patients where patientId="+patientId);

	}

	//Get the patient history
	public ResultSet getPatientHistory(int patientId) {
		return dbObject.select("SELECT  `time` as Time,  `bglValue` as BGL FROM `patientHistory` WHERE patientId="+patientId);
	}

}
