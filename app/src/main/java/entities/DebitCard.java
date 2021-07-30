package entities;

public class DebitCard extends Card
{
	public DebitCard() {}

	public DebitCard(String titular,
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
		return  "[DebitCard]" + "\n" + 
				"[+] Titular: " + GetTitularName() + "\n" + 
				"[+] Ver. Code: " + GetVerificationCode() + "\n" + 
				"[+] Number: " + GetCardNumber() + "\n" + 
				"[+] CPF: " + GetCPF() + "\n" + 
				"[+] Expr. Date: " + GetExpirationDate();
	}
}
