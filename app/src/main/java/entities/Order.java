package entities;

import java.time.LocalDateTime;
import java.util.List;

import enumerations.OrderStatus;
import interfaces.IID;

public class Order implements IID 
{
	private int order_id;
	private LocalDateTime order_time = LocalDateTime.now(); //Catch the instant the object is instantiated
	private OrderStatus status = OrderStatus.PENDING_REQUEST;
	private List<Product> products;
	private Client client;
	
	//p_attribute => parameter_attribute
	public Order(int p_order_id, Client p_client) //Add products during order creation
	{
		super();
		order_id = p_order_id;
		client = p_client;
		
		//TODO Add products to List<Product>
	}
	
	public Order(int p_order_id, Client p_client, List<Product> product_list) //Receive a ready-made product list
	{
		super();
		order_id = p_order_id;
		client = p_client;
		products = product_list;
	}

	public LocalDateTime getOrder_time() 
	{
		return order_time;
	}

	public OrderStatus getStatus() 
	{
		return status;
	}

	public void setStatus(OrderStatus p_status)
	{
		status = p_status;
	}

	public List<Product> getProducts() 
	{
		return products;
	}

	public Client getClient() 
	{
		return client;
	}

	public void setClient(Client p_client) 
	{
		client = p_client;
	}
	
	@Override
	public int getID() 
	{
		return order_id;
	}
	
	//TODO implement List<Product> methods
	
	//TODO implement the method GetTotalPrice();
}
