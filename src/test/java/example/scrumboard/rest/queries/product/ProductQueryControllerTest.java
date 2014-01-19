package example.scrumboard.rest.queries.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import example.scrumboard.rest.AbstractControllerTest;
import example.scrumboard.rest.queries.ScrumBoardRestQueryTests;

@ScrumBoardRestQueryTests
public class ProductQueryControllerTest extends AbstractControllerTest {

	public void getProducts() throws Exception {
		// @formatter:off
		getMockMvc().perform(get("/products").accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.totalElements").value(3))
			.andExpect(jsonPath("$.content[0].name").value("Example DDD/CQRS client"))
			.andExpect(jsonPath("$.content[1].name").value("Example DDD/CQRS server"))
			.andExpect(jsonPath("$.content[2].name").value("Product with no backlog items"));
		// @formatter:on
	}
}
