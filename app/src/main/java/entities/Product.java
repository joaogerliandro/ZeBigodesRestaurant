package entities;

import interfaces.IID;

public class Product implements IID 
{
	private int product_id;
	private String name;
	private double price;
	private int amount;
	
	public Product(){}
	
	public Product(int p_product_id, String p_name, double p_price) 
	{
		this.product_id = p_product_id;
		name       = p_name;
		price      = p_price;
		amount     = 1;
	}
	
	public Product(int p_product_id, String p_name, double p_price, int p_amount) 
	{
		product_id = p_product_id;
		name       = p_name;
		price      = p_price;
		amount     = p_amount;
	}

	public String GetName() 
	{
		return name;
	}

	public void SetName(String p_name) 
	{
		name = p_name;
	}

	public double GetPrice() 
	{
		return price;
	}

	public void SetPrice(double p_price) 
	{
		price = p_price;
	}
	
	public int GetAmount() 
	{
		return amount;
	}

	public void SetAmount(int p_amount) 
	{
		amount = p_amount;
	}

	@Override
	public int GetID()
	{
		return product_id;
	}
	
	@Override
	public void SetID(int p_product_id)
	{
		product_id = p_product_id;
	}

	public void ShowProperties()
	{
		System.out.printf("\nProduct ID: "
		+ product_id
		+ "\nProduct Name: "
		+ name
		+ "\nAmount: "
		+ amount
		+ "Price: "
		+ (amount * price));
	}	
}
