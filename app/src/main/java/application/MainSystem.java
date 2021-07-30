package application;
import entities.*;
import enumerations.OrderStatus;
import modules.PaymentManager;
public class MainSystem 
{   
    public static void main(String[] args) 
    {
        Order odr = new Order();
        odr.setStatus(OrderStatus.AWAITING_PAYMENT);
        PaymentManager payment_mgr = new PaymentManager(odr);
        
        Card c = payment_mgr.GetPaymentInfo();
        System.out.println(c.toString());
       
    }
}
