package example.scrumboard.domain.backlogitem;

import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemBuilder;
import example.scrumboard.domain.backlogitem.BacklogItemId;

public class BacklogItemBuilder {

	private BacklogItemId id = new BacklogItemId("any id");

	private String name = "any name";

	public BacklogItemBuilder withId(BacklogItemId id) {
		this.id = id;
		return this;
	}

	public BacklogItemBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public BacklogItem build() {
		return new BacklogItem(id, name);
	}

}
