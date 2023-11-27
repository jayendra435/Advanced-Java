package project.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepo extends  JpaRepository<OrderItem, OrderItemCompositePk>{
	List<OrderItem> findByOrderItemKeyOrdId(String ordId);
	 void deleteByOrderItemKeyOrdId(String ordId);

	 
	@Query("SELECT oi.product.productName as productName, oi.qty as qty , oi.price as price  from OrderItem oi WHERE oi.orderItemKey.ordId=:orderId ")
	 List<OrderItemList>getOrderItemListMethod(@Param("orderId") String ordId);
	
	@Query("SELECT oi.product.productName as productName, oi.order.customer.customerName as customerName, oi.qty as qty, oi.price as price, oi.order.orderDate as orderDate FROM OrderItem oi WHERE oi.product.productId = :productId")
		List<AllOrderItemList> getAllOrderItemList(@Param("productId") String productId);

	
		
	
}
