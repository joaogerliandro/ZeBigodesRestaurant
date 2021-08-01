package entities;

import exceptions.*;
import interfaces.IID;

public class Product implements IID 
{
	private int product_id;
	private String name;
	private double price;
	private int amount;
	
	public Product(String p_name, double p_price)
	{
		name  = p_name;
		price = p_price;
	}
	
	public Product(int p_product_id, String p_name, double p_price) 
	{
		product_id = p_product_id;
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

	public void SetAmount(int p_amount) throws InvalidProductAmount
	{
		if (p_amount <= 0)
			throw new InvalidProductAmount("The amount of the product can not be less than zero.");

		amount = p_amount;
	}

	public void AddAmount(int increase)
	{
		amount += increase;
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

	public void ShowProperties(boolean show_amount)
	{
		if(show_amount == true) 
		{
			System.out.printf("\n\t\t+ ID: "
					+ product_id
					+ "\n\t\t+ Name: "
					+ name
					+ "\n\t\t+ Amount: "
					+ amount
					+ "\n\t\t+ Total Price: "
					+ (amount * price));
		}
		else
		{
			System.out.printf("\n\t\t+ ID: "
					+ product_id
					+ "\n\t\t+ Name: "
					+ name
					+ "\n\t\t+ Price: "
					+ (amount * price));
		}
	}	
}
