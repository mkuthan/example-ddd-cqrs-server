package example.scrumboard.domain.backlog.item;

import java.util.UUID;

public class BacklogItemFactory {

	public BacklogItem create() {
		BacklogItemId id = new BacklogItemId(UUID.randomUUID().toString());
		return new BacklogItem(id);
	}
}
