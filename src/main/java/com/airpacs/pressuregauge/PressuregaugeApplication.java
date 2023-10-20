package com.airpacs.pressuregauge;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.fazecast.jSerialComm.SerialPort;

@SpringBootApplication(scanBasePackages = "com.airpacs.pressuregauge")
public class PressuregaugeApplication extends SpringBootServletInitializer{

	static Connection connection;
	static Statement statement;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return super.configure(builder);
	}

	public static void main(String[] args) {
		
		startUp();
		DBConnection.dbInit();
		
//		ComPortConnection.setupEventsForComPortIfAvailable();
		
//		printExistingData();
		
		SpringApplication.run(PressuregaugeApplication.class, args);
	}
	
	
	public static void startUp() {
		try {

			// Determine OS & Version
//			System.out.println(System.getProperty("os.name"));
//			System.out.println(System.getProperty("sun.arch.data.model"));

			String user = System.getProperty("user.name");
			System.out.println(user);

			// Create Folder of application if not exist.
			Files.createDirectories(Paths.get("C:\\Users\\" + user + "\\"+Constant.FOLDER_NAME));

			// Create DB File if it does not exist.
			File myObj = new File("C:\\Users\\" + user + "\\"+Constant.FOLDER_NAME+"\\"+Constant.DB_NAME);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printExistingData() {
		//Print Pre existing Data
				String sql = "SELECT instrumentID, value, time FROM "+Constant.TABLE_NAME;
				try {
					ResultSet rs    = statement.executeQuery(sql);
					if(!rs.isBeforeFirst()) {
						System.out.println("No data");
					}else
						while (rs.next()) {
							
							Long millisecond = Long.parseLong(rs.getString("time"));
							Date date = new Date(millisecond);
							String datetime = Constant.dateFormat.format(date);
			                System.out.println(rs.getString("instrumentID") +  "\t" + 
			                                   rs.getString("value") + "\t" +
			                                   datetime + "\t");
			            }
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

}
