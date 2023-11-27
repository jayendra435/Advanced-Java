package School;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String guardianName;
	private String guardianMobile;
	private String guardianEmail;
}
