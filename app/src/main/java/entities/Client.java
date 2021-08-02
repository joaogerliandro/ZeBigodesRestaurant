package entities;

import interfaces.IID;
import exceptions.*;

public class Client implements IID
{
	private String name;
	private int client_id;
	private int table_number;
	private static int client_count = 0;
	
	public Client(String p_name, int p_table_number) 
	{
		name = p_name;
		client_id = client_count++;
		table_number = p_table_number;
	}

	public String GetName() 
	{
		return name;
	}

	public void SetName(String p_name) 
	{
		name = p_name;
	}

	public int GetTableNumber() 
	{
		return table_number;
	}

	public void SetTableNumber(int p_table_number) throws InvalidTableNumber
	{
		if (p_table_number <= 0)
			throw new InvalidTableNumber("table number must be positive");
		table_number = p_table_number;
	}
	
	@Override
	public int GetID()
	{
		return client_id;
	}
	
	@Override
	public void SetID(int p_client_id)
	{
		client_id = p_client_id;
	}

	@Override
	public String toString()
	{
		return "\n\t+ ID: "
		+ client_id
		+ "\n\t+ Name: "
		+ name
		+ "\n\t+ Table Number: "
		+ table_number;
	}
}