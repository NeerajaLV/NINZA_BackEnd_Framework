		package com.ninza.hrm.api.genericutility;
		
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		
		import com.mysql.jdbc.Driver;
		
		/**
		 * This class contains generic methods to interact with database
		 * @author neera
		 */
		public class DatabaseUtility {
			static Connection con = null;
			ResultSet resultSet = null;
			PropertyFileUtility pfUtil=new PropertyFileUtility();
			/**
			 * This method will establish the connection from database
			 * @param url
			 * @param username
			 * @param password
			 * @throws SQLException
			 */
			public void getConnection(String url, String username, String password) throws SQLException
			{
				try {
				Driver driver=new Driver();
				DriverManager.registerDriver(driver);
				con=DriverManager.getConnection(url, username, password);
				}
				catch(Exception e) {}
			}
			/**
			 * This method will establish the connection from ninza_hrm database
			 * @throws SQLException
			 */
			public void getConnection() throws SQLException
			{
				try {
				Driver driver=new Driver();
				DriverManager.registerDriver(driver);
				con=DriverManager.getConnection(pfUtil.getDataFromPropertiesFile("DBUrl")
						, pfUtil.getDataFromPropertiesFile("DB_Username")
						, pfUtil.getDataFromPropertiesFile("DB_Password"));
				}
				catch(Exception e) {}
			}
			/**
			 * This method used to execute select query and return value to caller
			 * @param query
			 * @return
			 * @throws SQLException
			 */
			public ResultSet executeQuery(String query) throws SQLException
			{
				try {
				Statement state=con.createStatement();
				resultSet=state.executeQuery(query);
				}
				catch(Exception e) {}
				return resultSet;
			}
			/**
			 * This method used to execute non select query and return value to caller
			 * @param query
			 * @return
			 * @throws SQLException
			 */
			public int executeUpdate(String query) throws SQLException
			{
				int result = 0;
				try {
				Statement state=con.createStatement();
				result=state.executeUpdate(query);
				}
				catch(Exception e) {}
				return result;
			}
			/**
			 * This method will close the connection from database
			 * @throws SQLException
			 */
			public void closeConnection() throws SQLException
			{
				try {
				con.close();
				}
				catch(Exception e) {}
			}
			/**
			 * Executes Query Verify And GetData
			 * @param query
			 * @param columnIndex
			 * @param expectedData
			 * @throws Throwable 
			 */
			public boolean executeQueryVerifyAndGetData(String query,int columnIndex, String expectedData) throws Throwable
			{
				boolean flag = false;
				resultSet = con.createStatement().executeQuery(query);
				while (resultSet.next()) {
					if (resultSet.getString(columnIndex).equals(expectedData)) {
						flag = true;
						break;
					}
				}
				if(flag)
				{
		//			System.out.println(expectedData+"Data verified in DB table");
					return flag;
				}
				return flag;
			}
			public String executeQueryAndGetData(String query,int columnIndex) throws Throwable
			{
				resultSet = con.createStatement().executeQuery(query);
				String data = null;
				while (resultSet.next()) {
					data=resultSet.getString(columnIndex);
				}
					return data;
			}
			public int executeInsQuery(String query) throws Throwable
			{
				int result = con.createStatement().executeUpdate(query);
				return result;
			}
		}
