package entities;

import java.time.LocalDateTime;
import java.util.List;

import enumerations.OrderStatus;
import interfaces.IID;

public class Order implements IID 
{
	private int order_id;
	private LocalDateTime order_time; 
	private OrderStatus status = OrderStatus.PENDING_REQUEST;
	private List<Product> products;
	private Client client;
	
	public Order(int p_order_id, Client p_client, List<Product> product_list)
	{
		super();
		order_id   = p_order_id;
		client     = p_client;
		products   = product_list;
		order_time = LocalDateTime.now();
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

	public void EditStatus()
	{
		Scanner input_scanner = new Scanner(System.in);

		System.out.printf("\t\t[EDIT STATUS]",
		"\n1 - PENDING ORDER",
		"\n2 - IN PREPARATION",
		"\n3 - AWAITING PAYMENT",
		"Choice: ");

		switch(Integer.parseInt(input_scanner.nextLine()))
		{
			case 1:
				status = OrderStatus.PENDING_ORDER;
				break;
			case 2:
				status = OrderStatus.IN_PREPARATION;
				break;
			case 3:
				status = OrderStatus.AWAITING_PAYMENT;
				break;
		}

		input_scanner.close();
	}

	public void EditProductList()
	{
		//Implement the edit list logic
	}

	public void ShowProductList()
	{
		for(int count = 0; count < products.size(); count++)
		{
			System.out.printf("\n\t[PRODUCT #%d]", (count + 1));
			products.get(count).ShowProperties();
		}
	}

	public void ShowProperties()
	{
		System.out.printf("\nOrder ID: "
		+ order_id
		+ "\nOrder Time: "
		+ order_time
		+ "\nOrder Status: "
		+ status.name());
		System.out.printf("\n\t[CLIENT]");
		client.ShowProperties();
	}

	//TODO implement List<Product> methods	
	//TODO implement the method GetTotalPrice();
}
