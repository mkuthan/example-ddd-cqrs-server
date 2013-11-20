package example.ddd.scrumboard.domain.backlog.item;

import example.ddd.scrumboard.domain.shared.UniqueIdentifier;

public class BacklogItemId extends UniqueIdentifier<String> {

	public BacklogItemId(String id) {
		super(id);
	}

}
