package example.scrumboard.rest.commands.product;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import example.scrumboard.application.api.CreateProductCommand;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.rest.AbstractControllerTest;
import example.scrumboard.rest.commands.ScrumBoardRestCommandTests;

@ScrumBoardRestCommandTests
public class ProductCommandControllerTest extends AbstractControllerTest {

	@Autowired
	private ProductService productService;

	public void createProduct() throws Exception {
		CreateProductCommand command = givenCreateProductCommand();
		ProductId productId = givenResponse();

		when(productService.createProduct(eq(command))).thenReturn(productId);

		// @formatter:off
		getMockMvc().perform(post("/products")
				.content(toJson(command)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").value(productId.getId()));
		// @formatter:on
	}

	private CreateProductCommand givenCreateProductCommand() {
		CreateProductCommand command = new CreateProductCommand();
		command.setProductName("any name");
		return command;
	}

	private ProductId givenResponse() {
		return new ProductId("any product id");
	}

}
