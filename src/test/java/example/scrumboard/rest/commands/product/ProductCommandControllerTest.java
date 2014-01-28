package example.scrumboard.rest.commands.product;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import example.scrumboard.application.api.ProductService;
import example.scrumboard.application.api.commands.CreateProductCommand;
import example.scrumboard.application.api.commands.PlanBacklogItemCommand;
import example.scrumboard.application.api.commands.ReorderBacklogItemsCommand;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.rest.AbstractControllerTest;
import example.scrumboard.rest.commands.RestCommandTest;

@RestCommandTest
public class ProductCommandControllerTest extends AbstractControllerTest {

	private static final ProductId ANY_PRODUCT_ID = new ProductId("any product id");

	private static final BacklogItemId ANY_BACKLOG_ITEM_ID = new BacklogItemId("any backlog item id");

	@Autowired
	private ProductService productService;

	public void createProduct() throws Exception {
		CreateProductCommand command = givenCreateProductCommand();

		when(productService.createProduct(eq(command))).thenReturn(ANY_PRODUCT_ID);

		// @formatter:off
		getMockMvc().perform(post("/products")
				.content(toJson(command)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$id").value(ANY_PRODUCT_ID.getId()));
		// @formatter:on
	}

	public void planBacklogItem() throws Exception {
		PlanBacklogItemCommand command = givenPlanBacklogItemCommand();

		when(productService.planBacklogItem(eq(ANY_PRODUCT_ID), eq(command))).thenReturn(ANY_BACKLOG_ITEM_ID);

		// @formatter:off
		getMockMvc().perform(post("/products/{productId}/backlogItems", ANY_PRODUCT_ID.getId())
				.content(toJson(command)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$id").value(ANY_BACKLOG_ITEM_ID.getId()));
		// @formatter:on
	}

	public void reorderBacklogItems() throws Exception {
		ReorderBacklogItemsCommand command = givenReorderBacklogItemsCommand();

		// @formatter:off
		getMockMvc().perform(post("/products/{productId}/reorderBacklogItems", ANY_PRODUCT_ID.getId())
				.content(toJson(command)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk());
		// @formatter:on

		verify(productService).reorderBacklogItems(eq(ANY_PRODUCT_ID), eq(command));
	}

	private CreateProductCommand givenCreateProductCommand() {
		CreateProductCommand command = new CreateProductCommand();
		command.setProductName("any product name");
		return command;
	}

	private PlanBacklogItemCommand givenPlanBacklogItemCommand() {
		PlanBacklogItemCommand command = new PlanBacklogItemCommand();
		command.setBacklogItemStory("any backlog item story");
		return command;
	}

	private ReorderBacklogItemsCommand givenReorderBacklogItemsCommand() {
		ReorderBacklogItemsCommand command = new ReorderBacklogItemsCommand();
		return command;
	}

}
