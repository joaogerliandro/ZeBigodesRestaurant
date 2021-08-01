package modules;

import java.util.*;

import entities.*;
import enumerations.*;
import exceptions.*;

public class PaymentManager 
{
	private static double balance = 0.0;

	public static double GetBalance()
	{
		return balance;
	} 

	public static boolean IsPayable(List<Order> order_list)
	{
		for (Order order : order_list)
		{
			if(order.GetStatus() == OrderStatus.AwaitingPayment)
			{
				return true;
			}
		}
		
		return false;
	}

	public static void ShowPayableOrders(List<Order> order_list)
    {		
		int count = 0;
		System.out.println("");
								
		for (Order order : order_list)
		{ 
			if(order.GetStatus() == OrderStatus.AwaitingPayment)
			{
				System.out.printf("\t[Order #%d]", ++count); 
				System.out.println(order.toString());
				System.out.println("");
			}	
		}
    }

	public static Order GetPayableOrder(List<Order> order_list, Scanner input_stream)
	{
		if (!IsPayable(order_list))
			throw new NothingPayableOrders("[-] No orders awaiting payment found");
			
		//Lista somente os pedidos que estão aguardando pagamento
		ShowPayableOrders(order_list);

		// Pega o id do pedido -  
		int order_id = Utilities.GetTextFieldAsInteger("[+] Enter order id: ", input_stream);
			
		// Pega o index do pedido
		int order_index = Utilities.GetOrderIndex(order_list, order_id);

		// Verifica se o pedido pode ser pago
		Order current_order = order_list.get(order_index);
			
		if (current_order.GetStatus() == OrderStatus.AwaitingPayment)
			return current_order;
		else
			throw new InvalidOrderStatus("\nThe specified order is not awaiting payment");
	} 
	
	public static PaymentMethods ValidatePaymentMethod(int method, Scanner input_stream) throws InvalidPaymentMethod
	{
		Integer[] valid_methods = { 1, 2, 3 };
		boolean valid = false;

		for (Integer m : valid_methods)
		{
			if (m == method)
				valid = true;
		}

		if (!valid)
			throw new InvalidPaymentMethod("[!] Invalid payment method\n");

		return PaymentMethods.values()[method - 1];
	}

	public static void PrintDebitCardInfo(DebitCard card, Order order)
	{
		balance += order.GetTotalPrice();

		System.out.printf("\n\t\t [**] The payment succeeded [**]\n\n" + 
						  card.toString() +
						  "[+] Amount paid: " + order.GetTotalPrice() + '\n');
	}

	public static void PrintCreditCardInfo(CreditCard card, Order order)
	{
		balance += order.GetTotalPrice();

		System.out.printf("\n\t\t The payment succeeded\n\n"          + 
						  card.toString()							  +
						  "[+] Amount paid: " + order.GetTotalPrice() + '\n');
	}
	
	public static void PayDebit(Order order, Scanner input_stream) throws InvalidOrder
	{
		if (order == null)
			throw new InvalidOrder("[!] The order cannot be paid");
		
		String titular     = Utilities.GetTextField("[+] Enter titular name:", input_stream);
		String ver_code    = Utilities.GetTextField("[+] Enter verification code:", input_stream);
		String card_number = Utilities.GetTextField("[+] Enter credit card number:", input_stream);
		String expr_date   = Utilities.GetTextField("[+] Enter expiration date:", input_stream);

		DebitCard debit_card = new DebitCard(titular, ver_code, card_number, expr_date);
		PrintDebitCardInfo(debit_card, order);
	}

	public static void PayCredit(Order order, Scanner input_stream) throws InvalidOrder
	{
		if (order == null)
			throw new InvalidOrder("[!] The order cannot be paid");
		
		String titular     = Utilities.GetTextField("[+] Enter titular name:", input_stream);
		String ver_code    = Utilities.GetTextField("[+] Enter verification code:", input_stream);
		String card_number = Utilities.GetTextField("[+] Enter credit card number:", input_stream);
		String expr_date   = Utilities.GetTextField("[+] Enter expiration date:", input_stream);

		int parcels = Utilities.GetTextFieldAsInteger("[+] Enter the amount of parcels: ", input_stream);

		CreditCard credit_card = new CreditCard(titular, ver_code, card_number, expr_date, parcels);
		PrintCreditCardInfo(credit_card, order);
	}
	
	public static void Pay(PaymentMethods method, Order order, Scanner input_stream) throws InvalidOrder
	{	
		if (order == null)
			throw new InvalidOrder("[!] Order cannot be null");
		
		switch (method)
		{
			case Money:
				balance += order.GetTotalPrice();
				order.SetStatus(OrderStatus.Complete);
				System.out.println("[*] Payment completed successfully");
				break;
				
			case CreditCard:
				PayCredit(order, input_stream);
				order.SetStatus(OrderStatus.Complete);
				System.out.println("[*] Payment completed successfully");
				break;

			case DebitCard:
				PayDebit(order, input_stream);
				order.SetStatus(OrderStatus.Complete);
				System.out.println("[*] Payment completed successfully");
				break;
				
			default:
				System.out.println("[!] Invalid option");
				break;
		}
	}

	public static void Initialize(List<Order> order_list, 
								  int method, Scanner input_stream)
								  throws InvalidOrder, 
								  		 InvalidPaymentMethod,
										 NothingPayableOrders,
										 InvalidOrderStatus
									

	{
		Pay(ValidatePaymentMethod(method, input_stream), 
			GetPayableOrder(order_list, input_stream), 
			input_stream);
	}
}
