package project.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepo extends JpaRepository<Order, String> {
	List<Order> findByCustId(int customerId);

	Page<Order> findByStatus(String  status, PageRequest pageable);

	@Query("SELECT o.orderId as orderId, o.customer.customerId as customerId, o.customer.customerName as customerName, o.customer.mobile as mobile,o.status as status,o.orderDate as orderDate,o.deliveryDate as deliveryDate,o.orderItems  as orderItems FROM Order o WHERE o.orderId = :orderId")
	List<OrderList> getOrderDetails(String orderId);

	@Query("FROM Order WHERE orderDate between :startDate and :endDate")
	List<Order> getOrdersByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
