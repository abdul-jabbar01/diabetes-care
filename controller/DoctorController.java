package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DoctorModel;
import model.PatientModel;
import model.UserModel;
import view.MainScreenDoctor;

public class DoctorController {

	BasicController basicController;
	PatientModel patientModel;
	UserModel usermodel;
	DoctorModel doctorModel; 

	//Show the Doctor Panel
	public void showDoctorPanel() {

		new MainScreenDoctor(usermodel);
	}

	//Parameterized Constructor
	public DoctorController(UserModel userModel) {
		this.usermodel=userModel;
		basicController=new BasicController();
		patientModel=new PatientModel();
		doctorModel=new DoctorModel();
	}


	//Get the Pending Requests by patients
	public static DefaultTableModel getPendingRequests(String doctorEmail)
			throws SQLException {

		ResultSet rs = new DoctorModel().getPendingRequests(doctorEmail);
		ResultSetMetaData metaData = rs.getMetaData();

		// Names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		columnNames.add("Patient ID");
		columnNames.add("First Name");
		columnNames.add("Last Name");
		columnNames.add("Email");

		// Data of the table
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
	
	//Get the Patients History
	public static DefaultTableModel getPatientsHistory(String doctorEmail)
			throws SQLException {

		ResultSet rs = new DoctorModel().getPatientsHistory(doctorEmail);
		ResultSetMetaData metaData = rs.getMetaData();

		// Names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		columnNames.add("Patient ID");
		columnNames.add("First Name");
		columnNames.add("Last Name");
		columnNames.add("Email");

		// Data of the table
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

	//Configure the device of Patient
	public boolean configureDevice(int patientId, boolean autoMode,int maxDose, int sensitivityModel) {

		JOptionPane.showMessageDialog(null, "Device Configured Successfully");
		return	doctorModel.configureDevice(patientId, autoMode,maxDose,sensitivityModel);
		
	}

}
