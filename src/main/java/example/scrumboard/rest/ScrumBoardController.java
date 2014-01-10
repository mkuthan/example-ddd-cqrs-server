package example.scrumboard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.scrumboard.application.ScrumBoardFinder;
import example.scrumboard.application.dto.ProductDto;

@RestController
public class ScrumBoardController {

	@Autowired
	private ScrumBoardFinder scrumBoardFinder;

	@RequestMapping(value = "/product/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto productByName(@PathVariable("name") String name) {
		return scrumBoardFinder.findProductByName(name);
	}

}
