package example.scrumboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public abstract class AbstractControllerTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	protected MockMvc getMockMvc() {
		return mockMvc;
	}

	@BeforeMethod
	protected void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

}
