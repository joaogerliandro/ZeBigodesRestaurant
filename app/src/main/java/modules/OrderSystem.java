package modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Client;
import entities.Order;
import entities.Product;
import enumerations.OrderStatus;

public class OrderSystem 
{   
    private static int countID = 0;
    
    public static int GetOrderIndex(List<Order> order_list, int order_id)
    {   
        for(int count = 0; count < order_list.size(); count++)
            if(order_id == order_list.get(count).GetID())
                return count;

        return -1; 
    }
    // Menu is the list of available products
    public static void ShowMenu(List<Product> menu)
	{
        System.out.printf("\n\t\t[MENU]");
        for(int count = 0; count < menu.size(); count++)
		{
			System.out.printf("\n\t[PRODUCT #%d]", (count + 1));
			menu.get(count).ShowProperties();
		}
	}
    
	// (Bryan) Presumi que "source_list" era a lista carregada com dados do banco
    public static void AddOrder(List<Order> order_list, List<Product> source_list) //temporary source_list
    {
        Scanner input_scanner = new Scanner(System.in);
        
        Client client = null;
		List<Product> listProduct = new ArrayList<Product>();
        String client_name = null;
        int table_number, order_id = countID++;
        
		System.out.println("Enter the customer name: ");
		client_name = input_scanner.nextLine();

		try
		{
			System.out.print("\nEnter the table number: ");
			// Can put an execution specifies if you want
			table_number = Integer.parseInt(input_scanner.nextLine());

			client = new Client(client_name, table_number);
			
			int choice = 1;

			while(choice == 1)
			{
				List<Product> menu = source_list; //(TODO: GERLIANDRO) Carregar com a lista do banco
				ShowMenu(menu);

				System.out.print("\nEnter ID of the product: ");
				// Can put an execution specifies if you want
				int new_product_id = Integer.parseInt(input_scanner.nextLine());

				int product_index = GetProductIndex(listProduct, new_product_id);
			
				if(product_index == -1)
				{
					int menu_index = GetProductIndex(menu, new_product_id);
					if(menu_index != -1)
					{
						String new_product_name  = menu.get(menu_index).GetName();
						double new_product_price = menu.get(menu_index).GetPrice();
						
						Product new_product = new Product
						(
							new_product_id,
							new_product_name, 
							new_product_price, 1
						);
						
						System.out.print("\nEnter product amount: ");
						new_product.SetAmount(Integer.parseInt(input_scanner.nextLine()));
						// TODO(Gerliandro e Felipe) : adicionar uma exceção para prevenir
						// uma quantidade negativa (tipo -2)
						listProduct.add(new_product);
					}
					else
						System.out.println("\n[Error] This ID does not match any of the products in the menu.");
				}
				else
				{
					System.out.print("\nEnter product amount: ");
					listProduct.get(product_index).SetAmount(Integer.parseInt(input_scanner.nextLine()));
				}
			
				System.out.print("\n[1] - If you want to add another product\n"
								+"[Otherside] - Enter anything\n");
								
				choice = Integer.parseInt(input_scanner.nextLine());
			}

			Order order = new Order(order_id, client, listProduct);

			order_list.add(order);
		}
		catch(Exception e)
		{
			System.out.printf("Deu ruim !");
		}
		finally
		{
			input_scanner.close();
		}
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

    public static void RemoveOrder(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id, order_index;
        
        order_id = input_scanner.nextInt();
        input_scanner.close();

        order_index = GetOrderIndex(order_list, order_id);
        if(order_index != -1)
        {
        	order_list.remove(order_index);
        }else
        {
        	System.out.printf("\nOrder not found\r\n");
        }
    }
    
    public static void ClearOrderList(List<Order> order_list)
    {
        order_list.clear();
    }

    public static void EditOrder(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id, order_index;
        
        System.out.printf("\nEnter order id: ");

		try
		{
			order_id = Integer.parseInt(input_scanner.nextLine());
			order_index = GetOrderIndex(order_list, order_id);
			
			if(order_index != -1) 
				if(order_list.get(order_index).GetStatus() != OrderStatus.AWAITING_PAYMENT)
				{
					System.out.printf
					(
						"\t\t[EDIT ORDER]\n",
						"[1] - Edit customer name\n",
						"[2] - Edit table number\n",
						"[3] - Edit order status\n",
						"[4] - Edit product list\n\n",
						"> "
					);
		
					switch (Integer.parseInt(input_scanner.nextLine())) 
					{
						case 1:
							order_list.get(GetOrderIndex(order_list,
							order_id)).GetClient().EditName();
							break;
						case 2:
							order_list.get(GetOrderIndex(order_list,
							order_id)).GetClient().EditTableNumber();
							break;
						case 3:
							order_list.get(GetOrderIndex(order_list,
							order_id)).EditStatus();
							break;
						case 4:
							order_list.get(GetOrderIndex(order_list,
							order_id)).EditProductList();
							break;
						default:
							break;
					}
				}else
					System.out.printf("\nOrder can't be edited anymore !");
			else
				System.out.printf("Order not found\r\n");
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			input_scanner.close();
		}
    }

    public static Order FindOrder(List<Order> order_list, int order_id)
    {
        // Returns the order sought by id
        int order_found = GetOrderIndex(order_list, order_id);
        if(order_found != -1)
            return order_list.get(order_found);
        else
            return null; //Temporary
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
}