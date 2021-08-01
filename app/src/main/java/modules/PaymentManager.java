package modules;

import java.util.Scanner;

import entities.*;
import enumerations.*;
import exceptions.*;

public class PaymentManager 
{
	private Order m_order = null;

	public Order GetOrder()
	{
		Utilities.ThrowIf(m_order == null, "invalid order");
		return m_order;
	}

	public void SetOrder(Order order)
	{
		Utilities.ThrowIf(order == null, "invalid order argument");
		m_order = order;
	}

	public PaymentManager(Order order)
	{
		SetOrder(order);
	}

	public Card GetPaymentInfo()
	{
		String card_titular = "";
		String card_ver_code = "";
		String card_number = "";
		String card_cpf = ""; // pra q CPF?
		String card_expr_date = "";

		Utilities.ThrowIf(m_order == null, "invalid order");
		
		OrderStatus ord_status = m_order.getStatus();
		Utilities.ThrowIf(ord_status != OrderStatus.AWAITING_PAYMENT, 
						  "invalid order");

		System.out.println("[+] Insira o metodo de pagamento: \n" +
						   "\n[1] Dinheiro\n"  +
						   "[2] Cartao de Credito\n" +
						   "[3] Cartao de Debito\n");

		System.out.print("> ");

		Scanner input_stream = new Scanner(System.in);
		
		PaymentMethods payment_method = GetPaymentMethod(input_stream.nextLine());
		System.out.println();

		if (payment_method == PaymentMethods.Money)
		{
			// logica aqui
			return null;
		}
		else
		{
			card_titular 	= GetFieldText("[+] Insira o nome do titular do cartao: ", input_stream);
			card_ver_code 	= GetFieldText("[+] Insira o código de verificação do cartao: ", input_stream);
			card_number 	= GetFieldText("[+] Insira o número do cartao: ", input_stream);
			card_cpf 		= GetFieldText("[+] Insira o cpf: ", input_stream);
			card_expr_date  = GetFieldText("[+] Insira a data de expiração do cartao: ", input_stream);
		}
		
		input_stream.close();

		if (payment_method != PaymentMethods.Money)
		{
			Card result;

			if (payment_method == PaymentMethods.DebitCard)
				result = new DebitCard();
			else
				result = new CreditCard();

			result.SetTitularName(card_titular);
			result.SetVerificationCode(card_ver_code);
			result.SetCardNumber(card_number);
			result.SetCPF(card_cpf);
			result.SetExpirationDate(card_expr_date);

			return result;
		}	
		
		return new CreditCard();
	}

	// Private Methods
	private PaymentMethods GetPaymentMethod(String input) throws InvalidUserInput
	{
		Integer code;
		Integer[] valid_codes = { 1, 2, 3 };

		try 
		{
			code = Integer.parseInt(input);
			if (!Utilities.Contains(valid_codes, code))
				throw new RuntimeException(); 
		} 
		catch (Exception e) 
		{
			throw new InvalidUserInput("A entrada passada é inválida, somente os seguintes valores serão aceitos: 1, 2 e 3");
		}

		return PaymentMethods.values()[code - 1];
	}

	private String GetFieldText(String message, Scanner stream)
	{
		System.out.println(message);
		System.out.print("> ");

		String result = stream.nextLine();

		if (result.isEmpty())
			throw new EmptyTextField("esse campo não pode estar vazio");
		
		System.out.println();
		return result;
	}

}
