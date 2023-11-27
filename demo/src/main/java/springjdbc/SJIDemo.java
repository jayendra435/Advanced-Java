package springjdbc;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class SJIDemo {
private SimpleJdbcInsert sji;
	
    // Constructor injection 
	public SJIDemo(DataSource datasource) {
		System.out.println("SJIDemo()" + datasource.toString());
		this.sji = new SimpleJdbcInsert(datasource);
		this.sji.withTableName("categories").usingColumns("catcode","catdesc");
	}

	public void add(String code, String desc) {
		HashMap<String, String> category = new HashMap<>();
		category.put("catcode", code);
		category.put("catdesc", desc);
		int count = sji.execute(category);  // INSERT is executed 
		System.out.printf("Added %d Categories Successfully!\n", count);
	}
}
