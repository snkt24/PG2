package com.airpacs.pressuregauge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DBConnection {
	static Connection connection;
	static Statement statement; 
		
	public static void dbInit() {
		try {
			String user = System.getProperty("user.name");
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\" + user + "\\" + Constant.FOLDER_NAME + "\\" + Constant.DB_NAME);
			
			if (connection == null)
				System.out.println("SQL Connection is NULL. Not going to create Table.");
			else {
				statement = connection.createStatement();
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+Constant.TABLE_NAME+" (instrumentID TEXT, value TEXT, time TEXT)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Data> getAllData() {
		List<Data> dataList = new ArrayList<>();
				try {
					String sql = "SELECT instrumentID, value, time FROM " + Constant.TABLE_NAME;
					if (connection == null || connection.isClosed()) {
						System.out.println("SQL Connection is NULL or Closed. Not going to create Table.");
						String user = System.getProperty("user.name");
						connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\" + user + "\\" + Constant.FOLDER_NAME + "\\" + Constant.DB_NAME);
					}
						statement = connection.createStatement();
						statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + Constant.TABLE_NAME
								+ " (instrumentID TEXT, value TEXT, time TEXT)");

						ResultSet rs = statement.executeQuery(sql);
						if (!rs.isBeforeFirst()) {
							System.out.println("No data");
						} else
							while (rs.next()) {
								Data data = new Data();
								Long millisecond = Long.parseLong(rs.getString("time"));
								Date date = new Date(millisecond);
								String datetime = Constant.dateFormat.format(date);
								data.setInstrumentID(rs.getString("instrumentID"));
								data.setValue(rs.getString("value"));
								data.setTime(datetime);
								dataList.add(data);
							}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return dataList;
	}
}
