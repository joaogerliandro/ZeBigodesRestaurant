package modules;

import java.util.List;

import entities.Order;
import entities.Product;


public class OrderSystem 
{   
    
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
			menu.get(count).ShowProperties(false);
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