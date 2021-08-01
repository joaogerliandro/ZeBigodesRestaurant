package exceptions;

public class EmptyTextField extends RuntimeException
{
	// Parameterless Constructor
	public EmptyTextField() {}

	public EmptyTextField(String message)
	{
	   super(message);
	}
}
