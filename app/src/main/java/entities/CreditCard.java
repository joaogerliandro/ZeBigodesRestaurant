package entities;

public class CreditCard extends Card
{
	public CreditCard() {}

	public CreditCard(String titular,
					  String ver_code,
					  String card_number,
					  String cpf,
					  String expr_date)
	{
		super(titular, ver_code, card_number, cpf, expr_date);
	}

	@Override
	public String toString()
	{
		return  "[CreditCard]" + "\n" + 
				"[+] Titular: " + GetTitularName() + "\n" + 
				"[+] Ver. Code: " + GetVerificationCode() + "\n" + 
				"[+] Number: " + GetCardNumber() + "\n" + 
				"[+] CPF: " + GetCPF() + "\n" + 
				"[+] Expr. Date: " + GetExpirationDate();
	}
}
