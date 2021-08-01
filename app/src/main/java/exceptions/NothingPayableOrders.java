package exceptions;

public class NothingPayableOrders extends RuntimeException
{
	public NothingPayableOrders() {}

	public NothingPayableOrders(String message)
	{
	   super(message);
	}
}
