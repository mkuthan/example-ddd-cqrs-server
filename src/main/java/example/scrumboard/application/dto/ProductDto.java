package example.scrumboard.application.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {

	private String id;

	private String name;

	private List<ProductBacklogItemDto> backlogItems = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductBacklogItemDto> getBacklogItems() {
		return backlogItems;
	}

	public void setBacklogItems(List<ProductBacklogItemDto> backlogItems) {
		this.backlogItems = backlogItems;
	}

}
