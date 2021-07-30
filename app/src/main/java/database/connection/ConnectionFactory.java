package database.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import exceptions.DatabaseException;

public class ConnectionFactory {
	
	private static Connection connection = null;
	
	public static Connection GetConnection() 
	{
		if(connection == null) 
		{
			try
			{
				Properties properties = LoadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties);
			}
			catch(SQLException e) 
			{
				throw new DatabaseException(e.getMessage());
			}
		}
		return connection;
	}
	
	private static Properties LoadProperties() 
	{
		try(FileInputStream fs = new FileInputStream("db.properties"))
		{
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		}
		catch (IOException e) 
		{
			throw new DatabaseException(e.getMessage());
		}
	}
}
