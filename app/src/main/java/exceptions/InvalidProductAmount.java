package exceptions;

public class InvalidProductAmount extends RuntimeException
{
	// Parameterless Constructor
	public InvalidProductAmount() {}

	public InvalidProductAmount(String message)
	{
	   super(message);
	}
}
