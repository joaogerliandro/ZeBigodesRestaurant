package entities;
import  exceptions.*;

public abstract class Card
{
	private String m_titular;
	private String m_verification_code;
	private String m_card_number;
	private String m_expiration_date;

	public Card() {}
	
	public Card(String titular,
				String ver_code,
				String card_number,
				String expr_date)
	{
		SetTitularName(titular);
		SetVerificationCode(ver_code);
		SetCardNumber(card_number);
		SetExpirationDate(expr_date);
	}
	
	public String GetTitularName()
	{
		return m_titular;
	}

    public String GetVerificationCode()
	{
        return m_verification_code; 
    }

	public String GetCardNumber()
	{
		return m_card_number;
	}

	public String GetExpirationDate()
	{
		return m_expiration_date; 
	}

	/* Setters */
	public void SetCardNumber(String card)
	{
		if (!ValidateCardNumber(card))
			throw new InvalidCardNumber("Invalid card number");

		m_card_number = card; 
	}

    public void SetVerificationCode(String code) 
	{
        m_verification_code = code;
    }

    public void SetExpirationDate(String expr_date)
	{
        m_expiration_date = expr_date;
    }
    
    public void SetTitularName(String title)
    {
        m_titular = title;
    }

	/* Methods */
	private boolean ValidateCardNumber(String card)
	{
		return !card.isEmpty();
	}

	@Override
	public String toString()
	{
		return  "[+] Titular: "    + GetTitularName()      + '\n' +
				"[+] Ver. Code: "  + GetVerificationCode() + '\n' +
				"[+] Number: " 	   + GetCardNumber()       + '\n' +
				"[+] Expr. Date: " + GetExpirationDate();
	}
}
