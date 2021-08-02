package exceptions;

public class InvalidTableNumber extends RuntimeException
{
	public InvalidTableNumber() {}

	public InvalidTableNumber(String message)
	{
	   super (message);
	}
}
