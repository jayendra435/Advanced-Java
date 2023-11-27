package project.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class OrderView {

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}\\d{2}$", message = "Order ID must consist of three uppercase letters followed by two digits")
	String OrdViewId;

	@NotBlank

	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in yyyy-MM-dd format")
	String OrderDate;

	int CustId;

	@Pattern(regexp = "[NDC]", message = "Status must be in {'N','D','C'}")
	@NotBlank
	@Size(min = 1, max = 1)
	String Status;

	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in yyyy-MM-dd format")
	String DeliveryDate;

	@Valid
	List<OrderItem> OrderItems;

	public String getOrdViewId() {
		return OrdViewId;
	}

	public void setOrdViewId(String ordViewId) {
		OrdViewId = ordViewId;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public String getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		DeliveryDate = deliveryDate;
	}

	public int getCustId() {
		return CustId;
	}

	public void setCustId(int custId) {
		CustId = custId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<OrderItem> getOrderItems() {
		return OrderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		OrderItems = orderItems;
	}

}
