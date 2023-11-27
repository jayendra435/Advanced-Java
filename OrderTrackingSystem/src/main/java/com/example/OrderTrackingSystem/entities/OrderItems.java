package com.example.OrderTrackingSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderitems")
public class OrderItems {
	    @Id
	    private int ordId;

	    @Id
	    private int prodId;

	    private int qty;
	    private int price;

	    @ManyToOne
	    @JoinColumn(name = "ordId")
	    private Order order;

	    @ManyToOne
	    @JoinColumn(name = "prodId")
	    private Product product;

	    public int getOrdId() {
	        return ordId;
	    }

	    public void setOrdId(int ordId) {
	        this.ordId = ordId;
	    }

	    public int getProdId() {
	        return prodId;
	    }

	    public void setProdId(int prodId) {
	        this.prodId = prodId;
	    }

	    public int getQty() {
	        return qty;
	    }

	    public void setQty(int qty) {
	        this.qty = qty;
	    }

	    public int getPrice() {
	        return price;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }

	    public Order getOrder() {
	        return order;
	    }

	    public void setOrder(Order order) {
	        this.order = order;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }

		@Override
		public String toString() {
			return "OrderItems [ordId=" + ordId + ", prodId=" + prodId + ", qty=" + qty + ", price=" + price
					+ ", order=" + order + ", product=" + product + "]";
		}

}
