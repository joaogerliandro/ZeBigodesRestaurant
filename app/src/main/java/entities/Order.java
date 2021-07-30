package entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

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
		order_id   = p_order_id;
		client     = p_client;
		products   = product_list;
		order_time = LocalDateTime.now();
	}

	public LocalDateTime GetOrder_time() // ajeitar
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

	public void EditStatus() throws RuntimeException
	{
		Scanner input_scanner = new Scanner(System.in);

		System.out.printf("\t\t[Edit Status]",
						  "\n[1] - Pending Order", // vamos ajeitar isso aq
						  "\n[2] - In Preparation",
						  "\n[3] - Awaiting Payment",
						  "\n> ");

		
		
		int code;

		try 
		{
			code = Integer.parseInt(input_scanner.nextLine());

			switch (code)
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
				default:
					throw new RuntimeException("Invalid Input");
			}
		} 
		catch (Exception e) 
		{
			// substituir
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			input_scanner.close();
		}
	}

	public void EditProductList()
	{
		Scanner input_scanner = new Scanner(System.in);
        int product_id, product_index;
        
		//Show all product id in list
		System.out.printf("\n\t\t[PRODUCT LIST]");

		for (int count = 0; count < products.size(); count++)
		{
			System.out.printf("\n\t[PRODUCT #%d]", (count + 1));
			System.out.printf("Name: "
							  + products.get(count).GetName()
							  + "ID: "
							  + products.get(count).GetID()
							  + "Amount: "
							  + products.get(count).GetAmount());
		}

		System.out.printf
		(
			"\t\t[EDIT PRODUCT LIST]\n",
			"[1] - Add Product\n",
			"[2] - Remove Product\n",
			"[3] - Clear Product List\n",
			"[4] - Edit Product Amount\n",
			"\n> "
		);

		// muita coisa pra ajeitar
		// ajeitem aqui, façam do msm jeito q fiz antes
		// eh o msm problema
		
		int code;

		try
		{
			code = Integer.parseInt(input_scanner.nextLine());

			switch(Integer.parseInt(input_scanner.nextLine()))
			{
				case 1:
					/*
					- List<Product> menu = DBloadProductList();
					
					- ShowProductList(menu)
					- Choose an product by ID
					- int new_product_id = Integer.parseInt(input_scanner.nextLine());
					- int product_index = GetProductIndex(products, new_product_id);
					
						if(product_index == -1)
						{
						- int menu_index = GetProductIndex(menu, new_product_id);
						- if(menu_index != -1)
							{
								int new_product_id = menu.get(menu_index).GetID();
								String new_product_name = menu.get(menu_index).GetName();
								double new_product_price = menu.get(menu_index).GetPrice();
								
								Product new_product = new Product(new_product_id,
								new_product_name, new_product_price, 1);
								
								- receive product amount
								new_product.SetAmount(Integer.parseInt(input_scanner.nextLine()));
								
								products.add(new_product);
							}else
							{
								Product not found !
							}
						}else
						{
							//Product already exists, you can only improve it
							//Receive the new amount
							products.get(product_index).SetAmount;
						}
					
					*/
				
					break;
				case 2:
					System.out.printf("\nEnter product id: ");

					// tratar excecao aq
					product_id = Integer.parseInt(input_scanner.nextLine());
		
					product_index = GetProductIndex(products, product_id);
					
					if (product_index != -1)
						products.remove(product_index);
					else
						System.out.printf("\nProduct not found !");

					break;
				case 3:	
					products.clear();
					break;
				case 4:
					System.out.printf("\nEnter the product id: ");

					try{
						product_id = Integer.parseInt(input_scanner.nextLine());
					}
					cath()
					{
						
					}

					product_index = GetProductIndex(products, product_id);

					if (product_index != -1)
					{
						System.out.printf("\nEnter the new amount: ");
						
						// tratar exception
						int new_amount = Integer.parseInt(input_scanner.nextLine());
						
						if (new_amount > 0)
							products.get(product_index).SetAmount(new_amount);
						else
							products.remove(product_index);
					}
					else
					{
						System.out.printf("\nProduct not found !");
					}	
					break;
				default:
					System.out.printf("Invalid Operation, Nothing has altered");
					break;
			}
		}
		catch (Exception e) 
		{
			// substituir
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			input_scanner.close();
		}
	}

	public void ShowProductList()
	{
		int cnt = 0;

		for (Product p : products)
		{
			System.out.printf("\n\t[PRODUCT #%d]", cnt++);
			p.ShowProperties();
		}
	}
	
	public void ShowProductList(List<Product> source_list)
	{
		// mash tao fazendo alguma coisa ai
		int cnt = 0;

		for (Product p : source_list)
		{
			System.out.printf("\n\t[PRODUCT #%d]", cnt++);
			p.ShowProperties();
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
		int count = 0;

		for (Product p : product_list)
		{
			if (product_id == p.GetID())
                return count;
			++count;
		}

        return -1;
    }

	// guys vou ter q sair...
	// eu posso deixar aberto o vscode aq

	// esperar o bryan, ai eu commito

	//TODO implement List<Product> methods	
	//TODO implement the method GetTotalPrice();
}
