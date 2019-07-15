package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.NurseModel;
import model.PatientModel;
import model.UserModel;
import view.MainScreenNurse;

public class NurseController {

	BasicController basicController;
	PatientModel patientModel;
	UserModel usermodel;
	NurseModel nurseModel; 

	//Show the Nurse Panel
	public void showDoctorPanel() {

		new MainScreenNurse(usermodel);
	}

	//Parameterized Constructor
	public NurseController(UserModel userModel) {
		this.usermodel=userModel;
		basicController=new BasicController();
		patientModel=new PatientModel();
		nurseModel=new NurseModel();
	}


	//Get the Patients History
	public static DefaultTableModel getPatientsHistory()
			throws SQLException {

		ResultSet rs = new NurseModel().getPatientsHistory();
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

}
