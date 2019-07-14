package model;

import java.sql.ResultSet;

public class UserModel {

	private int userId;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userPassword;
	private String userType;
	private Database dbObject;
	
	public PatientModel patientDetails;
	
	//Default Constructor
	public UserModel() {
		
		this.dbObject=new Database();
		patientDetails=null;
	}
	
	//Set user id
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	//Get user id
	public int getUserId() {
		return userId;
	}
	
	//Set the user first name
	public void setUserFirstName(String userFirstName) {
		this.userFirstName=userFirstName;
	}
	
	//Get the user first name
	public String getUserFirstName() {
		return userFirstName;
	}

	//Set the user last name
	public void setUserLastName(String userLastName) {
		this.userLastName=userLastName;
	}
	
	//Get the user last name
	public String getUserLastName() {
		
		return userLastName;
	}
	
	//Set the user email
	public void setUserEmail(String userEmail) {
		this.userEmail=userEmail;
	}
	
	//Get the user email
	public String getUserEmail() {
		return userEmail;
	}
	
	//Set the user type
	public void setUserType(String userType) {
		this.userType=userType;
	}
	
	//Get the user type
	public String getUserType() {
		return userType;
	}
	
	//Get user password
	public String getUserPassword(){
		return userPassword;
	}
	
	//Get the id of last inserted record of user
	public ResultSet getLastInsertedId() {
		
		return dbObject.select("SELECT `userId` FROM `users` ORDER BY userId DESC LIMIT 1");
	}
	
	//Validate the user credentials
	public ResultSet validateUser(String userEmail, String userPassword,String userType) {
		
		return dbObject.select("SELECT * FROM users where userEmail='"+userEmail+"' and userPassword=SHA1('"+userPassword+"') and userType='"+userType+"'");
	}
	
	//Get the user by email
	public ResultSet getUserByEmail(String userEmail) {
		
		return dbObject.select("SELECT * FROM users where userEmail='"+userEmail+"'");
	
	}
	
	//Add the user
	public boolean add(String userFirstName, String userLastName,String userEmail,String userPassword, String userType) {
		
		return this.dbObject.executeQuery("INSERT INTO `users`( `userFirstName`, `userLastName`, `userEmail`, `userPassword`, `userType`) VALUES ('"+userFirstName+"','"+userLastName+"','"+userEmail+"',SHA1('"+userPassword+"'),'"+userType+"')");
	}
	
	
	
	
}
