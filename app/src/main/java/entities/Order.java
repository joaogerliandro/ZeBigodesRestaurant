package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import enumerations.OrderStatus;
import interfaces.IID;

public class Order implements IID 
{
	private int order_id;
	private LocalDateTime order_time; 
	private OrderStatus status = OrderStatus.PendingOrder;
	private List<Product> products;
	private Client client;

	public Order(){}
	
	public Order(int p_order_id, Client p_client, List<Product> product_list)
	{
		order_id   = p_order_id;
		client     = p_client;
		products   = product_list;
		order_time = LocalDateTime.now();
	}

	public LocalDateTime GetOrderTime()
	{
		return order_time;
	}

	public OrderStatus GetStatus() 
	{
		return status;
	}

	public void SetStatus(OrderStatus p_status)
	{
		status = p_status;
	}

	public List<Product> GetProducts() 
	{
		return products;
	}

	public Client GetClient() 
	{
		return client;
	}

	public void SetClient(Client p_client) 
	{
		client = p_client;
	}
	
	@Override
	public int GetID() 
	{
		return order_id;
	}
	
	@Override
	public void SetID(int p_order_id) 
	{
		order_id = p_order_id;
	}

	public void ShowProductList()
	{
		int cnt = 0;

		for (Product p : products)
		{
			System.out.printf("\n\t\t[PRODUCT #%d]", ++cnt);
			p.ShowProperties(true);
			System.out.println("");
		}
	}
	
	public double GetTotalPrice()
	{
		double total = 0.0;

		for(Product product : products)
		{
			total += (product.GetPrice() * product.GetAmount());
		}

		return total;
	}

	@Override
	public String toString()
	{
		DateTimeFormatter formatted_data = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");
		String formatted_time = order_time.format(formatted_data);
		
		return "\n\t+ ID: "
				+ order_id
				+ "\n\t+ Order Time: "
				+ formatted_time
				+ "\n\t+ Order Status: "
				+ status.name()
				+ "\n\t+ Total Price: "
				+ GetTotalPrice()
				+ '\n' + "\t[CLIENT]" + 
				client.toString();
	}

	public int GetProductIndex(List<Product> product_list, int product_id)
    {   
		int count = 0;

		for (Product p : product_list)
		{
			if (product_id == p.GetID())
                return count;
			++count;
		}

        return -1;
    }
}
