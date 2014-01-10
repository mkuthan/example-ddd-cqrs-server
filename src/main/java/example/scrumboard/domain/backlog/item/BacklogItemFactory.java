package example.scrumboard.domain.backlog.item;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class BacklogItemFactory {

	public BacklogItem create(String name) {
		BacklogItemId id = new BacklogItemId(UUID.randomUUID().toString());
		return new BacklogItem(id, name);
	}
}
