package modules;

import java.util.List;
import java.util.Scanner;
import entities.Client;
import entities.Order;
import entities.Product;

public class OrderSystem 
{   
    private static int countID = 0;
    
    public int GetOrderIndex(List<Order> order_list, int order_id)
    {
        int order_index = 0;
        // Return the order index
        return order_index; 
    }
    
    public void OrderAdd(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        
        List<Product> products;
        Client client;
        String NameClient;
        int NumTable, OrderId = countID++;

        System.out.println("Enter the customer name:");
        NameClient = input_scanner.nextLine();        
        
        System.out.print("Enter the table number: ");
        NumTable = Integer.parseInt(input_scanner.nextLine());

        client = new Client(NameClient, NumTable);
        
        /*
            TODO (Gerliandro): Make the loop of the added products
            Note: Make A for Database
        */
        
        //order_list.(order(name, table_n))
        
        input_scanner.close();
    }

    public void OrderShowList(List<Order> order_list)
    {
        // Print all order of list
    }

    public void OrderRemove(List<Order> order_list)
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
    
    public void OrderClear(List<Order> order_list)
    {
        order_list.clear();
    }

    public void OrderEdit(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id;
        
        // Receive order id
        order_id = input_scanner.nextInt();
        input_scanner.close();
        
        // Receive the choice of attribute to be edited
        
        // Calls the respective attribute editing method
    }
}
