package entities;

import interfaces.IID;

public class Product implements IID 
{
	private int product_id;
	private String name;
	private double price;
	private int amount;
	
	public Product(int p_product_id, String p_name, double p_price, int p_amount) 
	{
		super();
		product_id = p_product_id;
		name = p_name;
		price = p_price;
		amount = p_amount;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String p_name) 
	{
		name = p_name;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double p_price) 
	{
		price = p_price;
	}
	
	public int getAmount() 
	{
		return amount;
	}

	public void setAmount(int p_amount) 
	{
		amount = p_amount;
	}

	@Override
	public int getID()
	{
		return product_id;
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
