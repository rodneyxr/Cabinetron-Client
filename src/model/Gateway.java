package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class Gateway {
	boolean DEBUG = false;

	public DataSource getDataSource() {
		Properties props = new Properties();
		FileInputStream fp = null;
		try {
			if (DEBUG)
				System.out.println("Reading database properties.");
			fp = new FileInputStream("db.properties");
			props.load(fp);
			if (DEBUG)
				System.out.println("Database properties read successfully");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		try {
			MysqlDataSource mysqlDS = new MysqlDataSource();
			mysqlDS.setURL("jdbc:mysql://devcloud.fulgentcorp.com:3306/lrp333");
			mysqlDS.setUser("lrp333");
			mysqlDS.setPassword("MYDi57eKHZInMWXt5MkS");
			return mysqlDS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

}
