package entities;

import interfaces.IID;

public class Product implements IID 
{
	private int product_id;
	private String name;
	private double price;
	//private int amount;	Avoid object repetition
	
	//p_attribute => parameter_attribute
	public Product(int p_product_id, String p_name, double p_price) 
	{
		super();
		product_id = p_product_id;
		name = p_name;
		price = p_price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	/*public int getAmount() 
	{
		return amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}*/

	public int getID()
	{
		//To implement
		return product_id;
	}
	
}
