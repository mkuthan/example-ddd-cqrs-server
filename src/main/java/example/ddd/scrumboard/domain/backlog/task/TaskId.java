package example.ddd.scrumboard.domain.backlog.task;

import lombok.NonNull;
import lombok.Value;

@Value
public class TaskId {

	@NonNull
	String id;
	
}
