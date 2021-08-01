package entities;

import java.util.Scanner;
import interfaces.IID;

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

	public int GetTable_number() 
	{
		return table_number;
	}

	public void SetTableNumber(int p_table_number) /// ????
	{
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

	public void ShowProperties()
	{
		System.out.printf("\nClient ID: "
		+ client_id
		+ "\nClient Name: "
		+ name
		+ "\nTable Number: "
		+ table_number);
	}
}
