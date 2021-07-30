package modules;

import java.util.List;
import java.util.Scanner;
import entities.Client;
import entities.Order;
import entities.Product;
import enumerations.OrderStatus;

public class OrderSystem 
{   
    private static int countID = 0;
    
    public int GetOrderIndex(List<Order> order_list, int order_id)
    {   
        for(int count = 0; count < order_list.size(); count++)
            if(order_id == order_list.get(count).GetID())
                return count;

        return -1; 
    }

    // Menu is the list of available products
    public void ShowMenu(List<Product> menu)
	{
		//TODO (Gerliandro): Raising connection as a database
        
        System.out.printf("\n\t\t[MENU]");
        for(int count = 0; count < menu.size(); count++)
		{
			System.out.printf("\n\t[PRODUCT #%d]", (count + 1));
			menu.get(count).ShowProperties();
		}
	}
    
    public void AddOrder(List<Order> order_list, List<Product> source_list) //temporary source_list
    {
        Scanner input_scanner = new Scanner(System.in);
        
        Client client;
        String client_name;
        int table_number , order_id = countID++;

        System.out.println("Enter the customer name: ");
        client_name = input_scanner.nextLine();        
        
        System.out.print("Enter the table number: ");
		// T.E
        table_number = Integer.parseInt(input_scanner.nextLine());

        client = new Client(client_name, table_number);
        
        /*
		  int choice = 1;
		  while(choice == 1)
		  {
		  - List<Product> menu = DBloadProductList();
		  
		  - ShowMenu(menu);
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
		  
		  	- Want to add other product ? 1 - Yes || Other value - No
		  	choice = Integer.parseInt(input_scanner.nextLine());
		  	
		*/
        
        input_scanner.close();
    }

    public void ShowOrderList(List<Order> order_list)
    {
        for(int count = 0; count < order_list.size(); count++)
        {

            System.out.printf("\n\t[Order #%d]", (count + 1));
            order_list.get(count).ShowProperties();

            order_list.get(count).ShowProductList();
        }
    }

    public void RemoveOrder(List<Order> order_list)
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
        	System.out.printf("Order not found\r\n");
        }
    }
    
    public void ClearOrderList(List<Order> order_list)
    {
        order_list.clear();
    }

    public void EditOrder(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id, order_index;
        
        System.out.printf("\nEnter order id: ");
        order_id = Integer.parseInt(input_scanner.nextLine());
        
        order_index = GetOrderIndex(order_list, order_id);
        
        if(order_index != -1) 
        {
	        if(order_list.get(order_index).GetStatus() != OrderStatus.AWAITING_PAYMENT)
	        {
	            System.out.printf
	            (
	                "\t\t[EDIT ORDER]\n",
	                "1 - Edit customer name\n",
	                "2 - Edit table number\n",
	                "3 - Edit order status\n",
	                "4 - Edit product list\n\n",
	                "Choice: "
	            );
	
	            switch (Integer.parseInt(input_scanner.nextLine())) 
	            {
	                case 1:
	                    order_list.get(GetOrderIndex(order_list,
	                    order_id)).GetClient().EditName();
	                    break;
	                case 2:
	                    order_list.get(GetOrderIndex(order_list,
	                    order_id)).GetClient().EditTableN();;
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
	        {
	            System.out.printf("\nOrder can't be edited anymore !");
	        }
        }else
        {
        	System.out.printf("Order not found\r\n");
        }

        input_scanner.close();
    }

    public Order FindOrder(List<Order> order_list, int order_id)
    {
        // Returns the order sought by id
        int order_found = GetOrderIndex(order_list, order_id);
        if(order_found != -1)
        {
            return order_list.get(order_found);
        }else
        {
            return null; //Temporary
        }
    }
}
