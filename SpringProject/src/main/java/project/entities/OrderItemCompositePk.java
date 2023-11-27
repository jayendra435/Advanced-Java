package project.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@Embeddable
public class OrderItemCompositePk implements Serializable {
	@Column(name = "Order_Id")
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}\\d{2}$", message = "Order ID must consist of three uppercase letters followed by two digits")
	private String ordId;
	
	@Column(name = "Product_Id")
	@Pattern(regexp = "^[A-Z]\\d{3}$", message = "Product ID must consist of an uppercase letter followed by three digits")
	private String prodId;
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	
}

