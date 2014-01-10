package example.scrumboard.infrastructure.jdbc.finders;

import static java.util.Objects.requireNonNull;
import static org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper.newInstance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import example.scrumboard.application.ProductFinder;
import example.scrumboard.application.dto.ProductBacklogItemDto;
import example.scrumboard.application.dto.ProductDto;

@JdbcFinder
public class JdbcProductFinder implements ProductFinder {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ProductDto> findAll() {
		// @formatter:off
		String query = "SELECT p.c_id id, p.c_name name, COUNT(pbi.c_id) backlogItemsCount "
				+ "FROM t_product p "
				+ "LEFT OUTER JOIN t_product_backlog_item pbi "
				+ "ON p.c_id = pbi.c_product_id "
				+ "GROUP BY p.c_id, p.c_name "
				+ "ORDER BY p.c_name";
		// @formatter:on

		return jdbcTemplate.query(query, newInstance(ProductDto.class));
	}

	@Override
	public ProductDto findById(String productId) {
		requireNonNull(productId);

		// @formatter:off
		String query = "SELECT p.c_id id, p.c_name name, COUNT(pbi.c_id) backlogItemsCount "
				+ "FROM t_product p "
				+ "LEFT OUTER JOIN t_product_backlog_item pbi "
				+ "ON p.c_id = pbi.c_product_id "
				+ "WHERE p.c_id = ? "
				+ "GROUP BY p.c_id, p.c_name";
		// @formatter:on

		return jdbcTemplate.queryForObject(query, newInstance(ProductDto.class), productId);
	}

	@Override
	public List<ProductBacklogItemDto> findProductBacklogItemsById(String productId) {
		requireNonNull(productId);

		// @formatter:off
		String query = "SELECT bi.c_id id, bi.c_name name, pbi.c_position position "
				+ "FROM t_product_backlog_item pbi "
				+ "RIGHT OUTER JOIN t_product p "
				+ "ON pbi.c_product_id = p.c_id  "
				+ "RIGHT OUTER JOIN t_backlog_item bi "
				+ "ON pbi.c_id = bi.c_id  "
				+ "WHERE p.c_id = ? "
				+ "ORDER BY pbi.c_position";
		// @formatter:on

		return jdbcTemplate.query(query, newInstance(ProductBacklogItemDto.class), productId);
	}

	@Override
	public long count() {
		return jdbcTemplate.queryForObject("select count(*) from t_product", Long.class);
	}
}
