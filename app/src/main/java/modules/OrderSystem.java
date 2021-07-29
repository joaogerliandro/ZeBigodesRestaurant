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
            if(order_id == order_list.get(count).getID())
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
        NameClient = input_scanner.nextLine();        
        
        System.out.print("Enter the table number: ");
        NumTable = Integer.parseInt(input_scanner.nextLine());

        client = new Client(NameClient, NumTable);
        
        /*
            TODO (All): Make the loop of the added products

            options loop
                -ShowMenu(source_list);
                -AddProduct(order_list);
                    -Product and Amount
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
        int order_id;
        
        // Receive order id
        order_id = input_scanner.nextInt();
        input_scanner.close();

        /* Searches the order id from the list and returns the order's index,
           then deletes the order from the list.
        */
        order_list.remove(GetOrderIndex(order_list, order_id));
    }
    
    public void ClearOrderList(List<Order> order_list)
    {
        order_list.clear();
    }

    public void EditOrder(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id;
        
        System.out.printf("\nEnter order id: ");
        order_id = Integer.parseInt(input_scanner.nextLine());

        if(order_list.get(GetOrderIndex(order_list,
           order_id)).getStatus() != OrderStatus.AWAITING_PAYMENT)
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
                    order_id)).getClient().EditName();
                    break;
                case 2:
                    order_list.get(GetOrderIndex(order_list,
                    order_id)).getClient().EditTableN();;
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
            throw new Exception("Order not found");
        }
    }
}
