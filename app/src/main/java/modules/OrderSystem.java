package modules;

import java.util.List;
import java.util.Scanner;
import entities.Client;
import entities.Order;
import entities.Product;
import enumerations.OrderStatus;

public class OrderSystem 
{   
    private static int countID = 0;
    
    // Testa esse metodo joao e corrige o que estiver errado
    public int GetOrderIndex(List<Order> order_list, int order_id)
    {   
        for(int count = 0; count < order_list.size(); ++count)
            if(order_id == order_list.get(count).getID())
                return count;

        // Deixei como -1 para casa o ID não seja encontrado
        return -1; 
    }
    
    public void OrderAdd(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        
        List<Product> products;
        Client client;
        String NameClient;
        int NumTable, OrderId = countID++;

        System.out.println("Enter the customer name:");
        NameClient = input_scanner.nextLine();        
        
        System.out.print("Enter the table number: ");
        NumTable = Integer.parseInt(input_scanner.nextLine());

        client = new Client(NameClient, NumTable);
        
        /*
            TODO (Gerliandro): Make the loop of the added products
            Note: Make A for Database
        */
        
        // Não sei como fazer esse metodo
        // Acho que precisa de coisas do banco
        // Então joão... faz ou espera até amanhã pra fazer com a gente
        
        input_scanner.close();
    }

    public void OrderShowList(List<Order> order_list)
    {
        for(int count = 0; count < order_list.size(); ++count)
        {
            // mostrar
            /* 
                - [ID do pedido     ] x
                - [time do pedido   ] x
                - [nome do cliente  ] x
                - [numero da mesa   ] x
                - [status do pedido ] x
                - [lista de produtos] X
                    * nome  do produto
                    * preco do prduto
                    * quantidade
            */

            // Tira esse vário prints
            // Testa se ta pegando os valores certos

            System.out.printf("Nome do cliente:  %s \n", order_list.get(count).getClient().getName());
            System.out.printf("ID: %d", order_list.get(count).getID());
            System.out.printf("Hora do pedido:   %s \n", /* pra fazer */);
            System.out.printf("Número da mesa:   %d \n", order_list.get(count).getTableNumber());
            System.out.printf("Status do pedido: " + order_list.get(count).getStatus().name() + "\n");


            // A gente pode fazer uma metodo em Order para "mostrar a lista de produtos", joão
            for(Product prod : order_list.get(count).getProducts())
            {
                System.out.printf
                (
                    "Product: %s\nPreco: %f\nQuant: %d",
                    prod.getName(),
                    prod.getPrice(), 
                    prod.getAmount()
                );
            }
        }
    }

    public void OrderRemove(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id;
        
        // Receive order id
        order_id = input_scanner.nextInt();
        input_scanner.close();

        /* Searches the order id from the list and returns the order's index,
           then deletes the order from the list.
        */
        order_list.remove(GetOrderIndex(order_list, order_id));
    }
    
    public void OrderClear(List<Order> order_list)
    {
        order_list.clear();
    }

    public void OrderEdit(List<Order> order_list)
    {
        Scanner input_scanner = new Scanner(System.in);
        int order_id, index_order, choice;
        
        // Receive order id
        System.out.println("Enter the ID of the order you want to edit:");
        order_id = Integer.parseInt(input_scanner.nextLine());
        
        index_order = GetOrderIndex(order_list, order_id);

        System.out.printf
        (
            "\t\t[EDITAR]\n"
            "1 - Editar nome do cliente\n"
            "2 - Editar numero da mesa\n"
            "3 - Editar status do pedido\n"
            "4 - Editar propriedades do produto\n\n"
            "Digite: "
        );

        choice = Integer.parseInt(input_scanner.nextLine());

        switch (choice) {
            case 1:
                // usar a entrada do usuario para modificar o nome do cliente
                // do pedido medido pelo index
                break;
            case 2:
                // usar a entrada do usuario para modificar o numero da mesa
                // do pedido medido pelo index
                break;
            case 3:
                // usar a entrada do usuario para modificar o status
                // do pedido medido pelo index
                break;
            case 4:
                // usar a entrada do usuario para modificar as propriedades do produto
                // do pedido medido pelo index
                break;
            default:
                break;
        }

        // Receive the choice of attribute to be edited
        
        // Calls the respective attribute editing method
    }
}
