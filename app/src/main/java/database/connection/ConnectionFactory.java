package database.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.google.common.base.Optional;

import exceptions.*;

public class ConnectionFactory 
{
	private static Connection connection = null;
	
	public static Connection GetConnection() throws DatabaseException
	{
		if (connection == null) 
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
	
	private static Properties LoadProperties() throws DatabaseException
	{
		try (FileInputStream file_stream = new FileInputStream("database.properties"))
		{
			Properties properties = new Properties();
			properties.load(file_stream);
			file_stream.close();
			return properties;
		}
		catch (IOException e) 
		{
			throw new DatabaseException(e.getMessage());
		}
	}
}
