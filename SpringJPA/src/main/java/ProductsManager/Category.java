package ProductsManager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String category_description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryDescription() {
		return category_description;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.category_description = categoryDescription;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryDescription=" + category_description + "]";
	}

}
