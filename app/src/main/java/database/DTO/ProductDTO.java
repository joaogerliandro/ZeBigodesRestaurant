package database.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.ConnectionFactory;
import entities.Product;

public class ProductDTO 
{
	private Connection m_connection = ConnectionFactory.GetConnection();
	private PreparedStatement m_statement;
	private ResultSet m_resultset;

	public void InsertProduct(Product product) 
	{
		String sql = "INSERT INTO product(name, price) VALUES (?, ?)";
		m_statement = null;
		
		try 
		{
			m_statement = (PreparedStatement) m_connection.prepareStatement(sql);
			
			m_statement.setString(1, product.GetName());
			m_statement.setDouble(2, product.GetPrice());
			m_statement.execute();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void DeleteProduct(int product_id)
	{
		String sql = "DELETE FROM product WHERE id = ?";
		m_statement = null;
		
		try 
		{
			m_statement = (PreparedStatement) m_connection.prepareStatement(sql);
			m_statement.setInt(1, product_id);
			m_statement.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}

	public void EditProduct(Product product)
	{
		String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";
		m_statement = null;
		
		try 
		{
			m_statement = (PreparedStatement) m_connection.prepareStatement(sql);
		
			m_statement.setString(1, product.GetName());
			m_statement.setDouble(2, product.GetPrice());
			m_statement.setInt(3, product.GetID());
		
			m_statement.execute();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void FindProduct(int product_id)
	{
		//Menu have the products of database
		List<Product> database_products = ListProducts();
		
		for (int i = 0; i < database_products.size(); i++)
		{
			if (product_id == database_products.get(i).GetID())
			{
				System.out.printf("\n ID: "
								+ database_products.get(i).GetID()
								+ "\t\t Nome: "
								+ database_products.get(i).GetName()
								+ "\t\t Preço: "
								+ database_products.get(i).GetPrice());
			}
		}
	}
	public Product GetProduct(int product_id)
	{
		List<Product> database_products = ListProducts();
		
		for (int i = 0; i < database_products.size(); i++)
			if (product_id == database_products.get(i).GetID())
				return database_products.get(i);

		return (new Product());
	}
	
	public void ClearProductDatabase()
	{
		List<Product> database_products = ListProducts();
		
		for (int i = 0; i < database_products.size(); i++)
		{
			DeleteProduct(database_products.get(i).GetID());
		}
	}
	
	public List<Product> ListProducts()
	{
		String sql = "SELECT * FROM product";
		
		List<Product> database_products = new ArrayList<Product>();
		m_statement = null;
		m_resultset = null;
	
		try 
		{
			m_statement = (PreparedStatement) m_connection.prepareStatement(sql);
			m_resultset = m_statement.executeQuery();
			
			int    load_id;
			String load_name;
			double load_price;
			
			while (m_resultset.next()) 
			{	
				load_id    = m_resultset.getInt("id");
				load_name  = m_resultset.getString("name");
				load_price = m_resultset.getDouble("price");
				
				Product load_product = new Product(load_id, load_name, load_price);
				database_products.add(load_product); 
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return database_products;
	}
	
	public void CloseConnection()
	{
		try 
		{
			if (m_connection != null)
				m_connection.close();

			if (m_statement != null)
				m_statement.close();

			if (m_resultset != null)
				m_resultset.close();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}