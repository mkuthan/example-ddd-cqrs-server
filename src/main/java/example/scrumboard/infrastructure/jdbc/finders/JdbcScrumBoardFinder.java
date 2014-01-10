package example.scrumboard.infrastructure.jdbc.finders;

import static java.util.Objects.requireNonNull;
import static org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper.newInstance;

import org.springframework.beans.factory.annotation.Autowired;
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
		requireNonNull(name);

		// @formatter:off
		String query = "SELECT p.c_id id, p.c_name name, COUNT(i.c_id) backlogItemsCount "
				+ "FROM t_product p "
				+ "LEFT OUTER JOIN t_product_backlog_item i "
				+ "ON p.c_id = i.c_product_id "
				+ "WHERE p.c_name = ? "
				+ "GROUP BY p.c_id, p.c_name "
				+ "ORDER BY p.c_name";
		// @formatter:on

		return jdbcTemplate.queryForObject(query, newInstance(ProductDto.class), name);
	}
}
