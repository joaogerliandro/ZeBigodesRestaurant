package entities;

public class DebitCard extends Card
{
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
		return "";
	}
}
