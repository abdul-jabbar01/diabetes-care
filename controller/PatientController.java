package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.PatientModel;
import model.UserModel;

public class PatientController {

	private DeviceController deviceController;
	BasicController basicController;
	PatientModel patientModel;

	//Default Constructor
	public PatientController() {

		deviceController=new DeviceController();
		basicController=new BasicController();
		patientModel=new PatientModel();
	}

	//Request Doctor to setup the device
	public boolean requestDoctor(UserModel userDetails,String doctorEmail) {

		if(doctorEmail.equals("") ) 
			JOptionPane.showMessageDialog(null, "Please enter the email address of doctor");

		else if(!basicController.isValidEmailAddress(doctorEmail)) 
			JOptionPane.showMessageDialog(null, "Email is invalid. Please enter valid format of email");

		else {

			if(patientModel.requestDoctor(doctorEmail, userDetails.patientDetails.getPatientId()))
			{
				JOptionPane.showMessageDialog(null, "Request sent to doctor successfully");
				return true;
			}

		}

		return false;
	}
	
	//Add the Patient
	public boolean addPatient(int patientUserId,String patientDoctorEmail,String patientCareTakerEmail ) {
		this.patientModel.add(patientUserId, patientDoctorEmail, patientCareTakerEmail);
		this.deviceController.add(this.getLastInsertedPatientId());
		return true;
	}

	//Get the ID of last Inserted Record of Patient
	public int getLastInsertedPatientId() {

		ResultSet result=patientModel.getLastPatientId();
		try {
			while(result.next()) {
				return result.getInt("patientId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	//Returns the Patient History 
	public static DefaultTableModel getPatientHistory(int patientId)
			throws SQLException {

		ResultSet rs = new PatientModel().getPatientHistory(patientId);
		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		
		columnNames.add("Time");
		columnNames.add("BGL Value");

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data,columnNames){
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}};

	}
	
	//Get the Patient Model based on User ID
	public PatientModel getPatientDetailByUserId(int userId) {

		ResultSet result=patientModel.getPatientDetailsByUserId(userId);
		PatientModel patientDetails=new PatientModel();

		try {
			while (result.next()) {

				patientDetails.setPatientId(result.getInt("patientId"));
				patientDetails.setPatientUserId(result.getInt("patientUserId"));
				patientDetails.setPatientDocEmail(result.getString("patientDoctorEmail"));
				patientDetails.setPatientCareTakerEmail(result.getString("patientCareTakerEmail"));

				return patientDetails;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
