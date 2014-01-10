package example.scrumboard.infrastructure.jdbc.finders;

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
		// TODO Auto-generated method stub
		return null;
	}

}
