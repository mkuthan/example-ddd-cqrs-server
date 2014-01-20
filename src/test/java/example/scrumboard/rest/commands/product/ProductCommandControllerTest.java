package example.scrumboard.rest.commands.product;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.rest.AbstractControllerTest;
import example.scrumboard.rest.commands.ScrumBoardRestCommandTests;

@ScrumBoardRestCommandTests
public class ProductCommandControllerTest extends AbstractControllerTest {

	@Autowired
	private ProductService productService;

	public void createProduct() throws Exception {
		String productName = "any name";
		ProductId productId = new ProductId("any product id");

		when(productService.createProduct(eq(productName))).thenReturn(productId);

		// @formatter:off
		getMockMvc().perform(post("/products").contentType(MediaType.APPLICATION_JSON).content("{name: "+ productName + "}"))
			.andDo(print())
			.andExpect(status().isCreated())
			//.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			//.andExpect(jsonPath("$.id").value(productId.getId()));
			;
		// @formatter:on
	}

}
