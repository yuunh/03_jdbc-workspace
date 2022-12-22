package com.kh.netflix.run;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Properies {

	public static void main(String[] args) {

		Properties prop = new Properties();

		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		prop.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe");
		prop.setProperty("username", "JDBC");
		prop.setProperty("password", "JDBC");

		try {
			prop.store(new FileOutputStream("resources/test.properties"), "");
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
