package exceptions;

public class InvalidUserInput extends RuntimeException
{
	// Parameterless Constructor
	public InvalidUserInput() {}

	public InvalidUserInput(String message)
	{
	   super(message);
	}
}
