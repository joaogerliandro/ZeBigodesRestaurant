package entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import enumerations.OrderStatus;
import interfaces.IID;

public class Order implements IID 
{
	private int order_id;
	private LocalDateTime order_time; 
	private OrderStatus status = OrderStatus.PENDING_ORDER;
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
		"\n\nChoice: ");

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
		Scanner input_scanner = new Scanner(System.in);
        int product_id, product_index;
        
		//Show all product id in list
		System.out.printf("\n\t\t[PRODUCT LIST]");
		for(int count = 0; count < products.size(); count++)
		{
			System.out.printf("\n\t[PRODUCT #%d]", (count + 1));
			System.out.printf("Name: "
			+ products.get(count).getName()
			+ "ID: "
			+ products.get(count).getID()
			+ "Amount: "
			+ products.get(count).getAmount());
		}

		System.out.printf
		(
			"\t\t[EDIT PRODUCT LIST]\n",
			"1 - Add Product\n",
			"2 - Remove Product\n",
			"3 - Edit Product Amount\n",
			"\n\nChoice: "
		);

		switch(Integer.parseInt(input_scanner.nextLine()))
		{
			case 1:
				//Implement add product
				break;
			case 2:
				System.out.printf("\nEnter product id: ");
				product_id = Integer.parseInt(input_scanner.nextLine());
	
				product_index = GetProductIndex(products, product_id);
				if(product_index != -1)
				{
					products.remove(product_index);
				}else
				{
					System.out.printf("\nProduct not found !");
				}
				break;
			case 3:
				System.out.printf("\nEnter the product id: ");
				product_id = Integer.parseInt(input_scanner.nextLine());

				product_index = GetProductIndex(products, product_id);

				if(product_index != -1)
				{
					System.out.printf("\nEnter the new amount: ");
					
					int new_amount = Integer.parseInt(input_scanner.nextLine());
					if(new_amount > 0)
					{
						products.get(product_index).setAmount(new_amount);
					}else
					{
						products.remove(product_index);
					}
				}
				else
				{
					System.out.printf("\nProduct not found !");
				}	
		}

		input_scanner.close();
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

	public int GetProductIndex(List<Product> product_list, int product_id)
    {   
        for(int count = 0; count < product_list.size(); count++)
            if(product_id == product_list.get(count).getID())
                return count;

        return -1; 
    }

	//TODO implement List<Product> methods	
	//TODO implement the method GetTotalPrice();
}
