package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.DTO.ProductDTO;
import entities.*;
import enumerations.OrderStatus;
import modules.PaymentManager;

public class MainSystem
{   	
	private static int g_count_id = 0;
	private static List<Product> g_database_products;

	public static void ShowProductList(List<Product> products)
	{
		int cnt = 0;
        System.out.printf("\n\t\t\t[Product List]\n");
		
		for (Product product : products)
		{
			System.out.printf("\n\t\t[Product #%d]", ++cnt);
			product.ShowProperties(false);
		}
	}
	
	public static void AddOrder(List<Order> list_order, Scanner input_stream)
	{
		String client_name  = "";
		int    table_number = 0, order_id = g_count_id++;
		
		List<Product> list_products = new ArrayList<Product>();
		Client client = null;

		int new_product_id = 0, product_index = 0;

		client_name  = Utilities.GetTextField("[+] Enter the customer name: ", input_stream);
		table_number = Utilities.GetTextFieldAsInteger("[+] Enter the table number: ", input_stream);

		System.out.println("");
		client = new Client(client_name, table_number);
	
		for (int choice = 1; choice == 1; )
		{
			ShowProductList(g_database_products);

			System.out.println("");
			new_product_id = Utilities.GetTextFieldAsInteger("[+] Enter the product id: ", input_stream);

			product_index = GetProductIndex(list_products, new_product_id);
			if (product_index == -1) // produto nao adicionado ainda
			{
				int database_product_index = GetProductIndex(g_database_products, 
															 new_product_id);
				
				if (database_product_index != -1)
				{
					String new_product_name  = g_database_products.get(database_product_index).GetName();
					double new_product_price = g_database_products.get(database_product_index).GetPrice();
						
					Product new_product = new Product(new_product_id,
													  new_product_name, 
													  new_product_price);
						
					int product_amount = Utilities.GetTextFieldAsInteger("[+] Enter the product amount:", input_stream);
					new_product.SetAmount(product_amount);
					list_products.add(new_product);
				}
				else
					System.err.println("\n[!] This ID does not match any of the products in the menu.");
			}
			else
			{
				int product_amount = Utilities.GetTextFieldAsInteger("[+] Enter the product amount:", input_stream);
				list_products.get(product_index).AddAmount(product_amount);
			}	

			System.out.println("\n[1] Add another product" + "\n" + 
							   "[AnyKey] Back to Order Manager");
			
			System.out.print("> ");
			choice = Integer.parseInt(input_stream.nextLine());
		}

		list_order.add(new Order(order_id, client, list_products));
	}
	
	public static int GetProductIndex(List<Product> product_list, int product_id)
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

	public static boolean RemoveOrder(List<Order> order_list, int order_id)
	{
		for (Order order : order_list)
		{
			if (order_id == order.GetID())
			{
				order_list.remove(order);
				return true;
			}
		}

		return false;
	}

	public static void ShowOrderList(List<Order> order_list)
    {
		int count = 0;
		
		System.out.println("");

		for (Order order : order_list)
		{
			System.out.printf("\t[Order #%d]", ++count);
            System.out.println(order.toString());
            order.ShowProductList();
			System.out.println("\n");
		}
    }

	public static int GetOrderIndex(List<Order> order_list, int order_id)
    {   
		int count = 0;

		for (Order order : order_list)
		{
			if(order_id == order.GetID())
                return count;
			++count;
		}

        return -1; 
    }
	
	public static void EditProduct(List<Order> order_List, 
								   int order_index,
								   Scanner input_stream)
	{
		int choice = 0, amount = 0;
		double price = 0.0;
		String product_name = "";
		
		System.out.println("");
		choice = Utilities.GetTextFieldAsInteger("[1] Add a new product to the list\n"  +
						   			   "[2] Edit an existing product in the list", 
										input_stream);

		if (choice == 1)
		{
			ShowProductList(g_database_products);

			System.out.println("");
			
			int product_id = Utilities.GetTextFieldAsInteger("[+] Enter the order id you want to add:", input_stream);
			int new_product_index = GetProductIndex(g_database_products, product_id);
			
			if(GetProductIndex(order_List.get(order_index).GetProducts(), product_id) != -1)
			{
				System.err.printf("[!] The product already exists in your product list\n");
			}
			else
			{
				if(new_product_index >= 0 && new_product_index < g_database_products.size())
				{
					product_name = g_database_products.get(new_product_index).GetName();
					price = g_database_products.get(new_product_index).GetPrice();

					Product new_product = new Product(product_id, product_name, price);

					System.out.println("");
					amount = Utilities.GetTextFieldAsInteger("[+] Enter the product amount: ", input_stream);

					new_product.SetAmount(amount);
					order_List.get(order_index).GetProducts().add(new_product);
				}
				else
					System.err.printf("\n[!] Product not found in the menu\n");
			}
		}
		else if(choice == 2)
		{
			ShowProductList(order_List.get(order_index).GetProducts());

			System.out.println("\n");

			int product_id = Utilities.GetTextFieldAsInteger("[+] Enter the id of the product you want to change: ", input_stream);
			int product_index = GetProductIndex(order_List.get(order_index).GetProducts(), product_id);

			if(product_index >= 0 && product_index < order_List.get(order_index).GetProducts().size())
			{
				System.out.println("");
				amount = Utilities.GetTextFieldAsInteger("[+] Enter the new product amount: ", input_stream);
				order_List.get(order_index).GetProducts().get(product_index).SetAmount(amount);
			}
			else
				System.err.printf("[!] Product not found in the product list\n");
		}
		else
			System.err.printf("[!] Invalid operation, your products have not been changed\n");
		
	}

	public static void EditOrder(List<Order> order_list, Scanner input_stream)
    {
		int table_number, new_status;
        
		int order_id = Utilities.GetTextFieldAsInteger("[+] Enter the order id: ", input_stream);
		int order_index = GetOrderIndex(order_list, order_id);
			
		if (order_index >= 0 && order_index < order_list.size())
		{
			if (order_list.get(order_index).GetStatus() != OrderStatus.AwaitingPayment)
			{
				System.out.printf(
					"\n\t\t[EDIT ORDER]\n"        +
					"[1] - Edit customer name\n"  +
					"[2] - Edit table number\n"   +
					"[3] - Edit order status\n"   +
					"[4] - Edit product list\n\n" +
					"> "
				);

				int code = Integer.parseInt(input_stream.nextLine());
				switch (code) 
				{
					case 1:
						String client_name = Utilities.GetTextField("[+]Enter the new customer name: ", input_stream);

						order_list.get(order_index).GetClient().SetName(client_name);
						System.out.printf("\n[*] Updated customer name\n");
						break;
					case 2:
						table_number = Utilities.GetTextFieldAsInteger("[+] Enter new table number: ", input_stream);

						order_list.get(order_index).GetClient().SetTableNumber(table_number);
						System.out.printf("\n[*] Updated table number\n");
						break;
					case 3:
						String status_text = "[+] Enter the number representing the new status:\n" +
									         "[1] - Pending Order\n"    + 
											 "[2] - In Preparation\n"   +
									         "[3] - Awaiting Payment\n";
						
						new_status = Utilities.GetTextFieldAsInteger(status_text, input_stream);
						switch (new_status)
						{
							case 1:
								order_list.get(order_index).SetStatus(OrderStatus.PendingOrder);
								System.out.printf("[*] Order status has been updated\n");
								break;
							case 2:
								order_list.get(order_index).SetStatus(OrderStatus.Preparing);
								break;
							case 3:
								order_list.get(order_index).SetStatus(OrderStatus.AwaitingPayment);
								break;
							default:
								System.err.printf("[!] Invalid command, status remains unchanged\n");
								break;
						}
						break;
					case 4:
						EditProduct(order_list, order_index, input_stream);
						break;
					default:
						System.err.printf("[!] Invalid operation, your data has not been changed\n");
						break;
				}
			}
			else
				System.err.printf("\n[!] Order can't be edited anymore !");
		}
		else
			System.err.printf("[!] Order not found\r\n");
	}
	
	public static void OrderManager(List<Order> order_list, Scanner input)
	{
		int sent = 1, operation = 0;
		
		while (sent == 1)
		{
			System.out.printf
			(
				"\n\n\t\t\t[Order Manager]\n\n"             +
				"[1] - Add Orders\n"                        +
				"[2] - List Orders\n"                       +
				"[3] - Show Menu\n"                         +
				"[4] - Edit Order\n"                        +
				"[5] - Remove Order\n"                      +
				"[6] - Delete All Orders\n"                 +
				"[AnyKey] - Enter any other number to go back\n" +
				"> "
			);
			
			operation = Integer.parseInt(input.nextLine());
			switch(operation)
			{
				case 1:
					AddOrder(order_list, input);
					break;
	
				case 2:
					if(order_list.size() > 0)
					{
						System.out.printf("\n\n\t\t [Orders]\n");
						ShowOrderList(order_list);	
					}
					else
						System.out.printf("\n[!] Order list is empty\n");
					break;
					
				case 3:
					if(g_database_products.size() > 0)
						ShowProductList(g_database_products);
					else
						System.out.printf("\n[!] There are no products on the menu!");
					break;
	
				case 4:
					EditOrder(order_list, input);
					break;
				
				case 5:
					System.out.println("\n[+] Enter the ID of the order you want to exclude: ");
					int id = Integer.parseInt(input.nextLine());
	
					if(RemoveOrder(order_list, id))
						System.out.printf("\n[*] Order deleted successfully\n");
					else
						System.out.printf("\n[!] Order not found\n");
					break;
				
				case 6:
					if(order_list.size() > 0)
					{
						order_list.clear();
						System.out.println("\n[*] All orders have been deleted successfully\n");
					}
					else
						System.out.printf("\n[!] Order list is empty\n");
					break;
				
				default:
					sent = 0;
					break;
			}
		}
	}

	public static void PrintInfo()
	{
		System.out.printf(
			"\n\n\t\t\t[ZeBigode's Restaurant]\n\n"               +
			"[1] - Order Manager\n"     						  +
			"[2] - Payment Manager\n"                             +
			"[3] - Product Manager\n"                             +
			"[4] - Get Balance\n"                                 +
			"[5] - Exit\n" 										  +
			"> "
		);
	}
	
	public static void main(String[] args) 
	{
		ProductDTO productDTO = new ProductDTO();
		List<Order> main_order_list = new ArrayList<Order>();
		g_database_products = productDTO.ListProducts();

		Scanner input_scanner = new Scanner(System.in);
		int option = 0;
		
		while (true)
		{
			PrintInfo();
			
			option = Integer.parseInt(input_scanner.nextLine());
			if (option == 5)
				break;
			
			try
			{
				switch (option)
				{
					case 1:
						OrderManager(main_order_list, input_scanner);	
						break;
					case 2:
						int method = Utilities.GetTextFieldAsInteger(
							"\t\t[PAYMENT MANAGER]\n\n"	     +		
							"[1] Pay with cash\n"            +
							"[2] Pay with crebit card\n"     + 
							"[3] Pay with debit card\n"      + 
							"[4] Exit\n", 
							input_scanner
						);

						if (method == 4)
							break;
							
						PaymentManager.Initialize(main_order_list, method, input_scanner);
						break;
					case 3:
						System.out.printf("\n[+] In Progress ...");
						// ProductManager.Initialize(main_order_list, input_scanner);
						break;
					case 4:
						System.out.printf("\n[+] Current Balance: %.2f", PaymentManager.GetBalance());
					
						break;
					default:
						System.out.printf("\n[!] Opção Inválida\n");
						break;
				}
			} 
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		input_scanner.close();
		productDTO.CloseConnection();
    }
}
