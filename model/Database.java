package model;

import java.sql.*;

public class Database
{
	Connection conn;
	String databaseName;
	String username;
	String userPassword;
	String serverName;
	int port;
	String url;

	//Default Constructor
	public Database()
	{
		username="root";
		userPassword="root";
		serverName="localhost";
		databaseName="d-care";
		port=8889;
		this.url = "jdbc:mysql://"+serverName+":"+port+"/"+databaseName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}

	//Open connection to database
	public void openConnection() {
		
		try {
			conn = DriverManager.getConnection(this.url, this.username, this.userPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Close the connection to database
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Execute the Query
	public boolean executeQuery(String query) {
		
		try
		{
			this.openConnection();
			Statement st = this.conn.createStatement();
			st.execute(query);
			this.closeConnection();
			return true;
		}
		catch (SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		return false;
	}
	
	//Execute select query and return the result
	public ResultSet select(String query) {
		
		try
		{
			this.openConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		}
		catch (SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		
		return null;
	}


}