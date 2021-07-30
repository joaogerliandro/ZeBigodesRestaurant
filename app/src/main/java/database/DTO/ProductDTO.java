package database.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.connection.ConnectionFactory;
import entities.Product;

public class ProductDTO {
	Connection connection = ConnectionFactory.GetConnection();
	PreparedStatement statement;
	ResultSet resultset;
	
	public void InsertProduct(Product product) {
		String sql = "INSERT INTO product(name, price) VALUES (?, ?)";
		statement = null;
		
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			
			statement.setString(1, product.GetName());
			statement.setDouble(2, product.GetPrice());
			
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteProduct(int product_id)
	{
		String sql = "DELETE FROM product WHERE id = ?";
		statement = null;
		
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, product_id);
			
			statement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void EditProduct(Product product, int product_id)
	{
		String sql = "UPDATE produto SET name = ?, price = ? WHERE id = ?";
		statement = null;
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
		
			statement.setString(1, product.GetName());
			statement.setDouble(2, product.GetPrice());
			statement.setInt(3, product_id);
			
			statement.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void FindProduct(int product_id)
	{
		//Menu have the products of database
		List<Product> database_products = ListProducts();
		
		for(int i = 0; i < database_products.size(); i++)
		{
			if(product_id == database_products.get(i).GetID())
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
	
	public void ClearProductDatabase()
	{
		List<Product> database_products = ListProducts();
		
		for(int i = 0; i < database_products.size(); i++)
		{
			DeleteProduct(database_products.get(i).GetID());
		}
	}
	
	public List<Product> ListProducts()
	{
		String sql = "SELECT * FROM produto";
		
		List<Product> database_products = new ArrayList<Product>();
		statement = null;
		resultset = null;
	
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			
			resultset = statement.executeQuery();
			
			int load_id;
			String load_name;
			double load_price;
			
			while(resultset.next()) 
			{	
				load_id = resultset.getInt("id");
				load_name = resultset.getString("name");
				load_price = resultset.getDouble("price");
				
				Product load_product = new Product(load_id, load_name, load_price);
				
				database_products.add(load_product); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return database_products;
	}
	
	public void CloseConnection() {
		try {
			if(connection != null)
			{
				connection.close();
			}
			if(statement != null)
			{
				statement.close();
			}
			if(resultset != null)
			{
				resultset.close();
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}