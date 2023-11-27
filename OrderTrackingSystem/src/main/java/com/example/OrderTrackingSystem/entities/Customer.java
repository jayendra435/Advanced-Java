package com.example.OrderTrackingSystem.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="custId")
    private int custId;

    private String custName;
    private String custEmail;
    private String custMobile;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;
    
    public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custEmail=" + custEmail + ", custMobile="
				+ custMobile + ", orders=" + orders + "]";
	}

	public void setCustName(int custId2) {
		// TODO Auto-generated method stub
		
	}

	public void setCustEmail(int custId2) {
		// TODO Auto-generated method stub
		
	}

	public void setCustMobile(int custId2) {
		// TODO Auto-generated method stub
		
	}

}
