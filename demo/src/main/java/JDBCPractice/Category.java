package JDBCPractice;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
	private String code;
	private String desc;
	public Category() {
		
	}
    public Category(String code,String desc) {
    	this.code=code;
    	this.desc=desc;
    	
    }
//	@Override
//	public String toString() {
//		return "Category [code=" + code + ", desc=" + desc + "]";
//	}
//   
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}

