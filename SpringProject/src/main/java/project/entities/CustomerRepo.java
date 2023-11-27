package project.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.entities.*;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	Customer findByEmail(String email);
	
	Customer findByCustomerName(String customerName);
	
	 @Query("SELECT  oi.product from OrderItem oi where oi.order.customer.customerName =:customerName")
	List<Product> getProductsList(@Param("customerName")String customerName);
}
