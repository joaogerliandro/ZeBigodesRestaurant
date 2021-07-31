package application;

import java.util.ArrayList;
import java.util.List;

import database.DTO.ProductDTO;
import entities.Order;
import entities.Product;
import modules.OrderSystem;

public class MainSystem 
{   	
	
	public static void main(String[] args) 
    {
    	ProductDTO product_dto = new ProductDTO();//Inicia Conexão
    	
    	//Show Menu
    	List<Product> menu = product_dto.ListProducts();
    	OrderSystem.ShowMenu(menu);
    	
    	System.out.printf("\n\n");
    	
    	//Create the order_list
    	List<Order> order_list = new ArrayList<Order>();
    	
    	//Add Order
		OrderSystem.AddOrder(order_list, menu);
		
		System.out.printf("\n\n");
		OrderSystem.ShowOrderList(order_list);
		
		/*Add Second Order
		System.out.printf("\n\n");
		OrderSystem.AddOrder(order_list, menu);
		
		System.out.printf("\n\n");
		OrderSystem.ShowOrderList(order_list);*/
    	
    	System.out.print("\n\nAcabou o programa");
    	product_dto.CloseConnection();//Fecha conexão
    }
}
