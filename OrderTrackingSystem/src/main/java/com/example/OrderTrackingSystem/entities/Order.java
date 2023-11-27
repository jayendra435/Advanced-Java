package com.example.OrderTrackingSystem.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orderTable")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordId;

    private Date ordDate;

    @ManyToOne
    @JoinColumn(name = "custId")
    private Customer customer;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }


    private String ordStatus;
    private Date ordDeliveryDate;

 
    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(String ordStatus) {
        this.ordStatus = ordStatus;
    }

    public Date getOrdDeliveryDate() {
        return ordDeliveryDate;
    }

    public void setOrdDeliveryDate(Date ordDeliveryDate) {
        this.ordDeliveryDate = ordDeliveryDate;
    }

	@Override
	public String toString() {
		return "Order [ordId=" + ordId + ", ordDate=" + ordDate + ", customer=" + customer + ", orderItems="
				+ orderItems + ", ordStatus=" + ordStatus + ", ordDeliveryDate=" + ordDeliveryDate + "]";
	}

}
