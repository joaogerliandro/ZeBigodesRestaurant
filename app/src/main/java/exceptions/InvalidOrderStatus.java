package exceptions;

public class InvalidOrderStatus extends RuntimeException
{
	// Parameterless Constructor
	public InvalidOrderStatus() {}

	public InvalidOrderStatus(String message)
	{
	   super(message);
	}
}
