package example.ddd.scrumboard.domain.sprint;

import lombok.NonNull;
import lombok.Value;

@Value
public class SprintId {

	@NonNull
	String id;

}
