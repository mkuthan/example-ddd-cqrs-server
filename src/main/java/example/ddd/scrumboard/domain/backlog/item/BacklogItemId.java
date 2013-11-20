package example.ddd.scrumboard.domain.backlog.item;

import lombok.NonNull;
import lombok.Value;

@Value
public class BacklogItemId {

	@NonNull
	String id;

}
