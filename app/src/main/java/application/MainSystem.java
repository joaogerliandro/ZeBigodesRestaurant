package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.DTO.ProductDTO;
import entities.*;
import enumerations.*;
import modules.*;

public class MainSystem
{	
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
		List<Product> database_products = productDTO.ListProducts();

		Scanner input_scanner = new Scanner(System.in);
		int option = 0;
		
		while (true)
		{
			PrintInfo();
			
			try
			{
				option = Integer.parseInt(input_scanner.nextLine());
				if (option == 5)
					break;

				switch (option)
				{
					case 1:
						OrderManager.Initialize(main_order_list, 
												input_scanner,
												database_products);	
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
						System.out.printf("\n[+] Current Balance: %.2f", 
										  PaymentManager.GetBalance());
						break;
					default:
						System.out.printf("\n[!] Invalid Option\n");
						break;
				}
			} 
			catch (NumberFormatException nfe)
			{
				System.err.println("[!] Invalid Option");
				continue;
			}
			catch(Exception e)
			{
				System.err.println(e.toString());
			}
		}
		
		input_scanner.close();
		productDTO.CloseConnection();
    }
}