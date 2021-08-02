package entities;

import java.util.*;

public class Utilities 
{
	public static void ThrowIf(boolean condition, String message) throws RuntimeException
	{
		if (condition)
			throw new RuntimeException(message);
	} 

	public static boolean Contains(Integer[] input, Integer x)
	{
		for (Integer value : input)
		{
			if (value == x)
				return true;
		}

		return false;
	}

	public static String GetTextField(String message, Scanner stream)
	{
		System.out.println("\n" + message);
		System.out.print("> ");
		return stream.nextLine();
	}

	public static Integer GetTextFieldAsInteger(String message, Scanner stream) throws NumberFormatException
	{
		System.out.println("\n" + message);
		System.out.print("> ");
		return Integer.parseInt(stream.nextLine());
	}

	public static int GetOrderIndex(List<Order> order_list, int order_id)
    {   
		int count = 0;

		for (Order order : order_list)
		{
			if (order_id == order.GetID())
                return count;
			++count;
		}

        return -1;
    } 

	public static int GetProductIndex(List<Product> product_list, 
									  int product_id)
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
