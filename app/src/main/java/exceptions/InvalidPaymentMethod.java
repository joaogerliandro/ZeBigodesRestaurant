package exceptions;

public class InvalidPaymentMethod extends RuntimeException
{
	public InvalidPaymentMethod() {}

	public InvalidPaymentMethod(String message)
	{
	   super (message);
	}
}
