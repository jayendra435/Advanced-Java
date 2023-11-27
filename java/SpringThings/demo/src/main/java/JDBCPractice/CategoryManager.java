package JDBCPractice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryManager {
	@Autowired
	JdbcTemplate jt;

	public void countRows() {
		int rowCount = jt.queryForObject("Select count(*) from categories", Integer.class);
		System.out.println(rowCount);
	}

	public void descFromCode() {
		String desc = this.jt.queryForObject("select catdesc from categories where catcode = ?", new Object[]{"hd"},
				String.class);
		System.out.println(desc);
	}
	
	public void print() {
		List<Category> cat=jt.query("select * from categories",(rs,rowNum)-> new Category(rs.getString("CatCode"),rs.getString("CatDesc")));
		for(var i: cat) {
			System.out.println(i.getCode()+" , "+i.getDesc());
		}
	}
}
