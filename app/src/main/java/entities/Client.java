package entities;

import interfaces.IID;

public class Client implements IID
{
	private String name;
	private int client_id;
	private int table_number;
	//private int phone_number; 	Call the customer if necessary
	
	//p_attribute => parameter_attribute
	public Client(String p_name, int p_client_id, int p_table_number) 
	{
		super();
		name = p_name;
		client_id = p_client_id;
		table_number = p_table_number;
	}
	
	/*public Client(String p_name, int p_client_id, int p_table_number, int p_phone_number) 
	{
		super();
		name = p_name;
		client_id = p_client_id;
		table_number = p_table_number;
		phone_number = p_phone_number;
	}*/

	public String getName() 
	{
		return name;
	}

	public void setName(String p_name) 
	{
		name = p_name;
	}

	public int getTable_number() 
	{
		return table_number;
	}

	public void setTable_number(int p_table_number) 
	{
		table_number = p_table_number;
	}

	/*public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int p_phone_number) {
		phone_number = p_phone_number;
	}*/	
	
	@Override
	public int getID()
	{
		//To implement
		return client_id;
	}
}
