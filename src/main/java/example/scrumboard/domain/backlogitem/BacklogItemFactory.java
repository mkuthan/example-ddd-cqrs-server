package example.scrumboard.domain.backlogitem;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class BacklogItemFactory {

	public BacklogItem create(String story) {
		BacklogItemId id = new BacklogItemId(UUID.randomUUID().toString());
		return new BacklogItem(id, story);
	}
}
