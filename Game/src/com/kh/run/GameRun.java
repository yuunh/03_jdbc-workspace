package com.kh.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GameRun {

	public static void main(String[] args) {

		Properties prop = new Properties();
		
		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		prop.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe");
		prop.setProperty("userid", "JDBC");
		prop.setProperty("userpwd", "JDBC");
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "");
			prop.storeToXML(new FileOutputStream("resources/query.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
