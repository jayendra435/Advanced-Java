package project.entities;

import java.util.Date;
import java.util.List;

public interface OrderList {
   String getOrderId();
   int getCustomerId();
   String  getCustomerName();
   String getMobile();
   char getStatus();
   String  getOrderDate(); 
  String getDeliveryDate();
 List<OrderItem> getOrderItems();
  
}
