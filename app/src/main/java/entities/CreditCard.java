package entities;

public class CreditCard extends Card
{
	private int m_parcels = 0;
	
	public CreditCard() {}

	public CreditCard(String titular,
					  String ver_code,
					  String card_number,
					  String expr_date)
	{
		super(titular, ver_code, card_number, expr_date);
	}

	public CreditCard(String titular,
					  String ver_code,
					  String card_number,
					  String expr_date,
					  int parcels) 
	{
		super(titular, ver_code, card_number, expr_date);
		m_parcels = parcels;
	}

	public int GetParcels()
	{
		return m_parcels;
	}
                                                   
    public void SetParcels(int parcels)
	{
		m_parcels = parcels;
	}

	@Override
	public String toString()
	{
		return "[CreditCard]" + "\n" + super.toString() 
			   + "[+] Number of parcels: " + m_parcels + '\n';
	}
}
