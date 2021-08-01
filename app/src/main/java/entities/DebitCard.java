package entities;

public class DebitCard extends Card
{
	public DebitCard() {}

	public DebitCard(String titular,
					 String ver_code,
					 String card_number,
					 String expr_date)
	{
		super(titular, ver_code, card_number, expr_date);
	}

	@Override
	public String toString()
	{
		return "[DebitCard]" + "\n" + super.toString();
	}
}
