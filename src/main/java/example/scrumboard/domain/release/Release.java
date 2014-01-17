package example.scrumboard.domain.release;

import example.ddd.domain.AggregateRoot;

public class Release extends AggregateRoot<ReleaseId> {

	Release(ReleaseId id) {
		super(id);
	}

}
