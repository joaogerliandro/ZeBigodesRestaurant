package entities;

public class Utilities 
{
	public static void ThrowIf(boolean condition, String message) throws RuntimeException
	{
		if (condition)
			throw new RuntimeException(message);
	} 

	public static boolean Contains(Integer[] input, Integer x)
	{
		for (Integer value : input)
		{
			if (value == x)
				return true;
		}

		return false;
	}
}
