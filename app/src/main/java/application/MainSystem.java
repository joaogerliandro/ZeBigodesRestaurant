package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.DTO.ProductDTO;
import entities.Client;
import entities.Order;
import entities.Product;
import enumerations.OrderStatus;

public class MainSystem
{   	
	private static int countID = 0;

	
	public static void AddOrder(List<Order> list_order, List<Product> source_list, Scanner in)
	{
		String name_client    = "";
		int    table_number   = 0, id_order = countID++;
		List<Product> list_products = new ArrayList<Product>();
		Client client;

		int new_product_id = 0, product_index = 0;
		
		System.out.printf("\nEnter the customer name: ");
		name_client = in.nextLine();

		System.out.printf("\nEnter the table number: ");
		table_number = Integer.parseInt(in.nextLine());

		System.out.println("");

		client = new Client(name_client, table_number);
		
		int choice = 1;

		while(choice == 1)
		{
			ShowMenu(source_list);

			System.out.print("\n\nEnter the product id: ");
			new_product_id = Integer.parseInt(in.nextLine());

			product_index = GetProductIndex(list_products, new_product_id);

			if(product_index == -1)
			{
					int menu_index = GetProductIndex(source_list, new_product_id);
					if(menu_index != -1)
					{
						String new_product_name  = source_list.get(menu_index).GetName();
						double new_product_price = source_list.get(menu_index).GetPrice();
						
						Product new_product = new Product
						(
							new_product_id,
							new_product_name, 
							new_product_price, 1
						);
						
						System.out.print("\nEnter the product amount: ");
						int amount = Integer.parseInt(in.nextLine());
						
						new_product.SetAmount(amount);
						list_products.add(new_product);
					}
					else
						System.out.println("\n[!] - This ID does not match any of the products in the menu.");
			}
			else
			{
					System.out.print("\nEnter the product amount: ");
					int amount = Integer.parseInt(in.nextLine());
					
					//Somar ao existente
					list_products.get(product_index).AddAmount(amount);
			}

			System.out.print("\n[1] - If you want to add another product\n"
							+"[Otherside] - Enter anything\n");
								
			choice = Integer.parseInt(in.nextLine());
		}

		Order order = new Order(id_order, client, list_products);

		list_order.add(order);
		//in.close();
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
	public static void ShowMenu(List<Product> menu)
	{
        System.out.printf("\n\t\t\t[MENU]\n");
        for(int count = 0; count < menu.size(); count++)
		{
			System.out.printf("\n\t[PRODUCT #%d]", (count + 1));
			menu.get(count).ShowProperties(false);
		}
	}
	public static boolean RemoveOrder(List<Order> order_list, int order_id)
	{
		for(int count = 0; count < order_list.size(); ++count)
			if(order_id == order_list.get(count).GetID())
			{
				order_list.remove(order_list.get(count));
				return true;	
			}
		
		return false;
	}
	public static void ShowOrderList(List<Order> order_list)
    {
        for(int count = 0; count < order_list.size(); count++)
        {

            System.out.printf("\n\n\t[Order #%d]", (count + 1));
            order_list.get(count).ShowProperties();

            order_list.get(count).ShowProductList();
        }
    }
	public static int GetOrderIndex(List<Order> order_list, int order_id)
    {   
        for(int count = 0; count < order_list.size(); count++)
            if(order_id == order_list.get(count).GetID())
                return count;

        return -1; 
    }
	public static void EditProduct( List<Order> order_List, int order_indx, 
									List<Product> source_list, Scanner in)
	{
		int choice = 0, amount = 0;
		double price = 0.0;
		String name_product = "";

		System.out.println("\n[1] - Add a new product to the list\n"      +
							 "[2] - Edit an existing product in the list\n" +
							 "> ");
		
		choice = Integer.parseInt(in.nextLine());

		if(choice == 1)
		{
			ShowMenu(source_list);

			System.out.println("\nEnter the order id you want to add: ");
			int id_product       = Integer.parseInt(in.nextLine());
			int indx_new_product = GetProductIndex(source_list, id_product);
			
			if(GetProductIndex(order_List.get(order_indx).GetProducts(), id_product) != -1)
				System.out.printf("[!] - The product already exists in your product list\n");
			else
				if(indx_new_product >= 0 && indx_new_product < source_list.size())
				{
					name_product = source_list.get(indx_new_product).GetName();
					price = source_list.get(indx_new_product).GetPrice();

					Product new_Product = new Product(id_product, name_product, price);

					System.out.println("\nEnter the product amount: ");
					amount = Integer.parseInt(in.nextLine());

					new_Product.SetAmount(amount);

					order_List.get(order_indx).GetProducts().add(new_Product);
				}else
					System.out.printf("\n[!] - Product not on the menu\n");
		}
		else if(choice == 2)
		{
			ShowMenu(order_List.get(order_indx).GetProducts());

			System.out.printf("\nEnter the id of the product you want to change: ");
			int id_product = Integer.parseInt(in.nextLine());

			int indx_product = GetProductIndex(order_List.get(order_indx).GetProducts(), id_product);

			if(indx_product >= 0 && indx_product < order_List.get(order_indx).GetProducts().size())
			{
				System.out.printf("\nEnter the new product amount: ");
				amount = Integer.parseInt(in.nextLine());

				order_List.get(order_indx).GetProducts().get(indx_product).SetAmount(amount);
			}else
				System.out.printf("[!] - Product not found in the product list\n");
		}
		else
		{
			System.out.printf("[!] - Invalid operation, your products have not been changed\n");
		}
	}
	public static void EditOrder(List<Order> order_list, List<Product> source_list, Scanner in)
    {
        int order_id, order_index;
		String name_client;
		int table_number, new_status;
        
        System.out.printf("\nEnter the order id: ");

		order_id = Integer.parseInt(in.nextLine());
		order_index = GetOrderIndex(order_list, order_id);
			
		if(order_index >= 0 && order_index < order_list.size()) 
			if(order_list.get(order_index).GetStatus() != OrderStatus.AWAITING_PAYMENT)
			{
				System.out.printf
				(
					"\n\t\t[EDIT ORDER]\n"          +
					"[1] - Edit customer name\n"  +
					"[2] - Edit table number\n"   +
					"[3] - Edit order status\n"   +
					"[4] - Edit product list\n\n" +
					"> "
				);
		
				switch (Integer.parseInt(in.nextLine())) 
				{
					case 1:
						System.out.printf("\nEnter the new customer name: ");
						name_client = in.nextLine();

						order_list.get(order_index).GetClient().SetName(name_client);
						System.out.printf("\n[*] - Updated customer name\n");
						break;
					case 2:
						System.out.printf("\nEnter new table number: ");
						table_number = Integer.parseInt(in.nextLine());

						order_list.get(order_index).GetClient().SetTableNumber(table_number);
						System.out.printf("\n[*] - Updated table number\n");
						break;
					case 3:
						System.out.printf("\nEnter the number representing the new status:\n"     +
										  "[1] - PENDING_ORDER\n"    + "[2] - IN_PREPARATION\n" +
										  "[3] - AWAITING_PAYMENT\n" + "\n> ");
						
						new_status = Integer.parseInt(in.nextLine());

						switch(new_status)
						{
							case 1:
								order_list.get(order_index).SetStatus(OrderStatus.PENDING_ORDER);
								System.out.printf("[*] - Order status has been updated\n");
								break;
							case 2:
								order_list.get(order_index).SetStatus(OrderStatus.IN_PREPARATION);
								break;
							case 3:
								order_list.get(order_index).SetStatus(OrderStatus.AWAITING_PAYMENT);
								break;
							default:
								System.out.printf("[!] - Invalid command, status remains unchanged\n");
								break;
						}
						break;
					case 4:
						EditProduct(order_list, order_index, source_list, in);
						break;
					default:
						System.out.printf("[!] - Invalid operation, your data has not been changed\n");
						break;
				}
			}else
				System.out.printf("\n[!] - Order can't be edited anymore !");
		else
			System.out.printf("[!] - Order not found\r\n");
	}
	
	public static void OrderManager(List<Order> order_list,List<Product> menu, Scanner input)
	{
		int sent = 1, operation = 0;
		
		while(sent == 1)
		{

			System.out.printf
			(
				"\n\n\t\t\t[Order Manager]\n\n"                                 +
				"[1] - Add Orders\n"                                 +
				"[2] - List Orders\n"                               +
				"[3] - Show Menu\n"                                   +
				"[4] - Edit Order\n"                                +
				"[5] - Remove Order\n"                              +
				"[6] - Delete All Orders\n"                         +
				"[+] - Enter any other number to go back\n" +
				"> "
			);
			
			operation = Integer.parseInt(input.nextLine());
	
			switch(operation)
			{
				case 1:
					AddOrder(order_list, menu, input);
					break;
	
				case 2:
					if(order_list.size() > 0)
					{
						System.out.printf("\n\n\t\t [ORDERS]");
						ShowOrderList(order_list);	
					}
					else
						System.out.printf("\n[!] - Order list is empty\n");
					break;
					
				case 3:
					if(menu.size() > 0)
						ShowMenu(menu);
					else
						System.out.printf("\n[!] - There are no products on the menu!");
					break;
	
				case 4:
					EditOrder(order_list, menu, input);
					break;
				
				case 5:
					System.out.println("\nEnter the ID of the order you want to exclude: ");
					int id = Integer.parseInt(input.nextLine());
	
					if(RemoveOrder(order_list, id))
						System.out.printf("\n[*] - Order deleted successfully\n");
					else
						System.out.printf("\n[!] - Order not found\n");
					break;
				
				case 6:
					if(order_list.size() > 0)
					{
						order_list.clear();
						System.out.println("\n[*] - All orders have been deleted successfully\n");
					}
					else
						System.out.printf("\n[!] - Order list is empty\n");
					break;
				
				default:
					sent = 0;
					break;
			}
		}
	}	
	
	
	public static void main(String[] args) 
	{
		ProductDTO productDTO = new ProductDTO();
		List<Order> main_order_list = new ArrayList<Order>();
		List<Product> main_menu = productDTO.ListProducts();

		Scanner input_scanner = new Scanner(System.in);
		int option = 0, key = 1;
		
		while(key == 1)
		{
			System.out.printf
			(
				"\n\n\t\t\t[ZeBigode's Restaurant]\n\n"                                 +
				"[1] - Order Manager\n"                                 +
				"[2] - Payment Manager\n"                               +
				"[3] - Product Manager\n"                                   +
				"[+] - Enter any other number to close the program\n" +
				"> "
			);
			
			option = Integer.parseInt(input_scanner.nextLine());
			
			switch(option)
			{
				case 1:
					OrderManager(main_order_list, main_menu, input_scanner);	
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					System.out.printf("\n[X] - Closed program\n");
					key = 0;
					break;			
			}
		}
		
		input_scanner.close();
		productDTO.CloseConnection();
    }
}
