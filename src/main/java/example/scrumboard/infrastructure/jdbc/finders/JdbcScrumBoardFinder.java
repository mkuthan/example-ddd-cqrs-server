package example.scrumboard.infrastructure.jdbc.finders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import example.scrumboard.application.ScrumBoardFinder;
import example.scrumboard.application.dto.ProductDto;

@JdbcFinder
public class JdbcScrumBoardFinder implements ScrumBoardFinder {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public long productsCount() {
		return jdbcTemplate.queryForObject("select count(*) from t_product", Long.class);
	}

	@Override
	public ProductDto findProductByName(String name) {
		String query = "select p.c_id as id, p.c_name as name, count(i.id) as backlogItemsCount "
				+ "from t_product p left outer join t_product_backlog_item i on p.id = i.product_id "
				+ "where p.c_name = ? group by id, name";
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<ProductDto>(ProductDto.class), name);
	}
}
