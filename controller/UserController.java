package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.UserModel;

public class UserController {

	BasicController basicController;
	UserModel userModel;
	
	//Default Constructor
	public UserController() {
		
		basicController=new BasicController();
		userModel=new UserModel();
	}
	
	//Get the id of last record of the user
	public int getLastInsertedUserId() {
		
		ResultSet result=userModel.getLastInsertedId();
		try {
			while(result.next()) {
				return result.getInt("userId");
			}
		} catch (SQLException e) {
 			e.printStackTrace();
		}
		
		return -1;
	}
	
	//Returns true if the email address is already in use
	public boolean checkEmailAlreadyExist(String userEmail) {
		
		try {
			if(userModel.getUserByEmail(userEmail).next()==false)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	//returns the UserModel after validating if the user exist or not
	public UserModel validateUser(String userEmail, String userPassword,String userType) {
		
		ResultSet result=userModel.validateUser(userEmail, userPassword,userType);
		UserModel userInfo=new UserModel();
		try {
			System.out.println(result.toString());
			while(result.next()) {
				userInfo.setUserId(result.getInt("userId"));
				userInfo.setUserFirstName( result.getString("userFirstName"));
				userInfo.setUserLastName(result.getString("userLastName") );
				userInfo.setUserEmail( result.getString("userEmail"));
				userInfo.setUserType(result.getString("userType"));	 
				
				if(userInfo.getUserType().equals("Patient")) {
					
					userInfo.patientDetails=new PatientController().getPatientDetailByUserId(userInfo.getUserId());
				}
				return userInfo;
			}
		} catch (SQLException e) {
 			e.printStackTrace();
		}
		return null;
	}
	
	//Add the user into database after validating the values
	public boolean addUser( String userFirstName, String userLastName,String userEmail,String userPassword, String userType) {
		
		if(userFirstName.equals("")  || userLastName.equals("") || userEmail.equals("") || userPassword.equals("") || userType.equals("")) {
			
			JOptionPane.showMessageDialog(null, "Please fill all the required fields");
		}
		else if(!basicController.isValidEmailAddress(userEmail)) {
			JOptionPane.showMessageDialog(null, "Email is invalid. Please enter valid format of email");

		}
		else if(this.checkEmailAlreadyExist(userEmail)) {
			JOptionPane.showMessageDialog(null, "Email address is already in user. Please user another one");

		}
		else {
			userModel.add( userFirstName, userLastName, userEmail, userPassword, userType);
			JOptionPane.showMessageDialog(null, "User Added Successfully. Click OK to login");
			
			if(userType=="Patient") {
				PatientController patientController=new PatientController();
				patientController.addPatient(this.getLastInsertedUserId(), "", "");
			}
			
			return true;
			
		}
		return false;
	}

}
