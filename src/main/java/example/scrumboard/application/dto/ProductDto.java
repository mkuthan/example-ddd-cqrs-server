package example.scrumboard.application.dto;

public class ProductDto {

	private String id;

	private String name;
	
	private int backlogItemsCount;

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

	public int getBacklogItemsCount() {
		return backlogItemsCount;
	}

	public void setBacklogItemsCount(int backlogItemsCount) {
		this.backlogItemsCount = backlogItemsCount;
	}

}
