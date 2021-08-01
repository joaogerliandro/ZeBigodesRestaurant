package exceptions;

public class InvalidCardNumber extends RuntimeException
{
	// Parameterless Constructor
	public InvalidCardNumber() {}

	public InvalidCardNumber(String message)
	{
	   super(message);
	}
}
