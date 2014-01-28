package example.scrumboard.rest.queries.product;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import example.scrumboard.rest.AbstractControllerTest;
import example.scrumboard.rest.queries.RestQueryTest;

@RestQueryTest
public class ProductQueryControllerTest extends AbstractControllerTest {

	private String productsJson;

	public void shouldFindProducts() throws Exception {
		// @formatter:off
		productsJson = getMockMvc().perform(get("/products").accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$totalElements").value(3))
			.andExpect(jsonPath("$content[0].productName").value("Example DDD/CQRS client"))
			.andExpect(jsonPath("$content[1].productName").value("Example DDD/CQRS server"))
			.andExpect(jsonPath("$content[2].productName").value("Product with no backlog items"))
			.andReturn().getResponse().getContentAsString();
		// @formatter:on
	}
	
	@Test(dependsOnMethods = "shouldFindProducts")
	public void shouldFindProduct() throws Exception {
		String productName = "Example DDD/CQRS server";
		String productId = findProductIdByName(productName);

		// @formatter:off
		getMockMvc().perform(get("/products/{productId}", productId)
				.accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$productId").value(productId))
			.andExpect(jsonPath("$productName").value(productName));
		// @formatter:on
	}

	@Test(dependsOnMethods = "shouldFindProducts")
	public void shouldFindBacklogItems() throws Exception {
		String productName = "Example DDD/CQRS server";
		String productId = findProductIdByName(productName);

		// @formatter:off
		getMockMvc().perform(get("/products/{productId}/backlogItems", productId)
				.accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].backlogItemStory").value("Write documentation"))
			.andExpect(jsonPath("$[0].backlogItemPosition").value(0))
			.andExpect(jsonPath("$[1].backlogItemStory").value("Add more unit tests"))
			.andExpect(jsonPath("$[1].backlogItemPosition").value(1));
		// @formatter:on
	}

	private String findProductIdByName(String name) {
		return (String) JsonPath.read(productsJson, "$content[?].productId[0]", filter(where("productName").is(name)));
	}
}
